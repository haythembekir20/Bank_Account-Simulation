-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2021 at 12:44 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fx_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `NomCli` varchar(50) NOT NULL,
  `PrenomCli` varchar(50) NOT NULL,
  `adrCli` varchar(50) NOT NULL,
  `titulaire` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`idClient`, `NomCli`, `PrenomCli`, `adrCli`, `titulaire`) VALUES
(2, 'haythem', 'bekir', 'nainou', 'mm'),
(3, 'rami', 'chargui', 'djarba', 'm'),
(4, 'selim', 'khfifi', 'nabeul', 'aa'),
(5, 'oubay', 'ouani', 'dar cha3ben', 'mm'),
(6, 'aziz', 'abdelouai', 'doqsd', 'jfddf'),
(7, 'firas', 'ruine', 'darcha3ben', '89');

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

CREATE TABLE `compte` (
  `num_compte` int(11) NOT NULL,
  `solde` double NOT NULL,
  `decouvertMax` double NOT NULL,
  `debitMax` double NOT NULL,
  `situation_compte` varchar(50) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `compte`
--

INSERT INTO `compte` (`num_compte`, `solde`, `decouvertMax`, `debitMax`, `situation_compte`, `idClient`) VALUES
(1, -9, -1600.5, 10000, 'rouge', 2),
(2, 200, -5000, 300, 'normal', 2),
(3, 1600, 50, 35, 'normal', 2),
(4, -10, -100, 88, 'rouge', 3),
(5, 0, 99, 88, 'normal', 3),
(6, 0, 500, 300, 'normal', 2),
(7, 0, 520, 300, 'normal', 3),
(8, 0, -1000, 500, 'normal', 3),
(9, 0, -1000, 500.08, 'normal', 5),
(10, 10000, 500, 30, 'normal', 3),
(11, 0, -500, 100, '', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`);

--
-- Indexes for table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`num_compte`),
  ADD KEY `ck1` (`idClient`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `compte`
--
ALTER TABLE `compte`
  MODIFY `num_compte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `ck1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
