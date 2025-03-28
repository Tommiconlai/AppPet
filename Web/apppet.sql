-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: apppet
-- ------------------------------------------------------
-- Server version	8.0.40

use apppet;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animali`
--

LOCK TABLES `animali` WRITE;
/*!40000 ALTER TABLE `animali` DISABLE KEYS */;
INSERT INTO `animali` VALUES (1,NULL,'Dispry',0,'Gatto grigio Europeo castrato di anni 4','M','5','15'),(2,NULL,'Rocco',0,'Papaggallino inseparable di anni 3','M','0.35','15');
/*!40000 ALTER TABLE `animali` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attivita_calendario`
--

DROP TABLE IF EXISTS `attivita_calendario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attivita_calendario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ID_animale` int DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `descrizione` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_animale` (`ID_animale`),
  CONSTRAINT `attivita_calendario_ibfk_1` FOREIGN KEY (`ID_animale`) REFERENCES `animali` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attivita_calendario`
--

LOCK TABLES `attivita_calendario` WRITE;
/*!40000 ALTER TABLE `attivita_calendario` DISABLE KEYS */;
/*!40000 ALTER TABLE `attivita_calendario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartelle_cliniche`
--

DROP TABLE IF EXISTS `cartelle_cliniche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartelle_cliniche` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ID_animale` int DEFAULT NULL,
  `descrizione` varchar(200) DEFAULT NULL,
  `data_appuntamento` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_animale` (`ID_animale`),
  CONSTRAINT `cartelle_cliniche_ibfk_1` FOREIGN KEY (`ID_animale`) REFERENCES `animali` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartelle_cliniche`
--

LOCK TABLES `cartelle_cliniche` WRITE;
/*!40000 ALTER TABLE `cartelle_cliniche` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartelle_cliniche` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datiemotivi`
--

DROP TABLE IF EXISTS `datiemotivi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datiemotivi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ID_animale` int DEFAULT NULL,
  `valutazione` int DEFAULT NULL,
  `data_valutazione` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_animale` (`ID_animale`),
  CONSTRAINT `datiemotivi_ibfk_1` FOREIGN KEY (`ID_animale`) REFERENCES `animali` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datiemotivi`
--

LOCK TABLES `datiemotivi` WRITE;
/*!40000 ALTER TABLE `datiemotivi` DISABLE KEYS */;
/*!40000 ALTER TABLE `datiemotivi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornitori`
--

DROP TABLE IF EXISTS `fornitori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornitori` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `cognome` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornitori`
--

LOCK TABLES `fornitori` WRITE;
/*!40000 ALTER TABLE `fornitori` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornitori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenotazioni`
--

DROP TABLE IF EXISTS `prenotazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenotazioni` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ID_animale` int DEFAULT NULL,
  `ID_servizio` int DEFAULT NULL,
  `data_prenotazione` date DEFAULT NULL,
  `orario` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_animale` (`ID_animale`),
  KEY `ID_servizio` (`ID_servizio`),
  CONSTRAINT `prenotazioni_ibfk_1` FOREIGN KEY (`ID_animale`) REFERENCES `animali` (`id`),
  CONSTRAINT `prenotazioni_ibfk_2` FOREIGN KEY (`ID_servizio`) REFERENCES `servizi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenotazioni`
--

LOCK TABLES `prenotazioni` WRITE;
/*!40000 ALTER TABLE `prenotazioni` DISABLE KEYS */;
/*!40000 ALTER TABLE `prenotazioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensioni`
--

DROP TABLE IF EXISTS `recensioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensioni` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ID_prenotazione` int DEFAULT NULL,
  `valutazione` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_prenotazione` (`ID_prenotazione`),
  CONSTRAINT `recensioni_ibfk_1` FOREIGN KEY (`ID_prenotazione`) REFERENCES `prenotazioni` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensioni`
--

LOCK TABLES `recensioni` WRITE;
/*!40000 ALTER TABLE `recensioni` DISABLE KEYS */;
/*!40000 ALTER TABLE `recensioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servizi`
--

DROP TABLE IF EXISTS `servizi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servizi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ID_fornitore` int DEFAULT NULL,
  `ID_tipo_attività` int DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `indirizzo` varchar(100) DEFAULT NULL,
  `orario` varchar(20) DEFAULT NULL,
  `cap` char(5) DEFAULT NULL,
  `latitudine` double DEFAULT NULL,
  `longitudine` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_fornitore` (`ID_fornitore`),
  KEY `ID_tipo_attività` (`ID_tipo_attività`),
  CONSTRAINT `servizi_ibfk_1` FOREIGN KEY (`ID_fornitore`) REFERENCES `fornitori` (`id`),
  CONSTRAINT `servizi_ibfk_2` FOREIGN KEY (`ID_tipo_attività`) REFERENCES `tipo_attività` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servizi`
--

LOCK TABLES `servizi` WRITE;
/*!40000 ALTER TABLE `servizi` DISABLE KEYS */;
/*!40000 ALTER TABLE `servizi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_attività`
--

DROP TABLE IF EXISTS `tipo_attività`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_attività` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_attività`
--

LOCK TABLES `tipo_attività` WRITE;
/*!40000 ALTER TABLE `tipo_attività` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_attività` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenti` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) DEFAULT NULL,
  `Cognome` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES (1,'1','6','4','2','3'),(2,'aa','adas','asda','asas','sadasd'),(3,'sadass','xweve','rfsx','efefc','cwxs');
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-21 17:07:11