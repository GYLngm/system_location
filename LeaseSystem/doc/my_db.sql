-- ----------------------------
-- 建数据库
-- ----------------------------
create DATABASE my_db character set utf8;



-- ----------------------------
-- 建数据表
-- ----------------------------

use my_db;
-- ----------------------------
-- 管理员表
-- ----------------------------
DROP TABLE IF EXISTS t_admins;
CREATE TABLE t_admins (
  id varchar(40) NOT NULL unique,
  password varchar(40) NOT NULL,
  PRIMARY KEY  (id)
);


-- ----------------------------
-- 插入管理员数据：用户名和密码都是 admin
-- ----------------------------
INSERT INTO `t_admins` VALUES ('admin', 'admin');



-- ----------------------------
-- 租客表
-- ----------------------------
DROP TABLE IF EXISTS t_renters;
CREATE TABLE t_renters (
  id varchar(40) NOT NULL unique,
  password varchar(40) NOT NULL,
  familyName varchar(40) NOT NULL,
  lastName varchar(40) NOT NULL,
  houseNum varchar(40) NOT NULL,
  street varchar(40) NOT NULL,
  postcode varchar(40) NOT NULL,
  city varchar(40) NOT NULL,
  PRIMARY KEY  (id)
);



-- ----------------------------
-- 汽车表
-- ----------------------------
DROP TABLE IF EXISTS t_autos;
CREATE TABLE t_autos (
  id int(11) NOT NULL auto_increment,
  bianHao varchar(40) NOT NULL,
  km int(11) NOT NULL,
  price float NOT NULL,
  storeNum int(11) NOT NULL,
  xingHao varchar(40) NOT NULL,
  PRIMARY KEY  (id)
);



-- ----------------------------
-- 摩托车表
-- ----------------------------
DROP TABLE IF EXISTS t_motos;
CREATE TABLE t_motos (
  id int(11) NOT NULL auto_increment,
  bianHao varchar(40) NOT NULL,
  km int(11) NOT NULL,
  price float NOT NULL,
  storeNum int(11) NOT NULL,
  horsepower int(11) NOT NULL,
  PRIMARY KEY  (id)
);



-- ----------------------------
-- 订单表
-- ----------------------------
DROP TABLE IF EXISTS t_orders;
CREATE TABLE t_orders (
  id int(11) NOT NULL auto_increment,
  bianHao varchar(40) NOT NULL,
  xingHao varchar(40) NOT NULL,
  km int(11) NOT NULL,
  price float NOT NULL,
  startTime varchar(40) NOT NULL,
  endTime varchar(40) NOT NULL,
  renterName varchar(40) NOT NULL,
  hourseNunber int(11) NOT NULL,
  type int(11) NOT NULL,
  PRIMARY KEY  (id)
);
