Lekce 04
========

Opakování
---------

### Videozáznam

Na YouTube se můžete podívat na [záznam z lekce](https://www.youtube.com/watch?v=CIh9hdJxtKk),
případně si prohlédnout [celý playlist](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ6mcuJ1VaY8s0mzFsaMUzp-).

Úkol - Telefonní seznam
-----------------------

Vytvořte jednoduchou webovou aplikaci, která bude zobrazovat tabulku se seznamem telefonních kontaktů.

Když se klikne na konkrétní řádek v tabulce, zobrazí se druhá stránka s detailem záznamu.

### Seznam kontaktů

Tabulku kontaktů včetně počtu a významu jednotlivých sloupečků si můžete navrhnout podle svého. Můj návrh vypadá takto:

<table>
<thead>
<tr><th>Osoba</th><th>Stát</th><th>Telefon</th><th>Akce</th></tr>
</thead>
<tbody>
<tr><td>Thomas Alva Edison</td><td>USA</td><td>+1-123-555-666</td><td>Zobrazit detail</td></tr>
<tr><td>Albert Einstein</td><td>Švýcarsko</td><td>+41 953 203 569</td><td>Zobrazit detail</td></tr>
</tbody>
<table>

Tabulku kontaktů vystavte na adrese: http://localhost:8080/ukol04/kontakty/

### Detail jednoho kontaktu

Dále připravte stránku, která bude zobrazovat detail zvoleného záznamu.

V detailu zobrazujte více údajů o každém záznamu než v přehledu. Jinými slovy, třída TelefonniKontakt bude mít nejen
vlastnosti, co jsou zobrazeny v souhranné tabulce, ale i další vlastnosti, které jsou vidět až v na stránce detailu.

Odkaz ze souhrnné tabulky bude pro každý záznam jiný. Může vypadat například takto:

* http://localhost:8080/ukol04/kontakty/100.html ... pro záznam s ID = 100
* http://localhost:8080/ukol04/kontakty/101.html ... pro záznam s ID = 101
* http://localhost:8080/ukol04/kontakty/102.html ... pro záznam s ID = 102

Jak udělat metodu **zobrazDetail()** citlivou na všechny adresy **/kontakty/100.html**, **/kontakty/101.html**, ...? V
lekci jsme si ukazovali **@RequestParam**. V domácím úkolu použijte podobnou techniku, jen pomocí **@PathVariable**.

```java
@RequestMapping("/kontakty/{cislo}.html")
public ModelAndView zobrazDetail(@PathVariable("cislo") Long cisloKontaktu)
```

Ukázkový web můžete vidět na [http://margot.tomcat.cloud/ukol04/](http://margot.tomcat.cloud/ukol04/).

Stránky musejí z prohlížeče fungovat jak při souborovém přístupu, tak při přístupu přes webový server (se Spring
Bootem).

### Pár tipů

* Konstanty typu long se zapisují s 'L' na konci. Například 123456L. Bez L na konci jde o konstantu typu int.
* Pro naše účely vytvořte třídu TelefonniKontakt, která bude mít například tyto vlastnosti:
    * **Long id**,
	* **String jmeno**,
	* **String mesto**,
	* **String stat**,
	* **String telefonniCislo**.
* Třídě TelefonniKontakt vytvořte i konstruktor, aby se její objekty snadno vyráběly: **public TelefonniKontakt(Long id,
  String pocatecniJmeno, String pocatecniStat, String pocatecniMesto, String pocatecniTelefonniCislo)**.
* Vyjděte ze šablony projektu z hodiny (Czechitas Web App Template v4). Složku si prostě okopírujte a otevřete ji v
  IntelliJ IDEA. Po otevření je nutné přejmenovat tato místa, kde je jméno a adresa aplikace uvedena v konfiguračních
  souborech:
    * PROJEKT/src/main/resources/application.properties -> server.context-path = /ukol04
    * PROJEKT/pom.xml -> /project/artifactId = ukol04
    * PROJEKT/pom.xml -> /project/name = ukol04
    * PROJEKT/pom.xml -> /project/build/finalName = ukol04
* Archív .war vytvoříte v pravém panelu Maven Projects -> Lifecycle -> clean a potom Maven Projects -> Lifecycle ->
  package.
* Pozor! Mezi zdrojovým projektem (složkou) a výsledným webovým archívem .war je velký rozdíl. Do Tomcatu se nasazuje
  výsledný archív .war, do odevzdávárny na Google Drivu se nahrává zazipovaná složka celého projektu.
* Do Tomcatu se NIKDY nekopíruje rozbalená složka webu, pouze archív .war. Tomcat si tento archív sám rozbalí.
* Pokud se chcete zbavit nasazené webové aplikace z Tomcatu a máte ho spuštěný, smažte pouze archív .war ve složce
  TOMCAT/webapps. Nemažte rozbalenou složku webu. Tomcat sám pozná, že jste odebrali zdrojový archív .war a rozbalenou
  složku smaže sám. To slouží zároveň jako potvrzení, že byla webová aplikace úspěšně sesazena. Pouze pokud byste měly
  Tomcat zastavený, smažte i rozbalenou složku v TOMCAT/webapps.

### Odevzdání domácího úkolu

**Před odevzdáním úkolu smažte z projektu složku *target*!**

Domácí úkol (celou složku s projektem, ne jen výsledný webový archív .war!) zabalte pomocí 7-Zipu pod jménem
**Ukol04-Vase_Jmeno.7z**. (Případně lze použít prostý zip, například na Macu). Takto vytvořený archív nahrajte na Google
Drive do složky Ukol04.

Takto vytvořený archív .war (ukol04.war) nasaďte do vašeho lokálního Tomcatu (JAVA-TRAINING/Tomcat/webapps) a
vyzkoušejte, že funguje (http://localhost:8080/ukol04/).

Vytvořte snímek obrazovky spuštěného programu a pochlubte se s ním
v galerii na Facebooku.

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě), zabalte ji a nahrajte ji na stejný Google Drive znovu, jen
tentokrát se jménem **Ukol04-Vase_Jmeno-verze2.7z**.
