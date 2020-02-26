-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: service
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(100) NOT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `description` varchar(100) NOT NULL,
  `activity_address` varchar(100) NOT NULL,
  `activity_data` date NOT NULL,
  `numberSeat` varchar(100) NOT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'Queen\'s concert',NULL,'Concert of the legendary Queen','Minsk, Pobeditelej str., \"Prime Hall\"','2020-03-21','2s-100s'),(2,'Hockey championship',NULL,'Hockey championship among professional teams of europe','Minsk, Pobeditelej str., \"Minsk Arena\"','2020-02-26','11nn-34mn'),(3,'Night Movie',NULL,'3 most popular films for one night in the open air','Minsk, Borovaya','2020-07-20','1-100'),(4,'Opera Rigoletto',NULL,'Work of  Giuseppe Verdi in 2 acts','Minsk, Parizhskaya Kommuna sq, \"\"The Bolshoi Theatre of Belarus','2020-04-25','2-85'),(5,'Ballet Swan lake',NULL,'Based on the works of Peter Tchaikovsky. Ballet in 3 acts','Minsk, Parizhskaya Kommuna sq, \"\"The Bolshoi Theatre of Belarus','2020-02-22','5f-89'),(6,'Cinema',NULL,'Tickets to the cinema.  Only in our cinema are the newest and most popular films','Minsk, Lenina st.','2020-01-01','1-100'),(7,'Concert',NULL,'Tickets on popular concerts.','Minsk, Lenina st.','2020-02-02','2-200'),(8,'Exhibition',NULL,'The most popular art exhibitions.','Minsk, Y. Kolasa st.','2020-01-10','3-100'),(9,'Footbal championship',NULL,'Football championship among professional teams of europe','Minsk, stadium \"Dinamo\"','2020-07-07','1-20');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_type`
--

DROP TABLE IF EXISTS `event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_type` (
  `event_type_id` int NOT NULL AUTO_INCREMENT,
  `event_type` varchar(100) NOT NULL,
  PRIMARY KEY (`event_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_type`
--

LOCK TABLES `event_type` WRITE;
/*!40000 ALTER TABLE `event_type` DISABLE KEYS */;
INSERT INTO `event_type` VALUES (1,'Concert'),(2,'Ballet'),(3,'Cinema'),(4,'Opera'),(5,'Sport'),(6,'Thetre '),(7,'Exhibition');
/*!40000 ALTER TABLE `event_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info`
--

DROP TABLE IF EXISTS `info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_type` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_type_idx` (`event_type`),
  CONSTRAINT `event_type` FOREIGN KEY (`event_type`) REFERENCES `user_comment` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info`
--

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `event_id` int NOT NULL,
  `quantity` int NOT NULL,
  `event_type_id` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `event_id_idx` (`event_id`),
  KEY `event_type_id_idx` (`event_type_id`),
  CONSTRAINT `event_id` FOREIGN KEY (`event_id`) REFERENCES `activity` (`activity_id`),
  CONSTRAINT `event_type_id` FOREIGN KEY (`event_type_id`) REFERENCES `event_type` (`event_type_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,2,1,2,1,'2020-03-19'),(2,2,2,1,5,'2020-02-20'),(3,3,3,1,3,'2020-06-25'),(4,3,4,3,4,'2020-04-21'),(5,7,5,2,2,'2020-02-21'),(6,7,6,3,3,'2019-12-29'),(7,8,7,1,1,'2020-01-18'),(8,8,8,3,7,'2020-01-05'),(9,10,9,3,5,'2020-06-29'),(10,10,2,1,5,'2020-02-23');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_role` enum('USER','ADMINISTRATOR') NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_surname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `date_of_birth` date NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ADMINISTRATOR','Anastasia','Shpakova','shpakavaa@gmail.com','Nastya','Nastya210789','375291255196','1989-07-21'),(2,'USER','Kate','Smith','kate123@gmail.com','Katyaaa','Katya12345','375331111111','2000-01-01'),(3,'USER','William','Jonson','william123@gmail.com','William1','William12345','375441111111','1990-08-15'),(7,'USER','Arkadiy','Ivanov','1255196@list.ru','Arkadiy12','$2a$12$zZ/6HqjpKy1XDWkRll1pyuD33kCzKRt0IZRwAtDiK2WMn.XA3fPBi','375291222221','2000-07-20'),(8,'USER','Bruce','Willis','will89@list.ru','BruceWillis','$2a$12$ASebfRW/lD8yCijNWOs88.eO6XOjZfGSdr5VJG035X.LMtke2D/X2','375291222225','1960-02-01'),(10,'USER','Kate','Smith','katesm@gmail.com','KateSmi1','$2a$12$idCoQ.ynLQu1fulajpGWNuvx8MlgpkWTsb729dlIzM7776aF0jbXK','375895412547','1995-05-20');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_comment`
--

DROP TABLE IF EXISTS `user_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  ` date_of_comment` timestamp NOT NULL,
  `ticketDate` date NOT NULL,
  `user_id` int NOT NULL,
  `comment` varchar(200) NOT NULL,
  `commentator_id` int NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_comment`
--

LOCK TABLES `user_comment` WRITE;
/*!40000 ALTER TABLE `user_comment` DISABLE KEYS */;
INSERT INTO `user_comment` VALUES (1,'2020-03-24 21:00:00','2020-03-19',2,'Cool',1),(2,'2020-02-19 21:00:00','2020-02-20',2,'Not bad',2),(3,'2020-06-30 21:00:00','2020-06-25',3,'It was amazing!!',3),(4,'2020-05-01 21:00:00','2020-04-21',3,'Not really...',4),(5,'2020-12-30 21:00:00','2020-12-29',7,'There are was bad sound',5),(6,'2020-02-29 21:00:00','2020-01-18',8,'Not very friemdly people...',6),(7,'2020-06-30 21:00:00','2020-06-29',10,'It was the best day in my life!',7),(8,'2020-05-08 21:00:00','2020-02-21',7,'Great',8);
/*!40000 ALTER TABLE `user_comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-26 15:46:47
