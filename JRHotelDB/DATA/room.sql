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
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `roomNumber` int(11) NOT NULL,
  `roomType` varchar(6) NOT NULL,
  `ADAAccessible` tinyint(1) NOT NULL DEFAULT 0,
  `standardOccupancy` int(11) NOT NULL,
  `maximumOccupancy` int(11) NOT NULL,
  `basePrice` decimal(10,2) NOT NULL,
  `extraPerson` decimal(10,2) DEFAULT 0.00,
  PRIMARY KEY (`roomNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (201,'Double',0,2,4,199.99,10.00),(202,'Double',1,2,4,174.99,10.00),(203,'Double',0,2,4,199.99,10.00),(204,'Double',1,2,4,174.99,10.00),(205,'Single',0,2,2,174.99,0.00),(206,'Single',1,2,2,149.99,0.00),(207,'Single',0,2,2,174.99,0.00),(208,'Single',1,2,2,149.99,0.00),(301,'Double',0,2,4,199.99,10.00),(302,'Double',1,2,4,174.99,10.00),(303,'Double',0,2,4,199.99,10.00),(304,'Double',1,2,4,174.99,10.00),(305,'Single',0,2,2,174.99,0.00),(306,'Single',1,2,2,149.99,0.00),(307,'Single',0,2,2,174.99,0.00),(308,'Single',1,2,2,149.99,0.00),(401,'Suite',1,3,8,399.99,20.00),(402,'Suite',1,3,8,399.99,20.00);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-22 15:23:02
