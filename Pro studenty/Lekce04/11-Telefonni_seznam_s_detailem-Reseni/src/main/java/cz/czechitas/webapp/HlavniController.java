package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private List<TelefonniKontakt> seznamKontaktu = new ArrayList<>(Arrays.asList(
            new TelefonniKontakt(1000L, "Johann Gregor Mendel", "+420 544213255", "johann@dieceze-brno.cz", "Brno", "Rakousko-Uhersko"),
            new TelefonniKontakt(1001L, "Marie Curie Sklodowská", "+33 7456 123 523", "marie@sorbonne.fr", "Paříž", "Francie"),
            new TelefonniKontakt(1004L, "Alexander von Humboldt", "+30 450 576042", "alex@hu-berlin.de", "Berlín", "Německo")
    ));

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping("/seznam.html")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaDataAJmenoStranky = new ModelAndView("seznam");
        drzakNaDataAJmenoStranky.addObject("seznam", seznamKontaktu);
        return drzakNaDataAJmenoStranky;
    }

    @RequestMapping("/detail-{id}.html")
    public ModelAndView zobrazDetail(@PathVariable Long id) {
        ModelAndView drzakNaDataAJmenoStranky = new ModelAndView("detail");
        TelefonniKontakt nalezenyKontakt = findById(id);
        drzakNaDataAJmenoStranky.addObject("kontakt", nalezenyKontakt);
        return drzakNaDataAJmenoStranky;
    }

    private TelefonniKontakt findById(Long id) {
        for (TelefonniKontakt kontakt : seznamKontaktu) {
            if (kontakt.getId().equals(id)) return kontakt;
        }
        return null;
    }

}
