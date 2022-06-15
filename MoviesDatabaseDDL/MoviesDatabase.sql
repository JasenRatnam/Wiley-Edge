#Jasen Ratnam
#2022/06/14
#remove an existing database if it exists, to make sure database is empty
DROP DATABASE IF EXISTS movies;

#Create database
CREATE DATABASE movies;
SHOW DATABASES;

#make it the active database
USE movies;

#genre 
CREATE TABLE genre (
    genreId INT AUTO_INCREMENT,
    genreName VARCHAR(30) NOT NULL,
    CONSTRAINT pk_genre
        PRIMARY KEY (genreId)
);
DESCRIBE genre;

#director 
CREATE TABLE director (
    directorId INT AUTO_INCREMENT,
    directorFirstName VARCHAR(30) NOT NULL,
    directorLastName VARCHAR(30) NOT NULL,
    directorBirthDate DATETIME,
    CONSTRAINT pk_director
        PRIMARY KEY (directorId)
);
DESCRIBE director;

#rating 
CREATE TABLE rating (
    ratingId INT AUTO_INCREMENT,
    ratingName VARCHAR(5) NOT NULL,
    CONSTRAINT pk_rating
        PRIMARY KEY (ratingId)
);
DESCRIBE rating;

#actor 
CREATE TABLE actor (
    actorId INT AUTO_INCREMENT,
    directorFirstName VARCHAR(30) NOT NULL,
    directorLastName VARCHAR(30) NOT NULL,
    directorBirthDate DATETIME,
    CONSTRAINT pk_actor
        PRIMARY KEY (actorId)
);
DESCRIBE actor;

#movie 
DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
    movieId INT AUTO_INCREMENT,
    genreId INT NOT NULL,
    directorId INT,
    ratingId INT,
    title VARCHAR(128) NOT NULL,
    releaseDate DATETIME,
    CONSTRAINT pk_movie
        PRIMARY KEY (movieId),
	CONSTRAINT fk_movie_genre
    	FOREIGN KEY (genreId)
    	REFERENCES genre(genreId),
    CONSTRAINT fk_movie_director
    	FOREIGN KEY (directorId)
    	REFERENCES director(directorId),
	CONSTRAINT fk_movie_rating
    	FOREIGN KEY (ratingId)
    	REFERENCES rating(ratingId)
);
DESCRIBE movie;

#castMembers 
DROP TABLE IF EXISTS castMembers;
CREATE TABLE castMembers (
    castMembersId INT AUTO_INCREMENT,
    actorId INT NOT NULL,
    movieId INT NOT NULL,
    role VARCHAR(50) NOT NULL,
    CONSTRAINT pk_castMembers
        PRIMARY KEY (castMembersId),
	CONSTRAINT fk_castMembers_actor
    	FOREIGN KEY (actorId)
    	REFERENCES actor(actorId),
	CONSTRAINT fk_castMembers_movie
    	FOREIGN KEY (movieId)
    	REFERENCES movie(movieId)
);
DESCRIBE castMembers;

SHOW tables;

