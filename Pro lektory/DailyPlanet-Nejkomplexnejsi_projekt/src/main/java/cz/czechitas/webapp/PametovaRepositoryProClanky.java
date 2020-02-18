package cz.czechitas.webapp;

import java.time.*;
import java.util.*;

public class PametovaRepositoryProClanky {

    public static List<Clanek> seznamClanku = new ArrayList<>(Arrays.asList(
            new Clanek(2000L, "Boss podsvětí dostal 20 let", "Clark Kent", LocalDate.of(2019, 1, 31)),
            new Clanek(2001L, "Lékaři doporučují opatrnost", "Lois Lane", LocalDate.of(2019, 2, 28)),
            new Clanek(2002L, "Bezkontaktní karty lákají zloděje", "Perry White", LocalDate.of(2018, 12, 24)),
            new Clanek(2003L, "Ministryně navštívila problematické předměstí", "Jimmy Olsen", LocalDate.of(2017, 7, 31)),
            new Clanek(2004L, "Soutěž o lístky na fotbal", "Cat Grant", LocalDate.of(2018, 8, 1)),
            new Clanek(2005L, "Vrah prodavačky je ve vazbě", "Ron Troupe", LocalDate.of(2017, 10, 28))
    ));

    private Long idSequence = 3000L;

    public synchronized List<Clanek> findAll() {
        List<Clanek> clanky = new ArrayList<>(seznamClanku.size());
        for (Clanek jedenClanek : seznamClanku) {
            clanky.add(clone(jedenClanek));
        }
        return clanky;
    }

    public synchronized Clanek findById(Long id) {
        int index = najdiIndexZaznamu(id);
        if (index == -1) {
            return null;
        }
        return clone(seznamClanku.get(index));
    }

    public synchronized Clanek save(Clanek zaznamKUlozeni) {
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
        clanek.setId(idSequence);
        idSequence = idSequence + 1L;
        seznamClanku.add(clanek);
        return clone(clanek);
    }

    private int najdiIndexZaznamu(Long id) {
        if (id == null) {
            return -1;
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
