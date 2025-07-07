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
('alice', 'alice', 3, 15000),
('bob', 'bob', 2, 2000),
('carlos', 'carlos', 1, 100),
('system', 'system', NULL, NULL);


-- -----------------------------------------------------
-- Table Progetto_ISPW.Notification
-- -----------------------------------------------------

CREATE TABLE Notification (
    senderUsername VARCHAR(20) NOT NULL references Users(username),
    receiverUsername VARCHAR(20) NOT NULL references Users(username),
    message VARCHAR(100) NOT NULL,
    seen BOOLEAN NOT NULL DEFAULT FALSE
);

USE Progetto_ISPW ;

INSERT INTO Notification (senderUsername, receiverUsername, message, seen) VALUES
('alice', 'bob', 'I accepted your loan request!', FALSE),
('alice', 'carlos', 'I declined your loan request.', FALSE),
('bob', 'alice', 'I returned the object I borrowed!', FALSE),
('bob', 'carlos', 'I returned the object I borrowed!', FALSE),
('system', 'alice', 'New discounts have been added!', FALSE),
('system', 'bob', 'Loan an item !', FALSE);



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
('alice', 'Calcolatrice', 'Calcolatrice scientifica perfettamente funzionante.', 'DAY', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Calcolatrice.png'),
('alice', 'Zaino', 'Zaino capiente.', 'WEEK', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/ZainoTrekking.png'),
('bob', 'Appunti ISPW', 'Per superare il corso con 30L e bacio accademico.', 'WEEK', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Appunti.png'),
('bob', 'Cavo HDMI', 'Cavo HDMI to HDMI.', 'DAY', '/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/CavoHDMI.png'),
('carlos', 'Matita', 'Temperata', 'MONTH', '');


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
('bob', 'alice', 'Calcolatrice'),
('bob', 'alice', 'Zaino'),
('carlos', 'bob', 'Cavo HDMI'),
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

INSERT INTO LoanEffective (borrowingUsername, lendingUsername, loanObjectName) VALUES
('bob', 'alice', 'Borsa per computer'),
('alice', 'bob', 'Temperino'),
('carlos', 'bob', 'Borraccia'),
('carlos', 'alice', 'Appunti Chimica');

-- -----------------------------------------------------
-- Table Progetto_ISPW.Discount
-- -----------------------------------------------------

CREATE TABLE Discount (
    name VARCHAR(100) PRIMARY KEY,
    pathToImage VARCHAR(100) NOT NULL,
    percentage INT NOT NULL,
    cost INT NOT NULL,
    ownerUsername VARCHAR(20) references Users(username)
);

INSERT INTO Discount (name, pathToImage, percentage, cost, ownerUsername) VALUES
('SummerSale', '/images/summer.png', 20, 100, NULL),
('WinterBlowout', '/images/winter.png', 30, 150, NULL),
('Paninozzo', '/images/Panino.png', 20, 100, NULL),
('Bibita', '/images/Bibita.png', 20, 100, NULL),
('BlackFriday', '/images/blackfriday.jpg', 50, 200, 'alice'),
('CyberMonday', '/images/cyber.jpg', 40, 180, 'alice'),
('SpringFever', '/images/spring.jpg', 25, 120, 'bob');


-- -----------------------------------------------------
-- Utenti del DB
-- -----------------------------------------------------
DROP USER IF EXISTS 'login';
CREATE USER 'login' IDENTIFIED BY 'login';
GRANT SELECT ON Progetto_ISPW.Users TO 'login';

DROP USER IF EXISTS 'attivo';
CREATE USER 'attivo' IDENTIFIED BY 'attivo';
GRANT SELECT, UPDATE, DELETE, CREATE, INSERT ON Progetto_ISPW.* TO 'attivo';