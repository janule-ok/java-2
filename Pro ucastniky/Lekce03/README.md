Lekce 03
========

Adresy a odkazy, více o ThymeLeaf
---------------------------------

 Sepsal jsem článek o cestách a odkazech z pohledu různých programů. Hlavní protagonisté jsou:

* Webový prohlížeč
* Webový server
* Spring Boot

Nejde o vyčerpávající popis, chtěl jsem to ukázat na příkladech.

Mrkněte se na to. Ideální by bylo napsat si za (nepovinný) úkol malou webovou aplikaci a vyzkoušet si všechny příklady.

### Prohlížeč při odesílání požadavku rozpoznává tyto části adresy

#### Příklad 1

<table>
<thead>
<tr><th colspan="2">http://www.pokus.com/vtipy/zvireci/index.html</th></tr>
<thead>
<tbody>
<tr><td>http</td><td>Protokol.</td></tr>
<tr><td>www.pokus.com</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td>/vtipy/zvireci/index.html</td><td>Adresa stránky.</td></tr>
</tbody>
</table>

#### Příklad 2

<table>
<thead>
<tr><th colspan="2">https://pokus.com:8080/</th></tr>
<thead>
<tbody>
<tr><td>https</td><td>Protokol.</td></tr>
<tr><td>pokus.com</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td>8080</td><td>Číslo síťového portu na serveru.</td></tr>
<tr><td>/</td><td>Adresa stránky.</td></tr>
</tbody>
</table>

#### Příklad 3

<table>
<thead>
<tr><th colspan="2">https://pokus.com/vtipy/index.html?jazyk=cs</th></tr>
<thead>
<tbody>
<tr><td>https</td><td>Protokol.</td></tr>
<tr><td>pokus.com</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td>/vtipy/index.html</td><td>Adresa stránky.</td></tr>
<tr><td>jazyk=cs</td><td>Parametry požadavku (zde jeden: jazyk=cs).</td></tr>
</tbody>
</table>

#### Příklad 4

<table>
<thead>
<tr><th colspan="2">https://pokus.com/index.html?jazyk=cs&barvy=tmave</th></tr>
<thead>
<tbody>
<tr><td>https</td><td>Protokol.</td></tr>
<tr><td>pokus.com</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td>/vtipy/index.html</td><td>Adresa stránky.</td></tr>
<tr><td>jazyk=cs&amp;barvy=tmave</td><td>Parametry požadavku (zde dva: jazyk=cs, barvy=tmave).</td></tr>
</tbody>
</table>

#### Příklad 5

<table>
<thead>
<tr><th colspan="2">http://localhost:8000/index.html</th></tr>
<thead>
<tbody>
<tr><td>http</td><td>Protokol.</td></tr>
<tr><td>localhost</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td>8000</td><td>Číslo síťového portu na serveru.</td></tr>
<tr><td>/index.html</td><td>Adresa stránky.</td></tr>
</tbody>
</table>

#### Poznámky

* HTTP je klasický protokol. HTTPS je to stejné, jen šifrované (proti odposlechu).
* Pokud není uvedeno číslo síťového portu, pak se předpokládá 80 pro HTTP a 443 pro HTTPS.
* `pokus.com` a `www.pokus.com` jsou zcela jiné internetové adresyy serveru. Obvykle jsou nasměrovány na stejný server, ale
  vůbec tomu tak být nemusí.
* localhost znamená vždycky tento počítač (z hlediska počítače tedy požadavek sám na sebe).

### Webový server při obdržení požadavku rozpoznává tyto části adresy

Je důležité si uvědomit, že webový server ví, jaké má nasazené webové aplikace. Z toho plyne, že rozpoznává stejné části
adresy jako webový prohlížeč, jen navíc jméno webové aplikace. Předpokládejme, že má nasazeno:

* **ROOT.war**, tedy aplikace nasazena na **(prazdno)**.
* **vtipy.war**, tedy aplikace nasazena na **/vtipy**.
* **VideoBoss.war**, tedy aplikace nasazena na **/VideoBoss** (pozor, rozlišují se velká a malá písmena).

