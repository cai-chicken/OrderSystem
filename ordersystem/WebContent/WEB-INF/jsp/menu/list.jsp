<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单列表</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<form class="form-inline definewidth m20" action="menu_list.action" method="post">
		菜名：
		<input type="text" name="name" id="name" class="abc input-default" placeholder="请输入菜名" value="">&nbsp;&nbsp; 
		所属菜系：
		<select name="cuisineId">
			<option>请选择菜系</option>
			<c:forEach items="${cuisineList }" var="cuisine">
				<option value="${cuisine.id }">${cuisine.name }</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		<!--<input type="text" name="name" id="menuname" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;-->
		是否特色菜：
		<input type="radio" name="isSpecial" id="rolename" class="abc input-default" value="1">&nbsp;是&nbsp;
		<input type="radio" name="isSpecial" id="rolename" class="abc input-default" value="0">&nbsp;否&nbsp;

		<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增菜单</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>编码</th>
				<th>菜名</th>
				<th>价格</th>
				<th>图片</th>
				<th>简介</th>
				<th>是否特色菜</th>
				<th>所属菜系</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${pageBean.recordList }" var="menu">
			<tr>
				<td>${menu.num }</td>
				<td>${menu.name }</td>
				<td>￥${menu.price }</td>
				<td><img alt="no image" style="width: 250px;height: 100px" src="${menu.iamge }"/></td>
				<td>${menu.description }</td>
				<c:if test="${menu.isSpecial == 1}">
					<td>是</td>
				</c:if>
				<c:if test="${menu.isSpecial == 0}">
					<td>不是</td>
				</c:if>
				<td>${menu.cuisine.name }</td>
				<td>
					<a href="menu_editUI.action?id=${menu.id }">编辑</a>
					<a href="menu_delete.action?id=${menu.id }" onclick="return confirm('确定要删除吗？')">删除</a>
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
			window.location.href = "menu_addUI.action";
		});
	});
	function goToPage( pageNum ){
		/* pageNum为当前页，将被BaseAction中的成员变量接收 */
		/* $(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
		document.forms[0].submit(); */
		window.location.href = "/ordersystem/menu_list.action?pageNum=" + pageNum;
	}
</script>
</html>