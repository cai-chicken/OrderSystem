<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>员工列表</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<form class="form-inline definewidth m20" action="list.jsp">
		<!--过滤条件-->
		员工名称：
		<input type="text" name="name" class="abc input-default" value="">&nbsp;&nbsp;
		家庭地址：
		<input type="text" name="address" class="abc input-default" value="">&nbsp;&nbsp;
		性别：
		<input type="radio" name="sex" class="abc input-default"  value="男">&nbsp;男&nbsp;
		<input type="radio" name="sex" class="abc input-default"  value="女">&nbsp;女&nbsp;
		<input type="radio" name="sex" class="abc input-default"  value="">&nbsp;不选&nbsp;
		
		<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增员工</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>编码</th>
				<th>姓名</th>
				<th>登录名</th>
				<th>性别</th>
				<th>出生年月日</th>
				<th>家庭地址</th>
				<th>手机号码</th>
				<th>操作</th>
			</tr>
		</thead>
		<!--列表数据-->
		<c:forEach items="${employeeList }" var="employee">
			<tr>
				<td>${employee.num}</td>
				<td>${employee.name }</td>
				<td>${employee.loginName }</td>
				<td>${employee.sex }</td>
				<td>${employee.birthday }</td>
				<td>${employee.address }</td>
				<td>${employee.phoneNumber }</td>
				<td>
					<a href="saveUI.html">编辑</a>
					<a href="#" onclick="del(1)">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<!--分页-->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/WEB-INF/jsp/public/addAndDelete.js"></script>
</html>