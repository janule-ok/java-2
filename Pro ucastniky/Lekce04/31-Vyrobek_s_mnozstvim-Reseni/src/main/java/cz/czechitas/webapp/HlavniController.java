package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData;
        Vyrobek vec;

        vec = new Vyrobek("Rohlíky", 11);

        drzakNaData = new ModelAndView("index-template");
        drzakNaData.addObject("jednoZbozi", vec);

        return drzakNaData;
    }

}