#### Příklad 1

<table>
<thead>
<tr><th colspan="2">https://pokus.com/index.html?jazyk=cs&barvy=tmave</th></tr>
<thead>
<tbody>
<tr><td>https</td><td>Protokol.</td></tr>
<tr><td>pokus.com</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td></td><td>Webová aplikace (prázdno, tedy ROOT.war).</td></tr>
<tr><td>/index.html</td><td>Adresa stránky.</td></tr>
<tr><td>jazyk=cs&amp;barvy=tmave</td><td>Parametry požadavku (zde dva: jazyk=cs, barvy=tmave).</td></tr>
</tbody>
</table>

#### Příklad 2

<table>
<thead>
<tr><th colspan="2">http://www.pokus.com/vtipy/zvireci/index.html</th></tr>
<thead>
<tbody>
<tr><td>http</td><td>Protokol.</td></tr>
<tr><td>www.pokus.com</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td>/vtipy</td><td>Webová aplikace (vtipy.war).</td></tr>
<tr><td>/zvireci/index.html</td><td>Adresa stránky.</td></tr>
</tbody>
</table>

#### Příklad 3

<table>
<thead>
<tr><th colspan="2">http://tomcat.cloud/VideoBoss/customers/all.html</th></tr>
<thead>
<tbody>
<tr><td>http</td><td>Protokol.</td></tr>
<tr><td>tomcat.cloud</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td>/VideoBoss</td><td>Webová aplikace (VideoBoss.war).</td></tr>
<tr><td>/customers/all.html</td><td>Adresa stránky.</td></tr>
</tbody>
</table>

#### Příklad 4

<table>
<thead>
<tr><th colspan="2">https://tomcat.cloud/Pexeso/vecernicek/index.html</th></tr>
<thead>
<tbody>
<tr><td>https</td><td>Protokol.</td></tr>
<tr><td>tomcat.cloud</td><td>Doména, internetová adresa serveru.</td></tr>
<tr><td></td><td>Webová aplikace (prázdno, tedy ROOT.war).<br>
Ačkoliv to vypadá, že by na serveru mohla být webová aplikace Pexeso, výše jsme definovali, že webový server má nasazeny
jen 3 WARy, takže nutně půjde do ROOT.war a bude tam hledat složku Pexeso.</td></tr>
<tr><td>/Pexeso/vecernicek/index.html</td><td>Adresa stránky.</td></tr>
</tbody>
</table>

### Cesty z pohledu Spring Boot

Pro Spring Boot je důležitý @RequestMapping uvnitř Java třídy označené @Controller. Dejme tomu, že webová aplikace je
nasazena na /ukol02, tedy že je distribuována jako ukol02.war, a webový server běží na internetové adrese pokus.com.

#### Příklad 1

<table>
<thead>
<tr><th colspan="2">http://pokus.com/ukol02/index.html</th></tr>
<thead>
<tbody>
<tr><td>/index.html</td><td>Adresa stránky.</td></tr>
<tr><td colspan="2">Spring Boot vyvolá metodu označenou @RequestMapping("/index.html").</th></tr>
</tbody>
</table>

#### Příklad 2

<table>
<thead>
<tr><th colspan="2">http://pokus.com/ukol02/vyroky/index.html</th></tr>
<thead>
<tbody>
<tr><td>/vyroky/index.html</td><td>Adresa stránky.</td></tr>
<tr><td colspan="2">Spring Boot vyvolá metodu označenou @RequestMapping("/vyroky/index.html").</th></tr>
</tbody>
</table>

#### Příklad 3

<table>
<thead>
<tr><th colspan="2">http://pokus.com/ukol02/vyroky/</th></tr>
<thead>
<tbody>
<tr><td>/vyroky/</td><td>Adresa stránky.</td></tr>
<tr><td colspan="2">Spring Boot vyvolá metodu označenou @RequestMapping("/vyroky") nebo @RequestMapping("/vyroky/").</th></tr>
</tbody>
</table>

#### Příklad 4

