CREATE TABLE `order` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `order_status` tinyint(1) DEFAULT NULL,
                         `receiver_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `receiver_mobile` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `order_amount` decimal(16,2) DEFAULT NULL,
                         `create_time` time DEFAULT NULL,
                         `create_user` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `update_time` time DEFAULT NULL,
                         `update_user` varchar(0) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
