# 面试题

CREATE TABLE `db_user_info` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phonenumber` varchar(15) DEFAULT '',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (1, 'terry', '123890', '13453255');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (2, 'bob', '123456', '134511111');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (3, 'mary', '123234', '136511111');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (4, 'jack', '123234', '136512345');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (5, 'aoli', '123234', '136514324');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (6, 'joy', '123234', '136534345');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (7, 'jarden', '123234', '136543522');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (8, 'lance', '123234', '136562445');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (9, 'chen', '123234', '136536433');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (10, 'tang', '123234', '136536433');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (11, 'tina', '123234', '136548658');
INSERT INTO `db_user_info`(`uid`, `nickname`, `password`, `phonenumber`) VALUES (12, 'luna', '123234', '136558563');


问答题：

1.阅读项目结构，简单介绍下每个package的作用是什么？ 
分层，分功能，按模块编写代码
2.项目如何启动; 启动后访问的端口是什么？
启动方法：运行ApplicationName.main()函数
Tomcat started on port(s): 7778 (http) with context path
3.项目中的2个拦截器的作用是什么？

4.写一个注册接口:
    nickname不能重复；密码，手机号码不能为空；
    注册用户信息为(nickname=面试者,password=123456,phonenumber=1345876954)。

5.写一个分页接口:
    取出手机号码为 1345开头的用户列表信息,每页3条记录。