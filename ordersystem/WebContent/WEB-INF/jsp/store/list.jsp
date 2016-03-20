<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>本店信息列表</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<button type="button" style="margin-left: 25px; margin-top: 10px;" class="btn btn-success" id="addnew">新增门店信息</button>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th style="width: 100px">店名</th>
				<th>宣传图片</th>
				<th style="width: 450px">简介</th>
				<th>是否启用中</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${pageBean.recordList }" var="store">
			<tr>
				<td>${store.name }</td>
				<td><img alt="no image" style="width: 250px;height: 100px" src="${store.image }"/></td>
				<td>${store.description }</td>
				<c:if test="${store.state == 1 }">
					<td>是</td>
				</c:if>
				<c:if test="${store.state != 1 }">
					<td>否</td>
				</c:if>
				<td>
					<a href="store_editUI.action?id=${store.id }">编辑</a>
					<a href="store_delete.action?id=${store.id }" onclick="return confirm('确定要删除吗？')">删除</a>
					<a href="store_changeState.action?id=${store.id }">启用</a>
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
			window.location.href="store_addUI.action";
		});
    });
	function goToPage( pageNum ){
		/* pageNum为当前页，将被BaseAction中的成员变量接收 */
		/* $(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
		document.forms[0].submit(); */
		window.location.href = "/ordersystem/store_list.action?pageNum=" + pageNum;
	}
</script>
</html>