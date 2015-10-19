CREATE DATABASE  IF NOT EXISTS `xiaowei` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `xiaowei`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: xiaowei
-- ------------------------------------------------------
-- Server version	5.5.39

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
-- Table structure for table `test_course`
--

DROP TABLE IF EXISTS `test_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_course` (
  `id` char(32) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `creationTime` bigint(20) COLLATE utf8mb4_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_course`
--

LOCK TABLES `test_course` WRITE;
/*!40000 ALTER TABLE `test_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_date`
--

DROP TABLE IF EXISTS `test_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_date` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timeOne` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `timeTwo` bigint(20) COLLATE utf8mb4_bin,
  `timeThree` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_date`
--

LOCK TABLES `test_date` WRITE;
/*!40000 ALTER TABLE `test_date` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_sc`
--

DROP TABLE IF EXISTS `test_sc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_sc` (
  `id` char(32) COLLATE utf8mb4_bin NOT NULL,
  `sId` char(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `cId` char(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_sc`
--

LOCK TABLES `test_sc` WRITE;
/*!40000 ALTER TABLE `test_sc` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_sc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_student`
--

DROP TABLE IF EXISTS `test_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_student` (
  `id` char(32) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `creationTime` bigint(20) COLLATE utf8mb4_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_student`
--

LOCK TABLES `test_student` WRITE;
/*!40000 ALTER TABLE `test_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-10 16:39:10
