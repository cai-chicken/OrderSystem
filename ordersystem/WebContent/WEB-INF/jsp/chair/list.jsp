<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<button type="button" style="margin-left: 25px; margin-top: 10px;" class="btn btn-success" id="addnew">新增桌椅</button>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>编号</th>
				<th>是否可用</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${pageBean.recordList }" var="chair">
		<tr>
			<td>${chair.num }</td>
			<c:if test="${chair.isGood == 1 }">
				<td>可用</td>
			</c:if>
			<c:if test="${chair.isGood == 0 }">
				<td>不可用</td>
			</c:if>
			<td>
				<a href="chair_editUI.action?id=${chair.id }">编辑</a>
				<a href="chair_delete.action?id=${chair.id }" onclick="return confirm('确定要删除吗？')">删除</a>
				<a href="chair_changeState.action?id=${chair.id }" onclick="return confirm('确定修改${chair.num}桌的状态?')">改变可用状态</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<!--分页-->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
<script type="text/javascript">
	$(function () {
		$('#addnew').click(function(){
			window.location.href="chair_addUI.action";
		});
    });
	function goToPage( pageNum ){
		/* pageNum为当前页，将被BaseAction中的成员变量接收 */
		/* $(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
		document.forms[0].submit(); */
		window.location.href = "/ordersystem/chair_list.action?pageNum=" + pageNum;
	}
</script>
</html>