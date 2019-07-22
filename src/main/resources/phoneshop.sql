-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 22 Lip 2019, 16:59
-- Wersja serwera: 10.3.16-MariaDB
-- Wersja PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `phoneshop`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `order`
--

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `total_price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `order_list`
--

CREATE TABLE `order_list` (
  `order_list_id` int(11) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `is_accessory` bit(1) DEFAULT NULL,
  `items_number` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `picture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` decimal(19,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `product`
--

INSERT INTO `product` (`product_id`, `description`, `is_accessory`, `items_number`, `name`, `picture`, `price`) VALUES
(2, '<p>- CPU: 1.4 GHz</p><p>- Internal Memory: 16 GB</p><p>- RAM memory: 1 GB</p>', b'0', 5, 'Samsung Galaxy S3', 'https://i.imgur.com/n8JWrnM.jpg', '499.99'),
(3, '<p>- CPU: 2.5 GHz 4 cores</p><p>- Internal Memory: 16 GB</p><p>- RAM memory: 2 GB</p>', b'0', 3, 'Xiaomi Mi4', 'https://i.imgur.com/2Wir6Vq.jpg', '399.99'),
(4, '<p>- CPU: 2.26 GHz 4 cores</p><p>- Internal Memory: 16 GB</p><p>- RAM memory: 2 GB</p>', b'0', 1, 'LG G2', 'https://i.imgur.com/lvxNmuY.jpg', '299.99'),
(5, '<p>- CPU: 1.3 GHz</p><p>- Internal Memory: 16 GB</p><p>- RAM memory: 1 GB</p>', b'0', 10, 'iPhone 5S', 'https://i.imgur.com/WTzFEnx.jpg', '799.99'),
(6, '<p>- CPU: 2.45 GHz 8 cores</p><p>- Internal Memory: 128 GB</p><p>- RAM memory: 8 GB</p>', b'0', 3, 'OnePlus 5', 'https://i.imgur.com/tbYHBzj.jpg', '1899.99'),
(7, '<p>Magnetic flip cover for iPhone 5S</p>', b'1', 20, 'Mercury Flip Case for iPhone 5S', 'https://i.imgur.com/GNkiZOY.jpg', '30.00'),
(9, '<p>Transparent back case with pink ring on the edges for iPhone 5/5S/5C</p>', b'1', 5, 'Jelly Case Mercury Ring 2 for iPhone 5', 'https://i.imgur.com/i23fry0.jpg', '25.00'),
(18, '<p>- CPU: 1.4 GHz</p><p>- Internal Memory: 8 GB</p><p>- RAM memory: 1 GB</p>', b'0', 3, 'Samsung Galaxy S3C', 'https://i.imgur.com/n8JWrnM.jpg', '439.99'),
(17, '<p>- CPU: 1.4 GHz</p><p>- Internal Memory: 32 GB</p><p>- RAM memory: 1 GB</p>', b'0', 5, 'Samsung Galaxy S3E', 'https://i.imgur.com/n8JWrnM.jpg', '513.99');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`user_id`, `active`, `email`, `name`, `password`, `phone_number`, `surname`) VALUES
(1, 1, 'beniamin.abramowicz@gmail.com', 'Beniamin', '$2a$10$guVtQb4bka8UvdeZ32TzN.z87TtQe5cy6o.eveMiP7Q1gBnIsg2Ge', '123456789', 'Abramowicz'),
(2, 1, 'jan.kowalski@gmail.com', 'Jan', '$2a$10$guVtQb4bka8UvdeZ32TzN.z87TtQe5cy6o.eveMiP7Q1gBnIsg2Ge', '987654321', 'Kowalski');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`),
  ADD UNIQUE KEY `idUserProduct_UNIQUE` (`order_id`),
  ADD KEY `fk_UserProduct_User1_idx` (`user_id`);

--
-- Indeksy dla tabeli `order_list`
--
ALTER TABLE `order_list`
  ADD PRIMARY KEY (`order_list_id`),
  ADD KEY `fk_Order_OrderItemList_idx` (`order_id`),
  ADD KEY `fk_Product_OrderItemList_idx` (`product_id`);

--
-- Indeksy dla tabeli `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indeksy dla tabeli `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indeksy dla tabeli `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `order`
--
ALTER TABLE `order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `order_list`
--
ALTER TABLE `order_list`
  MODIFY `order_list_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT dla tabeli `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
