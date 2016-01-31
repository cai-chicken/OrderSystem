<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>小知识列表</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
<button type="button" style="margin-left: 25px; margin-top: 10px;" class="btn btn-success" id="addnew">新增小知识</button>
		<table class="table table-bordered table-hover definewidth m10">
			<thead>
				<tr>
					<th>题目</th>
					<th>内容</th>
					<th>操作</th>
				</tr>
			</thead>
			<!--列表数据-->
			<c:forEach items="${pageBean.recordList }" var="knowledge">
				<tr>
					<td>${knowledge.name }</td>
					<td>${knowledge.description }</td>
					<td>
						<a href="knowledge_editUI.action?id=${knowledge.id }">编辑</a>
						<a href="knowledge_delete.action?id=${knowledge.id }" onclick="return confirm('确定要删除吗？')">删除</a>
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
			window.location.href="knowledge_addUI.action";
		});
    });
	function goToPage( pageNum ){
		/* pageNum为当前页，将被BaseAction中的成员变量接收 */
		/* $(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
		document.forms[0].submit(); */
		window.location.href = "/ordersystem/knowledge_list.action?pageNum=" + pageNum;
	}
</script>
</html>