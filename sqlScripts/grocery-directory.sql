CREATE DATABASE  IF NOT EXISTS `grocery_directory`;
USE `grocery_directory`;

--
-- Table structure for table `grocery`
--

DROP TABLE IF EXISTS `grocery`;

CREATE TABLE `grocery` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` int DEFAULT 0,
  `count` int DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `grocery`
--

INSERT INTO `employee` VALUES 
	(1,'Mango',200,2),
	(2,'Apple',100,5),
	(3,'Pen',10,40),
	(4,'Book',50,4),
	(5,'TV',15000,3);

