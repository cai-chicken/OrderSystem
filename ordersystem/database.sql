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
INSERT INTO menu VALUES(NULL,'0004','老厨白菜粉','',12,'多吃大白菜，好处多多。',0,2,'2016-01-30 18:23:34','等待上菜',3,4,13)
INSERT INTO menu VALUES(NULL,'0005','焖肉龙','',14,'名字叫的那么好听，没口感。',1,NULL,NULL,NULL,NULL,3,4)
INSERT INTO menu VALUES(NULL,'0006','红烧鱼尾巴','',16,'鱼汤好喝，好有营养。',1,1,'2016-01-31 12:28:34','等待上菜',4,7,7);
INSERT INTO menu VALUES(NULL,'0007','八宝饭','',12,'你肯定喝过八宝粥，但你吃过八宝饭吗？',0,NULL,NULL,NULL,NULL,5,25);
INSERT INTO menu VALUES(NULL,'0008','荷香福鸡','',20,'香饽饽的鸡老满足吃货的你。',1,NULL,NULL,NULL,NULL,6,36);
INSERT INTO menu VALUES(NULL,'0009','如意蛋卷','',8,'如意蛋卷，祝您万事如意。',0,3,'2016-02-18 18:23:34','等待上菜',3,4,28);
INSERT INTO menu VALUES(NULL,'0010','金玉满堂','',9,'满汉全席',1,NULL,NULL,NULL,NULL,5,13);
INSERT INTO menu VALUES(NULL,'0011','上上签','',4,'并不是求签的上上签哦。',0,1,'2016-02-17 10:35:34','等待上菜',2,8,7);
INSERT INTO menu VALUES(NULL,'0012','清淡营养家常饭卷','',12,'营养多多，口味多多。',0,NULL,NULL,NULL,NULL,5,21);
INSERT INTO menu VALUES(NULL,'0013','蒜苗炒肉','',11,'你的家常，你的菜。',0,NULL,NULL,NULL,NULL,1,39);
INSERT INTO menu VALUES(NULL,'0014','红烧排骨','',15,'排骨红烧才好吃。',0,NULL,NULL,NULL,NULL,3,15);
INSERT INTO menu VALUES(NULL,'0015','虾油炒面','',12,'虾油炒面，让你油嘴滑舌。',1,NULL,NULL,NULL,NULL,5,39);
INSERT INTO menu VALUES(NULL,'0016','红烧鲫鱼','',18,'最有营养的也是你最爱的红烧鲫鱼。',1,NULL,NULL,NULL,NULL,7,28);
INSERT INTO menu VALUES(NULL,'0017','红烧牛肉','',18,'肉，我只爱牛肉。',1,2,'2016-02-18 18:23:34','等待上菜',2,3,48);
INSERT INTO menu VALUES(NULL,'0018','南昌炒粉','',5,'看你的口味了。',0,1,'2016-02-18 19:43:34','等待上菜',3,4,27);
INSERT INTO menu VALUES(NULL,'0019','油豆腐','',13,'你吃或不吃，它就在那里。',0,1,'2016-02-18 18:23:34','等待上菜',3,4,13);
INSERT INTO menu VALUES(NULL,'0020','韭菜盒子','',10,'让韭菜洗洗你的胃吧。',1,NULL,NULL,NULL,NULL,2,20);
INSERT INTO menu VALUES(NULL,'0021','银耳羹','',6,'清凉解渴',0,2,'2016-02-19 18:23:34','等待上菜',1,4,14);
INSERT INTO menu VALUES(NULL,'0022','豆角炒肉','',12,'豆角和肉更配哦。',0,1,'2016-02-19 18:23:34','等待上菜',2,5,21);
INSERT INTO menu VALUES(NULL,'0023','香肠','',17,'十里飘香',1,2,'2016-02-18 13:23:34','等待上菜',3,4,13);
DESC menu
SELECT * FROM menu m WHERE m.orderTime LIKE '%2016-02-18%' ORDER BY m.count DESC
ALTER TABLE menu MODIFY orderTime VARCHAR(50)
SELECT * FROM cuisine WHERE orderTime LIKE '2016-02-18';
DESC cuisine
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

