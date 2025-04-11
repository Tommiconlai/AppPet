-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: apppet
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `animali`
--

DROP TABLE IF EXISTS `animali`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animali` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ID_utente` int DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `ratingAnimale` float DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  `sesso` varchar(10) DEFAULT NULL,
  `peso` varchar(10) DEFAULT NULL,
  `altezza` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_utente` (`ID_utente`),
  CONSTRAINT `animali_ibfk_1` FOREIGN KEY (`ID_utente`) REFERENCES `utenti` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animali`
--

LOCK TABLES `animali` WRITE;
/*!40000 ALTER TABLE `animali` DISABLE KEYS */;
INSERT INTO `animali` VALUES (1,1,'Dispry',0,'Gatto grigio Europeo castrato di anni 4','M','5','15'),(2,NULL,'Rocco',0,'Papaggallino inseparable di anni 3','M','0.35','15'),(3,1,'fido',0,'ciapo','M','43','3'),(4,1,'samarai',0,'orso','M','50','50'),(5,NULL,'asda',0,'3','M','5','4'),(6,NULL,'s',0,'x',NULL,'x','x'),(7,NULL,'ssa',0,'x','M','z','c'),(8,1,'gatto',0,'stronzo','M','13','50');
/*!40000 ALTER TABLE `animali` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-11  9:38:53
