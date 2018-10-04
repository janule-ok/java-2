package cz.czechitas.webapp;

public class Vyrobek {

    private String nazev;
    private int mnozstvi;

    public Vyrobek(String nazev, int mnozstvi) {
        this.nazev = nazev;
        this.mnozstvi = mnozstvi;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String newValue) {
        nazev = newValue;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int newValue) {
        mnozstvi = newValue;
    }
}
