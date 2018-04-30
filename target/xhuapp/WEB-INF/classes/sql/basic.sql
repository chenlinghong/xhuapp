
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

# 向农场插入数据
INSERT INTO tb_farm(name,longitude,latitude,user_id,introduce)
    VALUES ('test','112.12','123.123',1,'This is a test.');

###创建dynamic表
CREATE table
  dynamic(
  dynamic_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL ,
  introduce VARCHAR(300) NOT NULL ,
  picture VARCHAR(200),
  user_id_f int NOT NULL,
  dynamic_type bool NOT NULL,
  address VARCHAR (100) ,
  prize int DEFAULT 0,
  look_persons int DEFAULT 0
);

###创建视频表
CREATE  TABLE
  tb_video(
  video_id int not null PRIMARY KEY auto_increment,
  title VARCHAR (255) not null  ,
  videopath VARCHAR (255) NOT NULL ,
  user_id_f int not null,
  video_type int,
  introduce VARCHAR (255),
  address VARCHAR (100),
  prize int DEFAULT 0,
  look_persons int DEFAULT 0,
  video_picture VARCHAR (255) not NULL
);

###创建评论表
create table
  tb_commont(
  commont_id int not null primary key auto_increment,
  user_id_f int not null,
  commont_body varchar(255),
  father_commont_id int default -1,
  son_commont_id varchar(255)
);

