DROP DATABASE IF EXISTS `ipk_2022_db`;

CREATE DATABASE `ipk_2022_db` DEFAULT CHARACTER SET utf8;

USE `ipk_2022_db`;

CREATE TABLE `product` (
    `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `price` INTEGER NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

INSERT INTO `product`
(`id`, `name`     , `price`) VALUES
(1   , "Картофель", 234    ),
(2   , "Авокадо"  , 345    ),
(3   , "Мандарин" , 123    );
