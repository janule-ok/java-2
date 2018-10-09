Lekce 01
========

Webový prohlížeč a webový server
--------------------------------

### Osnova

1. Uvítání
1. Druhy aplikací otevřené pro běžné programátorky
    1. Mobilní aplikace
    1. Desktopové aplikace
    1. Webový prohlížeč (front-end)
    1. Webový server (back-end)
1. Webový server Tomcat
1. Nasazení první aplikace (VideoBoss.war)
1. Lokální Tomcat a localhost vs. veřejná adresa
1. Zabudovaný Tomcat v projektu (10-SimpleWeb)
1. Cloudový webhosting - Tomcat.cloud
1. FTP a nasazení na Tomcat.cloud

### Videozáznam

Na YouTube se můžete podívat na [záznam z lekce](https://www.youtube.com/watch?v=nTyohnDlxCc),
případně si prohlédnout [celý playlist](https://www.youtube.com/playlist?list=PLTCx5oiCrIJ6mcuJ1VaY8s0mzFsaMUzp-).

Úkol - Experimentování se statickým webem a Tomcatem
----------------------------------------------------

Cílem domácího úkolu je vytvořit jednoduchý statický web a nasadit je na Tomcat.

### Krátký popis

Úkolem bude připravit si svůj osobní web (například na úrovni jednodenního workshopu Tvořím web HTML/CSS
[https://www.czechitas.cz/cs/co-delame/tvorim-web](https://www.czechitas.cz/cs/co-delame/tvorim-web)). Webové stránky
(index.html, styly.css, ...) vytvořte v projektu v IntelliJ IDEA použitém na hodině.

### Detailní popis

Abychom měli s čím experimentovat, budeme potřebovat nějaký váš web. Může to klidně být i váš již existující web nebo
statické webové stránky odkudkoliv. Pokud se necítíte na tvorbu vlastního webu, můžete si klidně půjčit nějaký
existující web. Například [http://krystufek.rolecek.cz/](http://krystufek.rolecek.cz/). Pro psaní webu existuje na internetu spousta návodů.

Vyjděte z projektu, na kterém jsme pracovali na hodině (Lekce01/20-SimpleWeb). Stránky můžete během editování zobrazit
přímo v prohlížeči zadáním adresy (adresa se může mírně lišit dle umístění projektu):

Na windows: **file:///C:/Java-Training/Projects/Java-2/Lekce01/20-SimpleWeb/src/main/resources/static/index.html**

Na Macu: **file:///Users/VASE\_JMENO/Java-Training/Projects/Java-2/Lekce01/20-SimpleWeb/src/main/resources/static/index.html**

Výsledný WAR vyrobíte pomocí Build -> Rebuild Project (vznikne ve složce target). Chcete-li mít jistotu, že se vše
správně provedlo a WAR skutečně obsahuje aktuální verzi projektu, můžete zkontrolovat čas poslední modifikace
vytvořeného souboru (případně ještě před Rebuild Project můžete klidně smazat celou složku target).

Vytvořený WAR si zkuste nasadit na Tomcat (pro připomenutí: Tomcat spustíte pomocí startup.bat/startup.sh ve složce
Tomcat/bin v instalaci a na závěr ho ukončíte stisknutím Ctrl+C). Vyzkoušejte si nasazení jak pomocí webové aplikace
*manager*, tak pomocí ručního nakopírování do Tomcat/webapps.

Pár tipů:

* **Pozor! Mezi zdrojovým projektem (složkou) a výsledným webovým archívem .war je velký rozdíl. Do Tomcatu se nasazuje
  výsledný archív .war, do odevzdávárny na Google Drivu se nahrává zazipovaná složka celého projektu.**
* **Do Tomcatu se NIKDY nekopíruje rozbalená složka webu, pouze archív .war. Tomcat si tento archív sám rozbalí.**
* **Pokud se chcete zbavit nasazené webové aplikace z Tomcatu, smažte pouze archív .war ve složce
  TOMCAT/webapps. Nemažte rozbalenou složku webu. Tomcat sám pozná, že jste odebrali zdrojový archív .war a rozbalenou
  složku smaže sám. To slouží zároveň jako potvrzení, že byla webová aplikace úspěšně sesazena.**

### Odevzdání domácího úkolu

**Před odevzdáním úkolu smažte z projektu složky *lib* a *target*!**

Domácí úkol (složky s projektem) zabalte pomocí 7-Zipu pod jménem **Ukol01-Vase_Jmeno.7z**. (Případně lze použít prostý
zip, například na Macu). Takto vytvořený archív nahrajte na Google Drive do složky
[Úkol 01](https://drive.google.com/drive/u/0/folders/144o0S3liFsFuUerPjDWxdlQeYgBwjRuM).

Vytvořte snímek obrazovky spuštěného programu a pochlubte se s ním
[v galerii na Facebooku](https://www.facebook.com/media/set/?set=oa.677094292672469&type=3).

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě), zabalte ji a nahrajte ji na stejný Google Drive znovu, jen
tentokrát se jménem **Ukol01-Vase_Jmeno-verze2.7z**.
