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
-- Table structure for table `amenitieshotel`
--

DROP TABLE IF EXISTS `amenitieshotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amenitieshotel` (
  `amenitiesId` int(11) NOT NULL,
  `roomNumber` int(11) NOT NULL,
  PRIMARY KEY (`amenitiesId`,`roomNumber`),
  KEY `fk_AmenitiesHotel_Room` (`roomNumber`),
  CONSTRAINT `fk_AmenitiesHotel_Amenities` FOREIGN KEY (`amenitiesId`) REFERENCES `amenities` (`amenitiesId`),
  CONSTRAINT `fk_AmenitiesHotel_Room` FOREIGN KEY (`roomNumber`) REFERENCES `room` (`roomNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenitieshotel`
--

LOCK TABLES `amenitieshotel` WRITE;
/*!40000 ALTER TABLE `amenitieshotel` DISABLE KEYS */;
INSERT INTO `amenitieshotel` VALUES (1,201),(1,203),(1,205),(1,206),(1,207),(1,208),(1,301),(1,303),(1,305),(1,306),(1,307),(1,308),(1,401),(1,402),(2,201),(2,203),(2,205),(2,207),(2,301),(2,303),(2,305),(2,307),(3,202),(3,204),(3,205),(3,206),(3,207),(3,208),(3,302),(3,304),(3,305),(3,306),(3,307),(3,308),(3,401),(3,402),(4,401),(4,402);
/*!40000 ALTER TABLE `amenitieshotel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-22 15:24:01
