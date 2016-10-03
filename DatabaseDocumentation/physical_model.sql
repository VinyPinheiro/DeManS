use mysql;

DROP DATABASE IF EXISTS DeManS_db;
CREATE DATABASE demans_db;

use demans_db;

#CREATE USER 'demans' IDENTIFIED BY 'demolay';
#GRANT ALL PRIVILEGES ON demans_db.* TO 'demans';

CREATE TABLE UF (
initials VARCHAR(2) NOT NULL,
State VARCHAR(50) NOT NULL,
CONSTRAINT uf_PK PRIMARY KEY(initials)
)ENGINE = InnoDb DEFAULT CHARSET utf8;

CREATE TABLE CITY (
code INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
initials VARCHAR(2) NOT NULL,
CONSTRAINT city_PK PRIMARY KEY(code),
CONSTRAINT city_uf_FK FOREIGN KEY (initials) REFERENCES UF (initials) ON UPDATE RESTRICT ON DELETE RESTRICT
)ENGINE = InnoDb DEFAULT CHARSET utf8;


CREATE TABLE ADDRESS (
code INT NOT NULL AUTO_INCREMENT,
street VARCHAR(100) NOT NULL,
number INT NOT NULL,
complement VARCHAR(50) NULL,
zip_code VARCHAR(9) NOT NULL,
city_code INT NOT NULL,
CONSTRAINT address_PK PRIMARY KEY(code),
CONSTRAINT address_city_FK FOREIGN KEY (city_code) REFERENCES CITY (code) ON UPDATE RESTRICT ON DELETE RESTRICT
)ENGINE = InnoDb DEFAULT CHARSET utf8;


CREATE TABLE MEMBER (
id INT NOT NULL,
name VARCHAR(100) NOT NULL,
birthdate DATE NOT NULL,
password VARCHAR(200) NOT NULL,
phone VARCHAR(15) NOT NULL,
dad_phone VARCHAR(15) NOT NULL,
address_code INT NOT NULL,
degree Enum('Iniciatico', 'DeMolay', 'Macom') NOT NULL DEFAULT 'Iniciatico',
situation Enum('Ativo', 'Irregular', 'Senior', 'Pendente', 'Recusado') NOT NULL DEFAULT 'Pendente',
approved_by INT,
CONSTRAINT member_address_FK FOREIGN KEY(address_code) REFERENCES ADDRESS (code) ON UPDATE RESTRICT ON DELETE RESTRICT,
CONSTRAINT member_PK PRIMARY KEY(id)
)ENGINE = InnoDb DEFAULT CHARSET utf8;

CREATE TABLE NOMINATA (
code INT NOT NULL AUTO_INCREMENT,
year INT NOT NULL,
semester Enum('1','2') NOT NULL,
CONSTRAINT nominata_PK PRIMARY KEY(code),
CONSTRAINT nominata_UK UNIQUE(year,semester)
)ENGINE = InnoDb DEFAULT CHARSET utf8;

CREATE TABLE belongs (
office Enum('Mestre Conselheiro', '1 Conselheiro', '2 Conselheiro', 'Tesoureiro',
			'Escrivao', 'Orador', '1 Diacono', '2 Diacono', '1 Mordomo', '2 Mordomo', 'Hospitaleiro', 'Capelao',
			'Porta Bandeira', 'Sentinela', 'Mestre de Cerimonias', '1 Preceptor', '2 Preceptor', '3 Preceptor',
			'4 Preceptor', '5 Preceptor', '6 Preceptor', '7 Preceptor') NOT NULL,
code INT NOT NULL,
id INT NULL,
CONSTRAINT belongs_UK UNIQUE(office,code),
CONSTRAINT belongs1_UK UNIQUE(id,code),
CONSTRAINT belongs_to_nominata_FK FOREIGN KEY(code) REFERENCES NOMINATA (code) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT member_belongs_FK FOREIGN KEY(id) REFERENCES MEMBER (id) ON UPDATE RESTRICT ON DELETE RESTRICT
)ENGINE = InnoDb DEFAULT CHARSET utf8;