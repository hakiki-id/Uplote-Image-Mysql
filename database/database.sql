-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 29, 2016 at 06:30 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kkn`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_kegiatan`
--

CREATE TABLE `tb_kegiatan` (
  `id` int(11) NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nama` varchar(50) NOT NULL,
  `keterangan` text NOT NULL,
  `photo` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kegiatan`
--

INSERT INTO `tb_kegiatan` (`id`, `date_time`, `nama`, `keterangan`, `photo`) VALUES
(7, '2016-06-28 23:56:24', 'Survey Lapangan Bola', 'Kegiatan ini dilaksanakan untuk memenuhi tugas kuliah di kanjuruhan', 'http://belajar.com/uplote/img_kegiatan/20160628165624301000.jpeg'),
(8, '2016-06-29 00:00:25', 'Kegiatan salah seni dan budah ', 'suleman', 'http://belajar.com/uplote/img_kegiatan/20160628170025880400.jpeg'),
(9, '2016-06-29 00:02:56', 'Malam Minggu Miko', 'Kegiatan ini bertujuan memberikan pengalaman berharga untuk para teman-teman yang ikut serta dalam melkukan antisipasi yang nyata', 'http://belajar.com/uplote/img_kegiatan/20160628170256960100.jpeg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_kegiatan`
--
ALTER TABLE `tb_kegiatan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_kegiatan`
--
ALTER TABLE `tb_kegiatan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
