drop schema if exists `ds`;
drop schema if exists `ds-0`;
drop schema if exists `ds-1`;
create schema `ds`;
create schema `ds-0`;
create schema `ds-1`;

--

CREATE TABLE `ds`.`t_order`
(
    `order_id`    bigint(20) NOT NULL COMMENT '订单ID',
    `user_id`     bigint(20) NOT NULL COMMENT '用户ID',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    `version`     int DEFAULT 0 COMMENT '版本号',
    PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ds-0`.`t_order` LIKE `ds`.`t_order`;

CREATE TABLE `ds-1`.`t_order` LIKE `ds`.`t_order`;

--

CREATE TABLE `ds`.`t_order_item`
(
    `order_item_id` bigint(20) NOT NULL COMMENT '订单项ID',
    `order_id`      bigint(20) NOT NULL COMMENT '订单ID',
    `user_id`     bigint(20) NOT NULL COMMENT '用户ID',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    `version`       int DEFAULT 0 COMMENT '版本号',
    PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ds-0`.`t_order_item` LIKE `ds`.`t_order_item`;

CREATE TABLE `ds-1`.`t_order_item` LIKE `ds`.`t_order_item`;

--

truncate table `ds-0`.`t_order`;
truncate table `ds-0`.`t_order_item`;
truncate table `ds-1`.`t_order`;
truncate table `ds-1`.`t_order_item`;

--

drop schema if exists `ds`;
drop schema if exists `ds-0`;
drop schema if exists `ds-1`;
