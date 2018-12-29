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
-- Table structure for table `ouvrage`
--
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `relance`;
DROP TABLE IF EXISTS `exemplaire`;
DROP TABLE IF EXISTS `pret`;
DROP TABLE IF EXISTS `ouvrage`;
DROP TABLE IF EXISTS `usager`;
DROP TABLE IF EXISTS `reservation`;
SET foreign_key_checks = 1;



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
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usager`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;




--
-- Table structure for table `exemplaire`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `exemplaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ouvrage_id` int(11) NOT NULL,
  `pret_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ouvrage` (`ouvrage_id`),
  CONSTRAINT `FK_ouvrage` FOREIGN KEY (`ouvrage_id`) REFERENCES `ouvrage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `pret`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pret` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usager_id` int(11) NOT NULL,
  `exemplaire_id` int(11) NOT NULL,
  `date_pret` date NOT NULL,
  `date_fin` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pret_usager` (`usager_id`),
  KEY `FK_pret_exemplaire` (`exemplaire_id`),
  CONSTRAINT `FK_pret_exemplaire` FOREIGN KEY (`exemplaire_id`) REFERENCES `exemplaire` (`id`),
  CONSTRAINT `FK_pret_usager` FOREIGN KEY (`usager_id`) REFERENCES `usager` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

ALTER TABLE `exemplaire` ADD CONSTRAINT `FK5rofl5edwkj66au1e6bhvsmg1` FOREIGN KEY (`pret_id`) REFERENCES `pret` (`id`);


--
-- Table structure for table `relance`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `relance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pret_id` int(11) NOT NULL,
  `date_fin` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_relance_pret` (`pret_id`),
  CONSTRAINT `FK_relance_pret` FOREIGN KEY (`pret_id`) REFERENCES `pret` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;



SET character_set_client = utf8mb4 ;
CREATE TABLE reservation (
id int NOT NULL,
ouvrage_id int  NOT NULL,
usager_id int  NOT NULL,
date_reservation DATETIME NOT NULL,
date_limite DATE,
PRIMARY KEY(id),
CONSTRAINT FK_ouvrage_reservation FOREIGN KEY (ouvrage_id) REFERENCES ouvrage(id),
CONSTRAINT FK_usager_reservation FOREIGN KEY (usager_id) REFERENCES usager(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;



-- Dump completed on 2018-12-21 22:47:36
