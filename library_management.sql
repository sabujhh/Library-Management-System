-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 29, 2019 at 11:22 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library management`
--

-- --------------------------------------------------------

--
-- Table structure for table `admininfo`
--

CREATE TABLE `admininfo` (
  `adminID` int(100) NOT NULL,
  `adminEmail` varchar(255) NOT NULL,
  `adminPassword` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admininfo`
--

INSERT INTO `admininfo` (`adminID`, `adminEmail`, `adminPassword`) VALUES
(1, 'admin1@gmail.com', 1234),
(2, 'admin2@gmail.com', 2345),
(3, 'admin3@gmail.com', 3456);

-- --------------------------------------------------------

--
-- Table structure for table `bookinfo`
--

CREATE TABLE `bookinfo` (
  `bookTitle` varchar(255) NOT NULL,
  `bookISBN` varchar(255) NOT NULL,
  `bookAuthor` varchar(255) NOT NULL,
  `bookPublisher` varchar(255) NOT NULL,
  `NumCopies` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookinfo`
--

INSERT INTO `bookinfo` (`bookTitle`, `bookISBN`, `bookAuthor`, `bookPublisher`, `NumCopies`) VALUES
('Java : How to Program', '01-2345-6789', 'Deitel, Neil', 'Java', 5),
('Head First Java', '02-3456-7890', 'Sierra, Patrick', 'Oracle', 3),
('Pattern Recognition', '03-4567-8901', 'Herbert, Stany', 'Osbome', 4),
('Computer Vision', '04-5678-9012', 'Andrew, Lielie', 'Stanford', 5),
('GRE Verbal Grail', '05-6789-0123', 'Aristotle', 'ETS', 7),
('Introduction to Algorithms ', '06-7890-1234', 'Thomas H. Cormen  ', 'Yale', 11),
('Deep learning with Python ', '07-8901-2345', 'Bengio, Courville ', 'Caltech', 8);

-- --------------------------------------------------------

--
-- Table structure for table `issuedbooks`
--

CREATE TABLE `issuedbooks` (
  `bookTitle` varchar(255) NOT NULL,
  `bookISBN` varchar(255) NOT NULL,
  `bookAuthor` varchar(255) NOT NULL,
  `bookPublisher` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `issuedbooks`
--

INSERT INTO `issuedbooks` (`bookTitle`, `bookISBN`, `bookAuthor`, `bookPublisher`) VALUES
('Java : How to Program', '01-2345-6789', 'Deitel, Neil', 'Java'),
('Head First Java', '02-3456-7890', 'Sierra, Patrick', 'Oracle'),
('Pattern Recognition', '03-4567-8901', 'Herbert, Stany', 'Osbome'),
('Introduction to Algorithms ', '06-7890-1234', 'Thomas H. Cormen  ', 'Yale');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `userID` int(100) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `userEmail` varchar(255) NOT NULL,
  `userPassword` varchar(255) NOT NULL,
  `confirmPass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`userID`, `Name`, `userEmail`, `userPassword`, `confirmPass`) VALUES
(1, 'Sabuj', 'sabuj.hh@gmail.com', '1234', '1234'),
(2, 'Hasan', 'hasan@gmail.com', '2345', '2345'),
(3, 'Hasib', 'hasib@gmail.com', '3456', '3456'),
(4, 'Rahim', 'rahim@gmail.com', '4567', '4567'),
(5, 'Ahsan', 'ahsan@gmail.com', '6bbfb83a57eb8a23fab347c1aa9bb818', '6bbfb83a57eb8a23fab347c1aa9bb818'),
(6, 'samin', 'samin', 'b93cac6e0272abb54732c2f381efed70', 'b93cac6e0272abb54732c2f381efed70');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admininfo`
--
ALTER TABLE `admininfo`
  ADD PRIMARY KEY (`adminID`);

--
-- Indexes for table `bookinfo`
--
ALTER TABLE `bookinfo`
  ADD PRIMARY KEY (`bookISBN`);

--
-- Indexes for table `issuedbooks`
--
ALTER TABLE `issuedbooks`
  ADD PRIMARY KEY (`bookISBN`);

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admininfo`
--
ALTER TABLE `admininfo`
  MODIFY `adminID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `userID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
