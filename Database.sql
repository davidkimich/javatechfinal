-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: davidapp
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `accountid` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  PRIMARY KEY (`accountid`),
  UNIQUE KEY `accountid_UNIQUE` (`accountid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'David','Kharrat','Best','Manager'),(2,'David2','Kharrat','Best','Manager'),(8,'AAAA','AAAA','KHARRAT','DAVIDDD'),(9,'user1','user1','kharrat','david'),(11,'a','a','a','a'),(12,'aa','aa','aa','aa'),(13,'john_doe','password123','John','Doe'),(14,'john_doea','password123','John','Doe'),(15,'dav','dav','dav','dav');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `employee_email` varchar(255) DEFAULT NULL,
  `employee_first_name` varchar(255) NOT NULL,
  `employee_last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `inventoryid` bigint NOT NULL AUTO_INCREMENT,
  `inventorydescription` varchar(45) DEFAULT NULL,
  `itemQuantity` bigint DEFAULT NULL,
  `productid` bigint DEFAULT NULL,
  `item_quantity` bigint DEFAULT NULL,
  PRIMARY KEY (`inventoryid`),
  UNIQUE KEY `idinventoryid_UNIQUE` (`inventoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,' placed for this reason',23,5,1),(2,'placed for that reason',40,8,12),(3,' for neither this nor that reason',23,5,21),(4,' for neither this nor that reason',11,12,312),(5,' for neither this nor that reason',11,12,321),(6,' for neither this nor that reason',11,2,124),(7,' for neither this nor that reason',11,2,414),(8,' for neither this nor that reason',11,2,14),(9,' for neither this nor that reason',11,213,1),(10,' for neither this nor that reason',11,31,4124),(11,' for neither this nor that reason',11,31,25),(12,' for neither this nor that reason',11,31,54),(13,' for neither this nor that reason',11,21,652),(14,' for neither this nor that reason',11,13,2),(15,' for neither this nor that reason',11,31,234),(16,' for neither this nor that reason',11,31,234),(17,' for neither this nor that reason',11,4,32),(18,' for neither this nor that reason',13,13,123),(19,'V',432,32,43);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `orderdetailsid` bigint NOT NULL AUTO_INCREMENT,
  `orderid` bigint DEFAULT NULL,
  `productid` bigint DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderdetailsid`),
  UNIQUE KEY `orderdetailsid_UNIQUE` (`orderdetailsid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (2,12,5,23,50,'heheheha'),(3,12,6,23,50,'heheheho'),(4,14,6,23,50,'heheheho'),(6,1,10,5,NULL,NULL),(7,1,10,5,NULL,NULL),(8,1,10,5,NULL,NULL),(9,1,10,5,NULL,NULL),(10,27,10,3,NULL,NULL),(11,27,10,5,NULL,NULL),(12,28,10,11,NULL,NULL),(13,28,10,10,NULL,NULL),(14,28,10,10,NULL,NULL),(15,28,10,3,NULL,NULL),(16,32,10,12,NULL,NULL),(17,33,10,5,NULL,NULL),(18,33,10,5,NULL,NULL),(19,33,21,5,NULL,NULL),(20,35,10,10,NULL,NULL),(21,38,10,3,NULL,NULL),(22,39,10,100,NULL,NULL);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderin`
--

DROP TABLE IF EXISTS `orderin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderin` (
  `orderid` bigint NOT NULL AUTO_INCREMENT,
  `accountid` bigint DEFAULT NULL,
  `ordername` varchar(45) DEFAULT NULL,
  `orderprice` double DEFAULT NULL,
  `orderdescription` varchar(45) DEFAULT NULL,
  `orderdetailsid` bigint DEFAULT NULL,
  `supplierid` bigint DEFAULT NULL,
  `orderdate` datetime DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderin`
--

LOCK TABLES `orderin` WRITE;
/*!40000 ALTER TABLE `orderin` DISABLE KEYS */;
INSERT INTO `orderin` VALUES (1,1,'few',20,'fwf',3,2,'2024-05-04 08:30:59'),(2,2,'fwefw',12,'few',5,4,'2024-05-04 08:30:59'),(3,4,'frwf',34,'fesf',8,1,'2024-05-04 08:30:59'),(6,2,'fewfwe',1,'fewfw',5,2,'2024-05-04 08:30:59'),(7,2,'fewfew',3,'fewfew',5,1,'2024-05-04 08:30:59'),(8,1,'fewfw',3,'fwrfwe',3,2,'2024-05-04 08:30:59'),(9,4,'fewfew',4,'fefwe',2,4,'2024-05-04 08:30:59'),(10,3,'fhgtee',3,'herw',1,1,'2024-05-04 08:30:59'),(11,NULL,'a',0,'aaaa',22,12,'2024-05-04 00:00:00'),(12,5,'a',0,'aaaa',22,12,'2024-05-04 00:00:00');
/*!40000 ALTER TABLE `orderin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `accountid` bigint DEFAULT NULL,
  `ordername` varchar(45) DEFAULT NULL,
  `orderprice` double DEFAULT NULL,
  `orderrecipient` varchar(45) DEFAULT NULL,
  `orderdescription` varchar(45) DEFAULT NULL,
  `orderdetailsid` bigint DEFAULT NULL,
  `orderdate` datetime DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'fwe',1,'ffrwef','fewf',2,'2018-03-22 08:30:59'),(2,2,'fewrfw',2,'fewf','fewfw',3,'2018-03-22 08:30:59'),(3,3,'htrhe',3,'grger','fewrgew',1,'2018-03-22 08:30:59'),(4,3,'gerge',1,'htyhtr','fwwfw',2,'2018-03-22 08:30:59'),(5,2,'fewf',1,'fwefew','fewfw',3,'2018-03-22 08:30:59'),(6,2,'gwfew',1,'ewfwe','fwefw',3,'2018-03-22 08:30:59'),(7,1,'fefe',2,'fewfw','ewfewf',3,'2018-03-22 08:30:59'),(9,2,'fewfw',3,'wefew','fewf',3,'2018-03-22 08:30:59'),(10,4,'fewfg',3,'efwe','fwef',3,'2018-03-22 08:30:59');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productid` bigint NOT NULL AUTO_INCREMENT,
  `productname` varchar(45) DEFAULT NULL,
  `productimage` blob,
  `productdescription` varchar(45) DEFAULT NULL,
  `productprice` bigint DEFAULT NULL,
  PRIMARY KEY (`productid`),
  UNIQUE KEY `productid_UNIQUE` (`productid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Null',NULL,'Null',0),(10,'itemidk',NULL,'idk',30),(12,'itemidk',NULL,'idk',30),(13,'itemidk',NULL,'idk',30),(18,'itemidk',NULL,'idk',30),(19,'itemidk',NULL,'idk',30),(20,'itemidk',NULL,'idk',30),(21,'itemidk',NULL,'idk',30),(32,'itemidk',NULL,'null',30),(33,'1221',NULL,'null',12);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplierid` bigint NOT NULL AUTO_INCREMENT,
  `suppliername` varchar(45) DEFAULT NULL,
  `supplierdescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`supplierid`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'david electric company','Best company ever'),(13,'david electrics company','Best company ever'),(19,'JhonDoe','Secon Best Company Ever'),(20,'JhonDoe','Secon Best Company Ever'),(21,'JhonDoe','Secon Best Company Ever'),(22,'JhonDoe','Secon Best Company Ever'),(23,'JhonDoe','Secon Best Company Ever'),(24,'JhonDoe','Secon Best Company Ever'),(25,'JhonDoe','Secon Best Company Ever'),(26,'JhonDoe','Secon Best Company Ever'),(27,'JhonDoe','Secon Best Company Ever'),(28,'JhonDoe','Secon Best Company Ever'),(29,'JhonDoe','Secon Best Company Ever'),(30,'JhonDoe','Secon Best Company Ever'),(31,'JhonDoe','Secon Best Company Ever'),(32,'JhonDoe','Secon Best Company Ever'),(33,'JhonDoe','Secon Best Company Ever'),(34,'JhonDoe','Secon Best Company Ever'),(35,'JhonDoe','Secon Best Company Ever'),(36,'JhonDoe','Secon Best Company Ever'),(37,'JhonDoe','Secon Best Company Ever');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-12 17:57:31
