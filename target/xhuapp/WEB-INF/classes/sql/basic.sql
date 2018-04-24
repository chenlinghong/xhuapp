
# 本SQL文件主要用于开发阶段测试使用

# 创建数据库
CREATE DATABASE app;

# 创建用户基本信息表
CREATE TABLE tb_user(
  user_id INT not NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(20),
  password VARCHAR(40),
  gender VARCHAR(5),
  birthday DATE,
  head_portrail VARCHAR(100),
  introduce VARCHAR(400),
  telphone VARCHAR(13)
);

# 创建dynamic表
CREATE table tb_dynamic(
  dynamic_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL ,
  introduce VARCHAR(300) NOT NULL ,
  picture VARCHAR(200),
  user_id_f int NOT NULL
);


# 创建农场表
CREATE TABLE tb_farm (
  farm_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20),
  longitude VARCHAR(30) NOT NULL ,
  latitude VARCHAR(30) NOT NULL ,
  location_name VARCHAR(50),
  user_id INT NOT NULL UNIQUE ,
  area INT,
  introduce VARCHAR(300),
  photo VARCHAR(500)
);