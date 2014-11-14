-- Adminer 4.1.0 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `image` varchar(100) NOT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `commandes`;
CREATE TABLE `commandes` (
  `idCommande` int(11) NOT NULL AUTO_INCREMENT,
  `idSession` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`idCommande`),
  KEY `idSession` (`idSession`),
  KEY `idProduit` (`idProduit`),
  CONSTRAINT `commandes_ibfk_1` FOREIGN KEY (`idSession`) REFERENCES `sessions` (`idSession`),
  CONSTRAINT `commandes_ibfk_2` FOREIGN KEY (`idSession`) REFERENCES `sessions` (`idSession`),
  CONSTRAINT `commandes_ibfk_3` FOREIGN KEY (`idProduit`) REFERENCES `produits` (`idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `produits`;
CREATE TABLE `produits` (
  `idProduit` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `image` varchar(100) NOT NULL,
  `description` varchar(300) NOT NULL,
  `prix` float NOT NULL,
  `idCategorie` int(11) NOT NULL,
  PRIMARY KEY (`idProduit`),
  KEY `idCategorie` (`idCategorie`),
  CONSTRAINT `produits_ibfk_1` FOREIGN KEY (`idCategorie`) REFERENCES `categories` (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sessions`;
CREATE TABLE `sessions` (
  `idSession` int(11) NOT NULL AUTO_INCREMENT,
  `numeroTable` int(11) NOT NULL,
  `expired` bit(1) NOT NULL,
  PRIMARY KEY (`idSession`),
  KEY `numeroTable` (`numeroTable`),
  CONSTRAINT `sessions_ibfk_1` FOREIGN KEY (`numeroTable`) REFERENCES `tables` (`numeroTable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tables`;
CREATE TABLE `tables` (
  `numeroTable` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`numeroTable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2014-11-14 12:32:25
