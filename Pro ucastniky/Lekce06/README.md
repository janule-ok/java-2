Lekce 06
========

Ještě více formulářů
--------------------

### Seznamy a pole v Javě

V Javě existuje pole, které je nutno před použitím naalokovat na požadovanou velikost. Nelze do něj prvky přidávat. Má
prostě pevnou kapacitu.

Proto v Javě používáme **List** (přesněji **java.util.List**), který je podobný, ale dokáže si do sebe prvky přidávat,
měnit a odebírat. **List** v Javě má ve skutečnosti několik variant. Je to implementováno jako dědičnost.

Základní typ je List, od něj jsou odvozeny potomky **LinkedList** (přesněji **java.util.LinkedList**), **ArrayList**
(přesněji **java.util.ArrayList**), **CopyOnWriteArrayList** (přesněji **java.util.concurrent.CopyOnWriteArrayList**) a
jedna skytá třída, jejíž instance se alokuje v **Arrays.asList()**.

Když tedy deklarujeme:

    List<Uzivatel> seznamUzivatelu;

Můžeme do této proměnné vložit jakýkoliv z výše uvedených potomků **List**u.

**Arrays.asList()** je statická (globální) metoda v pomocné třídě **Arrays** (přesněji **java.util.Arrays**), které lze
předat prvky oddělené čárkou a ona je naskládá do **List**u. Zvláštní je v tom, že daný **List** nelze zvětšovat ani
zmenšovat. Je to obalené klasické pole v Javě, ale tváří se to jako **List**.

    private List<Uzivatel> nemenitelnySeznamUzivatelu = Arrays.asList(
        new Uzivatel("pokus@pokus.com", "password"),
        new Uzivatel("admin@tomcat.cloud", "password")
    );

**LinkedList**, **ArrayList** a **CopyOnWriteArrayList** jsou plnotučné varianty **List**u. Lišší se v tom, že:

* **LinkedList** je tzv. lineární spojový seznam (viz wikipedia).
* **ArrayList** si interně pamatuje prvky taky v poli, ale při přidávání (a odebírání) prvků si obsah svého interního
  pole dokáže okopírovat do druhého pole, které je větší. Takže se dokáže zvětšovat bez problémů.
* **CopyOnWriteArrayList** je velice podobný ArrayListu, ale je vláknově bezpečný, tzn. že do něj lze přidávat více
  prvků současně a on se v takové situaci nerozsype. To je na webovém serveru důležité, protože na něj chodí požadavky
  současně z mnoha prohlížečů.

Příklad použití CopyOnWriteArrayList:

    private List<Uzivatel> menitelnySeznamUzivatelu = new CopyOnWriteArraysList<>();

    // nekdy pozdeji, napr. v konstruktoru:
    menitelnySeznamUzivatelu.add(new Uzivatel("pokus@pokus.com", "password"));
    menitelnySeznamUzivatelu.add(new Uzivatel("admin@tomcat.cloud", "password"));

A třetí věc: Všechny **List**y dodržují to, že mají konstruktor, který akceptuje jako parametr jiný **List**, ze kterého
se vykopírují do tohoto nového **List**u všechny prvky.

Takže já používám konstrukci:

    private List<Uzivatel> seznamUzivatelu = new CopyOnWriteArrayList<>(
        Arrays.asList(
            new Uzivatel("pokus@pokus.com", "password"),
            new Uzivatel("admin@tomcat.cloud", "password")
        )
    );

### Videozáznam

