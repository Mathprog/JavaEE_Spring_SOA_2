CREATE TABLE javaee.usager 
(
id int NOT NULL AUTO_INCREMENT,
email varchar(255) NOT NULL UNIQUE,
PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE VIEW javaee.nbouvrage AS SELECT COUNT(*) FROM javaee.ouvrage;

CREATE TABLE javaee.ouvrage
(
id int NOT NULL auto_increment,
titre varchar(255) NOT NULL,
auteur CHAR(80),
resume varchar(500),
image char(80),
imageb BLOB,
PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE javaee.pret
(
id int NOT NULL auto_increment,
usager_id int NOT NULL,
exemplaire_id int NOT NULL,
date_pret date NOT NULL,
date_fin date NOT NULL,
PRIMARY KEY (id),
CONSTRAINT FK_pret_usager FOREIGN KEY (usager_id)
    REFERENCES javaee.usager(id),
CONSTRAINT FK_pret_exemplaire FOREIGN KEY (exemplaire_id)
    REFERENCES javaee.exemplaire(id)
) ENGINE=InnoDB;

CREATE TABLE javaee.relance
(
id int NOT NULL auto_increment,
pret_id int NOT NULL,
date_fin date NOT NULL,
PRIMARY KEY(id),
CONSTRAINT FK_relance_pret FOREIGN KEY (pret_id)
    REFERENCES javaee.pret(id)
) ENGINE=InnoDB;

CREATE TABLE javaee.exemplaire 
(
id int NOT NULL auto_increment,
ouvrage_id int NOT NULL,
PRIMARY KEY (id),
CONSTRAINT FK_ouvrage FOREIGN KEY (ouvrage_id)
    REFERENCES javaee.ouvrage(id)
)ENGINE = InnoDB;