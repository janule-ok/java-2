package cz.czechitas.webapp;

import java.text.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import javax.servlet.http.*;
import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private List<Clanek> seznamClanku = new CopyOnWriteArrayList<>(Arrays.asList(
            new Clanek("Boss podsvětí dostal 20 let", "Clark Kent", LocalDate.of(2018, 1, 31)),
            new Clanek("Lékaři doporučují opatrnost", "Lois Lane", LocalDate.of(2018, 2, 28)),
            new Clanek("Bezkontaktní karty lákají zloděje", "Perry White", LocalDate.of(2017, 12, 24)),
            new Clanek("Ministryně navštívila problematické předměstí", "Jimmy Olsen", LocalDate.of(2016, 7, 31)),
            new Clanek("Soutěž o lístky na fotbal", "Cat Grant", LocalDate.of(2016, 8, 1)),
            new Clanek("Vrah prodavačky je ve vazbě", "Ron Troupe", LocalDate.of(2017, 10, 28))
    ));

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("redirect:/clanky/");
        return drzakNaData;
    }

    @RequestMapping("/clanky/")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaData;

        drzakNaData = new ModelAndView("clanky/index");
        drzakNaData.addObject("seznam", seznamClanku);

        return drzakNaData;
    }

    @RequestMapping(value = "/clanky/{cislo}.html", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable("cislo") Long id) {
        ModelAndView drzakNaData;

        drzakNaData = new ModelAndView("clanky/detail");
        Clanek nalezenyClanek = najdi(id);
        drzakNaData.addObject("clanek", nalezenyClanek);

        return drzakNaData;
    }

    @RequestMapping(value = "/clanky/{cislo}.html", method = RequestMethod.POST)
    public ModelAndView zpracujDetail(@PathVariable("cislo") Long id,
                                      DetailForm vyplnenyFormular) {
        Clanek nalezenyClanek = najdi(id);
        nalezenyClanek.setNazev(vyplnenyFormular.getNazev());
        nalezenyClanek.setAutor(vyplnenyFormular.getAutor());
        nalezenyClanek.setDatum(vyplnenyFormular.getDatum());

        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping(value = "/clanky/{cislo}.html",
            method = {RequestMethod.POST, RequestMethod.DELETE},
            params = "_method=delete")
    public ModelAndView odstranClanek(@PathVariable("cislo") Long id, HttpServletRequest request) {
        Clanek nalezenyClanek = najdi(id);
        seznamClanku.remove(nalezenyClanek);

        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping(value = "/clanky/new.html", method = RequestMethod.GET)
    public ModelAndView zobrazNovy() {
        ModelAndView drzakNaData;

        drzakNaData = new ModelAndView("clanky/detail");
        Clanek novyClanek = new Clanek();
        drzakNaData.addObject("clanek", novyClanek);

        return drzakNaData;
    }

    @RequestMapping(value = "/clanky/new.html", method = RequestMethod.POST)
    public ModelAndView zpracujNovy(DetailForm vyplnenyFormular) {
        Clanek novyClanek = new Clanek();
        novyClanek.setNazev(vyplnenyFormular.getNazev());
        novyClanek.setAutor(vyplnenyFormular.getAutor());
        novyClanek.setDatum(vyplnenyFormular.getDatum());
        seznamClanku.add(novyClanek);

        return new ModelAndView("redirect:/clanky/");
    }

    private Clanek najdi(Long id) {
        for (Clanek kontakt : seznamClanku) {
            if (kontakt.getId().equals(id)) {
                return kontakt;
            }
        }
        throw new NotFoundException();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {}

}
