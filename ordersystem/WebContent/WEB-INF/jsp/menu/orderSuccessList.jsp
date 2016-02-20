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
		<input type="text" name="menuName" value="${menuName }" class="abc input-default" placeholder="请输入菜名">&nbsp;&nbsp;
		几号桌：
		<input type="text" name="chairNum" value="${chairNum }" class="abc input-default" placeholder="请输入桌号">&nbsp;&nbsp;
		烹饪状态：
		<c:choose>
			<c:when test="${menuStatus == '等待上菜'}">
				<input type="radio" name="status" checked="checked" class="abc input-default" value="等待上菜">&nbsp;等待上菜&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" name="status" class="abc input-default" value="等待上菜">&nbsp;等待上菜&nbsp;
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${menuStatus == '正在烧'}">
				<input type="radio" name="status" checked="checked" class="abc input-default" value="正在烧">&nbsp;正在烧&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" name="status" class="abc input-default" value="正在烧">&nbsp;正在烧&nbsp;
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${menuStatus == '上菜'}">
				<input type="radio" name="status" checked="checked" class="abc input-default" value="上菜">&nbsp;上菜&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" name="status" class="abc input-default" value="上菜">&nbsp;上菜&nbsp;
			</c:otherwise>
		</c:choose>

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
				<button type="button" class="btn btn-primary" onclick="changeOrderState('${menu.id}','${menu.status }',1)">正在烧</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-success" onclick="changeOrderState('${menu.id}','${menu.status }',2)">上菜</button>
				<button type="button" class="btn btn-danger" onclick="changeOrderState('${menu.id}','${menu.status }',3)">移除</button>
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
	/* window.location.href = "/ordersystem/menu_orderSuccess.action?pageNum=" + pageNum; */
	$(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
	document.forms[0].submit(); 
}
function changeOrderState(id, status, num){
	/* 将状态更改为"正在烧" */
	if(num == 1){
		if(status == "正在烧" || status == "上菜"){
			alert("当前状态已为正在烧的状态");
		} else {
			status = "正在烧";
			window.location.href = "/ordersystem/menu_changeOrderState.action?id="+id+"&statusNum="+num;
			alert("修改成功");
		}
	} else if(num == 2){
		//将状态改为"上菜"
		if(status == "上菜"){
			alert("当前状态已为上菜的状态");
		} else {
			status = "上菜";
			window.location.href = "/ordersystem/menu_changeOrderState.action?id="+id+"&statusNum="+num;
			alert("修改成功");
		}
	} else if(num == 3){
		if(confirm("确定移除该菜单吗？")){
			window.location.href = "/ordersystem/menu_changeOrderState.action?id="+id+"&statusNum="+num;
		}
	}
}
</script>
</html>