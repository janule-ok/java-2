package cz.czechitas.webapp;

public class TelefonniKontakt {

    private Long id;
    private String jmeno;
    private String mesto;
    private String stat;
    private String telefon;

    public TelefonniKontakt() {
    }

    public TelefonniKontakt(Long id, String jmeno, String mesto, String stat, String telefon) {
        this.id = id;
        this.jmeno = jmeno;
        this.mesto = mesto;
        this.stat = stat;
        this.telefon = telefon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newValue) {
        id = newValue;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String newValue) {
        jmeno = newValue;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String newValue) {
        stat = newValue;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String newValue) {
        telefon = newValue;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String newValue) {
        mesto = newValue;
    }
}
