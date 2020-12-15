CREATE TABLE `distribute_lock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_code` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `business_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;