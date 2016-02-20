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
	<form class="form-inline definewidth m20" action="employee_list.action" method="post">
		<!--过滤条件-->
		员工名称：
		<input type="text" name="name" id="empName" value="${emp.name }" class="abc input-default" placeholder="请输入员工名称">&nbsp;&nbsp;
		家庭地址：
		<input type="text" name="address" id="empAddress" value="${emp.address }" class="abc input-default" placeholder="请输入家庭地址">&nbsp;&nbsp;
		性别：
		<c:choose>
			<c:when test="${emp.sex == '男' }">
				<input type="radio" name="sex" class="abc input-default" checked="checked" value="男">&nbsp;男&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" name="sex" class="abc input-default"  value="男">&nbsp;男&nbsp;
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${emp.sex == '女' }">
				<input type="radio" name="sex" class="abc input-default" checked="checked" value="女">&nbsp;女&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" name="sex" class="abc input-default"  value="女">&nbsp;女&nbsp;
			</c:otherwise>
		</c:choose>
		
		<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增员工</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>编号</th>
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
		<c:forEach items="${pageBean.recordList }" var="employee">
			<tr>
				<td>${employee.num}</td>
				<td>${employee.name }</td>
				<td>${employee.loginName }</td>
				<td>${employee.sex }</td>
				<td>${employee.birthday }</td>
				<td>${employee.address }</td>
				<td>${employee.phoneNumber }</td>
				<td>
					<a href="employee_editUI.action?id=${employee.id }">编辑</a>
					<a href="employee_delete.action?id=${employee.id }" onclick="return confirm('确定要删除吗？')">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<!--分页-->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
<script type="text/javascript" >
	$(function() {
		$('#addnew').click(function() {
			window.location.href = "employee_addUI.action";
		});
	});
	function goToPage( pageNum ){
		/* pageNum为当前页，将被BaseAction中的成员变量接收 */
		$(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
		document.forms[0].submit(); 
		/* var empName = $("#empName").val();
		var empAddress = $("#empAddress").val();
		var empSex = $('input:radio[name="sex"]:checked').val();
		window.location.href = "/ordersystem/employee_list.action?pageNum=" + pageNum + "&name="+empName+"&address="+empAddress+"&sex="+empSex; */
	}
</script>
</html>