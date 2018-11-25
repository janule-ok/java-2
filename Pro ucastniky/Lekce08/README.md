Lekce 08
========

Síťová vrstva TCP/IP, HTTP, Databázový server
---------------------------------------------

### Obsah

* Poznámky k domácí úloze
* Vzor Repository
* Síťová vrstva
  * IP (protokol), IP adresa, IP port
  * TCP
  * HTTP
  * Chrome, Developer Tools a Network
  * MySQL protokol
* Databázový server MySQL / MariaDB
* Řádkový klient mysql.exe
* IntelliJ IDEA jako klient

### Soubory

Instalaci MySQL / MariaDB na lekci najdete zde:
[MySQL-macOS.zip](http://javabrno.czechitas.cz/install/2018-jaro/install-ultimate/MySQL-macOS.zip),
[MySQL-Windows.7z](http://javabrno.czechitas.cz/install/2018-jaro/install-ultimate/MySQL-Windows.7z).

### Videozáznam

Na YouTube se můžete podívat na [záznam z lekce](https://www.youtube.com/watch?v=-7nzXIdP6ZA),
případně si prohlédnout [celý playlist](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ6mcuJ1VaY8s0mzFsaMUzp-).

Úkol - Daily Planet
-------------------

Vytvořte webovou aplikaci pro CRUD operace nad entitou **Clanek**. Vyjděte ze šablony projektu, jak jsme si ji na hodině
vytvořili pomocí [Spring Initializr](https://start.spring.io/). (Pokud byste potřebovali projekt vytvořit znovu,
použijte návod popsaný v dokumentu na Drivu.)

Jako základ řešení použijte podklady z
[Lekce07/30-Daily_Planet-Podklady/Java-Pokrocilejsi](../Lekce07/30-Daily_Planet-Podklady/Java-Pokrocilejsi) (javovské
třídy) a [Lekce07/30-Daily_Planet-Podklady/Web](../Lekce07/30-Daily_Planet-Podklady/Web) (šablony pro Thymeleaf). Pomocí
nich vytvořte aplikaci, pomocí které bude možné prohlédnout si seznam článků, vytvořit nový článek, modifikovat
existující článek a smazat existující článek. Pokud budete chtít, vytvořte i samostatnou obrazovku pro detail článku
(dodané podklady aktuálně předpokládají, že detail článku je zároveň i editací článku).

V dodané třídě **Clanek** naleznete jednu zajímavou funkčnost, o které jsme na hodině nemluvili. Třída obsahuje
statickou (tedy takovou, která je společná všem instancím třídy) proměnnou **idSequence**, která slouží k bezpečnému
přiřazování identifikátorů každé vytvořené instanci článku. Vždy při konstrukci libovolného článku dojde k přiřazení
identifikátoru článku, a to bez ohledu na to, který z konstruktorů (jsou hned tři) použijeme. V jednom z nich sami
určíme konkrétní identifikátor (chceme-li), ve zbývajících dvou se vezme příští hodnota ze sekvence.

Se seznamem článků nechceme (jako v minulosti) manipulovat přímo v rámci kontroleru. Místo toho použijeme návrhový vzor
*repository*. Pro připomenutí, kostra naší repository vypadá takto:

```java
public class ClanekRepository {

    private List<Clanek> seznamClanku = new ArrayList<>(Arrays.asList(
            new Clanek("Boss podsvětí dostal 20 let", "Clark Kent", LocalDate.of(2018, 1, 31)),
            new Clanek("Lékaři doporučují opatrnost", "Lois Lane", LocalDate.of(2018, 2, 28)),
            new Clanek("Bezkontaktní karty lákají zloděje", "Perry White", LocalDate.of(2017, 12, 24)),
            new Clanek("Ministryně navštívila problematické předměstí", "Jimmy Olsen", LocalDate.of(2016, 7, 31)),
            new Clanek("Soutěž o lístky na fotbal", "Cat Grant", LocalDate.of(2016, 8, 1)),
            new Clanek("Vrah prodavačky je ve vazbě", "Ron Troupe", LocalDate.of(2017, 10, 28))
    ));

    public synchronized List<Clanek> findAll() {
        // nejaky kod
    }

    public synchronized Clanek findOne(Long id) {
        // nejaky kod
    }

    public synchronized Clanek save(Clanek zaznamKUlozeni) {
        // nejaky kod
    }

    public synchronized void delete(Long id) {
        // nejaky kod
    }
}
```

*Poznámka:* Slovo **synchronized** zajišťuje tzv. vláknovou bezpečnost metod. Tedy to, že do metod v
**ClanekRepository** může vstoupit souběžně pouze 1 požadavek (a měnit interní **List**). To je na webovém serveru
důležité, protože k aplikaci přistupuje souběžně více prohlížečů / uživatelů.

V **HlavnimController**u tuto repository normálně instanciujte a nahraďte tím původní deklaraci **List**u s články. V
budoucnu to vylepšíme, ale teď to stačí takhle.

```java
@Controller
public class HlavniController {

    private ClanekRepository dodavatelDat = new ClanekRepository();

    // Metody s RequestMappingy

}
```

Ukázkový web můžete vidět na [https://margot.tomcat.cloud/ukol07/](https://margot.tomcat.cloud/ukol07/).

### Pár tipů

* Nastavte si PROJEKT/src/main/resources/application.properties -> server.context-path = /ukol08, aby Spring Boot věděl,
  že chcete spouštět zabudovaný Tomcat tak, že appka bude k dispozici na http://localhost:8080/ukol08.
* Aby se vám při Maven Projects -> package vytvořil soubor .war se správným jménem, nastavte si ještě PROJEKT/pom.xml ->
  /project/build/finalName na ukol08

### Odevzdání domácího úkolu

**Před odevzdáním úkolu smažte z projektu složku *target*!**

Domácí úkol (celou složku s projektem, ne jen výsledný webový archív .war!) zabalte pomocí 7-Zipu pod jménem
**Ukol08-Vase_Jmeno.7z**. (Případně lze použít prostý zip, například na Macu). Takto vytvořený archív nahrajte na Google
Drive do složky [Úkol 08](https://drive.google.com/drive/u/0/folders/1NfMorvBpchDsETTuA5-PIYFdoHo8AUeP).

Vytvořte archív .war v IntelliJ IDEA -> Maven Projects -> ukol -> Lifecycle -> clean a následně IntelliJ IDEA -> Maven
Projects -> ukol -> Lifecycle -> package. Goal "package" vytvoří archív .war v PROJEKT/target/ukol06.war. Nasaďte jej do
vašeho lokálního Tomcatu (JAVA-TRAINING/Tomcat/webapps) a vyzkoušejte, že funguje (http://localhost:8080/ukol08/).

Po odladění nasaďte tento archív ještě na server Tomcat.cloud.

Vytvořte snímek obrazovky spuštěného programu a pochlubte se s ním v galerii
[na Facebooku](https://www.facebook.com/media/set/?set=oa.701789553536276&type=3).

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě), zabalte ji a nahrajte ji na stejný Google Drive znovu, jen
tentokrát se jménem **Ukol08-Vase_Jmeno-verze2.7z**.
