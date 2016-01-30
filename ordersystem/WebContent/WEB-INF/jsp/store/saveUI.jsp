<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>本店信息编辑</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<c:if test="${store.id == null }">
		<form action="store_add.action" method="post" enctype="multipart/form-data" class="definewidth m20">
	</c:if>
	<c:if test="${store.id != null }">
		<form action="store_edit.action" method="post" enctype="multipart/form-data" class="definewidth m20">
	</c:if>
	<input type="hidden" name="id" value="${store.id }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">店名</td>
				<td>
					<input type="text" name="name" value="${store.name }"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">宣传图片</td>
				<td>
					<input type="file" name="upload" />
				</td>
			</tr>
			<tr>
				<td class="tableleft">简介</td>
				<td>
					<textarea name="description"><c:out value="${store.description }"></c:out></textarea>
				</td>
			</tr>
			<tr>
				<td class="tableleft">是否启用</td>
				<td>
					<input type="radio" name="state" value="1" checked/> &nbsp;是&nbsp;&nbsp;
					<input type="radio" name="state" value="0" /> &nbsp;否
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
			window.location.href = "store_list.action";
		});
	});
</script>
</html>