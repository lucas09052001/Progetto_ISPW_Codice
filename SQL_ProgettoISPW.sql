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
  username VARCHAR(45) NOT NULL,
  password VARCHAR(32) NOT NULL,
  rating INT,
  points INT default 0,
  PRIMARY KEY (username));

USE Progetto_ISPW ;

INSERT INTO Progetto_ISPW.Users (username, password, rating, points) VALUES
('alice', 'alice', 4, 5000),
('bob', 'bob', 3, 1200);


-- -----------------------------------------------------
-- Utenti del DB
-- -----------------------------------------------------
DROP USER IF EXISTS 'login';
CREATE USER 'login' IDENTIFIED BY 'login';
GRANT SELECT ON Progetto_ISPW.Users TO 'login';

DROP USER IF EXISTS 'attivo';
CREATE USER 'attivo' IDENTIFIED BY 'attivo';
GRANT SELECT, UPDATE, DELETE, CREATE ON Progetto_ISPW.* TO 'attivo';