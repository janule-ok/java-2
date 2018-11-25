Lekce 07
========

Založení projektu od začátku
----------------------------

### Obsah

* Dědičnost v Javě
* Maven (IntelliJ IDEA -> Maven -> JDK)
* Spring: Spring Framework, Spring MVC, Spring Boot
* start.spring.io
* Naše webová aplikace založená od začátku pomocí průvodce

### Videozáznam

Na YouTube se můžete podívat na [záznam z lekce](https://www.youtube.com/watch?v=IIuPw9AoWZE),
případně si prohlédnout [celý playlist](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ6mcuJ1VaY8s0mzFsaMUzp-).

Úkol - Daily Planet
-------------------

Vytvořte webovou aplikaci, podle dodaných podkladů z hodiny
**JAVATRAINING/Projects/WebLekce07/30-Daily_Planet-Podklady/Java-Pokrocilejsi**.

Naopak **Java-jednodussi** prosím ignorujte. Původně to bylo připraveno na hodinu, abychom ten příklad rychle dokončili.

V podkladech jsou webové stránky. Už s ThymeLeafem, abych vás nezatěžoval převodem. Dále je tam k dispozici pár javových
tříd, které můžete použít, pokud budete chtít. Vyjděte z vlastní naklikané šablony projektu, jak jsme si ji stáhli z
webu [start.spring.io](https://start.spring.io/). Srovnejte, v čem se lišší moje šablona aplikace oproti vaší
naklikané. Přidejte si chybějící featury do vaší appky ručně, ideálně jednu po druhé, abyste si všimly, co a proč jsem
do svojí šablony přidal.

Při zakládání šablony na webu start.spring.io se do pom.xml vygenerovala deklarace staré knihovny ThymeLeaf 2. My bychom
chtěli používat ThymeLeaf 3. Proto si do pom.xml přideklarujte novější verzi ThymeLeafu do /project/properties:

    <thymeleaf.version>3.0.2.RELEASE</thymeleaf.version>
    <thymeleaf-layout-dialect.version>2.1.1</thymeleaf-layout-dialect.version>

Dále bychom chtěli v ThymeLeafu používat přídavek pro zpracovávání datumu a času LocalDate a LocalTime.

    <dependency>
        <groupId>org.thymeleaf.extras</groupId>
        <artifactId>thymeleaf-extras-java8time</artifactId>
        <version>3.0.1.RELEASE</version>
        <exclusions>
            <exclusion>
                <groupId>ognl</groupId>
                <artifactId>ognl</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

### Návrhový vzor Repository

Práci s články by bylo dobré převést do samostatné třídy, která bude sama udržovat seznam článků a navenek bude umět
všechny články poskytnout (metoda **findAll()**), a dále jeden článek vyhledat podle ID (metoda **findOne(...)**),
přidat nebo updatovat (metoda **save(...)**) a smazat (**remove(...)**).

Metoda **save(...)** bude fungovat tak, že se pokusí záznam najít podle ID v seznamu. Pokud ho tam najde, updatuje
seznam, pokud ho tam nenajde, přidá záznam na konec seznamu.

Kostra takové třídy by mohla vypadat takto:

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

Pár tipů:

* Nastavte si PROJEKT/src/main/resources/application.properties -> server.context-path = /ukol07, aby Spring Boot věděl,
  že chcete spouštět zabudovaný Tomcat tak, že appka bude k dispozici na http://localhost:8080/ukol07.
* Aby se vám při Maven Projects -> package vytvořil soubor .war se správným jménem, nastavte si ještě PROJEKT/pom.xml ->
  /project/build/finalName na ukol07

### Odevzdání domácího úkolu

**Před odevzdáním úkolu smažte z projektu složku *target*!**

Domácí úkol (celou složku s projektem, ne jen výsledný webový archív .war!) zabalte pomocí 7-Zipu pod jménem
**Ukol07-Vase_Jmeno.7z**. (Případně lze použít prostý zip, například na Macu). Takto vytvořený archív nahrajte na Google
Drive do složky Ukol07.

Vytvořte archív .war v IntelliJ IDEA -> Maven Projects -> ukol -> Lifecycle -> clean a následně IntelliJ IDEA -> Maven
Projects -> ukol -> Lifecycle -> package. Goal "package" vytvoří archív .war v PROJEKT/target/ukol06.war. Nasaďte jej do
vašeho lokálního Tomcatu (JAVA-TRAINING/Tomcat/webapps) a vyzkoušejte, že funguje (http://localhost:8080/ukol07/).

Po odladění nasaďte tento archív ještě na server Tomcat.cloud.

Vytvořte snímek obrazovky spuštěného programu a pochlubte se s ním v galerii
na Facebooku.

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě), zabalte ji a nahrajte ji na stejný Google Drive znovu, jen
tentokrát se jménem **Ukol07-Vase_Jmeno-verze2.7z**.
