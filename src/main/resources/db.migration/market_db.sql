# Host: 192.168.99.100  (Version 5.7.21)
# Date: 2018-06-01 15:53:32
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "Inventory"
#

CREATE TABLE `Inventory` (
  `id` int(10) NOT NULL DEFAULT '0',
  `count` int(10) NOT NULL DEFAULT '0',
  `lockedCount` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "Inventory"
#

INSERT INTO `Inventory` VALUES (1,113,100),(2,30,5),(3,100,25),(4,100,94);

#
# Structure for table "DeliveryForm"
#

CREATE TABLE `DeliveryForm` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orderId` int(10) NOT NULL DEFAULT '0',
  `logisticsStatus` varchar(20) NOT NULL DEFAULT '',
  `sendTime` varchar(40) NOT NULL DEFAULT 'null',
  `signedTime` varchar(40) NOT NULL DEFAULT 'null',
  `deliveryMan` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "DeliveryForm"
#


#
# Structure for table "Product"
#

CREATE TABLE `Product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '',
  `description` varchar(100) DEFAULT NULL,
  `price` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

#
# Data for table "Product"
#

INSERT INTO `Product` VALUES (1,'test666','test',1000),(2,'noodles','test',20),(3,'noodles','cocal',5),(4,'pureWater','water',3);

#
# Structure for table "ProductSnap"
#

CREATE TABLE `ProductSnap` (
  `snapId` int(10) NOT NULL AUTO_INCREMENT,
  `id` int(10) NOT NULL DEFAULT '0',
  `orderId` int(10) NOT NULL DEFAULT '0',
  `productName` varchar(40) NOT NULL DEFAULT '',
  `productDescription` varchar(100) DEFAULT NULL,
  `purchasePrice` varchar(40) NOT NULL DEFAULT '',
  `purchaseCount` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`snapId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=gbk;

#
# Data for table "ProductSnap"
#

INSERT INTO `ProductSnap` VALUES (1,1,1,'test666','test','1000',3),(2,2,1,'noodles','test','20',1),(3,3,1,'noodles','cocal','5',5),(4,1,2,'test666','test','1000',3),(5,2,2,'noodles','test','20',1),(6,3,2,'noodles','cocal','5',5),(7,4,3,'pureWater','water','3',3),(8,2,3,'noodles','test','20',1),(9,3,3,'noodles','cocal','5',5),(10,4,4,'pureWater','water','3',3),(11,2,4,'noodles','test','20',1),(12,3,4,'noodles','cocal','5',5),(13,4,5,'pureWater','water','3',3),(14,2,5,'noodles','test','20',1),(15,3,5,'noodles','cocal','5',5);

#
# Structure for table "UserOrder"
#

CREATE TABLE `UserOrder` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `totalPrice` varchar(20) NOT NULL DEFAULT '',
  `status` varchar(20) NOT NULL DEFAULT '',
  `createTime` varchar(20) NOT NULL DEFAULT '',
  `finishTime` varchar(20) DEFAULT NULL,
  `paidTime` varchar(20) DEFAULT NULL,
  `withdrawnTime` varchar(20) DEFAULT NULL,
  `userId` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "UserOrder"
#

INSERT INTO `UserOrder` VALUES (1,'3045','unPaid','2018-06-01','','','',1),(2,'3045','unPaid','2018-06-01','','','',1),(3,'54','unPaid','2018-06-01','','','',1),(4,'54','unPaid','2018-06-01','','','',1),(5,'54','unPaid','2018-06-01','','','',1);
