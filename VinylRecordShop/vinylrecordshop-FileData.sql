USE vinylrecordshop;

LOAD DATA LOCAL INFILE 'C:/Users/TheBoss/Desktop/Wiley-Edge/Wiley-Edge/VinylRecordShop/artist.csv'
INTO TABLE vinylrecordshop.artist 
FIELDS TERMINATED BY ',';

SELECT * FROM artist;

SELECT * FROM band;
