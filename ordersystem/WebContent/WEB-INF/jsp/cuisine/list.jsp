<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<form class="form-inline definewidth m20" action="cuisine_list.action" method="post">
		菜系名称：
		<input type="text" name="name" value="${c.name }" id="cuisineName" class="abc input-default" placeholder="请输入菜系名称" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增菜单</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>菜系名</th>
				<th>简介</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${pageBean.recordList }" var="cuisine">
		<tr>
			<td>${cuisine.name }</td>
			<td>${cuisine.description }</td>
			<td>
				<a href="cuisine_editUI.action?id=${cuisine.id }">编辑</a>
				<a href="cuisine_delete.action?id=${cuisine.id }" onclick="return confirm('确定要删除吗？')">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<!-- 分页 -->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
<script>
	$(function() {
		$('#addnew').click(function() {
			window.location.href = "cuisine_addUI.action";
		});
	});
	function goToPage( pageNum ){
		/* pageNum为当前页，将被BaseAction中的成员变量接收 */
		$(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
		document.forms[0].submit();
		/* window.location.href = "/ordersystem/cuisine_list.action?pageNum=" + pageNum; */
	}
</script>
</html>