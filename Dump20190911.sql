-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: wetech_admin
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `testing_template`
--

DROP TABLE IF EXISTS `testing_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testing_template` (
  `testing_template_id` int(11) NOT NULL,
  `testing_template_group_id` int(11) DEFAULT NULL,
  `template_name` varchar(128) DEFAULT NULL,
  `test_type` int(11) DEFAULT NULL,
  `sort_level` int(11) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `test_timeout` int(11) DEFAULT NULL,
  `test_interval` int(11) DEFAULT NULL,
  `test_execute_count` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by_no` int(11) DEFAULT NULL,
  `modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_by_no` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testing_template_parameter_dns`
--

DROP TABLE IF EXISTS `testing_template_parameter_dns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testing_template_parameter_dns` (
  `testing_template_parameter_id` int(11) NOT NULL,
  `testing_template_id` int(11) DEFAULT NULL,
  `packet_count` int(11) DEFAULT NULL,
  `ignore_count` int(11) DEFAULT NULL,
  `round_item_count` int(11) DEFAULT NULL,
  `packet_timeout` int(11) DEFAULT NULL,
  `spacing_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`testing_template_parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testing_template_parameter_ping`
--

DROP TABLE IF EXISTS `testing_template_parameter_ping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testing_template_parameter_ping` (
  `testing_template_parameter_id` int(11) NOT NULL,
  `testing_template_id` int(11) DEFAULT NULL,
  `packet_count` int(11) DEFAULT NULL,
  `spaceing_time` int(11) DEFAULT NULL,
  `packet_timeout` int(11) DEFAULT NULL,
  `payload_size` int(11) DEFAULT NULL,
  `payload_data` int(11) DEFAULT NULL,
  `max_ttl` int(11) DEFAULT NULL,
  `tos` int(11) DEFAULT NULL,
  PRIMARY KEY (`testing_template_parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testing_template_parameter_speed`
--

DROP TABLE IF EXISTS `testing_template_parameter_speed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testing_template_parameter_speed` (
  `testing_template_parameter_id` int(11) NOT NULL,
  `testing_template_id` int(11) DEFAULT NULL,
  `host_Ips` varchar(256) DEFAULT NULL,
  `max_test_time` int(11) DEFAULT NULL,
  `min_test_time` int(11) DEFAULT NULL,
  `continue_times` int(11) DEFAULT NULL,
  `jitter_throughput` int(11) DEFAULT NULL,
  `request_timeout` int(11) DEFAULT NULL,
  `request_piece_size` int(11) DEFAULT NULL,
  `payload_size` int(11) DEFAULT NULL,
  `download_size` int(11) DEFAULT NULL,
  PRIMARY KEY (`testing_template_parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testing_template_parameter_trace`
--

DROP TABLE IF EXISTS `testing_template_parameter_trace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testing_template_parameter_trace` (
  `testing_template_parameter_id` int(11) NOT NULL,
  `testing_template_id` int(11) DEFAULT NULL,
  `payload_size` int(11) DEFAULT NULL,
  `protocol_type` int(11) DEFAULT NULL,
  `max_hops` int(11) DEFAULT NULL,
  `reply_timeout` int(11) DEFAULT NULL,
  `packet_count` int(11) DEFAULT NULL,
  `spacing_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`testing_template_parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testing_template_parameter_web`
--

DROP TABLE IF EXISTS `testing_template_parameter_web`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testing_template_parameter_web` (
  `testing_template_parameter_id` int(11) NOT NULL,
  `testing_template_id` int(11) DEFAULT NULL,
  `max_download_size` int(11) DEFAULT NULL,
  `max_thread_count` int(11) DEFAULT NULL,
  `max_sub_count` int(11) DEFAULT NULL,
  `max_sub_save_count` int(11) DEFAULT NULL,
  `item_timeout` int(11) DEFAULT NULL,
  `use_dns_cache` int(11) DEFAULT NULL,
  `user_agent` varchar(64) DEFAULT NULL,
  `max_page_depth` int(11) DEFAULT NULL,
  `valid_response_codes` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`testing_template_parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testing_template_target`
--

DROP TABLE IF EXISTS `testing_template_target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testing_template_target` (
  `testing_template_target_id` int(11) NOT NULL,
  `testing_template_id` int(11) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `node_ip` varchar(256) DEFAULT NULL,
  `sort_level` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `target_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`testing_template_target_id`)
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

-- Dump completed on 2019-09-11 18:02:07
