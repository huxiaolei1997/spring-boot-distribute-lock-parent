CREATE TABLE `product` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `product_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                           `price` decimal(16,2) DEFAULT NULL,
                           `count` bigint(20) DEFAULT NULL,
                           `product_desc` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                           `create_time` time DEFAULT NULL,
                           `create_user` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                           `update_time` time DEFAULT NULL,
                           `update_user` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;