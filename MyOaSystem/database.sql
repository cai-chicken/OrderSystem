/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.1.52-community : Database - myoasystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myoasystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myoasystem`;

/*Table structure for table `application` */

DROP TABLE IF EXISTS `application`;

CREATE TABLE `application` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `applyTime` datetime DEFAULT NULL,
  `applicationTemplateId` bigint(20) DEFAULT NULL,
  `applicantId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5CA40550D28E82BD` (`applicationTemplateId`),
  KEY `FK5CA40550B057F8BA` (`applicantId`),
  CONSTRAINT `FK5CA40550B057F8BA` FOREIGN KEY (`applicantId`) REFERENCES `user` (`id`),
  CONSTRAINT `FK5CA40550D28E82BD` FOREIGN KEY (`applicationTemplateId`) REFERENCES `applicationtemplate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `application` */

insert  into `application`(`id`,`path`,`title`,`status`,`applyTime`,`applicationTemplateId`,`applicantId`) values (3,'J:\\eclipseForJ2eeProject\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\MyOaSystem\\WEB-INF\\upload_files/2015/11/09/890bbbcf-1017-4f95-9ca2-66321504b83a','请假条_超级管理员_2015-11-09','审批中','2015-11-09 11:20:02',1,4),(7,'J:\\eclipseForJ2eeProject\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\MyOaSystem\\WEB-INF\\upload_files/2015/11/09/7551d6d2-1f7c-43a2-a269-f461c58410a5','请假条_张三_2015-11-09','已通过','2015-11-09 15:37:26',1,1),(8,'J:\\eclipseForJ2eeProject\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\MyOaSystem\\WEB-INF\\upload_files/2015/11/18/9443512f-33e3-4e14-bfb1-4582fdeae7b6','请假条_张三_2015-11-18','已通过','2015-11-18 21:07:59',1,1);

/*Table structure for table `applicationtemplate` */

DROP TABLE IF EXISTS `applicationtemplate`;

CREATE TABLE `applicationtemplate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `processDefinitionKey` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `applicationtemplate` */

insert  into `applicationtemplate`(`id`,`name`,`processDefinitionKey`,`path`) values (1,'请假条','员工请假流程','J:\\eclipseForJ2eeProject\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\MyOaSystem\\WEB-INF\\upload_files/2015/11/09/c7795809-e87c-4603-9a21-a678b68d518f');

/*Table structure for table `approveinfo` */

DROP TABLE IF EXISTS `approveinfo`;

CREATE TABLE `approveinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approveTime` datetime DEFAULT NULL,
  `approval` bit(1) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `applicationId` bigint(20) DEFAULT NULL,
  `approverId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK29E16D9B89EEAA29` (`applicationId`),
  KEY `FK29E16D9B869D5BBD` (`approverId`),
  CONSTRAINT `FK29E16D9B869D5BBD` FOREIGN KEY (`approverId`) REFERENCES `user` (`id`),
  CONSTRAINT `FK29E16D9B89EEAA29` FOREIGN KEY (`applicationId`) REFERENCES `application` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `approveinfo` */

insert  into `approveinfo`(`id`,`approveTime`,`approval`,`comment`,`applicationId`,`approverId`) values (1,'2015-11-09 11:27:37','','',3,107),(2,'2015-11-09 15:46:42','','部门经理同意',7,107),(3,'2015-11-09 15:47:52','','不同意',7,108),(4,'2015-11-18 21:09:10','','好',8,107),(5,'2015-11-18 21:13:44','','不同意',8,108);

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK328E43527C01AA69` (`parentId`),
  CONSTRAINT `FK328E43527C01AA69` FOREIGN KEY (`parentId`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`name`,`description`,`parentId`) values (3,'人事部','谁知道他是干嘛的?',NULL),(4,'技术部','搞技术的撒',NULL),(5,'研发部','技术研发',4),(6,'测试部','专门搞测试的',4),(7,'财务部','算钱的',3),(8,'运营部','这又是干嘛的？',NULL);

/*Table structure for table `forum` */

DROP TABLE IF EXISTS `forum`;

