CREATE DATABASE  IF NOT EXISTS `mario` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mario`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: mario
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_nr` int NOT NULL AUTO_INCREMENT,
  `pizza_id` int NOT NULL,
  `amount` int NOT NULL,
  `pickup_time` int NOT NULL,
  `order_time` timestamp(2) NOT NULL,
  `date` date DEFAULT NULL,
  `custemor_name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `remove` tinyint NOT NULL,
  PRIMARY KEY (`order_nr`),
  KEY `pizza_no_idx` (`pizza_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (40,2,3,1455,'2020-12-22 14:53:32.00','2020-12-22','Nick','43',0),(41,5,22,1555,'2020-12-22 14:53:51.00','2020-12-22',':D','555',0),(42,2,2,1300,'2020-12-22 20:09:46.00','2020-12-22','nik','42',0),(43,3,5,1200,'2020-12-22 20:11:35.00','2020-12-22','nikk','55',0),(47,2,12,1300,'2020-12-22 20:22:29.00','2020-12-22','nik','42',0),(48,2,4,1400,'2020-12-22 20:23:48.00','2020-12-22','niker','55',0),(49,1,2,1100,'2020-12-22 20:25:01.00','2020-12-22','Nicklas','4242',1),(50,4,3,1300,'2020-12-22 20:25:50.00','2020-12-22','ReneFEDE','666',0),(51,4,5,1345,'2020-12-22 20:26:30.00','2020-12-22','Rene','666',0),(52,3,13,1444,'2020-12-22 20:31:49.00','2020-12-22','nik','55',0),(53,3,5,1300,'2020-12-22 20:34:10.00','2020-12-22','nik','555555',0),(54,2,1,1345,'2020-12-22 20:34:47.00','2020-12-22','Hejjj','5',0),(55,2,3,1355,'2020-12-22 20:36:12.00','2020-12-22',':D','66',0),(56,2,4,1355,'2020-12-22 20:40:53.00','2020-12-22','Thorbjørn','55',0),(57,2,1,1555,'2020-12-22 20:49:22.00','2020-12-22','rene2','77',0),(58,3,2,1300,'2020-12-30 10:26:15.00','2020-12-30','Nik','42',0),(59,4,3,1355,'2020-12-30 10:28:07.00','2020-12-30','Thorbjørn','555',0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizza` (
  `pizza_id` int NOT NULL AUTO_INCREMENT,
  `pizza_no` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `ingredients` varchar(45) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`pizza_id`),
  UNIQUE KEY `pizza_no_UNIQUE` (`pizza_no`),
  UNIQUE KEY `pizza_id_UNIQUE` (`pizza_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (1,1,'Vesuvio','ost, skinke og tomat',70),(2,2,'Amerikaner','ost, kebab og løg',80),(3,3,'Diavola','Ost, Chillie, pepperonie oghvildløg ',75),(4,4,'Hawai','ost,  skinke og annanas',65),(5,5,'Rønne','ost, tomat, skinke, champingon',88),(6,6,'Halse','Rejer, hvidløg, ost og tomat',90),(7,15,'ReneSpecial','Rene',55);
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-30 11:46:14
