package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("index-template");
        return drzakNaData;
    }

    @RequestMapping("/seznam.html")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaData;
        List<TelefonniKontakt> seznamKontaktu;

        drzakNaData = new ModelAndView("seznam-template");
        seznamKontaktu = Arrays.asList(
                new TelefonniKontakt("Johan Gregor Mendel", "Rakousko-Uhersko", "+420 544213255"),
                new TelefonniKontakt("Marie Curie Sklodowská", "Francie", "+33 7456 123 523")
        );
        drzakNaData.addObject("seznam", seznamKontaktu);

        return drzakNaData;
    }

}
