DROP TYPE "ORDB".contacte;
DROP TABLE "ORDB".alumnes;
DROP TABLE "ORDB".cicles;
DROP TABLE "ORDB".assignatures;
DROP TABLE "ORDB".matricula

--DROP SCHEMA ORDB;
--CREATE SCHEMA ORDB;

CREATE TYPE "ORDB".contacte AS (
	telefon TEXT,
	email TEXT,
	twitter TEXT
);

CREATE TABLE "ORDB".alumnes(
	NIF TEXT PRIMARY KEY,
	nom TEXT,
	llinatges TEXT,
	contacte ORDB.contacte, -- Usa el tipo compuesto
	baixa BOOLEAN DEFAULT FALSE
);

CREATE TABLE "ORDB".cicles(
	codi INT PRIMARY KEY,
	nom TEXT
);

CREATE TABLE "ORDB".assignatures(
	codi int PRIMARY KEY,
	nom TEXT,
	codi_cicle INT, -- Clave foranea de cicles
	FOREIGN KEY (codi_cicle) REFERENCES "ORDB".cicles (codi)
);

CREATE TABLE "ORDB".matricula(
	nif_alumne TEXT,
	codi_assignatura INT,
	notes INTEGER[], --Almacenar las notas
	PRIMARY KEY (nif_alumne, codi_assignatura), --PK compuesta
	FOREIGN KEY (nif_alumne) REFERENCES "ORDB".alumnes (NIF),
	FOREIGN KEY (codi_assignatura) REFERENCES "ORDB".assignatures (codi)
);