-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javaee
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
-- Table structure for table `exemplaire`
--

DROP TABLE IF EXISTS `exemplaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `exemplaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ouvrage_id` int(11) NOT NULL,
  `pret_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrubcrg51n3duk6yjm4yflyubs` (`ouvrage_id`),
  KEY `FK_exemplaire_pret` (`pret_id`),
  CONSTRAINT `FK_exemplaire_pret` FOREIGN KEY (`pret_id`) REFERENCES `pret` (`id`),
  CONSTRAINT `FK_ouvrage` FOREIGN KEY (`ouvrage_id`) REFERENCES `ouvrage` (`id`),
  CONSTRAINT `FKanyher7vd5o14qpmcgcfede10` FOREIGN KEY (`ouvrage_id`) REFERENCES `ouvrage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4058 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ouvrage`
--

DROP TABLE IF EXISTS `ouvrage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ouvrage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `auteur` char(80) DEFAULT NULL,
  `resume` varchar(500) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `imageb` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4056 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pret`
--

DROP TABLE IF EXISTS `pret`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pret` (
  `id` int(11) NOT NULL,
  `date_fin` date DEFAULT NULL,
  `date_pret` date DEFAULT NULL,
  `exemplaire_id` int(11) NOT NULL,
  `usager_id` int(11) DEFAULT NULL,
  `ouvrage_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKryd1x5fme3awtoykchid0ri0y` (`ouvrage_id`),
  KEY `FKjtomu4b9nf3r5tlk8uo9y2exl` (`exemplaire_id`),
  KEY `FK3a03b5qf6bxhioxyyg65wjc78` (`usager_id`),
  CONSTRAINT `FK3a03b5qf6bxhioxyyg65wjc78` FOREIGN KEY (`usager_id`) REFERENCES `usager` (`id`),
  CONSTRAINT `FKjtomu4b9nf3r5tlk8uo9y2exl` FOREIGN KEY (`exemplaire_id`) REFERENCES `exemplaire` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `relance`
--

DROP TABLE IF EXISTS `relance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `relance` (
  `id` int(11) NOT NULL,
  `date_fin` date DEFAULT NULL,
  `pret_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKny3amykegqqqb346jc0gqm03m` (`pret_id`),
  CONSTRAINT `FKny3amykegqqqb346jc0gqm03m` FOREIGN KEY (`pret_id`) REFERENCES `pret` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usager`
--

DROP TABLE IF EXISTS `usager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usager` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
	`pret_expiration` boolean,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-21 22:47:03
