-- MySQL dump 10.13  Distrib 6.0.10-alpha, for Win32 (ia32)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	6.0.10-alpha-community

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
-- Table structure for table `contact_address`
--

DROP TABLE IF EXISTS `contact_address`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `contact_address` (
  `CONTACT_ADDRESS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `ADDRESS_TYPE` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_ADDRESS_ID`),
  KEY `FK40C57E9520905FF5` (`CONTACT_ID`),
  CONSTRAINT `FK40C57E9520905FF5` FOREIGN KEY (`CONTACT_ID`) REFERENCES `unregistered_contact` (`CONTACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `contact_address`
--

LOCK TABLES `contact_address` WRITE;
/*!40000 ALTER TABLE `contact_address` DISABLE KEYS */;
INSERT INTO `contact_address` VALUES (1,0,'home','30 Commercial Rd.\r\nFratton\r\nPORTSMOUTH\r\nHampshire',1),(2,1,'school','6 Xujiazhai, Huaqiaocun, Xinzhong Xiang, Tiantai Xian, ZHEJIANG, P.R. CHINA',1),(3,0,'company','Piedras 623\r\nPiso 2, depto 4, Capital Federal',2),(4,0,'home','15 Sample St\r\nEXAMPLE BAY  VIC',3),(5,1,'college','62 Renmin lu, Qingdao Shi, SHANDONG, P.R. CHINA',4),(6,1,'company','123 Imaginary Ave\r\nFLOREAT\r\nPERTH  WA',5);
/*!40000 ALTER TABLE `contact_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_email`
--

DROP TABLE IF EXISTS `contact_email`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `contact_email` (
  `CONTACT_EMAIL_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `EMAIL_TYPE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_EMAIL_ID`),
  KEY `FK28CB667D20905FF5` (`CONTACT_ID`),
  CONSTRAINT `FK28CB667D20905FF5` FOREIGN KEY (`CONTACT_ID`) REFERENCES `unregistered_contact` (`CONTACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `contact_email`
--

LOCK TABLES `contact_email` WRITE;
/*!40000 ALTER TABLE `contact_email` DISABLE KEYS */;
INSERT INTO `contact_email` VALUES (1,0,'friend','tomcat@gmail.com',1),(2,1,'student','tom06@mails.tsinghua.edu.cn',1),(3,0,'relations','jerry@disney.land',2),(4,1,'company','rach@cnn.com',3),(5,1,'general','rachael@hotmail.com',3),(6,0,'default','adniv@example.com',4),(7,0,'home','ross_g@gmail.com',5);
/*!40000 ALTER TABLE `contact_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_image`
--

DROP TABLE IF EXISTS `contact_image`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `contact_image` (
  `CONTACT_IMAGE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `IM_TYPE` varchar(255) DEFAULT NULL,
  `IM` varchar(255) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_IMAGE_ID`),
  KEY `FK2903C43C20905FF5` (`CONTACT_ID`),
  CONSTRAINT `FK2903C43C20905FF5` FOREIGN KEY (`CONTACT_ID`) REFERENCES `unregistered_contact` (`CONTACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `contact_image`
--

LOCK TABLES `contact_image` WRITE;
/*!40000 ALTER TABLE `contact_image` DISABLE KEYS */;
INSERT INTO `contact_image` VALUES (1,0,'qq','601524835',1),(2,1,'gtalk','tom06@mails.tsinghua.edu.cn',1),(3,0,'msn','sunnyrain',2),(4,0,'gtalk','jerry',2),(5,1,'qq','234533215',3),(6,1,'icq','mistery',4),(7,1,'gtalk','ross_g',5);
/*!40000 ALTER TABLE `contact_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_phone_number`
--

DROP TABLE IF EXISTS `contact_phone_number`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `contact_phone_number` (
  `CONTACT_PHONENO_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `PHONE_TYPE` varchar(255) DEFAULT NULL,
  `PHONE_NO` varchar(255) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_PHONENO_ID`),
  KEY `FKDF614D3920905FF5` (`CONTACT_ID`),
  CONSTRAINT `FKDF614D3920905FF5` FOREIGN KEY (`CONTACT_ID`) REFERENCES `unregistered_contact` (`CONTACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `contact_phone_number`
--

LOCK TABLES `contact_phone_number` WRITE;
/*!40000 ALTER TABLE `contact_phone_number` DISABLE KEYS */;
INSERT INTO `contact_phone_number` VALUES (1,0,'home','7670191',1),(2,1,'office','72737382',2),(3,0,'cellphone','13581979236',2),(4,1,'school','51532403',3),(5,0,'dorm','51532401',4),(6,1,'mobile','13810234572',5);
/*!40000 ALTER TABLE `contact_phone_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_url`
--

DROP TABLE IF EXISTS `contact_url`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `contact_url` (
  `CONTACT_URL_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `URL_TYPE` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_URL_ID`),
  KEY `FK82D7289020905FF5` (`CONTACT_ID`),
  CONSTRAINT `FK82D7289020905FF5` FOREIGN KEY (`CONTACT_ID`) REFERENCES `unregistered_contact` (`CONTACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `contact_url`
--

LOCK TABLES `contact_url` WRITE;
/*!40000 ALTER TABLE `contact_url` DISABLE KEYS */;
INSERT INTO `contact_url` VALUES (1,0,'tomcat','http://tomcat.apache.org/',1),(2,1,'homepage','http://jerry.ratnations.gov',2),(3,0,'blog','http://rainyday.blogspot.com',3),(4,1,'homepage','http://monica.me',4),(5,0,'photo','http://picasaweb.google.com/ross',5),(6,1,'twitter','http://twitter.com/iamross',5);
/*!40000 ALTER TABLE `contact_url` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_user`
--

DROP TABLE IF EXISTS `group_user`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `group_user` (
  `GROUP_ID` bigint(20) NOT NULL,
  `MEMBER_ID` bigint(20) NOT NULL,
  `IS_ADMINISTRATOR` int(11) DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`,`MEMBER_ID`),
  KEY `FK6B1EC1AB29F35450` (`MEMBER_ID`),
  KEY `FK6B1EC1AB81CBA255` (`GROUP_ID`),
  CONSTRAINT `FK6B1EC1AB29F35450` FOREIGN KEY (`MEMBER_ID`) REFERENCES `registered_user` (`USER_ID`),
  CONSTRAINT `FK6B1EC1AB81CBA255` FOREIGN KEY (`GROUP_ID`) REFERENCES `groups` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `group_user`
--

LOCK TABLES `group_user` WRITE;
/*!40000 ALTER TABLE `group_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `groups` (
  `GROUP_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATOR_NAME` varchar(255) DEFAULT NULL,
  `GROUP_NAME` varchar(255) DEFAULT NULL,
  `PERMISSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `notification` (
  `NOTIFICATION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOTIFICATION_CONTENT` varchar(255) DEFAULT NULL,
  `NOTIFICATION_TYPE` int(11) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `RECEIVER_ID` bigint(20) DEFAULT NULL,
  `SENDER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`NOTIFICATION_ID`),
  KEY `FKAD9970EBDBDDDAD5` (`SENDER_ID`),
  KEY `FKAD9970EB5CA038DB` (`RECEIVER_ID`),
  KEY `FKAD9970EB81CBA255` (`GROUP_ID`),
  CONSTRAINT `FKAD9970EB5CA038DB` FOREIGN KEY (`RECEIVER_ID`) REFERENCES `registered_user` (`USER_ID`),
  CONSTRAINT `FKAD9970EB81CBA255` FOREIGN KEY (`GROUP_ID`) REFERENCES `groups` (`GROUP_ID`),
  CONSTRAINT `FKAD9970EBDBDDDAD5` FOREIGN KEY (`SENDER_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registered_user`
--

DROP TABLE IF EXISTS `registered_user`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `registered_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BIRTHDAY` datetime DEFAULT NULL,
  `VIEW_BIRTHDAYDAY_PERMISSION` int(11) DEFAULT NULL,
  `VIEW_BIRTHYEAR_PERMISSION` int(11) DEFAULT NULL,
  `NAME_TO_DISPLAY` varchar(255) DEFAULT NULL,
  `GENDER` int(11) DEFAULT NULL,
  `VIEW_GENDER_PERMISSION` int(11) DEFAULT NULL,
  `IMAGE_URL` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `NOTES` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PERMISSION` int(11) DEFAULT NULL,
  `REG_EMAIL` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `registered_user`
--

LOCK TABLES `registered_user` WRITE;
/*!40000 ALTER TABLE `registered_user` DISABLE KEYS */;
INSERT INTO `registered_user` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123',NULL,'jinyq06@gmail.com','jinyq06');
/*!40000 ALTER TABLE `registered_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tag` (
  `TAG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TAG_NAME` varchar(255) DEFAULT NULL,
  `OWNER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`TAG_ID`),
  KEY `FK1437A3D144997` (`OWNER_ID`),
  CONSTRAINT `FK1437A3D144997` FOREIGN KEY (`OWNER_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_contact`
--

DROP TABLE IF EXISTS `tag_contact`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tag_contact` (
  `CONTACT_ID` bigint(20) NOT NULL,
  `TAG_ID` bigint(20) NOT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`CONTACT_ID`,`TAG_ID`),
  KEY `FKF0BD803B20905FF5` (`CONTACT_ID`),
  KEY `FKF0BD803B67264B35` (`TAG_ID`),
  CONSTRAINT `FKF0BD803B20905FF5` FOREIGN KEY (`CONTACT_ID`) REFERENCES `unregistered_contact` (`CONTACT_ID`),
  CONSTRAINT `FKF0BD803B67264B35` FOREIGN KEY (`TAG_ID`) REFERENCES `tag` (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tag_contact`
--

LOCK TABLES `tag_contact` WRITE;
/*!40000 ALTER TABLE `tag_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_user`
--

DROP TABLE IF EXISTS `tag_user`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tag_user` (
  `TAG_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`TAG_ID`,`USER_ID`),
  KEY `FK2E27570D12D997F` (`USER_ID`),
  KEY `FK2E2757067264B35` (`TAG_ID`),
  CONSTRAINT `FK2E2757067264B35` FOREIGN KEY (`TAG_ID`) REFERENCES `tag` (`TAG_ID`),
  CONSTRAINT `FK2E27570D12D997F` FOREIGN KEY (`USER_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tag_user`
--

LOCK TABLES `tag_user` WRITE;
/*!40000 ALTER TABLE `tag_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unregistered_contact`
--

DROP TABLE IF EXISTS `unregistered_contact`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `unregistered_contact` (
  `CONTACT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BIRTHDAY` varchar(255) DEFAULT NULL,
  `GENDER` int(11) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `NOTES` varchar(255) DEFAULT NULL,
  `PERMISSION` int(11) DEFAULT NULL,
  `RELATIONSHIP` varchar(255) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `OWNER_ID` bigint(20) DEFAULT NULL,
  `SHADOW_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_ID`),
  KEY `FKE243B59C3D144997` (`OWNER_ID`),
  KEY `FKE243B59C645F900A` (`SHADOW_ID`),
  KEY `FKE243B59C81CBA255` (`GROUP_ID`),
  CONSTRAINT `FKE243B59C3D144997` FOREIGN KEY (`OWNER_ID`) REFERENCES `registered_user` (`USER_ID`),
  CONSTRAINT `FKE243B59C645F900A` FOREIGN KEY (`SHADOW_ID`) REFERENCES `registered_user` (`USER_ID`),
  CONSTRAINT `FKE243B59C81CBA255` FOREIGN KEY (`GROUP_ID`) REFERENCES `groups` (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `unregistered_contact`
--

LOCK TABLES `unregistered_contact` WRITE;
/*!40000 ALTER TABLE `unregistered_contact` DISABLE KEYS */;
INSERT INTO `unregistered_contact` VALUES (1,'1988/02/11',0,'http://example.com/tom.jpg','Tom','tommy','a cat',0,'friend',NULL,1,NULL),(2,'1989/02/14',1,'http://example.com/jerry.jpg','Jerry','jerry','a rat',1,'acquaintance',NULL,1,NULL),(3,'1990/08/01',1,'http://example.com/rachael.jpg','Rachael','rachae','somebody',0,'sweetheart',NULL,1,NULL),(4,'1987/06/01',1,'http://example.com/monica.png','Monica','monica','somebody else',0,'met',NULL,1,NULL),(5,'1988/02/12',0,'http://example.com/ross.bmp','Ross','rossy','somebody else else',2,'co-worker',NULL,1,NULL);
/*!40000 ALTER TABLE `unregistered_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `user_address` (
  `USER_ADDRESS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FORMATTED` varchar(255) DEFAULT NULL,
  `PERM` int(11) DEFAULT NULL,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `ADDRESS_TYPE` varchar(255) DEFAULT NULL,
  `ZIP` varchar(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ADDRESS_ID`),
  KEY `FK9CCBB760D12D997F` (`USER_ID`),
  CONSTRAINT `FK9CCBB760D12D997F` FOREIGN KEY (`USER_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` VALUES (1,NULL,1,0,'home','100084',1);
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_email`
--

DROP TABLE IF EXISTS `user_email`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `user_email` (
  `USER_EMAIL_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERM` int(11) DEFAULT NULL,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `EMAIL_TYPE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_EMAIL_ID`),
  KEY `FKC60F5608D12D997F` (`USER_ID`),
  CONSTRAINT `FKC60F5608D12D997F` FOREIGN KEY (`USER_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `user_email`
--

LOCK TABLES `user_email` WRITE;
/*!40000 ALTER TABLE `user_email` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_image`
--

DROP TABLE IF EXISTS `user_image`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `user_image` (
  `USER_IMAGE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERM` int(11) DEFAULT NULL,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `IM_TYPE` varchar(255) DEFAULT NULL,
  `IM_VALUE` varchar(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_IMAGE_ID`),
  KEY `FKC647B3C7D12D997F` (`USER_ID`),
  CONSTRAINT `FKC647B3C7D12D997F` FOREIGN KEY (`USER_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `user_image`
--

LOCK TABLES `user_image` WRITE;
/*!40000 ALTER TABLE `user_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_phone_number`
--

DROP TABLE IF EXISTS `user_phone_number`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `user_phone_number` (
  `USER_PHONENO_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERM` int(11) DEFAULT NULL,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `PHONE_TYPE` varchar(255) DEFAULT NULL,
  `PHONE_NO` varchar(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_PHONENO_ID`),
  KEY `FKE5DB4ED12D997F` (`USER_ID`),
  CONSTRAINT `FKE5DB4ED12D997F` FOREIGN KEY (`USER_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `user_phone_number`
--

LOCK TABLES `user_phone_number` WRITE;
/*!40000 ALTER TABLE `user_phone_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_phone_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_url`
--

DROP TABLE IF EXISTS `user_url`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `user_url` (
  `USER_URL_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERM` int(11) DEFAULT NULL,
  `IS_PRIMARY` int(11) DEFAULT NULL,
  `URL_TYPE` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_URL_ID`),
  KEY `FK1ED77EDBD12D997F` (`USER_ID`),
  CONSTRAINT `FK1ED77EDBD12D997F` FOREIGN KEY (`USER_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `user_url`
--

LOCK TABLES `user_url` WRITE;
/*!40000 ALTER TABLE `user_url` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_url` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user`
--

DROP TABLE IF EXISTS `user_user`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `user_user` (
  `USER1_ID` bigint(20) NOT NULL,
  `USER2_ID` bigint(20) NOT NULL,
  `LEFT_PERM` int(11) DEFAULT NULL,
  `RIGHT_PERM` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER1_ID`,`USER2_ID`),
  KEY `FKBC185FBFCA0071A4` (`USER1_ID`),
  KEY `FKBC185FBFCA00E603` (`USER2_ID`),
  CONSTRAINT `FKBC185FBFCA0071A4` FOREIGN KEY (`USER1_ID`) REFERENCES `registered_user` (`USER_ID`),
  CONSTRAINT `FKBC185FBFCA00E603` FOREIGN KEY (`USER2_ID`) REFERENCES `registered_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `user_user`
--

LOCK TABLES `user_user` WRITE;
/*!40000 ALTER TABLE `user_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-05-09 18:26:22