CREATE TABLE `forum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `topicCount` int(11) DEFAULT NULL,
  `articleCount` int(11) DEFAULT NULL,
  `lastTopicId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lastTopicId` (`lastTopicId`),
  KEY `FK5D18D21EC382151` (`lastTopicId`),
  CONSTRAINT `FK5D18D21EC382151` FOREIGN KEY (`lastTopicId`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `forum` */

insert  into `forum`(`id`,`name`,`description`,`position`,`topicCount`,`articleCount`,`lastTopicId`) values (7,'java开发','搞java开发',7,3,7,9),(13,'Android开发','搞Android开发的',13,1,2,18),(15,'IOS','搞Ios开发的',15,1,1,19);

/*Table structure for table `privilege` */

DROP TABLE IF EXISTS `privilege`;

CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA1FAF6B18D4BF3E4` (`parentId`),
  CONSTRAINT `FKA1FAF6B18D4BF3E4` FOREIGN KEY (`parentId`) REFERENCES `privilege` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `privilege` */

insert  into `privilege`(`id`,`name`,`url`,`parentId`) values (1,'系统管理',NULL,NULL),(2,'岗位管理','/role_list',1),(3,'部门管理','/department_list',1),(4,'用户管理','/user_list',1),(5,'岗位添加','/role_add',2),(6,'岗位删除','/role_delete',2),(7,'岗位修改','/role_edit',2),(8,'岗位列表','/role_list',2),(9,'部门列表','/department_list',3),(10,'部门删除','/department_delete',3),(11,'部门添加','/department_add',3),(12,'部门修改','/department_edit',3),(13,'用户列表','/user_list',4),(14,'用户删除','/user_delete',4),(15,'用户添加','/user_add',4),(16,'用户修改','/user_edit',4),(17,'初始化密码','/user_initPassword',4),(18,'网上交流',NULL,NULL),(19,'论坛管理','/forumManage_list',18),(20,'论坛','/forum_list',18),(21,'论坛添加','/forumManage_add',19),(22,'论坛删除','/forumManage_delete',19),(23,'论坛修改','/forumManage_edit',19),(24,'论坛列表','/forumManage_list',19),(25,'论坛上移','/forumManage_moveUp',19),(26,'论坛下移','/forumManage_moveDown',19),(27,'审批流转',NULL,NULL),(28,'审批流程管理','/processDefinition_list',27),(29,'申请模板管理','/applicationTemplate_list',27),(30,'起草申请','/flow_applicationTemplateList',27),(31,'待我审批','/flow_myTaskList',27),(32,'我的申请查询','/flow_myApplicationList',27);

/*Table structure for table `privilege_role` */

DROP TABLE IF EXISTS `privilege_role`;

CREATE TABLE `privilege_role` (
  `roleId` bigint(20) NOT NULL,
  `privilegeId` bigint(20) NOT NULL,
  PRIMARY KEY (`privilegeId`,`roleId`),
  KEY `FK85E8C94456587C2B` (`privilegeId`),
  KEY `FK85E8C9441C8C61D9` (`roleId`),
  CONSTRAINT `FK85E8C9441C8C61D9` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `FK85E8C94456587C2B` FOREIGN KEY (`privilegeId`) REFERENCES `privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `privilege_role` */

insert  into `privilege_role`(`roleId`,`privilegeId`) values (4,1),(5,1),(6,1),(4,2),(5,2),(6,2),(4,3),(5,3),(6,3),(4,4),(5,4),(6,4),(4,5),(4,6),(4,7),(6,7),(4,8),(5,8),(6,8),(4,9),(5,9),(6,9),(4,10),(4,11),(4,12),(6,12),(4,13),(5,13),(6,13),(4,14),(6,14),(4,15),(6,15),(4,16),(6,16),(4,17),(5,17),(6,17),(4,18),(5,18),(6,18),(4,19),(5,19),(6,19),(4,20),(5,20),(6,20),(4,21),(5,21),(6,21),(4,22),(4,23),(4,24),(5,24),(6,24),(4,25),(6,25),(4,26),(6,26),(4,27),(5,27),(6,27),(4,28),(5,28),(6,28),(4,29),(5,29),(6,29),(4,30),(5,30),(6,30),(4,31),(5,31),(6,31),(4,32),(5,32),(6,32);

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `ipAddr` varchar(255) DEFAULT NULL,
  `postTime` datetime DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `topicId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK67612EADF22A8A7` (`topicId`),
  KEY `FK67612EAA9217D03` (`authorId`),
  CONSTRAINT `FK67612EAA9217D03` FOREIGN KEY (`authorId`) REFERENCES `user` (`id`),
  CONSTRAINT `FK67612EADF22A8A7` FOREIGN KEY (`topicId`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `reply` */

insert  into `reply`(`id`,`title`,`content`,`ipAddr`,`postTime`,`authorId`,`topicId`) values (1,'回复：jave基础知识','&nbsp;java啊<img src=\"http://localhost:8080/MyOaSystem/fckeditor/editor/images/smiley/wangwang/4.gif\" alt=\"\" />','0:0:0:0:0:0:0:1','2015-11-07 09:16:57',NULL,1),(2,'回复：jave基础知识','也就这样的<img src=\"http://localhost:8080/MyOaSystem/fckeditor/editor/images/smiley/wangwang/12.gif\" alt=\"\" />','0:0:0:0:0:0:0:1','2015-11-07 09:20:12',NULL,1),(5,'回复：jave基础知识','','0:0:0:0:0:0:0:1','2015-11-08 20:56:03',4,1),(6,'回复：jave基础知识','我哪知道啊','0:0:0:0:0:0:0:1','2015-11-08 20:56:19',4,1),(7,'回复：你要干嘛','我也不知道他要干嘛','0:0:0:0:0:0:0:1','2015-11-09 09:35:43',4,2),(8,'回复：java都有哪些基础知识？','方法覆盖和方法重写','0:0:0:0:0:0:0:1','2015-11-09 10:09:20',4,7),(9,'回复：java都有哪些基础知识？','多了去了','0:0:0:0:0:0:0:1','2015-11-09 10:11:48',1,7),(10,'回复：ssm指的是什么？','好厉害的样子','0:0:0:0:0:0:0:1','2015-11-09 10:14:09',4,9),(12,'回复：Android四大组件','activity + intent + service + &hellip;&hellip;还有一个忘了','0:0:0:0:0:0:0:1','2015-11-09 14:30:43',4,18),(13,'回复：ssh指的是什么？','<img src=\"http://localhost:8080/MyOaSystem/fckeditor/editor/images/smiley/wangwang/4.gif\" alt=\"\" />&nbsp;','0:0:0:0:0:0:0:1','2015-11-09 15:20:21',4,8);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`) values (4,'总经理','谁知道他是干嘛的'),(5,'员工','打工仔'),(6,'部门经理','部门的老大');

