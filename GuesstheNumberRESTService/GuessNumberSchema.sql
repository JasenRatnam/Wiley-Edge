DROP DATABASE IF EXISTS GuessNumbersDB;
CREATE DATABASE GuessNumbersDB;
USE GuessNumbersDB;

CREATE TABLE game (
	gameId INT PRIMARY KEY AUTO_INCREMENT,
    answer char(4) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT false
    );


CREATE TABLE round (
	roundId INT PRIMARY KEY AUTO_INCREMENT,
    guess CHAR(4) NOT NULL,
    time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    result CHAR(7) NOT NULL DEFAULT "e:0:p:0",
    gameId INT NOT NULL,
    FOREIGN KEY fk_gameId (gameId) REFERENCES game(gameId)
    );

    