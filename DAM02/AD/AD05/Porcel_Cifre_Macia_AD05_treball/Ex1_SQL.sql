-- Tipus de dades personalitzat per mantenir les dades de conexions
CREATE TYPE "ut5-practica".conection AS (
	sgdb TEXT, -- MySQL, PostgreSQL, Oracle, ...
	host TEXT, -- IP o domini
	port INT -- port(numèric)
);

-- Taula servidors
CREATE TABLE "ut5-practica".servidors (
	codi TEXT PRIMARY KEY,
	descriptiu TEXT NOT NULL,
	usuaris TEXT[] NOT NULL,
	servidor_actiu boolean NOT NULL,
	conection "ut5-practica".conection NOT NULL
)

-- Insereix tres servidors amb les dades completes
INSERT INTO "ut5-practica".servidors
VALUES 
(1, 'MySQL01', ARRAY['MPC', 'LGL'], TRUE, ROW('MySQL', '192.168.xxx.xxx', '5432')),
(2, 'PostgreSQL01', ARRAY['MPC', 'LGL'], FALSE, ROW('PostgreSQL', '192.168.xxx.xxx', '5432')),
(3, 'OracleDB01', ARRAY['MPC', 'LGL'], TRUE, ROW('Oracle', '192.168.xxx.xxx', '5432'));

SELECT * FROM "ut5-practica".servidors;

-- Afegeix un usuari nou a un servidor
UPDATE "ut5-practica".servidors
SET usuaris = array_append(usuaris, 'Jose')
WHERE servidors.codi = '1';

SELECT * FROM "ut5-practica".servidors;

-- Modifica un nom d'usuari a un servidor
UPDATE "ut5-practica".servidors
SET usuaris = array_replace(usuaris, 'Jose', 'Juan')
WHERE codi = '1';

SELECT * FROM "ut5-practica".servidors;

-- Modifica el port de la connexió d'un servidor
UPDATE "ut5-practica".servidors
SET conection = ROW((conection).sgdb, (conection).host, '12345')
WHERE codi = '1';

SELECT * FROM "ut5-practica".servidors;