#创建数据库
CREATE DATABASE ordersystem
SHOW TABLES;
SELECT * FROM privilege


#查看员工表结构
DESC employee
#向员工表中插入数据
INSERT INTO employee VALUES(NULL,'0001','张三','zhangsan','男','1990-11-16','天河区岗顶','13262527978');
INSERT INTO employee VALUES(NULL,'0002','李四','lisi','男','1991-11-16','上饶广丰','13578639034');
INSERT INTO employee VALUES(NULL,'0003','王五','wangwu','女','1992-11-16','上海徐汇','13578639044');
INSERT INTO employee VALUES(NULL,'0004','赵六','zhaoliu','女','1993-11-16','北京朝阳','13262527898');
INSERT INTO employee VALUES(NULL,'0005','阿七','aqi','男','1994-11-16','美国纽约','13262523456');
INSERT INTO employee VALUES(NULL,'0006','三八','sanba','女','1995-11-16','洛杉矶湖人','13262522378');
INSERT INTO employee VALUES(NULL,'0007','九九','jiujiu','男','1996-11-16','休斯顿火箭','13274527978');
INSERT INTO employee VALUES(NULL,'0008','老王','laowang','女','1997-11-16','西雅图','13289227978');
INSERT INTO employee VALUES(NULL,'0009','老程','laocheng','女','1998-11-16','澳大利亚','13262521978');
INSERT INTO employee VALUES(NULL,'0010','老徐','laoxu','男','1999-11-16','加拿大','13267892378');

#模糊查询测试
SELECT employee0_.id AS id1_, employee0_.num AS num1_, employee0_.name AS name1_, employee0_.loginName AS loginName1_, employee0_.sex AS sex1_, employee0_.birthday AS birthday1_, employee0_.address AS address1_, employee0_.phoneNumber AS phoneNum8_1_ FROM employee employee0_ WHERE employee0_.name LIKE '%三%' ORDER BY employee0_.id DESC
SHOW VARIABLES LIKE 'character%';
#查看表中插入的数据
DESC employee
SELECT * FROM employee;
SELECT * FROM role
SELECT * FROM privilege_role WHERE roleId = 4
SELECT * FROM employee
SELECT * FROM employee_role WHERE employeeId = 21
SELECT * FROM privilege WHERE parentId IS NULL
SELECT image FROM store WHERE id = 2;
DESC Privilege
#查找用户id为21所拥有的所有顶级权限
SELECT * FROM privilege p WHERE p.parentId IS NULL AND p.id IN (
	SELECT DISTINCT privilegeId FROM privilege_role t1 WHERE t1.roleId IN (
		SELECT roleId FROM employee_role WHERE employeeId=21
	)
)

SELECT DISTINCT privilegeId FROM privilege_role t1 WHERE t1.roleId IN (
	SELECT roleId FROM employee_role WHERE employeeId=21
)

SELECT roleId FROM employee_role WHERE employeeId=19

SELECT privilegeId FROM privilege_role WHERE roleId=3


SELECT * FROM menu
DESC menu
ALTER TABLE menu MODIFY isSpecial VARCHAR(10)
SELECT * FROM cuisine
SELECT * FROM COMMENT
SELECT * FROM USER;
INSERT INTO USER VALUES(NULL,'张三');
INSERT INTO USER VALUES(NULL,'李四');
INSERT INTO USER VALUES(NULL,'王五');
INSERT INTO USER VALUES(NULL,'赵六');
INSERT INTO USER VALUES(NULL,'孙七');
DESC COMMENT
INSERT INTO COMMENT VALUES(NULL,3,'红烧排骨还不错','2016-02-02 21:29:50',1,1)
INSERT INTO COMMENT VALUES(NULL,1,'什么鬼，这是肉沫茄子？','2016-02-01 08:29:50',3,2);
INSERT INTO COMMENT VALUES(NULL,4,'红烧排骨，很有家的味道','2016-01-02 13:59:25',2,1);
INSERT INTO COMMENT VALUES(NULL,3,'红烧排骨，一般般吧，没我妈弄的好吃','1996-11-16 12:35:50',4,1);
INSERT INTO COMMENT VALUES(NULL,5,'满分满分','2015-12-02 21:19:50',2,2);
INSERT INTO COMMENT VALUES(NULL,2,'也就那样吧，我都会。','2014-02-14 13:14:50',1,2);