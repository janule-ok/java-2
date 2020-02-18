package cz.czechitas.webapp;

import java.time.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData;
        String jmeno;
        LocalDate datum;

        drzakNaData = new ModelAndView("index-template");

        jmeno = "Kamil";
        drzakNaData.addObject("mojeJmeno", jmeno);
        datum = LocalDate.now();
        drzakNaData.addObject("dnesniDatum", datum);

        return drzakNaData;
    }

}
