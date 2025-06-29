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
    id INT AUTO_INCREMENT PRIMARY KEY,
    lendingUsername VARCHAR(20) NOT NULL references Users(username),
    loanObjectName VARCHAR(100) NOT NULL,
    loanDescription VARCHAR(100) NOT NULL,
    loanInterval VARCHAR(100) NOT NULL -- This emulates the LoanIntervalEnum. It must always be one of these strings: HOUR, DAY, WEEK, MONTH
);

INSERT INTO LoanPost (lendingUsername, loanObjectName, loanDescription, loanInterval) VALUES
('alice', 'Calcolatrice scientifica', 'Presto calcolatrice scientifica completamente funzionante. Non è grafica', 'DAY'),
('bob', 'Apppunti ISPW', 'Appunti utilissimi per passare il corso di ISPW con 30, lode e bacio accademico', 'WEEK');


-- -----------------------------------------------------
-- Utenti del DB
-- -----------------------------------------------------
DROP USER IF EXISTS 'login';
CREATE USER 'login' IDENTIFIED BY 'login';
GRANT SELECT ON Progetto_ISPW.Users TO 'login';

DROP USER IF EXISTS 'attivo';
CREATE USER 'attivo' IDENTIFIED BY 'attivo';
GRANT SELECT, UPDATE, DELETE, CREATE, INSERT ON Progetto_ISPW.* TO 'attivo';