SELECT * FROM "ORDB".alumnes;
SELECT * FROM "ORDB".cicles;
SELECT * FROM "ORDB".assignatures;
SELECT * FROM "ORDB".matricula;
DELETE FROM "ORDB".alumnes WHERE NIF = '12345678F';
DELETE FROM "ORDB".cicles WHERE codi = 1;
DELETE FROM "ORDB".assignatures WHERE codi = 1;

-- Insereix a la base de dades anterior dos alumnes
INSERT INTO "ORDB".alumnes (NIF, nom, llinatges, contacte, baixa) VALUES ('12345678F', 'Macia', 'Porcel', ROW('123456789','mpc@gmail.com', 'mpcTwitter'), false);
INSERT INTO "ORDB".alumnes VALUES ('123123123R', 'Laura', 'Guirado', ROW('669669669', 'lau@gmail.com', 'lauTwitter'), false);
INSERT INTO "ORDB".alumnes VALUES ('12352167R', 'Joan', 'Rigo', ROW('555555555', 'Joan@gmail.com', null), false);

-- dues assignatures
INSERT INTO "ORDB".cicles (codi, nom) VALUES (1, 'DAW01');  
INSERT INTO "ORDB".cicles (codi, nom) VALUES (2, 'DAM01');
INSERT INTO "ORDB".cicles (codi, nom) VALUES (3, 'DAW02');
INSERT INTO "ORDB".cicles (codi, nom) VALUES (4, 'DAM02');

INSERT INTO "ORDB".assignatures (codi,	nom, codi_cicle) VALUES (1, 'AD', 4);
INSERT INTO "ORDB".assignatures (codi, nom, codi_cicle) VALUES (2, 'DI', 4);

-- les notes d'aquests alumnes a aquestes assignatures
INSERT INTO "ORDB".matricula (nif_alumne, codi_assignatura, notes) VALUES ('12345678F', 1, ARRAY[7,8,9]); -- Macia en AD(1)
INSERT INTO "ORDB".matricula (nif_alumne, codi_assignatura, notes) VALUES ('12345678F', 2, ARRAY[10,7,8]); -- Macia en DI(2)
INSERT INTO "ORDB".matricula (nif_alumne, codi_assignatura, notes) VALUES ('123123123R', 1, ARRAY[10,10,10]); -- Laura en AD(1)
INSERT INTO "ORDB".matricula (nif_alumne, codi_assignatura, notes) VALUES ('123123123R', 2, ARRAY[10,10,10]); -- Laura en DI(2)

--Realitza les següents consultes:
--Recupera tots els alumnes de la base de dades.
SELECT * FROM "ORDB".alumnes;

--Mostra totes les assignatures d'un cicle determinat.
SELECT * FROM "ORDB".assignatures WHERE codi_cicle = 4;

--Recupera tots els alumnes d'un cicle determinat.
SELECT * 
FROM "ORDB".alumnes
JOIN "ORDB".matricula ON "ORDB".alumnes.NIF = "ORDB".matricula.nif_alumne
JOIN "ORDB".assignatures ON "ORDB".assignatures.codi = "ORDB".matricula.codi_assignatura
JOIN "ORDB".cicles ON "ORDB".cicles.codi = "ORDB".assignatures.codi_cicle
WHERE "ORDB".cicles.nom = 'DAM02';

--Recupera totes les notes d'un alumne d'una assignatura determinada.
SELECT "ORDB".matricula.notes 
FROM "ORDB".matricula
JOIN "ORDB".alumnes ON "ORDB".alumnes.NIF = "ORDB".matricula.nif_alumne
JOIN "ORDB".assignatures ON "ORDB".assignatures.codi = "ORDB".matricula.codi_assignatura
WHERE "ORDB".assignatures.nom = 'AD' AND "ORDB".alumnes.nom = 'Macia';

--Afegeix una nota d'un alumne per una assignatura determinada.
UPDATE "ORDB".matricula
SET notes = array_append(notes, 4)
WHERE nif_alumne = '12345678F'
AND codi_assignatura = (SELECT codi FROM "ORDB".assignatures WHERE nom = 'AD');

--Mostra tots els alumnes que tenguin alguna nota inferior a 5 per una assignatura determinada.
SELECT "ORDB".alumnes.nom, "ORDB".matricula.notes 
FROM "ORDB".matricula
JOIN "ORDB".alumnes ON "ORDB".alumnes.NIF = "ORDB".matricula.nif_alumne
JOIN "ORDB".assignatures ON "ORDB".assignatures.codi = "ORDB".matricula.codi_assignatura
WHERE 5 > ANY("ORDB".matricula.notes)
AND "ORDB".assignatures.nom = 'AD';

--Cerca un alumne per el seu correu.
SELECT "ORDB".alumnes.nom, "ORDB".alumnes.contacte
FROM "ORDB".alumnes
WHERE ("ORDB".alumnes.contacte).email = 'mpc@gmail.com';

--Mostra els alumnes que no tenguin twitter.
SELECT "ORDB".alumnes.nom, "ORDB".alumnes.contacte
FROM "ORDB".alumnes
WHERE ("ORDB".alumnes.contacte).twitter IS NULL;

--Corregeix el telèfon d'un alumne.
UPDATE "ORDB".alumnes
SET contacte = ROW('1000015', (contacte).email, (contacte).twitter)
WHERE "ORDB".alumnes.nom = 'Joan';