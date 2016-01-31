<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色列表</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<form class="form-inline definewidth m20" action="role_list.action" method="post">
		角色名称：
		<input type="text" name="name" class="abc input-default" value="" placeholder="请输入角色名称">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增角色</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>角色名称</th>
				<th>角色描述</th>
				<th>操作</th>
			</tr>
		</thead>
		<!-- 列表数据 -->
		<c:forEach items="${pageBean.recordList }" var="role">
		<tr>
			<td>${role.name }</td>
			<td>${role.description}</td>
			<td>
				<a href="role_editUI.action?id=${role.id }">编辑</a>
				<a href="role_delete.action?id=${role.id }" onclick="return confirm('确定要删除吗？')">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<!--分页-->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
	<script>
		$(function () {
			$('#addnew').click(function(){
				window.location.href = "role_addUI.action";
			});
	    });
		function goToPage( pageNum ){
			/* pageNum为当前页，将被BaseAction中的成员变量接收 */
			/* $(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
			document.forms[0].submit(); */
			window.location.href = "/ordersystem/role_list.action?pageNum=" + pageNum;
		}
	</script>

</html>

