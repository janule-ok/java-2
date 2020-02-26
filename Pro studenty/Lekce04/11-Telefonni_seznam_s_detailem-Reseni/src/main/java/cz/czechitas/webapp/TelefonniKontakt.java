package cz.czechitas.webapp;

public class TelefonniKontakt {

    private Long id;
    private String jmeno;
    private String telefon;
    private String email;
    private String mesto;
    private String stat;

    public TelefonniKontakt(Long id, String jmeno, String telefon, String email, String mesto, String stat) {
        this.id = id;
        this.jmeno = jmeno;
        this.telefon = telefon;
        this.email = email;
        this.mesto = mesto;
        this.stat = stat;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String newValue) {
        email = newValue;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String newValue) {
        mesto = newValue;
    }
}
