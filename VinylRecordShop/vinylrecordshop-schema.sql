#Jasen Ratnam
#2022/06/14

/*
1.Drop the vinylrecordstore database, if it exists.
2.Recreate the database.
3.Use the database.
4.Define all tables in the database, including appropriate primary and foreign key fields:
	*album
	*artist
	*band
	*song
	*songAlbum
	*bandArtist
*/

#remove an existing database if it exists, to make sure database is empty
DROP DATABASE IF EXISTS vinylrecordshop;

#Create database
CREATE DATABASE vinylrecordshop;
SHOW DATABASES;

#make it the active database
USE vinylrecordshop;

#album 
/*
The albumId field auto-increments, automatically assign a sequential number to each new record, 
	if no value is specified. 
The string values are defined using VARCHAR, specifying the maximum number of characters.
releaseDate is a DATE field. 
	default format for a date is yyyy-mm-dd. 
The price is a DECIMAL field with a maximum value of 999.99. 
price, releaseDate, and label are nullable fields.
We define the PRIMARY KEY constraint on albumId.
*/
CREATE TABLE album (
    albumId INT AUTO_INCREMENT,
    albumTitle VARCHAR(100) NOT NULL,
    label VARCHAR(50),
    releaseDate DATE,
    price DECIMAL(5,2),
    CONSTRAINT pk_album 
        PRIMARY KEY (albumId)
);
DESCRIBE album;

#artist
CREATE TABLE artist (
    artistId INT AUTO_INCREMENT,
    artistFirstName VARCHAR(25),
    artistLastName VARCHAR(50) NOT NULL,
    CONSTRAINT pk_artist 
        PRIMARY KEY (artistId)
);
DESCRIBE artist;

#band
CREATE TABLE band (
    bandId INT AUTO_INCREMENT,
    bandName VARCHAR(50) NOT NULL,
    CONSTRAINT pk_band 
        PRIMARY KEY (bandId)
);
DESCRIBE band;

CREATE TABLE song (
    songId INT NOT NULL AUTO_INCREMENT,
    songTitle VARCHAR(100) NOT NULL,
    videoUrl VARCHAR(100),
    bandId INT NOT NULL,
    CONSTRAINT pk_song 
    	PRIMARY KEY (songId),
	CONSTRAINT fk_song_band 
    	FOREIGN KEY (bandID)
    	REFERENCES band(bandId)
);
DESCRIBE song;

DROP TABLE IF EXISTS songAlbum;
CREATE TABLE songAlbum (
    songId INT,
    albumId INT,
    CONSTRAINT pk_songAlbum 
    	PRIMARY KEY (songId, albumId),
    CONSTRAINT fk_songAlbum_song
    	FOREIGN KEY (songId)
    	REFERENCES song(songId),
    CONSTRAINT fk_songAlbum_album
    	FOREIGN KEY (albumId)
    	REFERENCES album(albumId)
);
DESCRIBE songAlbum;

DROP TABLE IF EXISTS bandArtist;
CREATE TABLE bandArtist (
    bandId  INT,
    artistId  INT,
    CONSTRAINT pk_bandArtist
    	PRIMARY KEY (bandId, artistId),
    CONSTRAINT fk_bandArtist_band
    	FOREIGN KEY (bandId)
    	REFERENCES band(bandId),
    CONSTRAINT fk_bandArtist_artist
    	FOREIGN KEY (artistId)
    	REFERENCES artist(artistId)
);
DESCRIBE bandArtist;


SHOW tables;







