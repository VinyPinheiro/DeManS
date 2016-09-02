DROP DATABASE IF EXISTS DeManS_db;
CREATE DATABASE DeManS_db;

use DeManS_db;

CREATE USER 'DeManS' IDENTIFIED BY 'demolay';
GRANT ALL PRIVILEGES ON DeManS_db.* TO 'DeManS';

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
CONSTRAINT member_address_FK FOREIGN KEY(address_code) REFERENCES ADDRESS (code),
CONSTRAINT member_PK PRIMARY KEY(id)
)ENGINE = InnoDb DEFAULT CHARSET utf8;
