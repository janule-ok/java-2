package cz.czechitas.webapp;

import java.time.*;
import java.util.concurrent.atomic.*;

public class Clanek {

    private static AtomicLong idSequence = new AtomicLong(3000L);

    private Long id;
    private String nazev;
    private String autor;
    private LocalDate datum;

    public Clanek() {
        this.id = idSequence.getAndIncrement();
        this.datum = LocalDate.now();
    }

    public Clanek(String nazev, String autor, LocalDate datum) {
        this.id = idSequence.getAndIncrement();
        this.nazev = nazev;
        this.autor = autor;
        this.datum = datum;
    }

    public Clanek(Long id, String nazev, String autor, LocalDate datum) {
        this.id = id;
        this.nazev = nazev;
        this.autor = autor;
        this.datum = datum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newValue) {
        id = newValue;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String newValue) {
        nazev = newValue;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate newValue) {
        datum = newValue;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String newValue) {
        autor = newValue;
    }
}
