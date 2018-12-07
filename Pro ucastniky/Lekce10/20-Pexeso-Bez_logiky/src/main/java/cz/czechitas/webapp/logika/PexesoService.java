package cz.czechitas.webapp.logika;

import java.time.*;
import java.util.*;
import org.springframework.stereotype.*;
import cz.czechitas.webapp.entity.*;
import cz.czechitas.webapp.persistence.*;

@Component
public class PexesoService {

    private PexesoRepository ulozisteHer;

    public PexesoService(PexesoRepository ulozisteHer) {
        this.ulozisteHer = ulozisteHer;
    }

    public HerniPlocha vytvorNovouHerniPlochu() {

        // TODO

        HerniPlocha novaPlocha = ...;
        novaPlocha = ulozisteHer.save(novaPlocha);
        return novaPlocha;
    }

    public HerniPlocha najdiHerniPlochu(Long id) {
        HerniPlocha aktualniPlocha = ulozisteHer.findOne(id);
        return aktualniPlocha;
    }

    public void provedTah(Long idHerniPlochy, int poziceKartyNaKterouSeKliknulo) {
        HerniPlocha aktualniPlocha = ulozisteHer.findOne(idHerniPlochy);

        // TODO

        ulozisteHer.save(aktualniPlocha);
    }

}

