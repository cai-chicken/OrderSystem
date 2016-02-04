<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<c:if test="${cuisine.id == null }">
	<form action="cuisine_add.action" id="myForm" method="post" class="definewidth m20">
	</c:if>
	<c:if test="${cuisine.id != null }">
	<form action="cuisine_edit.action" id="myForm" method="post" class="definewidth m20">
	</c:if>
		<input type="hidden" name="id" value="${cuisine.id }" />
		<table class="table table-bordered table-hover m10">
			<tr>
				<td class="tableleft">菜系名称<span style="color: red;">*</span></td>
				<td>
					<input type="text" id="name" placeholder="请输入菜系名称" name="name" value="${cuisine.name }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">简介<span style="color: red;">*</span></td>
				<td>
					<textarea name="description" id="description" placeholder="请输入简介"><c:out value="${cuisine.description }"></c:out></textarea>
				</td>
			</tr>
			
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="button" class="btn btn-primary" onclick="beforeSubmit()">保存</button> &nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
	$(function() {
		$('#backid').click(function() {
			window.location.href = "cuisine_list.action";
		});
	});
	
	function beforeSubmit(){
		var name = $("#name").val();
		var description = $("#description").val();
		if(name == null || name == ""){
			alert("请输入菜系名称");
			$("#name").focus();
			return;
		}
		if(description == null || description == ""){
			alert("请输入内容");
			return ;
		}
		
		$("#myForm").submit();
	}
</script>
</html>