Na YouTube se můžete podívat na [záznam z lekce](https://www.youtube.com/watch?v=ekrr4AZMhMM),
případně si prohlédnout [celý playlist](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ6mcuJ1VaY8s0mzFsaMUzp-).

Úkol - Přihlašovací dialog
--------------------------

Vytvořte webovou aplikaci, podle dodaných podkladů webových stránek
[60-Daily_Planet-Prihlasovani-Podklady](60-Daily_Planet-Prihlasovani-Podklady/).

V podkladech jsou pouze statické webové stránky, jak byste je mohli dostat od tvůrce webu. Vaším úkolem je tento
statický web předělat do naší šablony Spring Bootu a rozběhnout základní funkcionalitu.

### Popis stránek

* **index.html**. Úvodní stránka. Za normálních okolností by z ní šlo na **hlavni.html**. Nicméně jsme se rozhodli, že
  pro přístup na **hlavni.html** je nutné se nejprve přihlásit. Musí se tedy nejprve projít přes stránku
  **prihlaseni.html**.
* **prihlaseni.html**. Přihlašovací formulář. Po odeslání formuláře se na serveru zkontroluje zadaný email a
  heslo. Stačí, když bude aplikace akceptovat napevno email **pokus@pokus.com** a heslo **password**. Nezapomeňte, že
  **String**y se porovnávají pomocí **equals()**, nikoliv **==**. Tedy například
  **vyplnenyFormular.getPrihlasovaciJmeno().equals("pokus@pokus.com")**. V případě platného přihlášení se prohlížeč
  přesměruje na **hlavni.html**.  V případě neplatného přihlášení se znovu zobrazí původní stránka **prihlaseni.html** a
  chybová hláška. Je velmi vhodné, aby zůstal vyplněný email z minulého pokusu.
* **hlavni.html**. Stránka se seznamem. Buď použijte tu, která je ve statickém web designu, nebo jako bonus použijte
  aplikaci z hodiny na evidenci telefonního seznamu.
* **registrace.html**, **zapomenute-heslo.html**. Při přihlašování je typicky možné se i alternativně zaregistrovat a
  nebo si vyžádat email se zapomenutým heslem. Rozchoďte tyto formuláře. Stačí, když budou fungovat "na oko", ale ve
  skutečnosti žádný email posílat nebudou (zapomenute-heslo.html), ani neakceptují registraci nového uživatele
  (registrace.html) a ve skutečnosti zůstane jediný možný email a heslo stále jen **pokus@pokus.com** a
  **password**". Nepovinně bych ale velmi doporučoval zkusit si evidenci uživatelů naprogramovat.

*Poznámka:* Takto naprogramované přihlašování samozřejmě funguje jen při přístupu z **index.html**. Pokud by uživatel
přistoupil přímo k **hlavni.html**, nemusel by se přihlašovat. Tento problém neřešte a tvařme se, že neexistuje. Řešením
je tzv. **HttpSession** a vložení přihlášeného uživatele do této **HttpSession**. Stránka **prihlaseni.html** by se
potom zobrazovala automaticky, pokud by nebyl v **HttpSession** přihlášený uživatel.

Jednak by to ale bylo pracné, ale zároveň je mnohem lepší použít existující autentizační framework, například Spring
Security. Jeho složitost je ale daleko za rozsahem jednoho domácího úkolu.

Ukázkový web můžete vidět na [https://margot.tomcat.cloud/ukol06/](https://margot.tomcat.cloud/ukol06/).

Stránky musejí z prohlížeče fungovat jak při souborovém přístupu, tak při přístupu přes webový server (se Spring
Bootem).

### Pár tipů:

* Vyjděte buď z příkladu z hodiny (Telefonní seznam) nebo ze šablony projektu (Czechitas Web App Template v4). Složku si
  prostě okopírujte a otevřete ji v IntelliJ IDEA. Po otevření je nutné přejmenovat místa, kde je jméno a adresa
  aplikace uvedena v konfiguračních souborech:
    * PROJEKT/src/main/resources/application.properties -> server.context-path = /ukol06
    * PROJEKT/pom.xml -> /project/artifactId = ukol06
    * PROJEKT/pom.xml -> /project/name = ukol06
    * PROJEKT/pom.xml -> /project/build/finalName = ukol06

### Odevzdání domácího úkolu

**Před odevzdáním úkolu smažte z projektu složku *target*!**

Domácí úkol (celou složku s projektem, ne jen výsledný webový archív .war!) zabalte pomocí 7-Zipu pod jménem
**Ukol06-Vase_Jmeno.7z**. (Případně lze použít prostý zip, například na Macu). Takto vytvořený archív nahrajte na Google
Drive do složky Ukol06.

Vytvořte archív .war v IntelliJ IDEA -> Maven Projects -> ukol -> Lifecycle -> clean a následně IntelliJ IDEA -> Maven
Projects -> ukol -> Lifecycle -> package. Goal "package" vytvoří archív .war v PROJEKT/target/ukol06.war. Nasaďte jej do
vašeho lokálního Tomcatu (JAVA-TRAINING/Tomcat/webapps) a vyzkoušejte, že funguje (http://localhost:8080/ukol06/).

Po odladění nasaďte tento archív ještě na server Tomcat.cloud.

Vytvořte snímek obrazovky spuštěného programu a pochlubte se s ním v galerii na Facebooku.

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě), zabalte ji a nahrajte ji na stejný Google Drive znovu, jen
tentokrát se jménem **Ukol06-Vase_Jmeno-verze2.7z**.