SELECT * FROM t_today
#插入今天的收入数据
INSERT INTO t_today VALUES(NULL,'2016-2-1','100','101','102','106','140','132','438','1119');
INSERT INTO t_today VALUES(NULL,'2016-2-2','200','146','321','106','140','269','421','1603');
INSERT INTO t_today VALUES(NULL,'2016-2-3','78','101','87','183','263','362','142','1216');
INSERT INTO t_today VALUES(NULL,'2016-2-4','52','89','102','106','426','302','250','1327');
INSERT INTO t_today VALUES(NULL,'2016-2-5','99','156','132','248','261','320','120','1336');
INSERT INTO t_today VALUES(NULL,'2016-2-6','100','101','102','106','140','132','438','1119');
INSERT INTO t_today VALUES(NULL,'2016-2-7','200','146','321','106','140','269','421','1603');
INSERT INTO t_today VALUES(NULL,'2016-2-8','78','101','87','183','263','362','142','1216');
INSERT INTO t_today VALUES(NULL,'2016-2-9','52','89','102','106','426','302','250','1327');
INSERT INTO t_today VALUES(NULL,'2016-2-10','99','156','132','248','261','320','120','1336');
INSERT INTO t_today VALUES(NULL,'2016-2-11','99','156','132','248','261','','','896');
INSERT INTO t_today VALUES(NULL,'2016-2-12','99','156','132','248','','','','635');
INSERT INTO t_today VALUES(NULL,'2016-2-13','99','156','132','248','','200','','835');

SELECT * FROM t_week
SELECT * FROM t_month
DESC t_year;
#插入本月的数据
INSERT INTO t_month VALUES(NULL,'2016-1','1024','1056','1265','1432','1001','873','992','7643');
INSERT INTO t_month VALUES(NULL,'2016-2','1056','1265','1024','873','992','1001','1432','7643');
INSERT INTO t_month VALUES(NULL,'2016-3','1001','873','1265','1056','992','1432','1024','7643');
INSERT INTO t_month VALUES(NULL,'2016-4','873','1024','1432','1265','992','1056','1001','7643');
INSERT INTO t_month VALUES(NULL,'2016-5','1993','2016','1992','1974','2015','723','1045','11758');
INSERT INTO t_month VALUES(NULL,'2016-6','1224','1156','1035','1883','901','1073','882','8154');
INSERT INTO t_month VALUES(NULL,'2016-7','1024','1056','1265','1432','1001','873','992','7643');
INSERT INTO t_month VALUES(NULL,'2016-8','1056','1265','1024','873','992','1001','1432','7643');
INSERT INTO t_month VALUES(NULL,'2016-9','1001','873','1265','1056','992','1432','1024','7643');
INSERT INTO t_month VALUES(NULL,'2016-10','873','1024','1432','1265','992','1056','1001','7643');
INSERT INTO t_month VALUES(NULL,'2016-11','1993','2016','1992','1974','2015','723','1045','11758');
INSERT INTO t_month VALUES(NULL,'2016-12','1224','1156','1035','1883','901','1073','882','8154');

INSERT INTO t_month VALUES(NULL,'2015-1','1024','1056','1265','1432','1001','873','992','7643');
INSERT INTO t_month VALUES(NULL,'2015-2','1056','1265','1024','873','992','1001','1432','7643');
INSERT INTO t_month VALUES(NULL,'2015-3','1001','873','1265','1056','992','1432','1024','7643');
INSERT INTO t_month VALUES(NULL,'2015-4','873','1024','1432','1265','992','1056','1001','7643');
INSERT INTO t_month VALUES(NULL,'2015-5','1993','2016','1992','1974','2015','723','1045','11758');
INSERT INTO t_month VALUES(NULL,'2015-6','1224','1156','1035','1883','901','1073','882','8154');
INSERT INTO t_month VALUES(NULL,'2015-7','1024','1056','1265','1432','1001','873','992','7643');
INSERT INTO t_month VALUES(NULL,'2015-8','1056','1265','1024','873','992','1001','1432','7643');
INSERT INTO t_month VALUES(NULL,'2015-9','1001','873','1265','1056','992','1432','1024','7643');
INSERT INTO t_month VALUES(NULL,'2015-10','873','1024','1432','1265','992','1056','1001','7643');
INSERT INTO t_month VALUES(NULL,'2015-11','1993','2016','1992','1974','2015','723','1045','11758');
INSERT INTO t_month VALUES(NULL,'2015-12','1224','1156','1035','1883','901','1073','882','8154');

SELECT * FROM t_year
#插入今年数据
INSERT INTO t_year VALUES(NULL,'2016','7643','8320','9430','7824','11758','8154','9302','6991','8923','9734','10324','8997','107400');
INSERT INTO t_year VALUES(NULL,'2015','7824','11758','7643','8320','6991','8923','9734','9430','8154','9302','10324','8997','107400');

DESC role
DESC privilege
DESC employee_role
DESC privilege_role
DESC menu
DESC cuisine
DESC COMMENT
DESC store
DESC chair
DESC knowledge
DESC t_today
DESC t_month
DESC t_year

SELECT * FROM menu;