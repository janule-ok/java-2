Lekce 03
========

Úkol - Pexeso
-------------

### Stůl s kartičkami

Vytvořte jednoduchou webovou aplikaci, která bude zobrazovat kartičky pexesa v náhodném pořadí.

Kartičky pexesa si můžete vytvořit vlastní nebo použít ty z lekce ze složky `podklady`.

Pokud se vám nechce vytvářet vlastní kaskádové styly, můžete si vzít ty z dema.

Jak bude web vypadat, nechám na vás. Ukázkový web můžete vidět zde:

![Pexeso screenshot](img/ukol03-screenshot.png)

Tip: Pokud potřebujete zamíchat seznam objektů, můžete použít `Collections.shuffle(...)`:

```java
List<String> seznamKaret = new ArrayList<>();
// ... vynechano naplneni seznamuKaret ...
Collections.shuffle(seznamKaret);
```



### Seznam přeborníků pexesa

Dále připravte stránku, na které bude tabulka nejlepších hráčů pexesa.

Pro tyto účely si připravte novou třídu **Hrac** (ve stejné složce jako **HlavniController**).

Třída bude sdružovat pro daného hráče údaje **jmeno**, **prijmeni**, **email**,
**pocetVyhranychTurnaju**, **pocetTurnajuCelkem**.

Třída bude mít vnitřní proměnné, konstruktor a metody get a set pro zpřístupnění vnitřních proměnných (ty totiž definují tzv. `vlastnosti`).

Pokud víte, jak to napsat, napište ji sami. Pokud ne, můžete vyjít z mojí verze:

```java
public class Hrac {

    private String jmeno;
    private int pocetVyhranychTurnaju;
    private int pocetTurnajuCelkem;

    public Hrac() {
    }

    public Hrac(String jmeno, int pocetVyhranychTurnaju, int pocetTurnajuCelkem) {
        this.jmeno = jmeno;
        this.pocetVyhranychTurnaju = pocetVyhranychTurnaju;
        this.pocetTurnajuCelkem = pocetTurnajuCelkem;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String newValue) {
        jmeno = newValue;
    }

    public int getPocetVyhranychTurnaju() {
        return pocetVyhranychTurnaju;
    }

    public void setPocetVyhranychTurnaju(int newValue) {
        pocetVyhranychTurnaju = newValue;
    }

    public int getPocetTurnajuCelkem() {
        return pocetTurnajuCelkem;
    }

    public void setPocetTurnajuCelkem(int newValue) {
        pocetTurnajuCelkem = newValue;
    }

    public double getUspesnost() {
        return 100.0 * pocetVyhranychTurnaju / pocetTurnajuCelkem;
    }
}
```

Jak si můžete všimnout, některé vlastnosti chybějí (např. `prijmeni`, `email`). Dopište je do třídy. Pokud
chcete, můžete přidat i nějaké další.

Když jste ve třídě **Hrac** nadefinovali metodu **String getJmeno()**, a máte v ThymeLeafu v proměnné ${prebornik}
jednoho hráče (objekt třídy **Hrac**) lze k jeho jménu v Thymeleafu přistupovat pomocí **${prebornik.jmeno}**.



### Pár tipů

* Trik s cestami, aby se soubory se šablonami daly pořád otevírat jako statické soubory:
    ~~~~html
    <link rel="stylesheet" href="../static/css/styles.css" th:href="${'css/styles.css'}" />
    ~~~~

* Pokud si nebudete vůbec vědět rady, v odevzdávárně je můj vzorový domácí úkol. Snažte se ho ale nepoužít.

* Vyjděte ze šablony projektu z hodiny (Czechitas Web App Template). Složku si prostě okopírujte a otevřete ji v
  IntelliJ IDEA. Po otevření je nutné přejmenovat tato místa, kde je jméno a adresa aplikace uvedena v konfiguračních
  souborech:
  * `PROJEKT/src/main/resources/application.properties` -> `server.servlet.context-path` = `/ukol03`
  * `PROJEKT/pom.xml` -> `/project/groupId` = `cz.czechitas.java2`
  * `PROJEKT/pom.xml` -> `/project/artifactId` = `ukol03`
  * `PROJEKT/pom.xml` -> `/project/name` = `ukol03`
  * `PROJEKT/pom.xml` -> `/project/build/finalName` = `ukol03`

* Archív .war vytvoříte v pravém panelu Maven Projects -> Lifecycle -> clean a potom Maven Projects -> Lifecycle ->
  package.

* Pozor! Mezi zdrojovým projektem (složkou) a výsledným webovým archívem .war je velký rozdíl. Do Tomcatu se nasazuje
  výsledný archív .war, do odevzdávárny na Google Drivu se nahrává zazipovaná složka celého projektu.

* Do Tomcatu se NIKDY nekopíruje rozbalená složka webu, pouze archív .war. Tomcat si tento archív sám rozbalí.

* Pokud se chcete zbavit nasazené webové aplikace z Tomcatu a máte ho spuštěný, smažte pouze archív .war ve složce
  `TOMCAT/webapps`. Nemažte rozbalenou složku webu. Tomcat sám pozná, že jste odebrali zdrojový archív .war a rozbalenou
  složku smaže sám. To slouží zároveň jako potvrzení, že byla webová aplikace úspěšně sesazena.
  Pouze pokud by Tomcat zrovna neběžel, můžete smazat nejen archív .war, ale i rozbalenou složku z `TOMCAT/webapps`.

* Pro psaní doporučuji používat javový projekt se zabudovaným malým webovým serverem, který spustíte klasicky pomocí zelené
  šipky. Adresa vašeho webu je potom http://localhost:8080/ukol03. Případně místo `/ukol03` to, co jste uvedli v
  `application.properties` -> `server.servlet.context-path`. V tomto případě stačí pouze editovat zdrojové soubory webu a obnovovat
  stránku v prohlížeči.

* Pro zajímavost, jméno výsledného archívu .war se nastavuje v `PROJEKT/pom.xml` -> `/project/build/finalName`



### Odevzdání domácího úkolu

Nejprve appku/appky zbavte přeložených spustitelných souborů.
Zařídíte to tak, že v IntelliJ IDEA vpravo zvolíte
Maven Projects -> Lifecycle -> Clean.
Úspěch se projeví tak, že v projektové složce zmizí
podsložka `target`.
Následně složku s projektem
zabalte pomocí 7-Zipu pod jménem `Ukol-CISLO-Vase_Jmeno.7z`.
(Případně lze použít prostý zip, například na Macu).
Takto vytvořený archív nahrajte na Google Drive do Odevzdávárny.

Vytvořte snímek obrazovky spuštěného programu (webové stránky) a vložte jej
do galerie `Ukol CISLO` ve skupině našeho kurzu na Facebooku.

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě),
zabalte ji a nahrajte ji na stejný Google Drive znovu,
jen tentokrát se jménem `Ukol-CISLO-Vase_Jmeno-verze2.7z`.

Termín odevzdání je dva dny před další lekcí, nejpozději 23:59.
Tedy pokud je další lekce ve čtvrtek, termín je úterý 23:59.
Pokud úkol nebo revizi odevzdáte později,
prosím pošlete svému opravujícímu kouči/lektorovi email nebo zprávu přes FB.
