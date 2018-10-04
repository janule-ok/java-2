package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private List<TelefonniKontakt> seznamKontaktu = Arrays.asList(
            new TelefonniKontakt(500L, "Thomas Alva Edison", "New Jersey", "USA", "+1-123-555-666"),
            new TelefonniKontakt(501L, "Johan Gregor Mendel", "Brno", "Rakousko-Uhersko", "+420 544213255"),
            new TelefonniKontakt(502L, "Albert Einstein", "Bern", "Švýcarsko", "+41 953 203 569"),
            new TelefonniKontakt(503L, "Marie Curie Sklodowská", "Paříž", "Francie", "+33 7456 123 523"),
            new TelefonniKontakt(504L, "Kamil Ševeček", "Brno", "Česko", "+420 604 123 456")
    );

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("index");
        return drzakNaData;
    }

    @RequestMapping("/kontakty/")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaData;

        drzakNaData = new ModelAndView("kontakty/index");
        drzakNaData.addObject("seznam", seznamKontaktu);

        return drzakNaData;
    }

    @RequestMapping("/kontakty/{cislo}.html")
    public ModelAndView zobrazSeznam(@PathVariable("cislo") Long id) {
        ModelAndView drzakNaData;

        drzakNaData = new ModelAndView("kontakty/detail");
        TelefonniKontakt nalezenyKontakt = najdi(id);
        drzakNaData.addObject("kontakt", nalezenyKontakt);

        return drzakNaData;
    }

    private TelefonniKontakt najdi(Long id) {
        for (TelefonniKontakt kontakt : seznamKontaktu) {
            if (kontakt.getId().equals(id)) {
                return kontakt;
            }
        }
        throw new IllegalArgumentException("Kontakt nenalezen");
    }

}
