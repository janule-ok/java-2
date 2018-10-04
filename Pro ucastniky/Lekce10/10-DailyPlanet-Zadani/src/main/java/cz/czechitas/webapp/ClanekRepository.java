package cz.czechitas.webapp;

import java.time.*;
import java.util.*;

public class ClanekRepository {

    private List<Clanek> seznamClanku = new ArrayList<>(Arrays.asList(
            new Clanek("Boss podsvětí dostal 20 let", "Clark Kent", LocalDate.of(2018, 1, 31)),
            new Clanek("Lékaři doporučují opatrnost", "Lois Lane", LocalDate.of(2018, 2, 28)),
            new Clanek("Bezkontaktní karty lákají zloděje", "Perry White", LocalDate.of(2017, 12, 24)),
            new Clanek("Ministryně navštívila problematické předměstí", "Jimmy Olsen", LocalDate.of(2016, 7, 31)),
            new Clanek("Soutěž o lístky na fotbal", "Cat Grant", LocalDate.of(2016, 8, 1)),
            new Clanek("Vrah prodavačky je ve vazbě", "Ron Troupe", LocalDate.of(2017, 10, 28))
    ));

    public synchronized List<Clanek> findAll() {
        List<Clanek> clanky = new ArrayList<>(seznamClanku.size());
        for (Clanek jedenClanek : seznamClanku) {
            clanky.add(clone(jedenClanek));
        }
        return clanky;
    }

    public synchronized Clanek findOne(Long id) {
        int index = najdiIndexZaznamu(id);
        if (index == -1) {
            return null;
        }
        return clone(seznamClanku.get(index));
    }

    public synchronized Clanek save(Clanek zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() == null) {
            throw new NullPointerException("Clanek.ID nesmi byt null");
        }
        int index = najdiIndexZaznamu(zaznamKUlozeni.getId());
        if (index == -1) {
            return pridej(zaznamKUlozeni);
        }
        return updatuj(zaznamKUlozeni, index);
    }

    public synchronized void delete(Long id) {
        int index = najdiIndexZaznamu(id);
        if (index == -1) return;
        seznamClanku.remove(index);
    }

    //-------------------------------------------------------------------------

    private Clanek updatuj(Clanek zaznamKUlozeni, int index) {
        Clanek clanek = clone(zaznamKUlozeni);
        seznamClanku.set(index, clanek);
        return clone(clanek);
    }

    private Clanek pridej(Clanek zaznamKPridani) {
        Clanek clanek = clone(zaznamKPridani);
        seznamClanku.add(clanek);
        return clone(clanek);
    }

    private int najdiIndexZaznamu(Long id) {
        if (id == null) {
            throw new NullPointerException("Clanek.ID nesmi byt null");
        }
        for (int i = 0; i < seznamClanku.size(); i++) {
            Clanek clanek = seznamClanku.get(i);
            if (clanek.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private Clanek clone(Clanek puvodni) {
        return new Clanek(puvodni.getId(), puvodni.getNazev(), puvodni.getAutor(), puvodni.getDatum());
    }

}
