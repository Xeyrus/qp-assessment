USE `grocery_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--

INSERT INTO `users` 
VALUES 
('user','{bcrypt}$2a$10$UOoujBeUy2MUXM6B84JqUu7EJA4Asv/b1N.TcdiulZTozLqCWNMtG',1),
('admin','{bcrypt}$2a$10$qiYH8IR3PXeA4oX6FgQxtuEtG.TtGj6WBWtXkIQSTeMME6AMa0Xw6',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('user','ROLE_USER'),
('admin','ROLE_USER'),
('admin','ROLE_ADMIN');