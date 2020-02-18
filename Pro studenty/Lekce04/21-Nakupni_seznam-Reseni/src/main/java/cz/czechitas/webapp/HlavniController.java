package cz.czechitas.webapp;

import java.time.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData;
        List<String> zbozi = Arrays.asList("Banány", "Meloun", "Třešně");

        drzakNaData = new ModelAndView("index-template");
        drzakNaData.addObject("nakup", zbozi);

        return drzakNaData;
    }

}
