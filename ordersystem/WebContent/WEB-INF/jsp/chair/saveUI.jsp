<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<c:if test="${chair.id == null }">
	<form action="chair_add.action" method="post" class="definewidth m20">
	</c:if>
	<c:if test="${chair.id != null }">
	<form action="chair_edit.action" method="post" class="definewidth m20">
	</c:if>
	<input type="hidden" name="id" value="${chair.id }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">编号</td>
				<td>
					<input type="text" name="num" value="${chair.num }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">是否可用</td>
				<td>
					<input type="radio" name="isGood" value="1" checked/> &nbsp;是&nbsp;&nbsp;
					<input type="radio" name="isGood" value="0" /> &nbsp;否
				</td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
	$(function() {
		$('#backid').click(function() {
			window.location.href = "chair_list.action";
		});
	});
</script>
</html>