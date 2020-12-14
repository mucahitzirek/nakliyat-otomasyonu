-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: nak
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
-- Table structure for table `arac`
--

DROP TABLE IF EXISTS `arac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arac` (
  `idarac` int NOT NULL AUTO_INCREMENT,
  `plaka` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `araccins` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `yukcins` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `yukmiktar` float DEFAULT NULL,
  `yukfiyat` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `yuklemeyeri` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `yuklemetarihi` date DEFAULT NULL,
  `bosaltmayeri` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `bosaltmatarihi` date DEFAULT NULL,
  `komisyon` int DEFAULT NULL,
  `soforid` int DEFAULT NULL,
  `firmaid` int DEFAULT NULL,
  `kullaniciid` int DEFAULT NULL,
  PRIMARY KEY (`idarac`),
  KEY `fk_arac_sofor` (`soforid`),
  KEY `fk_arac_firma` (`firmaid`),
  KEY `fk_arac_kullanici` (`kullaniciid`),
  CONSTRAINT `fk_arac_firma` FOREIGN KEY (`firmaid`) REFERENCES `firma` (`idfirma`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_arac_kullanici` FOREIGN KEY (`kullaniciid`) REFERENCES `kullanici` (`idkullanici`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_arac_sofor` FOREIGN KEY (`soforid`) REFERENCES `sofor` (`idsofor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arac`
--

LOCK TABLES `arac` WRITE;
/*!40000 ALTER TABLE `arac` DISABLE KEYS */;
INSERT INTO `arac` VALUES (1,'31 GBH 3265','13.60 Duz','Demir',25,'50','Hatay','2020-05-31','Diyarbakır','2020-06-01',100,1,1,1),(2,'31 GBH 3265','13.60 Duz','Kömür',24,'70','Osmaniye','2020-06-04','Bursa','2020-06-05',140,1,2,2),(3,'31 GBH 3265','13.60 Duz','Demir',28,'65','Adana','2020-06-05','Muğla','2020-06-06',1300,1,3,3),(4,'31 GBH 3265','13.60 Duz','Demir',25,'70','Mersin','2020-06-05','İzmir','2020-06-06',1500,1,1,4),(5,'31 GBH 3265','13.60 Duz','Demir',30,'40','Antep','2020-06-08','Mardin','2020-06-08',80,1,2,1),(6,'02 ADF 325','Kisa','Demir',36,'60','Hatay','2020-06-10','Mardin','2020-06-11',100,2,1,2),(7,'02 ADF 325','Kisa','Demir',26,'50','Hatay','2020-06-10','Diyarbakır','2020-06-11',80,2,1,3),(8,'02 ADF 325','Kisa','Mısır',26,'50','Hatay','2020-06-10','Batman','2020-06-11',80,2,1,4),(9,'35 KLM 351','Damperli','Demir',26,'50','Hatay','2020-05-13','Bitlis','2020-05-14',50,3,2,1),(10,'35 KLM 351','Damperli','Kömür',27,'70','Hatay','2020-06-07','Bolu','2020-06-09',120,3,3,1),(11,'35 KLM 351','Damperli','Kömür',21,'65','Osmaniye','2020-06-06','Balıkesir','2020-06-08',100,3,3,3),(12,'35 KLM 351','Damperli','Kömür',21,'40','Mersin','2020-06-06','Bingöl','2020-06-08',70,3,3,3),(13,'28 AAL 654','13.60 Duz','Kömür',24,'68','Adana','2020-06-07','Ordu','2020-06-08',1000,4,2,4),(14,'28 AAL 654','13.60 Duz','Kömür',30,'40','Adana','2020-06-07','Siir','2020-06-08',70,4,2,4),(15,'03 NBB 7489','Jumbo','Sac',28,'70','Hatay','2020-06-11','Van','2020-06-13',1500,5,1,3),(27,'23 GHT 1231','Kisa','Demir',32,'65','Hatay','2020-06-02','Batman','2020-06-04',120,1,2,1),(29,'25 ASD 2435','13.60 Duz','Demir',35,'25','Adana','2020-06-02','Diyarbakır','2020-06-03',50,1,3,1),(31,'35 AD 254','Tenteli Optima','Demir',25,'50','Adana','2020-06-02','Diyarbakır','2020-06-03',100,1,1,1),(32,'35 ADF 123','13.60 Duz','Kömür',35,'60','Hatay','2020-06-02','Diyarbakır','2020-06-03',80,2,2,1),(34,'32 KJH 123','13.60 Duz','Demir',25,'50','Hatay','2020-06-02','Diyarbakır','2020-06-03',100,1,2,1),(36,'34 AKD 123','Kisa','Demir',28,'45','Hatay','2020-06-02','Batman','2020-06-03',100,1,2,1);
/*!40000 ALTER TABLE `arac` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `kar` AFTER INSERT ON `arac` FOR EACH ROW begin 
declare topgelir int;
declaRE topgider int; 
declare netkar int;
set topgelir=(select sum(komisyon) from arac);
set topgider=(select sum(gider) from gider);
set netkar=topgelir-topgider;
update karzarar set gelir=topgelir,gider=topgider,kar=netkar where idkarzarar=1;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `karzararAylikarac` AFTER INSERT ON `arac` FOR EACH ROW begin 
declare topgelir int;
declaRE topgider int; 
declare netkar int;
set topgelir=(SELECT sum(komisyon) FROM arac WHERE yuklemetarihi between  DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW());
set topgider=(SELECT sum(gider) FROM gider WHERE tarih between  DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW());
set netkar=topgelir-topgider;
update karzarar set gelir=topgelir,gider=topgider,kar=netkar where idkarzarar=2;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `firma`
--

DROP TABLE IF EXISTS `firma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firma` (
  `idfirma` int NOT NULL AUTO_INCREMENT,
  `ad` varchar(45) DEFAULT NULL,
  `adres` varchar(45) DEFAULT NULL,
  `yuk` int DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idfirma`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firma`
--

LOCK TABLES `firma` WRITE;
/*!40000 ALTER TABLE `firma` DISABLE KEYS */;
INSERT INTO `firma` VALUES (1,'ornekfirma43123123','hatays',131124,'3123','132','1321'),(2,'ornekfirma43123123','hatays',131124,'3123','132','1321'),(3,'asfa','asfasf',55555,'1','132','1'),(5,'ornekfirma43123','hatays',131124,'3123','132','1321'),(6,'fasf222','hatays',131124,'3123','132','1321'),(7,'asfasf','asf',23,'asf','asf','af');
/*!40000 ALTER TABLE `firma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gider`
--

DROP TABLE IF EXISTS `gider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gider` (
  `idgider` int NOT NULL AUTO_INCREMENT,
  `aciklama` varchar(45) DEFAULT NULL,
  `gider` int DEFAULT NULL,
  `tarih` date DEFAULT NULL,
  `kullaniciid` int DEFAULT NULL,
  PRIMARY KEY (`idgider`),
  KEY `fk_gider_kullanici` (`kullaniciid`),
  CONSTRAINT `fk_gider_kullanici` FOREIGN KEY (`kullaniciid`) REFERENCES `kullanici` (`idkullanici`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gider`
--

LOCK TABLES `gider` WRITE;
/*!40000 ALTER TABLE `gider` DISABLE KEYS */;
INSERT INTO `gider` VALUES (1,'Fatura',50,'2020-06-11',1),(2,'Fatura',100,'2020-06-10',2),(3,'Fatura',150,'2020-06-09',3),(7,'Gitti',30,'2020-06-08',2),(8,'Gider',60,'2020-06-15',2),(9,'Gider',80,'2020-06-20',4),(16,'gitmis',100,'2020-06-02',1),(18,'market2',500,'2020-06-29',1),(19,'merkaet',200,'2020-06-30',1),(20,'market',200,'2020-07-03',1);
/*!40000 ALTER TABLE `gider` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `giderler` AFTER INSERT ON `gider` FOR EACH ROW begin 
declare topgelir int;
declaRE topgider int;
declare netkar int;
set topgelir=(select sum(komisyon) from arac);
set topgider=(select sum(gider) from gider);
set netkar=topgelir-topgider;
update karzarar set gelir=topgelir,gider=topgider,kar=netkar where idkarzarar=1;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `karzararAylik` AFTER INSERT ON `gider` FOR EACH ROW begin 
declare topgelir int;
declaRE topgider int; 
declare netkar int;
set topgelir=(SELECT sum(komisyon) FROM arac WHERE yuklemetarihi between  DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW());
set topgider=(SELECT sum(gider) FROM gider WHERE tarih between  DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW());
set netkar=topgelir-topgider;
update karzarar set gelir=topgelir,gider=topgider,kar=netkar where idkarzarar=2;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `karzarar`
--

DROP TABLE IF EXISTS `karzarar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `karzarar` (
  `idkarzarar` int NOT NULL AUTO_INCREMENT,
  `gelir` int DEFAULT NULL,
  `gider` int DEFAULT NULL,
  `kar` int DEFAULT NULL,
  PRIMARY KEY (`idkarzarar`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `karzarar`
--

LOCK TABLES `karzarar` WRITE;
/*!40000 ALTER TABLE `karzarar` DISABLE KEYS */;
INSERT INTO `karzarar` VALUES (1,6840,1670,5170),(2,6140,1570,4570);
/*!40000 ALTER TABLE `karzarar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici`
--

DROP TABLE IF EXISTS `kullanici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanici` (
  `idkullanici` int NOT NULL AUTO_INCREMENT,
  `ad` varchar(45) DEFAULT NULL,
  `soyad` varchar(45) DEFAULT NULL,
  `kullaniciad` varchar(45) DEFAULT NULL,
  `kullanicisifre` varchar(45) DEFAULT NULL,
  `kullaniciemail` varchar(45) DEFAULT NULL,
  `yoneticiid` int DEFAULT NULL,
  `kullanicidurum` tinyint DEFAULT NULL,
  PRIMARY KEY (`idkullanici`),
  KEY `fk_kullanici_yonetici` (`yoneticiid`),
  CONSTRAINT `fk_kullanici_yonetici` FOREIGN KEY (`yoneticiid`) REFERENCES `yonetici` (`idyonetici`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici`
--

LOCK TABLES `kullanici` WRITE;
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` VALUES (1,'Mücahit','Yusuf','muco','123','foreign133@gmail.com',1,1),(2,'Birsen','Okcu','birsen','1212','birsenokcu@gmail.com',2,1),(3,'Baran','Beger','baran','baran123baran','baranbeger7231@gmail.com',2,1),(4,'Mert','Elibol','mert','mert123','mertelibol@gmail.com',1,1),(5,'KadirDef','Kalmaz','kadir','kalmaz','kadirkalmaz@gmial.com',1,0),(16,'yenikullanici','silinecek','silbeni','123','benimemailim@gmail.com',15,1),(17,'silkullanici','sil','kimkuollanici','123','silinecek@gmail.com',16,1),(18,'aa','aa','a','a','aa@gmail.com',17,1),(19,'asd','asd','asd','asd','asd@gmail.com',18,1);
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notlar`
--

DROP TABLE IF EXISTS `notlar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notlar` (
  `idnot` int NOT NULL AUTO_INCREMENT,
  `baslik` varchar(45) DEFAULT NULL,
  `detay` varchar(300) DEFAULT NULL,
  `tarih` date DEFAULT NULL,
  `kullaniciid` int DEFAULT NULL,
  PRIMARY KEY (`idnot`),
  KEY `fk_notlar_kullanici` (`kullaniciid`),
  CONSTRAINT `fk_notlar_kullanici` FOREIGN KEY (`kullaniciid`) REFERENCES `kullanici` (`idkullanici`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notlar`
--

LOCK TABLES `notlar` WRITE;
/*!40000 ALTER TABLE `notlar` DISABLE KEYS */;
INSERT INTO `notlar` VALUES (2,'Not2Baslik','e','2020-06-05',2),(3,'Baslik3','denem','2020-06-20',3),(4,'Baslık','Deneme','2020-05-05',4),(6,'Yeni not baslık Deneme',' Demneme Not içeriğiDemneme Not içeriği\nDemneme Not içeriği\nDemneme Not içeriği\nDemneme Not içeriği','2020-06-02',1),(13,'hjöhö','gjöhö','2020-06-30',1);
/*!40000 ALTER TABLE `notlar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sofor`
--

DROP TABLE IF EXISTS `sofor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sofor` (
  `idsofor` int NOT NULL AUTO_INCREMENT,
  `ad` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `ehliyetno` varchar(45) DEFAULT NULL,
  `ruhsatno` varchar(45) DEFAULT NULL,
  `hesapno` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsofor`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sofor`
--

LOCK TABLES `sofor` WRITE;
/*!40000 ALTER TABLE `sofor` DISABLE KEYS */;
INSERT INTO `sofor` VALUES (1,'Furkan Avcı','0532 231 3235','123','123','123'),(2,'Fatih Mutlu','0544 564 7865','123123','123123','123123'),(3,'Kaan Getir','0545 123 5645','111','111','111'),(4,'İsmet Gider','0532 456 3252','234','234','234'),(5,'Ahmet Geldi','0535 254 3256','456','456','456');
/*!40000 ALTER TABLE `sofor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yonetici`
--

DROP TABLE IF EXISTS `yonetici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yonetici` (
  `idyonetici` int NOT NULL AUTO_INCREMENT,
  `ad` varchar(45) DEFAULT NULL,
  `soyad` varchar(45) DEFAULT NULL,
  `girisad` varchar(45) DEFAULT NULL,
  `girissifre` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idyonetici`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yonetici`
--

LOCK TABLES `yonetici` WRITE;
/*!40000 ALTER TABLE `yonetici` DISABLE KEYS */;
INSERT INTO `yonetici` VALUES (1,'Mucahit Yusuf','Zirek','mucahit','123','foreign133@gmail.com'),(2,'Ali','Gökkaya','ali','123','foreign133@gmail.com'),(15,'yeniyonetici','yonet','yonetici','123','foreign133@gmail.com'),(16,'yeniyonet','yonetsil','silinecekyonetici','123','foreign133@gmail.com'),(17,'a','a','a','a','a@gail.com'),(18,'asd','asd','asd','asd','foreign133@gmail.com');
/*!40000 ALTER TABLE `yonetici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'nak'
--

--
-- Dumping routines for database 'nak'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-21 13:19:00
