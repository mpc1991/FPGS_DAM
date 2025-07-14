CREATE TABLE IF NOT EXISTS "ut5-practica".notificacio
(
    codi text COLLATE pg_catalog."default" NOT NULL,
    texte text COLLATE pg_catalog."default" NOT NULL,
    id_usuari integer NOT NULL,
    is_enviat boolean NOT NULL,
    metode text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT notificacioid_pkey PRIMARY KEY (codi)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "ut5-practica".notificacio
    OWNER to mporcel;