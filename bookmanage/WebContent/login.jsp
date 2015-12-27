<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<s:form action="user_login.action">
		<table border="1px" style="margin: 0 auto;width: 450px;height: 200px">
			<tr><th colspan="2">登录图书管理系统</th></tr>
			<tr>
				<td  style="background-color: plum">用户名：</td>
				<td><s:textfield name="loginName" /></td>
			</tr>
			<tr>
				<td  style="background-color: plum">密码：</td>
				<td><s:password name="password" /></td>
			</tr>
			<tr><th colspan="2"><input type="submit" value="登录" /></th></tr>
			<tr><td colspan="2">
				<!-- 用于显示错误提醒 -->
				<font color="red"><s:fielderror /></font>
			</td></tr>
		</table>
	</s:form>
</body>
</html>