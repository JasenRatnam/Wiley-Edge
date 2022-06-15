#Jasen Ratnam
#2022/06/14
#remove an existing database if it exists, to make sure database is empty
DROP DATABASE IF EXISTS books;

#Create database
CREATE DATABASE books;
SHOW DATABASES;

#make it the active database
USE books;

#author 
CREATE TABLE author (
    authorId INT AUTO_INCREMENT,
    firstName VARCHAR(25) NOT NULL,
    middleName VARCHAR(25),
    lasyName VARCHAR(50) NOT NULL,
    gender VARCHAR(1),
    dateOfBirth DATETIME NOT NULL,
    dateOfDeath DATETIME,
    CONSTRAINT pk_author 
        PRIMARY KEY (authorId)
);
DESCRIBE author;

#book 
CREATE TABLE book (
    bookId INT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    publicationDate DATETIME,
    CONSTRAINT pk_book 
        PRIMARY KEY (bookId)
);
DESCRIBE book;

#format 
CREATE TABLE format (
    formatId INT AUTO_INCREMENT,
    formatName VARCHAR(12) NOT NULL,
    CONSTRAINT pk_format
        PRIMARY KEY (formatId)
);
DESCRIBE format;

#genre 
CREATE TABLE genre (
    genreId INT AUTO_INCREMENT,
    genreName VARCHAR(25) NOT NULL,
    CONSTRAINT pk_genre
        PRIMARY KEY (genreId)
);
DESCRIBE genre;

#authorBook
DROP TABLE IF EXISTS authorBook;
CREATE TABLE authorBook (
    authorId  INT,
    bookId  INT,
    CONSTRAINT pk_authorBook
    	PRIMARY KEY (authorId, bookId),
    CONSTRAINT fk_authorBook_author
    	FOREIGN KEY (authorId)
    	REFERENCES author(authorId),
    CONSTRAINT fk_authorBook_book
    	FOREIGN KEY (bookId)
    	REFERENCES book(bookId)
);
DESCRIBE authorBook;

#bookFormat
DROP TABLE IF EXISTS bookFormat;
CREATE TABLE bookFormat (
    bookId  INT,
    formatId  INT,
    price DOUBLE,
    quantityOnHand INT,
    CONSTRAINT pk_bookFormat
    	PRIMARY KEY (bookId, formatId),
    CONSTRAINT fk_bookFormat_book
    	FOREIGN KEY (bookId)
    	REFERENCES book(bookId),
    CONSTRAINT fk_bookFormat_format
    	FOREIGN KEY (formatId)
    	REFERENCES format(formatId)
);
DESCRIBE bookFormat;

#bookGenre
DROP TABLE IF EXISTS bookGenre;
CREATE TABLE bookGenre (
    bookId  INT,
    genreId  INT,
    CONSTRAINT pk_bookGenre
    	PRIMARY KEY (bookId, genreId),
    CONSTRAINT fk_bookGenre_book
    	FOREIGN KEY (bookId)
    	REFERENCES book(bookId),
    CONSTRAINT fk_bookGenre_genre
    	FOREIGN KEY (genreId)
    	REFERENCES genre(genreId)
);
DESCRIBE bookGenre;

SHOW tables;