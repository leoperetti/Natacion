-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: natacion
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `andarivel`
--

DROP TABLE IF EXISTS `andarivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `andarivel` (
  `nroAndarivel` int(11) NOT NULL,
  PRIMARY KEY (`nroAndarivel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `andarivel`
--

LOCK TABLES `andarivel` WRITE;
/*!40000 ALTER TABLE `andarivel` DISABLE KEYS */;
INSERT INTO `andarivel` VALUES (1),(2),(3),(4),(5),(6);
/*!40000 ALTER TABLE `andarivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `andarivelporserie`
--

DROP TABLE IF EXISTS `andarivelporserie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `andarivelporserie` (
  `nroAnda` int(11) NOT NULL,
  `nroS` int(11) NOT NULL,
  PRIMARY KEY (`nroAnda`,`nroS`),
  KEY `nroS_idx` (`nroS`),
  CONSTRAINT `nroAnda` FOREIGN KEY (`nroAnda`) REFERENCES `andarivel` (`nroAndarivel`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `nroS` FOREIGN KEY (`nroS`) REFERENCES `series` (`nroSerie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `andarivelporserie`
--

LOCK TABLES `andarivelporserie` WRITE;
/*!40000 ALTER TABLE `andarivelporserie` DISABLE KEYS */;
/*!40000 ALTER TABLE `andarivelporserie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carreras` (
  `nroCarrera` int(11) NOT NULL,
  `tipoCarrera` int(11) NOT NULL,
  `metros` int(11) DEFAULT NULL,
  `nroPrograma` int(11) DEFAULT '1',
  `sexo` char(4) DEFAULT NULL,
  PRIMARY KEY (`nroCarrera`),
  KEY `nroPrograma_idx` (`nroPrograma`),
  CONSTRAINT `nroPrograma` FOREIGN KEY (`nroPrograma`) REFERENCES `programas` (`nroPrograma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carreras`
--

LOCK TABLES `carreras` WRITE;
/*!40000 ALTER TABLE `carreras` DISABLE KEYS */;
INSERT INTO `carreras` VALUES (1,6,15,1,'f'),(2,6,15,1,'m'),(3,7,15,1,'f'),(4,7,15,1,'m'),(5,8,15,1,'f'),(6,8,15,1,'m'),(7,9,25,1,'f'),(8,9,25,1,'m'),(9,10,25,1,'f'),(10,10,25,1,'m'),(11,11,25,1,'f'),(12,11,25,1,'m'),(13,12,50,1,'f'),(14,12,50,1,'m'),(15,13,50,1,'f'),(16,13,50,1,'m'),(17,14,50,1,'f'),(18,14,50,1,'m'),(19,15,50,1,'f'),(20,15,50,1,'m'),(21,6,15,2,'f'),(22,6,15,2,'m'),(23,7,15,2,'f'),(24,7,15,2,'m'),(25,8,15,2,'f'),(26,8,15,2,'m'),(27,9,25,2,'f'),(28,9,25,2,'m'),(29,10,25,2,'f'),(30,10,25,2,'m'),(31,11,25,2,'f'),(32,11,25,2,'m'),(33,12,25,2,'f'),(34,12,25,2,'m'),(35,13,50,2,'f'),(36,13,50,2,'m'),(37,14,50,2,'f'),(38,14,50,2,'m'),(39,15,50,2,'f'),(40,15,50,2,'m');
/*!40000 ALTER TABLE `carreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nadadores`
--

DROP TABLE IF EXISTS `nadadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nadadores` (
  `dni` int(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `nombreClub` varchar(45) DEFAULT NULL,
  `edad` int(4) DEFAULT NULL,
  `tiempoPreCompetencia` time DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nadadores`
--

LOCK TABLES `nadadores` WRITE;
/*!40000 ALTER TABLE `nadadores` DISABLE KEYS */;
INSERT INTO `nadadores` VALUES (303030,'Facundo','Alvarez','Rosario',24,'00:39:22'),(3400210,'Martin','Guereta','San Pedro',21,'00:21:12'),(3892929,'Luisi','Peretti','Sastre',19,'00:22:11'),(3932909,'Guille','Vescovo','Rosario',22,'00:23:12'),(30303030,'Gabriel','Seveso','Sastre',22,'00:22:33'),(33333333,'Sherard','Peretti','Sastre',23,'00:45:34'),(36543161,'Leo','Peretti','Sastre',21,'00:43:12'),(37544555,'Juan','Grasso','Sportivo Usche',21,'00:34:34');
/*!40000 ALTER TABLE `nadadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nadadorporandarivel`
--

DROP TABLE IF EXISTS `nadadorporandarivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nadadorporandarivel` (
  `nroAndarivel` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `tiempoCompetencia` time NOT NULL,
  PRIMARY KEY (`nroAndarivel`,`dni`),
  KEY `dni_idx` (`dni`),
  CONSTRAINT `dni` FOREIGN KEY (`dni`) REFERENCES `nadadores` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `nroAndarivel` FOREIGN KEY (`nroAndarivel`) REFERENCES `andarivel` (`nroAndarivel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nadadorporandarivel`
--

LOCK TABLES `nadadorporandarivel` WRITE;
/*!40000 ALTER TABLE `nadadorporandarivel` DISABLE KEYS */;
/*!40000 ALTER TABLE `nadadorporandarivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nadadorporcarrera`
--

DROP TABLE IF EXISTS `nadadorporcarrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nadadorporcarrera` (
  `nroCarrera` int(11) NOT NULL,
  `dniNadador` int(11) NOT NULL,
  PRIMARY KEY (`nroCarrera`,`dniNadador`),
  KEY `dniNadador_idx` (`dniNadador`),
  CONSTRAINT `dniNadador` FOREIGN KEY (`dniNadador`) REFERENCES `nadadores` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `nroCarrer` FOREIGN KEY (`nroCarrera`) REFERENCES `carreras` (`nroCarrera`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nadadorporcarrera`
--

LOCK TABLES `nadadorporcarrera` WRITE;
/*!40000 ALTER TABLE `nadadorporcarrera` DISABLE KEYS */;
INSERT INTO `nadadorporcarrera` VALUES (6,3932909),(6,30303030);
/*!40000 ALTER TABLE `nadadorporcarrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programadetorneo`
--

DROP TABLE IF EXISTS `programadetorneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programadetorneo` (
  `torneo` date NOT NULL,
  `programa` int(11) NOT NULL,
  PRIMARY KEY (`torneo`,`programa`),
  KEY `programa_idx` (`programa`),
  CONSTRAINT `programa` FOREIGN KEY (`programa`) REFERENCES `programas` (`nroPrograma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `torneo` FOREIGN KEY (`torneo`) REFERENCES `torneo` (`fechaTorneo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programadetorneo`
--

LOCK TABLES `programadetorneo` WRITE;
/*!40000 ALTER TABLE `programadetorneo` DISABLE KEYS */;
/*!40000 ALTER TABLE `programadetorneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programas`
--

DROP TABLE IF EXISTS `programas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programas` (
  `nroPrograma` int(11) NOT NULL,
  `estilo1` varchar(45) DEFAULT NULL,
  `estilo2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nroPrograma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programas`
--

LOCK TABLES `programas` WRITE;
/*!40000 ALTER TABLE `programas` DISABLE KEYS */;
INSERT INTO `programas` VALUES (1,'Mariposa','Pecho'),(2,'Espalda','Croll');
/*!40000 ALTER TABLE `programas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `series`
--

DROP TABLE IF EXISTS `series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `series` (
  `nroSerie` int(11) NOT NULL,
  `nroCarrera` int(11) NOT NULL,
  PRIMARY KEY (`nroSerie`),
  KEY `nroCarrera_idx` (`nroCarrera`),
  CONSTRAINT `nroCarrera` FOREIGN KEY (`nroCarrera`) REFERENCES `carreras` (`nroCarrera`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `series`
--

LOCK TABLES `series` WRITE;
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
/*!40000 ALTER TABLE `series` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneo`
--

DROP TABLE IF EXISTS `torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo` (
  `fechaTorneo` date NOT NULL,
  PRIMARY KEY (`fechaTorneo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneo`
--

LOCK TABLES `torneo` WRITE;
/*!40000 ALTER TABLE `torneo` DISABLE KEYS */;
/*!40000 ALTER TABLE `torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'natacion'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-17 16:04:07