/*Table structure for table `topic` */

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `ipAddr` varchar(255) DEFAULT NULL,
  `postTime` datetime DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `forumId` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `replyCount` int(11) DEFAULT NULL,
  `lastReplyId` bigint(20) DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lastReplyId` (`lastReplyId`),
  KEY `FK696CD2F713C2A07` (`lastReplyId`),
  KEY `FK696CD2FA9217D03` (`authorId`),
  KEY `FK696CD2FF9E7F40B` (`forumId`),
  CONSTRAINT `FK696CD2F713C2A07` FOREIGN KEY (`lastReplyId`) REFERENCES `reply` (`id`),
  CONSTRAINT `FK696CD2FA9217D03` FOREIGN KEY (`authorId`) REFERENCES `user` (`id`),
  CONSTRAINT `FK696CD2FF9E7F40B` FOREIGN KEY (`forumId`) REFERENCES `forum` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `topic` */

insert  into `topic`(`id`,`title`,`content`,`ipAddr`,`postTime`,`authorId`,`forumId`,`type`,`replyCount`,`lastReplyId`,`lastUpdateTime`) values (1,'jave基础知识','&nbsp;对象，继承，接口。','0:0:0:0:0:0:0:1','2015-11-06 21:02:51',NULL,NULL,0,4,6,'2015-11-08 20:56:19'),(2,'你要干嘛','我不干嘛啊','0:0:0:0:0:0:0:1','2015-11-07 09:20:44',NULL,NULL,0,1,7,'2015-11-09 09:35:43'),(3,'傻吊','傻吊，你说谁？','0:0:0:0:0:0:0:1','2015-11-07 09:30:35',NULL,NULL,2,0,NULL,'2015-11-07 09:30:35'),(4,'android四大组件','你猜？','0:0:0:0:0:0:0:1','2015-11-08 19:47:56',4,NULL,0,0,NULL,'2015-11-08 19:47:56'),(5,'Android开发好玩吗？','我哪知道好不好玩、','0:0:0:0:0:0:0:1','2015-11-08 19:59:20',4,NULL,0,0,NULL,'2015-11-08 19:59:20'),(6,'我要移动到别的板块去','你去啊，傻吊','0:0:0:0:0:0:0:1','2015-11-09 09:52:15',4,NULL,0,0,NULL,'2015-11-09 09:52:15'),(7,'java都有哪些基础知识？','对象，继承，接口，文件io，线程&hellip;&hellip;','0:0:0:0:0:0:0:1','2015-11-09 10:08:55',4,7,1,2,9,'2015-11-09 10:11:48'),(8,'ssh指的是什么？','struts2 + spring + hibernate','0:0:0:0:0:0:0:1','2015-11-09 10:10:23',1,7,2,1,13,'2015-11-09 15:20:21'),(9,'ssm指的是什么？','spring MVC + spring + mybatis','0:0:0:0:0:0:0:1','2015-11-09 10:11:08',1,7,1,1,10,'2015-11-09 10:14:09'),(18,'Android四大组件','谁知道啊？','0:0:0:0:0:0:0:1','2015-11-09 14:30:14',4,13,0,1,12,'2015-11-09 14:30:43'),(19,'现在是iphone几了？','快iphone7了、','0:0:0:0:0:0:0:1','2015-11-10 22:36:53',4,15,0,0,NULL,'2015-11-10 22:36:53');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phoneNum` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `departmentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36EBCBFE00DB11` (`departmentId`),
  CONSTRAINT `FK36EBCBFE00DB11` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`loginName`,`name`,`password`,`gender`,`phoneNum`,`email`,`description`,`departmentId`) values (1,'zs','张三','81dc9bdb52d04dc20036dbd8313ed055','男','12345','12345','打工仔',4),(4,'admin','超级管理员','21232f297a57a5a743894a0e4a801fc3',NULL,NULL,NULL,NULL,NULL),(5,'testUser0',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(6,'testUser1',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(7,'testUser2',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(8,'testUser3',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(9,'testUser4',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(10,'testUser5',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(11,'testUser6',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(12,'testUser7',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(13,'testUser8',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(14,'testUser9',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(15,'testUser10',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(16,'testUser11',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(17,'testUser12',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(18,'testUser13',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(19,'testUser14',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(20,'testUser15',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(21,'testUser16',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(22,'testUser17',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(23,'testUser18',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(24,'testUser19',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(25,'testUser20',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(26,'testUser21',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(27,'testUser22',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(28,'testUser23',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(29,'testUser24',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(30,'testUser25',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(31,'testUser26',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(32,'testUser27',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(33,'testUser28',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(34,'testUser29',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(35,'testUser30',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(36,'testUser31',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(37,'testUser32',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(38,'testUser33',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(39,'testUser34',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(40,'testUser35',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(41,'testUser36',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(42,'testUser37',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(43,'testUser38',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(44,'testUser39',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(45,'testUser40',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(46,'testUser41',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(47,'testUser42',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(48,'testUser43',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(49,'testUser44',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(50,'testUser45',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(51,'testUser46',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(52,'testUser47',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(53,'testUser48',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(54,'testUser49',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(55,'testUser50',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(56,'testUser51',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(57,'testUser52',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(58,'testUser53',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(59,'testUser54',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(60,'testUser55',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(61,'testUser56',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(62,'testUser57',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(63,'testUser58',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(64,'testUser59',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(65,'testUser60',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(66,'testUser61',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(67,'testUser62',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(68,'testUser63',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(69,'testUser64',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(70,'testUser65',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(71,'testUser66',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(72,'testUser67',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(73,'testUser68',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(74,'testUser69',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(75,'testUser70',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(76,'testUser71',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(77,'testUser72',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(78,'testUser73',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(79,'testUser74',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(80,'testUser75',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(81,'testUser76',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(82,'testUser77',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(83,'testUser78',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(84,'testUser79',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(85,'testUser80',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(86,'testUser81',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(87,'testUser82',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(88,'testUser83',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(89,'testUser84',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(90,'testUser85',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(91,'testUser86',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(92,'testUser87',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(93,'testUser88',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(94,'testUser89',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(95,'testUser90',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(96,'testUser91',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(97,'testUser92',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(98,'testUser93',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(99,'testUser94',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(100,'testUser95',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(101,'testUser96',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(102,'testUser97',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(103,'testUser98',NULL,NULL,NULL,NULL,NULL,'测试分页的数据',NULL),(107,'ls','李四','81dc9bdb52d04dc20036dbd8313ed055','男','','','',4),(108,'ww','王五','81dc9bdb52d04dc20036dbd8313ed055','男','','','',NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`userId`),
  KEY `FK143BF46A1C8DCD2E` (`roleId`),
  KEY `FK143BF46A21E04BEE` (`userId`),
  CONSTRAINT `FK143BF46A1C8DCD2E` FOREIGN KEY (`roleId`) REFERENCES `user` (`id`),
  CONSTRAINT `FK143BF46A21E04BEE` FOREIGN KEY (`userId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`userId`,`roleId`) values (5,1),(6,107),(4,108);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
