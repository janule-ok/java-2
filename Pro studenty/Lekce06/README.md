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

Na YouTube je k dispozici:
* [záznam z lekce (2019)](https://www.youtube.com/watch?v=HHWL4ByVWkQ)
* [záznam z lekce (2018)](https://www.youtube.com/watch?v=ekrr4AZMhMM)
* [záznam z lekce (2017)](https://www.youtube.com/watch?v=e6tmum7B9EQ)

Případně je k dispozici playlist všech lekcí:
* [Jaro 2020](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ5H1uPvwQYUkhQuznifLe-L)
* [Jaro 2019](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ7I5m_zJtjZoLS-pxSi859Z)
* [Jaro 2018](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ6mcuJ1VaY8s0mzFsaMUzp-)
* [Jaro 2017](https://www.youtube.com/playlist?list=PLUVJxzuCt9ATwP3dFn5xCHvObtu2EveNZ)
