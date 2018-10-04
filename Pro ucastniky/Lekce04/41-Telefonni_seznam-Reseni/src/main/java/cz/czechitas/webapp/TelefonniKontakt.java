package cz.czechitas.webapp;

public class TelefonniKontakt {

    private String jmeno;
    private String stat;
    private String telefon;

    public TelefonniKontakt() {
    }

    public TelefonniKontakt(String jmeno, String stat, String telefon) {
        this.jmeno = jmeno;
        this.stat = stat;
        this.telefon = telefon;
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
}
