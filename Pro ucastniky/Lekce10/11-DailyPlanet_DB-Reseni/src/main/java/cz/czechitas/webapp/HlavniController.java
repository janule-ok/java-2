package cz.czechitas.webapp;

import java.util.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private ClanekRepository dodavatelDat = new ClanekRepository();

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping("/clanky/")
    public ModelAndView zobrazSeznam() {
        List<Clanek> seznamClanku = dodavatelDat.findAll();

        ModelAndView drzakNaData = new ModelAndView("clanky/index");
        drzakNaData.addObject("seznam", seznamClanku);
        return drzakNaData;
    }

    @RequestMapping(value = "/clanky/{cislo}.html", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable("cislo") Long id) {

        Clanek nalezenyClanek = dodavatelDat.findOne(id);
        if (nalezenyClanek == null) {
            throw new NotFoundException();
        }

        ModelAndView drzakNaData = new ModelAndView("clanky/detail");
        drzakNaData.addObject("clanek", nalezenyClanek);
        return drzakNaData;
    }

    @RequestMapping(value = "/clanky/{cislo}.html", method = RequestMethod.POST)
    public ModelAndView zpracujDetail(@PathVariable("cislo") Long id,
                                      DetailForm vyplnenyFormular) {
        Clanek nalezenyClanek = new Clanek(id,
                vyplnenyFormular.getNazev(),
                vyplnenyFormular.getAutor(),
                vyplnenyFormular.getDatum());
        dodavatelDat.save(nalezenyClanek);

        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping(value = "/clanky/{cislo}.html",
            method = {RequestMethod.POST, RequestMethod.DELETE},
            params = "_method=delete")
    public ModelAndView odstranClanek(@PathVariable("cislo") Long id) {
        dodavatelDat.delete(id);
        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping(value = "/clanky/new.html", method = RequestMethod.GET)
    public ModelAndView zobrazNovy() {
        Clanek novyClanek = new Clanek();

        ModelAndView drzakNaData = new ModelAndView("clanky/detail");
        drzakNaData.addObject("clanek", novyClanek);
        return drzakNaData;
    }

    @RequestMapping(value = "/clanky/new.html", method = RequestMethod.POST)
    public ModelAndView zpracujNovy(DetailForm vyplnenyFormular) {
        Clanek novyClanek = new Clanek();
        novyClanek.setNazev(vyplnenyFormular.getNazev());
        novyClanek.setAutor(vyplnenyFormular.getAutor());
        novyClanek.setDatum(vyplnenyFormular.getDatum());
        dodavatelDat.save(novyClanek);

        return new ModelAndView("redirect:/clanky/");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {}

}
