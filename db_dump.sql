-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: university
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `application_id` int(11) NOT NULL AUTO_INCREMENT,
  `out_of_competition` tinyint(1) NOT NULL DEFAULT '0',
  `date` date NOT NULL,
  `confirmed` tinyint(1) NOT NULL DEFAULT '0',
  `profile_profile_id` int(11) NOT NULL,
  `profile_faculty_faculty_id` int(11) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`application_id`,`profile_profile_id`,`profile_faculty_faculty_id`),
  KEY `fk_application_profile1_idx` (`profile_profile_id`,`profile_faculty_faculty_id`),
  CONSTRAINT `fk_application_profile1` FOREIGN KEY (`profile_profile_id`, `profile_faculty_faculty_id`) REFERENCES `profile` (`profile_id`, `faculty_faculty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,0,'2016-02-25',0,6,1,'DELETED'),(2,0,'2016-02-27',0,6,1,'DELETED'),(3,0,'2016-02-27',0,6,1,'DELETED'),(4,0,'2016-02-27',0,6,1,'ACTIVE'),(5,0,'2016-02-28',0,11,1,'ACTIVE'),(6,0,'2016-02-28',0,13,1,'ACTIVE');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `faculty_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `free_quota` int(11) NOT NULL,
  `paid_quota` int(11) NOT NULL,
  `free_point` int(11) NOT NULL,
  `paid_point` int(11) NOT NULL,
  `terms_terms_id` int(11) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`faculty_id`,`terms_terms_id`),
  KEY `fk_faculty_terms1_idx` (`terms_terms_id`),
  CONSTRAINT `fk_faculty_terms1` FOREIGN KEY (`terms_terms_id`) REFERENCES `terms` (`terms_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Инженерно-Экономический',60,140,0,0,1,'ACTIVE');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_has_subject`
--

DROP TABLE IF EXISTS `faculty_has_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty_has_subject` (
  `faculty_faculty_id` int(11) NOT NULL,
  `subject_subject_id` int(11) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`faculty_faculty_id`,`subject_subject_id`),
  KEY `fk_faculty_has_subject_subject1_idx` (`subject_subject_id`),
  KEY `fk_faculty_has_subject_faculty1_idx` (`faculty_faculty_id`),
  CONSTRAINT `fk_faculty_has_subject_faculty1` FOREIGN KEY (`faculty_faculty_id`) REFERENCES `faculty` (`faculty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_faculty_has_subject_subject1` FOREIGN KEY (`subject_subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_has_subject`
--

LOCK TABLES `faculty_has_subject` WRITE;
/*!40000 ALTER TABLE `faculty_has_subject` DISABLE KEYS */;
INSERT INTO `faculty_has_subject` VALUES (1,1,'ACTIVE'),(1,2,'ACTIVE'),(1,3,'ACTIVE');
/*!40000 ALTER TABLE `faculty_has_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `passport_id` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `birth_date` date NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` longtext NOT NULL,
  `points` int(11) NOT NULL,
  `medal` varchar(45) NOT NULL DEFAULT 'NONE',
  `free_form` tinyint(1) NOT NULL DEFAULT '0',
  `privilegies` longtext,
  `applied` tinyint(1) NOT NULL DEFAULT '0',
  `faculty_faculty_id` int(11) NOT NULL,
  `user_user_id` int(11) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`profile_id`,`faculty_faculty_id`,`user_user_id`),
  KEY `fk_profile_faculty1_idx` (`faculty_faculty_id`),
  KEY `fk_profile_user1_idx` (`user_user_id`),
  CONSTRAINT `fk_profile_faculty1` FOREIGN KEY (`faculty_faculty_id`) REFERENCES `faculty` (`faculty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_profile_user1` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (6,'BM1994853','Евгений','Александрович','Чайчиц','1996-04-03','+375 (29) 299-14-59','г. Минск, ул. Беды, д. 4',330,'SILVER',1,'Олимпиада по астрономии последнее место',1,1,16,'ACTIVE'),(7,'te1111111','test','test','test','2016-02-10','+444 (44) 444-44-44','test',200,'GOLDEN',0,'test',0,1,19,'DELETED'),(8,'te1111112','test','test','test','2016-02-11','+555 (55) 555-55-55','test',200,'SILVER',0,NULL,0,1,20,'DELETED'),(9,'AD1111111','ADMIN','ADMIN','ADMIN','2016-02-11','+555 (55) 555-55-55','ADMIN',200,'SILVER',0,'ADMIN',0,1,22,'ACTIVE'),(10,'aa1111111','aaa','aaaa','aaa','2016-02-06','+111 (11) 111-11-11','aaa',400,'GOLDEN',0,'aaa',0,1,23,'ACTIVE'),(11,'xx7777777','Иван','Иванович','Иванов','1999-02-13','+999 (99) 999-99-99','gjhhds',220,'GOLDEN',1,NULL,1,1,24,'ACTIVE'),(13,'AD1234567','Иван','Иванович','Иванов','1998-02-13','+375 (29) 299-14-59','г. Минск',312,'GOLDEN',1,NULL,1,1,25,'ACTIVE'),(15,'zz1111111','zzzzzzzz','zzzzzz','zzzzzz','2016-02-11','+888 (88) 888-88-88','zzzzzzzz',360,'SILVER',0,NULL,0,1,27,'ACTIVE'),(16,'BM1994852','Чайчиц','Чицлщвопщукд','рлплррл','2014-02-17','+377 (77) 777-77-77','рлоаовдавлра',210,'SILVER',0,'иоррешгупреоедркгнгн',0,1,28,'ACTIVE'),(17,'rg6454326','bfgfngf','gfjnb','gjnfbg','2016-02-10','+555 (55) 555-55-55','dfhdtd',160,'NONE',0,NULL,0,1,30,'ACTIVE'),(18,'kj9085908','ÑÐ³ÐºÑÐ¿Ð´ÑÐºÑ','Ð¾Ð»ÐºÑÐ¿Ð»ÐºÑÐ»','478594','2016-02-17','+588 (88) 888-88-88','ÐµÐ½ÐºÐµÐ¿ÑÐ°Ð¿',340,'GOLDEN',1,NULL,0,1,31,'ACTIVE');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `min_points` int(11) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Русский/Белорусский язык',10,'ACTIVE'),(2,'Математика',15,'ACTIVE'),(3,'Физика',15,'ACTIVE');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terms`
--

DROP TABLE IF EXISTS `terms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terms` (
  `terms_id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`terms_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terms`
--

LOCK TABLES `terms` WRITE;
/*!40000 ALTER TABLE `terms` DISABLE KEYS */;
INSERT INTO `terms` VALUES (1,'2016-01-20','2016-02-28','ACTIVE');
/*!40000 ALTER TABLE `terms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password_hash` varchar(100) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'STUDENT',
  `status` varchar(45) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@university.com','21232f297a57a5a743894a0e4a801fc3','ADMIN','ACTIVE'),(16,'zhenyachay@gmail.com','010c8d472c5c9500bcf23816db0f9e3f','STUDENT','ACTIVE'),(19,'test@test.com','05a671c66aefea124cc08b76ea6d30bb','STUDENT','DELETED'),(20,'test@test.test','05a671c66aefea124cc08b76ea6d30bb','STUDENT','DELETED'),(22,'admin@admin.com','73acd9a5972130b75066c82595a1fae3','STUDENT','ACTIVE'),(23,'a@a.a','0b4e7a0e5fe84ad35fb5f95b9ceeac79','STUDENT','ACTIVE'),(24,'x@x.com','45ed9cc2f92b77cd8b2f5bd59ff635f8','STUDENT','ACTIVE'),(25,'zhenyachay@mail.ru','e10adc3949ba59abbe56e057f20f883e','STUDENT','ACTIVE'),(27,'z@z.z','453e41d218e071ccfb2d1c99ce23906a','STUDENT','ACTIVE'),(28,'admin@university.chjshd','df64dc2eb4a0b85091dd31eb4923eaac','STUDENT','ACTIVE'),(29,'admin@university.chjshd','df64dc2eb4a0b85091dd31eb4923eaac','STUDENT','ACTIVE'),(30,'qwe@r.ty','d8578edf8458ce06fbc5bb76a58c5ca4','STUDENT','ACTIVE'),(31,'qwe@rty','a36eff62909b61dbe2d554d27c57ffcf','STUDENT','ACTIVE'),(32,'привет','608333adc72f545078ede3aad71bfe74','STUDENT','ACTIVE'),(33,'привет','c2a05319fe7480c9a0cd530b2a3b47dd','STUDENT','ACTIVE'),(34,'support@university.com','434990c8a25d2be94863561ae98bd682','SUPPORT','ACTIVE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-28 15:49:36
