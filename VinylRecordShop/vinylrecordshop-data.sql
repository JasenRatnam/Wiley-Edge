-- Jasen Ratnam
-- 20/06/2022 

USE vinylrecordshop;

INSERT INTO album 
VALUES (1,'Imagine','Apple','1971-9-9',9.99);

INSERT INTO album (albumTitle, releaseDate, price, label)
VALUES ('2525 (Exordium & Terminus)', '1969-7-1', 25.99, 'RCA');



-- multiple entry
INSERT INTO album (albumTitle, releaseDate, price, label)
VALUES 
	 ("No One's Gonna Change Our World", '1969-12-12', 39.95,'Regal Starline'), 
	 ('Moondance Studio Album', '1969-8-1',14.99,'Warner Bros');


-- m/d/yyyy to yyyy-m-d.
INSERT INTO album (albumTitle, releaseDate, price, label)
VALUES 
	("Clouds", '1969-5-1', 9.99,'Reprise'), 
	("Sounds of Silence Studio Album", '1966-1-17',9.99,'Columbia'),
	("Abbey Road", '1969-1-10',12.99,'Apple'),
    ("Smiley Smile", '1967-9-18',5.99,'Capitol');
    

SELECT * FROM album;

INSERT INTO `artist` VALUES (1,'John','Lennon',1),(2,'Paul','McCartney',1),(3,'George','Harrison',1),(4,'Ringo','Starr',1),(5,'Denny','Zager',0),(6,'Rick','Evans',0),(10,'Van','Morrison',1),(11,'Judy','Collins',0),(12,'Paul','Simon',1),(13,'Art','Garfunkel',0),(14,'Brian','Wilson',0),(15,'Dennis','Wilson',0),(16,'Carl','Wilson',0),(17,'Ricky','Fataar',0),(18,'Blondie','Chaplin',0),(19,'Jimmy','Page',0),(20,'Robert','Plant',0),(21,'John Paul','Jones',0),(22,'John','Bonham',0),(23,'Mike ','Love',0),(24,'Al ','Jardine',0),(25,'David','Marks',0),(26,'Bruce ','Johnston',0);
SELECT * FROM artist;

