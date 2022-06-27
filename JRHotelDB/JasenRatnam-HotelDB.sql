-- Jasen Ratnam
-- 22/06/2022

-- This script will destroy existing database and start a new!
DROP DATABASE IF EXISTS JasenRatnamHotelDB;

CREATE DATABASE JasenRatnamHotelDB;

-- Make sure we're in the correct database before we add schema.
USE JasenRatnamHotelDB;

CREATE TABLE Room (
	roomNumber INT PRIMARY KEY,
    roomType VARCHAR(6) NOT NULL,
    ADAAccessible BOOLEAN  NOT NULL DEFAULT 0,
    standardOccupancy INT  NOT NULL,
    maximumOccupancy INT  NOT NULL,
    basePrice DECIMAL(10,2) NOT NULL,
    extraPerson DECIMAL(10,2) NULL DEFAULT 0
);

CREATE TABLE Guests (
    `name` VARCHAR(100) PRIMARY KEY,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state CHAR(2) NOT NULL,
    zipCode CHAR(5) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL
);

CREATE TABLE Amenities (
	amenitiesId INT PRIMARY KEY AUTO_INCREMENT,
    amenityType VARCHAR(20) NOT NULL,
    price DECIMAL(10,2) NULL DEFAULT 0
);

CREATE TABLE AmenitiesHotel (
	amenitiesId INT NOT NULL,
    roomNumber INT NOT NULL,
    PRIMARY KEY pk_AmenitiesHotel (amenitiesId, roomNumber),
	FOREIGN KEY fk_AmenitiesHotel_Amenities (amenitiesId)
		REFERENCES Amenities(amenitiesId)
         ON DELETE CASCADE
         ON UPDATE CASCADE,
	FOREIGN KEY fk_AmenitiesHotel_Room (roomNumber)
		REFERENCES Room(roomNumber)
         ON DELETE CASCADE
         ON UPDATE CASCADE
);

CREATE TABLE Reservations (
    reservationId INT PRIMARY KEY AUTO_INCREMENT,
    roomNumber INT NOT NULL,
    `name`  VARCHAR(100) NOT NULL,
    numberOfAdults INT NOT NULL,
	numberOfChildrens INT NULL DEFAULT 0,
	startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    totalCostOfRoom DECIMAL(10,2) NOT NULL,
	FOREIGN KEY fk_Reservations_Guests (`name`)
		REFERENCES Guests(`name`)
         ON DELETE CASCADE
         ON UPDATE CASCADE,
	FOREIGN KEY fk_Reservations_Room (roomNumber)
		REFERENCES Room(roomNumber)
         ON DELETE CASCADE
         ON UPDATE CASCADE
);
