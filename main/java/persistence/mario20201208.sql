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
                         `order_nr` int NOT NULL,
                         `pizza_id` int NOT NULL,
                         `amount` int NOT NULL,
                         `pickup_time` int NOT NULL,
                         `order_time` timestamp(2) NOT NULL,
                         `custemor_name` varchar(45) NOT NULL,
                         `phone` varchar(45) NOT NULL,
                         `remove` tinyint NOT NULL,
                         PRIMARY KEY (`order_nr`),
                         KEY `pizza_no_idx` (`pizza_id`),
                         CONSTRAINT `pizzaId` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`pizza_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,2,6,1260,'2020-12-10 11:06:30.00','rene','51',1),(3,5,5,4444,'2020-12-10 11:30:30.00','kmsdck','65465',1),(4,4,5,1212,'2020-12-10 11:28:30.00','Nicklas','5555',1),(5,5,5,1525,'2020-12-11 08:48:19.00','jaja','888',1);
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
                         PRIMARY KEY (`pizza_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (1,1,'Vesuvio','ost, skinke og tomat',70),(2,2,'Amerikaner','ost, kebab og løg',80),(3,3,'Diavola','Ost, Chillie, pepperonie oghvildløg ',75),(4,4,'Hawai','ost,  skinke og annanas',65),(5,5,'Rønne','ost, tomat, skinke, champingon',88),(6,6,'Halse','Rejer, hvidløg, ost og tomat',90);
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

-- Dump completed on 2020-12-11 10:04:46
