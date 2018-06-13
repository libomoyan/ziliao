# Host: localhost  (Version 5.0.51b-community-nt)
# Date: 2017-06-27 19:16:12
# Generator: MySQL-Front 6.0  (Build 1.57)


#
# Structure for table "h_admin"
#

CREATE TABLE `h_admin` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `level` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "h_admin"
#

INSERT INTO `h_admin` VALUES (1,'admin','admin','超级');

#
# Structure for table "h_client"
#

CREATE TABLE `h_client` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "h_client"
#

INSERT INTO `h_client` VALUES (1,'123','lx123456','870734985@qq.com');

#
# Structure for table "h_order"
#

CREATE TABLE `h_order` (
  `id` bigint(20) NOT NULL auto_increment,
  `servicer_name` varchar(255) default NULL,
  `total_time` int(11) default NULL,
  `ordercheck` varchar(255) default NULL,
  `client_name` varchar(255) default NULL,
  `kehu_tel` varchar(255) default NULL,
  `create_date` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "h_order"
#


#
# Structure for table "h_servicer"
#

CREATE TABLE `h_servicer` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "h_servicer"
#

INSERT INTO `h_servicer` VALUES (1,'辽宁工程技术大学','龙湾大街188号','0429-58888');

#
# Structure for table "h_bike"
#

CREATE TABLE `h_bike` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `price` double default NULL,
  `yajin` double default NULL,
  `pic_url` varchar(255) default NULL,
  `count` int(11) default NULL,
  `servicer_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB6E0DD584C6C5EE7` (`servicer_id`),
  CONSTRAINT `FKB6E0DD584C6C5EE7` FOREIGN KEY (`servicer_id`) REFERENCES `h_servicer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "h_bike"
#

INSERT INTO `h_bike` VALUES (1,'永久',50,88,'www.永久.com',9,1),(2,'凤凰',60,88,'www.凤凰.com',10,1);

#
# Structure for table "h_item"
#

CREATE TABLE `h_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `count` int(11) default NULL,
  `order_id` bigint(20) default NULL,
  `bike_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB6E4348A156CAEE7` (`bike_id`),
  KEY `FKB6E4348AAF824BCD` (`order_id`),
  CONSTRAINT `FKB6E4348AAF824BCD` FOREIGN KEY (`order_id`) REFERENCES `h_order` (`id`),
  CONSTRAINT `FKB6E4348A156CAEE7` FOREIGN KEY (`bike_id`) REFERENCES `h_bike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "h_item"
#

