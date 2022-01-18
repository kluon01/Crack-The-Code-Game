DROP DATABASE IF EXISTS guessingGame;
CREATE DATABASE guessingGame;

USE guessingGame;

CREATE TABLE game(
id INT PRIMARY KEY AUTO_INCREMENT,
answer VARCHAR(4),
finished BOOLEAN DEFAULT false);


CREATE TABLE round(
id INT PRIMARY KEY AUTO_INCREMENT,
gameid INT,
`time` timestamp,
result VARCHAR(10),
guess VARCHAR(4))