<table>
<thead>
<tr><th colspan="2">http://pokus.com/ukol02/vyroky/?id=100</th></tr>
<thead>
<tbody>
<tr><td>/vyroky/</td><td>Adresa stránky.</td></tr>
<tr><td colspan="2">Spring Boot vyvolá metodu označenou @RequestMapping("/vyroky") nebo @RequestMapping("/vyroky/").</th></tr>
</tbody>
</table>

### Které soubory bude Spring Boot odesílat

Dejme tomu, že webová aplikace je nasazena na /ukol02, tedy že je distribuována jako ukol02.war, a webový server běží na
internetové adrese pokus.com. Mějme ve webové aplikaci tyto soubory:

* PROJEKT
    * src
        * main
            * resources
                * static
                    * index.html
                    * css
                        * styly.css
                    * kontakt
                        * o-nas.html
                    * test
                        * testik.html
                * templates
                    * meme-template.html
                    * kontakt
                        * napiste-nam-template.html
                    * vsichni-uzivatele
                        * login-template.html
                    * admin
                        * pokus.html

Dále mějme ve webové aplikaci javovou třídu označenou @Controller, která má následující @RequestMapping:

* **@RequestMapping("/meme.html")** (metoda vrací ModelAndView("meme-template"))
* **@RequestMapping("/test/testik.html")** (metoda vrací ModelAndView("meme-template"))
* **@RequestMapping("/kontakt/napiste-nam.html")** (metoda vrací ModelAndView("kontakt/napiste-nam-template"))
* **@RequestMapping("/admin/prihlaseni.html")** (metoda vrací ModelAndView("vsichni-uzivatele/login-template"))
* **@RequestMapping("/admin/pokus.html")** (metoda vrací ModelAndView() bez udání jména šablony)

<table>
<thead>
<tr><th>Požadavek</th><th>Výsledek</th></tr>
<thead>
<tbody>
<tr><td>http://pokus.com/ukol02/index.html</td><td>Spring Boot vrátí statický soubor: PROJEKT/src/main/resources/static/index.html.</td></tr>
<tr><td>http://pokus.com/ukol02/css/styly.css</td><td>Spring Boot vrátí statický soubor: PROJEKT/src/main/resources/static/css/styly.css.</td></tr>
<tr><td>http://pokus.com/ukol02/kontakt/o-nas.html</td><td>Spring Boot vrátí statický soubor: PROJEKT/src/main/resources/static/kontakt/o-nas.html.</td></tr>
<tr><td>http://pokus.com/ukol02/meme.html</td><td>Spring Boot provede šablonu: PROJEKT/src/main/resources/templates/meme-template.html.</td></tr>
<tr><td>http://pokus.com/ukol02/test/testik.html</td><td>Spring Boot provede šablonu: PROJEKT/src/main/resources/templates/meme-template.html.</td></tr>
<tr><td>http://pokus.com/ukol02/kontakt/napiste-nam.html</td><td>Spring Boot provede šablonu: PROJEKT/src/main/resources/templates/kontakt/napiste-nam-template.html.</td></tr>
<tr><td>http://pokus.com/ukol02/admin/prihlaseni.html</td><td>Spring Boot provede šablonu: PROJEKT/src/main/resources/templates/vsichni-uzivatele/login-template.html.</td></tr>
<tr><td>http://pokus.com/ukol02/admin/pokus.html</td><td>Spring Boot provede šablonu: PROJEKT/src/main/resources/templates/admin/pokus.html.</td></tr>
<tr><td>http://pokus.com/ukol02/cervena-karkulka.html</td><td>Neexistuje ani @RequestMapping("/cervena-karkulka.html"),
ani statický soubor PROJEKT/src/main/resources/static/cervena-karkulka.html. Spring Boot odešle chybovou stráku.</td></tr>
</tbody>
</table>

### Relativní vs. absolutní ceste ve webové stránce (uvnitř HTML souboru)

Odkazy vyhodnocuje webový prohlížeč.

