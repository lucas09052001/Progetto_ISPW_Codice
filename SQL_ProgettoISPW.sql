-- -------------------------------------------------
-- Schema
-- -------------------------------------------------
DROP SCHEMA IF EXISTS Progetto_ISPW;
CREATE SCHEMA IF NOT EXISTS Progetto_ISPW;
USE Progetto_ISPW;

-- -----------------------------------------------------
-- Table Progetto_ISPW.Utenti
-- -----------------------------------------------------
DROP TABLE IF EXISTS Progetto_ISPW.Users;
CREATE TABLE IF NOT EXISTS Progetto_ISPW.Users (
  username VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  rating INT,
  points INT default 0,
  PRIMARY KEY (username));

USE Progetto_ISPW ;

INSERT INTO Progetto_ISPW.Users (username, password, rating, points) VALUES
('alice', 'alice', 4, 5000),
('bob', 'bob', 3, 1200);


-- -----------------------------------------------------
-- Table Progetto_ISPW.CustomNotification
-- -----------------------------------------------------

CREATE TABLE CustomNotification (
    id INT AUTO_INCREMENT PRIMARY KEY,
    senderUsername VARCHAR(20) NOT NULL references Users(username),
    receiverUsername VARCHAR(20) NOT NULL references Users(username),
    message VARCHAR(100) NOT NULL,
    seen BOOLEAN NOT NULL DEFAULT FALSE
);

USE Progetto_ISPW ;

INSERT INTO CustomNotification (senderUsername, receiverUsername, message, seen) VALUES
('alice', 'bob', 'Ciao Bob!', FALSE),
('bob', 'alice', 'Ciao Alice!', FALSE),
('alice', 'bob', 'Hai visto questo?', FALSE),
('bob', 'alice', 'Tutto ok!', FALSE),
('alice', 'bob', 'Chiamami.', FALSE);


-- -----------------------------------------------------
-- Table Progetto_ISPW.LoanPost
-- -----------------------------------------------------

CREATE TABLE LoanPost (
    lendingUsername VARCHAR(20) NOT NULL references Users(username),
    loanObjectName VARCHAR(100) NOT NULL,
    loanDescription VARCHAR(100) NOT NULL,
    loanInterval VARCHAR(100) NOT NULL, -- This emulates the LoanIntervalEnum. It must always be one of these strings: HOUR, DAY, WEEK, MONTH
    pathToImage VARCHAR(200) NOT NULL,
    primary key (lendingUsername, loanObjectName)
);

INSERT INTO LoanPost (lendingUsername, loanObjectName, loanDescription, loanInterval, pathToImage) VALUES
('alice', 'Calcolatrice scientifica', 'Calcolatrice scientifica perfettamente funzionante, non grafica.', 'DAY', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png'),
('alice', 'Zaino trekking', 'Zaino da escursione impermeabile, 50L.', 'WEEK', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png'),
('bob', 'Appunti ISPW', 'Appunti dettagliati per superare il corso con 30 e lode.', 'WEEK', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png'),
('bob', 'Set cacciaviti', 'Set completo di cacciaviti di precisione.', 'DAY', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png'),
('bob', 'Coda di cavallo', 'Per le persone senza una coda di cavallo', 'WEEK', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png'),
('bob', 'Monitor 24"', 'Monitor 24 pollici full HD, solo VGA.', 'MONTH', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png');


-- -----------------------------------------------------
-- Table Progetto_ISPW.LoanRequest
-- -----------------------------------------------------

CREATE TABLE LoanRequest(
    borrowingUsername VARCHAR(20) NOT NULL references Users(username),
    lendingUsername VARCHAR(100) NOT NULL,
    loanObjectName VARCHAR(100) NOT NULL,
	CONSTRAINT fk_LoanPost
	FOREIGN KEY (lendingUsername, loanObjectName)
	REFERENCES LoanPost (lendingUsername, loanObjectName) on delete cascade,
    primary key (borrowingUsername, lendingUsername, loanObjectName)
);

INSERT INTO LoanRequest (borrowingUsername, lendingUsername, loanObjectName) VALUES
('bob', 'alice', 'Calcolatrice scientifica'),
('alice', 'bob', 'Appunti ISPW');

-- -----------------------------------------------------
-- Table Progetto_ISPW.EffectiveLoan
-- -----------------------------------------------------

CREATE TABLE LoanEffective (
    borrowingUsername VARCHAR(20) NOT NULL REFERENCES Users(username),
    lendingUsername VARCHAR(20) NOT NULL REFERENCES Users(username),
    loanObjectName VARCHAR(100) NOT NULL,
    PRIMARY KEY (borrowingUsername, lendingUsername, loanObjectName)
);

-- -----------------------------------------------------
-- Utenti del DB
-- -----------------------------------------------------
DROP USER IF EXISTS 'login';
CREATE USER 'login' IDENTIFIED BY 'login';
GRANT SELECT ON Progetto_ISPW.Users TO 'login';

DROP USER IF EXISTS 'attivo';
CREATE USER 'attivo' IDENTIFIED BY 'attivo';
GRANT SELECT, UPDATE, DELETE, CREATE, INSERT ON Progetto_ISPW.* TO 'attivo';