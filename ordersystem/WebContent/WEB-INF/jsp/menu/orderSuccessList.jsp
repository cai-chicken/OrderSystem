<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<form class="form-inline definewidth m20" action="menu_orderSuccess.action" method="post">
		菜名：
		<input type="text" name="menuName" class="abc input-default" placeholder="请输入菜名">&nbsp;&nbsp;
		几号桌：
		<input type="text" name="chairNum" class="abc input-default" placeholder="请输入桌号">&nbsp;&nbsp;
		烹饪状态：
		<input type="radio" name="status" class="abc input-default" value="等待下锅">&nbsp;等待下锅&nbsp;
		<input type="radio" name="status" class="abc input-default" value="正在烧">&nbsp;正在烧&nbsp;
		<input type="radio" name="status" class="abc input-default" value="上菜">&nbsp;上菜&nbsp;

		<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>菜名</th>
				<th>价格</th>
				<th>几号桌</th>
				<th>下单份数</th>
				<th>下单时间</th>
				<th>烹饪状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${pageBean.recordList }" var="menu">
		<tr>
			<td>${menu.name }</td>
			<td>${menu.price }</td>
			<td><span style="color: red;font-weight: bold;">${menu.chair.num }</span></td>
			<td><span style="color: red;font-weight: bold;">${menu.count }</span></td>
			<td>${menu.orderTime }</td>
			<td>${menu.status }</td>
			<td>
				<button type="button" class="btn btn-primary">正在烧</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-success">上菜</button>
			</td>
		</tr>
		</c:forEach>
	</table>
	<!-- 分页 -->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
<script type="text/javascript">
function goToPage( pageNum ){
	/* pageNum为当前页，将被BaseAction中的成员变量接收 */
	window.location.href = "/ordersystem/menu_orderSuccess.action?pageNum=" + pageNum;
}
</script>
</html>