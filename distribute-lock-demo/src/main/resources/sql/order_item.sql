CREATE TABLE `order_item` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `order_id` bigint(20) DEFAULT NULL,
                              `product_id` bigint(20) DEFAULT NULL,
                              `purchase_price` decimal(16,2) DEFAULT NULL,
                              `purchase_num` bigint(20) DEFAULT NULL,
                              `create_time` time DEFAULT NULL,
                              `create_user` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                              `update_time` time DEFAULT NULL,
                              `update_user` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
