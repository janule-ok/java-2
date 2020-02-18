package cz.czechitas.webapp;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;

public class SouborovaRepositoryProClanky {

    public static final Pattern REGEX_RADKU = Pattern.compile("([0-9]+)[,;]\"(.*?)\"[,;]\"(.*?)\"[,;]([0-9]{4}-[0-9]{1,2}-[0-9]{1,2})");

    private Long idSequence = 3000L;

    public synchronized List<Clanek> findAll() {
        try {
            List<String> radky = Files.readAllLines(Paths.get("data.csv"));

            List<Clanek> clanky = new ArrayList<>(radky.size());
            for (String radek : radky) {
                Matcher regularniAutomat = REGEX_RADKU.matcher(radek);
                if (!regularniAutomat.find()) continue;

                Clanek jedenClanek = new Clanek();
                jedenClanek.setId(Long.parseLong(regularniAutomat.group(1)));
                jedenClanek.setNazev(regularniAutomat.group(2));
                jedenClanek.setAutor(regularniAutomat.group(3));
                jedenClanek.setDatum(LocalDate.parse(regularniAutomat.group(4), DateTimeFormatter.ISO_DATE));
                clanky.add(jedenClanek);
            }
            return clanky;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Clanek findById(Long id) {
        List<Clanek> clanky = findAll();
        int index = najdiIndexZaznamu(clanky, id);
        if (index == -1) {
            return null;
        }
        return clone(clanky.get(index));
    }

    public synchronized Clanek save(Clanek zaznamKUlozeni) {
        List<Clanek> clanky = findAll();
        int index = najdiIndexZaznamu(clanky, zaznamKUlozeni.getId());
        if (index == -1) {
            return pridej(clanky, zaznamKUlozeni);
        }
        return updatuj(clanky, zaznamKUlozeni, index);
    }

    public synchronized void delete(Long id) {
        List<Clanek> clanky = findAll();
        int index = najdiIndexZaznamu(clanky, id);
        if (index == -1) return;
        clanky.remove(index);
        ulozClanky(clanky);
    }

    //-------------------------------------------------------------------------

    private Clanek updatuj(List<Clanek> clanky, Clanek zaznamKUlozeni, int index) {
        Clanek clanek = clone(zaznamKUlozeni);
        clanky.set(index, clanek);
        ulozClanky(clanky);
        return clone(clanek);
    }

    private Clanek pridej(List<Clanek> clanky, Clanek zaznamKPridani) {
        Clanek clanek = clone(zaznamKPridani);
        clanek.setId(idSequence);
        idSequence = idSequence + 1L;
        clanky.add(clanek);
        ulozClanky(clanky);
        return clone(clanek);
    }

    private int najdiIndexZaznamu(List<Clanek> clanky, Long id) {
        if (id == null) {
            return -1;
        }
        for (int i = 0; i < clanky.size(); i++) {
            Clanek clanek = clanky.get(i);
            if (clanek.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private Clanek clone(Clanek puvodni) {
        return new Clanek(puvodni.getId(), puvodni.getNazev(), puvodni.getAutor(), puvodni.getDatum());
    }

    private void ulozClanky(List<Clanek> clanky) {
        try {
            List<String> radky = new ArrayList<>(clanky.size());
            for (Clanek jedenClanek : clanky) {
                String radek = jedenClanek.getId() + ",\"" + jedenClanek.getNazev() + "\",\""+jedenClanek.getAutor()+"\","+jedenClanek.getDatum().getYear()+"-"+String.format("%02d", jedenClanek.getDatum().getMonthValue())+"-"+String.format("%02d",jedenClanek.getDatum().getDayOfMonth());
                radky.add(radek);
            }
            Files.write(Paths.get("data.csv"), radky, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
