package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private static final List<String> VYROKY = Arrays.asList(
            "Když vidím na stromě vyřezaná jména milenců, tak mi to nepřijde vůbec  vtipné... Zarážející je, kolik lidí si na rande bere nůž...",
            "Včera jsem potkal dívku, přeskočila jiskra a hned mi ležela u nohou, miluji svůj nový paralizér...",
            "Pro hlupáka neexistuje problém, který by se nedal vytvořit.",
            "Všechno se dá vysvětlit, bohužel ne všem.",
            "Romantické filmy ničí vztah, protože budí v ženách pocit, že něco podobného je v životě potká. Podobně klamáni jsou muži pornem.",
            "K navození vhodné atmosféry pro početí prvního potomka může pomoci víno a porno. U druhého dítěte postačí piškotky a DVD s Krtečkem."
    );


    @RequestMapping("/vyroky.html")
    public ModelAndView zobrazVyroky() {
        ModelAndView drzakNaDataAJmenoSablony =
                new ModelAndView("vyroky-template");
        Random generatorNahodnychCisel = new Random();
        String nahodnyVyrok = VYROKY.get(
                generatorNahodnychCisel.nextInt(VYROKY.size()));

        drzakNaDataAJmenoSablony.addObject("jedenVyrok",
                nahodnyVyrok);
        drzakNaDataAJmenoSablony.addObject("seznamVyroku", VYROKY);
        return drzakNaDataAJmenoSablony;
    }

}
