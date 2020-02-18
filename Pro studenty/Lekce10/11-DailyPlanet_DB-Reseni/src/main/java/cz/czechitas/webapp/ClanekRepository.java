package cz.czechitas.webapp;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.jdbc.support.*;

public class ClanekRepository {

    private MariaDbDataSource konfiguraceDatabaze;
    private JdbcTemplate odesilacDotazu;
    private RowMapper<Clanek> prevodnik;

    public ClanekRepository() {
        try {
            konfiguraceDatabaze = new MariaDbDataSource();
            konfiguraceDatabaze.setUserName("student");
            konfiguraceDatabaze.setPassword("password");
            konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/DailyPlanet_margot");

            odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
            prevodnik = BeanPropertyRowMapper.newInstance(Clanek.class);
        } catch (SQLException e) {
            throw new DataSourceLookupFailureException("Nepodarilo se vytvorit DataSource", e);
        }
    }


    public List<Clanek> findAll() {
        List<Clanek> zakaznici = odesilacDotazu.query(
                "SELECT ID, Nazev, Autor, Datum FROM Clanky",
                prevodnik);
        return zakaznici;
    }

    public Clanek findOne(Long id) {
        Clanek zakaznik = odesilacDotazu.queryForObject(
                "SELECT ID, Nazev, Autor, Datum" +
                        " FROM Clanky WHERE ID = ?",
                prevodnik,
                id);
        return zakaznik;
    }

    public Clanek save(Clanek zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() != null) {
            return updatuj(zaznamKUlozeni);
        } else {
            return pridej(zaznamKUlozeni);
        }
    }

    private Clanek pridej(Clanek zaznamKPridani) {
        Clanek zakaznik = clone(zaznamKPridani);
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO Clanky (Nazev, Autor, Datum) " +
                "VALUES (?, ?, ?)";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, zakaznik.getNazev());
                    prikaz.setString(2, zakaznik.getAutor());
                    prikaz.setObject(3, zakaznik.getDatum());
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        zakaznik.setId(drzakNaVygenerovanyKlic.getKey().longValue());
        return zakaznik;
    }

    private Clanek updatuj(Clanek zaznamKUlozeni) {
        zaznamKUlozeni = clone(zaznamKUlozeni);
        odesilacDotazu.update(
                "UPDATE Clanky SET Nazev = ?, Autor = ?, Datum = ? WHERE ID = ?",
                zaznamKUlozeni.getNazev(),
                zaznamKUlozeni.getAutor(),
                zaznamKUlozeni.getDatum(),
                zaznamKUlozeni.getId());
        return zaznamKUlozeni;
    }

    public void delete(Long id) {
        odesilacDotazu.update(
                "DELETE FROM Clanky WHERE ID = ?",
                id);
    }

    private Clanek clone(Clanek puvodni) {
        return new Clanek(puvodni.getId(), puvodni.getNazev(), puvodni.getAutor(), puvodni.getDatum());
    }
}