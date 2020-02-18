# DROP DATABASE DailyPlanet;

CREATE DATABASE DailyPlanet
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_czech_ci;

USE DailyPlanet;

CREATE TABLE Clanky (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  Nazev VARCHAR(250),
  Autor VARCHAR(250),
  Datum DATE DEFAULT now() NOT NULL
);

INSERT INTO Clanky (Nazev, Autor, Datum) VALUES ('Lidé doma hromadí léky', 'Loise Lane', '2019-03-20');
INSERT INTO Clanky (Nazev, Autor, Datum) VALUES ('Nový japonský císaø', 'Clark Kent', '2019-04-30');
INSERT INTO Clanky (Nazev, Autor, Datum) VALUES ('Prodeje androidových telefonù rostou', 'Bob Harley', '2018-08-22');
INSERT INTO Clanky (Nazev, Autor, Datum) VALUES ('U pøehrady našli utonulého muže', 'Cat Grant', '2019-06-01');
