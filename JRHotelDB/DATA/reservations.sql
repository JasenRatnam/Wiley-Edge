-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: JasenRatnamHotelDB
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations` (
  `reservationId` int(11) NOT NULL AUTO_INCREMENT,
  `roomNumber` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `numberOfAdults` int(11) NOT NULL,
  `numberOfChildrens` int(11) DEFAULT 0,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `totalCostOfRoom` decimal(10,2) NOT NULL,
  PRIMARY KEY (`reservationId`),
  KEY `fk_Reservations_Guests` (`name`),
  KEY `fk_Reservations_Room` (`roomNumber`),
  CONSTRAINT `fk_Reservations_Guests` FOREIGN KEY (`name`) REFERENCES `guests` (`name`),
  CONSTRAINT `fk_Reservations_Room` FOREIGN KEY (`roomNumber`) REFERENCES `room` (`roomNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,308,'Mack Simmer',1,0,'2023-02-02','2023-02-04',299.98),(2,203,'Bettyann Seery',2,1,'2023-02-05','2023-02-10',999.95),(3,305,'Duane Cullison',2,0,'2023-02-22','2023-02-24',349.98),(4,201,'Karie Yang',2,2,'2023-03-06','2023-03-07',199.99),(5,307,'Jasen Ratnam',1,1,'2023-03-17','2023-03-20',524.97),(6,302,'Aurore Lipton',3,0,'2023-03-18','2023-03-23',924.95),(7,202,'Zachery Luechtefeld',2,2,'2023-03-29','2023-03-31',349.98),(8,304,'Jeremiah Pendergrass',2,0,'2023-03-31','2023-04-05',874.95),(9,301,'Walter Holaway',1,0,'2023-04-09','2023-04-13',799.96),(10,207,'Wilfred Vise',1,1,'2023-04-23','2023-04-24',174.99),(11,401,'Maritza Tilton',2,4,'2023-05-30','2023-06-02',1199.97),(12,206,'Joleen Tison',2,0,'2023-06-10','2023-06-14',599.96),(13,208,'Joleen Tison',1,0,'2023-06-10','2023-06-14',599.96),(14,304,'Aurore Lipton',3,0,'2023-06-17','2023-06-18',184.99),(15,205,'Jasen Ratnam',2,0,'2023-06-28','2023-07-02',699.96),(16,204,'Walter Holaway',3,1,'2023-07-13','2023-07-14',184.99),(17,401,'Wilfred Vise',4,2,'2023-07-18','2023-07-21',1259.97),(18,303,'Bettyann Seery',2,1,'2023-07-28','2023-07-29',199.99),(19,305,'Bettyann Seery',1,0,'2023-08-30','2023-09-01',349.98),(20,208,'Mack Simmer',2,0,'2023-09-16','2023-09-17',149.99),(21,203,'Karie Yang',2,2,'2023-09-13','2023-09-15',399.98),(22,401,'Duane Cullison',2,2,'2023-11-22','2023-11-25',1199.97),(23,206,'Mack Simmer',2,0,'2023-11-22','2023-11-25',449.97),(24,301,'Mack Simmer',2,2,'2023-11-22','2023-11-25',599.97),(25,302,'Maritza Tilton',2,0,'2023-12-24','2023-12-28',699.96);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-22 15:29:30
