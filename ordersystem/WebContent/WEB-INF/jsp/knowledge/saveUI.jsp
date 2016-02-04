<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${knowledge.id == null }">
		<form action="knowledge_add.action" id="myForm" method="post" class="definewidth m20">
	</c:if>
	<c:if test="${knowledge.id != null }">
		<form action="knowledge_edit.action" id="myForm" method="post" class="definewidth m20">
	</c:if>
	<input type="hidden" name="id" value="${knowledge.id }">
			<table class="table table-bordered table-hover definewidth m10">
				<tr>
					<td width="10%" class="tableleft">标题<span style="color: red;">*</span></td>
					<td>
						<input type="text" name="name" id="name" placeholder="请输入标题" value="${knowledge.name }"/>
					</td>
				</tr>
				<tr>
					<td class="tableleft">内容<span style="color: red;">*</span></td>
					<td>
						<textarea name="description" placeholder="请输入相关内容" id="description"><c:out value="${knowledge.description }"></c:out></textarea>
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
			window.location.href = "knowledge_list.action";
		});
	});
	function beforeSubmit(){
		var name = $("#name").val();
		var description = $("#description").val();
		if(name == null || name == ""){
			alert("请输入标题");
			$("#name").focus();
			return;
		}
		if(description == null || description == ""){
			alert("请输入相关内容");
			return ;
		}
		
		$("#myForm").submit();
	}
</script>
</html>