<table>
<thead>
<tr><th>Jsme na stránce</th><th>V ní je odkaz</th><th>Po kliknutí na odkaz půjde prohlížeč na</th></tr>
<thead>
<tbody>
<tr><td>http://sladkost.tomcat.cloud/ukol02/vyrok/index.html</td><td>&lt;a href="pokus.html"&gt;Odkaz&lt;/a&gt;</td><td>http://sladkost.tomcat.cloud/ukol02/vyrok/pokus.html</td></tr>
<tr><td>http://sladkost.tomcat.cloud/ukol02/vyrok/index.html</td><td>&lt;a href="../o-me/"&gt;Odkaz&lt;/a&gt;</td><td>http://sladkost.tomcat.cloud/ukol02/o-me/</td></tr>
<tr><td>http://sladkost.tomcat.cloud/ukol02/vyrok/index.html</td><td>&lt;a href="/pokus.html"&gt;Odkaz&lt;/a&gt;</td><td>http://sladkost.tomcat.cloud/pokus.html</td></tr>
<tr><td>http://sladkost.tomcat.cloud/ukol02/vyrok/index.html</td><td>&lt;a href="//zkouska/pokus.html"&gt;Odkaz&lt;/a&gt;</td><td>http://zkouska/pokus.html</td></tr>
</tbody>
</table>

### Videozáznam

