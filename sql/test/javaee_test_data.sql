-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javaee_test
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `exemplaire`
--

LOCK TABLES `exemplaire` WRITE;
/*!40000 ALTER TABLE `exemplaire` DISABLE KEYS */;
INSERT INTO `exemplaire` VALUES (16,14,20),(17,15,19),(18,15,NULL),(23,22,NULL),(24,22,NULL);
/*!40000 ALTER TABLE `exemplaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (117),(117),(117),(117),(117);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ouvrage`
--

LOCK TABLES `ouvrage` WRITE;
/*!40000 ALTER TABLE `ouvrage` DISABLE KEYS */;
INSERT INTO `ouvrage` VALUES (14,'Spring Framework','Harry Potter','Eh oui il est partout ce petit binoclard.',NULL,NULL),(15,'Spring Framework 2','Luc','Je suis ton père.',NULL,NULL),(22,'Ouvrage disponible','Aragorn','La communauté de l\'anneau doit être préservée.',NULL,NULL);
/*!40000 ALTER TABLE `ouvrage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pret`
--

LOCK TABLES `pret` WRITE;
/*!40000 ALTER TABLE `pret` DISABLE KEYS */;
INSERT INTO `pret` VALUES (19,13,17,'2018-12-12','2019-01-09'),(20,13,16,'2018-12-12','2019-01-09');
/*!40000 ALTER TABLE `pret` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `relance`
--

LOCK TABLES `relance` WRITE;
/*!40000 ALTER TABLE `relance` DISABLE KEYS */;
INSERT INTO `relance` VALUES (21,20,'2019-02-06');
/*!40000 ALTER TABLE `relance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usager`
--

LOCK TABLES `usager` WRITE;
/*!40000 ALTER TABLE `usager` DISABLE KEYS */;
INSERT INTO `usager` VALUES (13,'mathieu-martinez');
/*!40000 ALTER TABLE `usager` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-21 22:47:49
