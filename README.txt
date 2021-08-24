1. proyecto realizado en springToolsSuits
2. para ejecutar necesita una versiona de java 8 y se usa el servidor que viene por defecto
3. se crea una conexion a base de datos postgresql con la siguiente informacion:
	* nombre de base de datos: Demo
	* tabla con el nombre: mutant
	Script 
	
	CREATE TABLE public.mutant
(
    id_mutant numeric NOT NULL,
    adn character varying(1000) COLLATE pg_catalog."default",
    adn_mutant character varying(1000) COLLATE pg_catalog."default",
    mutante numeric(1,0),
    fecha_validacion date,
    CONSTRAINT id_mutant_pk PRIMARY KEY (id_mutant)
)

se crea la siguiente secuencia

CREATE SEQUENCE public.seq_mutant_pk
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000000
    CACHE 1;
	
4. el puerto para ejecutarlo es el 8888 las dos uri creadas son:
	metodo post: http://localhost:8888/mutant/
	metodo get: http://localhost:8888/mutant/stats
5. consumo desde postman
	["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