Na YouTube se můžete podívat na [záznam z lekce](https://www.youtube.com/watch?v=2exrkEkaxIU),
případně si prohlédnout [celý playlist](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ6mcuJ1VaY8s0mzFsaMUzp-).

Úkol - Pexeso
-------------

### Stůl s kartičkami

Vytvořte jednoduchou webovou aplikaci, která bude zobrazovat kartičky pexesa v náhodném pořadí.

Kartičky pexesa si můžete vytvořit vlastní nebo použít ty z hodiny.

Děkuji Martině Adamusové Hanušové za poskytnutí večerníčkových kartiček.

Pokud se vám nechce vytvářet vlastní kaskádové styly, můžete využít ty [od Luďka Rolečka](podklady/).

Stačí jedna sada kartiček.

![](ukol03-screenshot.png)

### Seznam přeborníků pexesa

Dále připravte stránku, na které bude tabulka nejlepších hráčů pexesa.

Pro tyto účely si připravte novou třídu **Hrac** (ve stejné složce jako **HlavniController**).

Třída bude sdružovat pro daného hráče údaje **jmeno**, **prijmeni**, **email**, hráčovu **oblibenaBarva**,
**pocetVyhranychTurnaju**, **pocetTurnajuCelkem**.

Třída bude mít vnitřní proměnné, konstruktor a metody get a set pro zpřístupnění vnitřních proměnných.

Pokud víte, jak to napsat, napište ji sami. Pokud ne, můžete vyjít z mojí verze:

```java
public class Hrac {

    private String jmeno;
    private String prijmeni;
    private int pocetVyhranychTurnaju;
    private int pocetTurnajuCelkem;

    public Hrac() {
    }

    public Hrac(String jmeno, String prijmeni, int pocetVyhranychTurnaju, int pocetTurnajuCelkem) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.pocetVyhranychTurnaju = pocetVyhranychTurnaju;
        this.pocetTurnajuCelkem = pocetTurnajuCelkem;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String newValue) {
        jmeno = newValue;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String newValue) {
        prijmeni = newValue;
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

Jak si můžete všimnout, některé datové položky jí chybí (např. email, oblíbená barva). Dopište je do třídy. Pokud
chcete, můžete přidat i nějaké další.

V HlavniController vytvořte **List<Hrac> prebornici**. Je to podobné jako **List<String> vyroky**.

```java
private static final List<Hrac> HRACI = Arrays.asList(
            new Hrac("Jmeno1", "Prijmeni1", 20, 100),
            new Hrac("Jmeno2", "Prijmeni2", 80, 100),
            new Hrac("Jmeno3", "Prijmeni3", 950, 1000),
            new Hrac("Jmeno4", "Prijmeni4", 50, 500),
            new Hrac("Jmeno5", "Prijmeni5", 75, 400),
    );
```

Když jste ve třídě **Hrac** nadefinovali metodu **String getJmeno()**, a máte v ThymeLeafu v proměnné ${prebornik}
jednoho hráče (objekt třídy **Hrac**) lze k jeho jménu v Thymeleafu přistupovat pomocí **${prebornik.jmeno}**.

Jak bude web vypadat, necháme na vás. Ukázkový web můžete vidět na
[http://margot.tomcat.cloud/ukol03/](http://margot.tomcat.cloud/ukol03/).

### Pár tipů

* Trik s cestami, aby se soubory se šablonami daly pořád otevírat jako statické soubory: &lt;link rel="stylesheet"
  href="../static/css/styles.css" th:href="${'css/styles.css'}" /&gt;.
* Pokud si nebudete vůbec vědět rady, v odevzdávárně je můj vzorový domácí úkol. Snažte se ho ale nepoužít.
* Vyjděte ze šablony projektu z hodiny (Czechitas Web App Template v4). Složku si prostě okopírujte a otevřete ji v
  IntelliJ IDEA. Po otevření je nutné přejmenovat tato místa, kde je jméno a adresa aplikace uvedena v konfiguračních
  souborech:
    * PROJEKT/src/main/resources/application.properties -> server.context-path = /ukol03
    * PROJEKT/pom.xml -> /project/artifactId = ukol03
    * PROJEKT/pom.xml -> /project/name = ukol03
    * PROJEKT/pom.xml -> /project/build/finalName = ukol03
* Archív .war vytvoříte v pravém panelu Maven Projects -> Lifecycle -> clean a potom Maven Projects -> Lifecycle ->
  package.
* Pozor! Mezi zdrojovým projektem (složkou) a výsledným webovým archívem .war je velký rozdíl. Do Tomcatu se nasazuje
  výsledný archív .war, do odevzdávárny na Google Drivu se nahrává zazipovaná složka celého projektu.
* Do Tomcatu se NIKDY nekopíruje rozbalená složka webu, pouze archív .war. Tomcat si tento archív sám rozbalí.
* Pokud se chcete zbavit nasazené webové aplikace z Tomcatu a máte ho spuštěný, smažte pouze archív .war ve složce
  TOMCAT/webapps. Nemažte rozbalenou složku webu. Tomcat sám pozná, že jste odebrali zdrojový archív .war a rozbalenou
  složku smaže sám. To slouží zároveň jako potvrzení, že byla webová aplikace úspěšně sesazena. Pouze pokud byste měly
  Tomcat zastavený, smažte i rozbalenou složku v TOMCAT/webapps.
* Pro psaní doporučuji používat javový projekt se zabudovaným malým Tomcatem, který spustíte klasicky pomocí zelené
  šipky. Adresa vašeho webu je potom http://localhost:8080/ukol03. Případně místo /ukol03 to, co jste uvedly v
  application.properties -> server.context-path. V tomto případě stačí pouze editovat zdrojové soubory webu a obnovovat
  stránku v prohlížeči.
* Pro zajímavost, jméno výsledného archívu .war se nastavuje v PROJEKT/pom.xml -> /project/build/finalName.

### Odevzdání domácího úkolu

**Před odevzdáním úkolu smažte z projektu složku *target*!**

Domácí úkol (celou složku s projektem, ne jen výsledný webový archív .war!) zabalte pomocí 7-Zipu pod jménem
**Ukol03-Vase_Jmeno.7z**. (Případně lze použít prostý zip, například na Macu). Takto vytvořený archív nahrajte na Google
Drive do složky Ukol03.

Takto vytvořený archív .war (ukol03.war) nasaďte do vašeho lokálního Tomcatu (JAVA-TRAINING/Tomcat/webapps) a
vyzkoušejte, že funguje (http://localhost:8080/ukol03/).

Vytvořte snímek obrazovky spuštěného programu a pochlubte se s ním
v galerii na Facebooku.

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě), zabalte ji a nahrajte ji na stejný Google Drive znovu, jen
tentokrát se jménem **Ukol03-Vase_Jmeno-verze2.7z**.
