package cz.czechitas.webapp;

import java.util.*;
import java.util.concurrent.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private List<String> seznamVeci;

    public HlavniController() {
        seznamVeci = new CopyOnWriteArrayList<>();
        seznamVeci.add("Banány");
        seznamVeci.add("Meloun");
        seznamVeci.add("Třešně");
        seznamVeci.add("Mango");
    }

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaDataAJmenoStranky;

        drzakNaDataAJmenoStranky = new ModelAndView("index");
        drzakNaDataAJmenoStranky.addObject("nakup", seznamVeci);

        return drzakNaDataAJmenoStranky;
    }

    @RequestMapping(value = "/novy.html", method = RequestMethod.GET)
    public ModelAndView zobrazNovy() {
        ModelAndView drzakNaDataAJmenoStranky;
        drzakNaDataAJmenoStranky = new ModelAndView("novy");
        return drzakNaDataAJmenoStranky;
    }

    @RequestMapping(value = "/novy.html", method = RequestMethod.POST)
    public ModelAndView zpracujNovy(String vyrobek) {
        ModelAndView drzakNaDataAJmenoStranky;
        seznamVeci.add(vyrobek);
        drzakNaDataAJmenoStranky = new ModelAndView("redirect:/");
        return drzakNaDataAJmenoStranky;
    }

}
