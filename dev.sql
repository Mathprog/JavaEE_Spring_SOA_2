SET character_set_client = utf8mb4 ;
CREATE TABLE javaee.reservation (
id int NOT NULL,
ouvrage_id int  NOT NULL,
usager_id int  NOT NULL,
date_reservation DATETIME NOT NULL,
date_limite DATE,
PRIMARY KEY(id),
CONSTRAINT FK_ouvrage_reservation FOREIGN KEY (ouvrage_id) REFERENCES javaee.ouvrage(id),
CONSTRAINT FK_usager_reservation FOREIGN KEY (usager_id) REFERENCES javaee.usager(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;