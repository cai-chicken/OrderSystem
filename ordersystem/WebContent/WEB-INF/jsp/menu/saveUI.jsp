<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<c:if test="${menu.id == null }">
	<form action="menu_add.action" method="post" enctype="multipart/form-data" class="definewidth m20">
	</c:if>
	<c:if test="${menu.id != null }">
	<form action="menu_edit.action" method="post" enctype="multipart/form-data" class="definewidth m20">
	</c:if>
		<input type="hidden" name="id" value="${menu.id }" />
		<table class="table table-bordered table-hover m10">
			<tr>
				<td class="tableleft">编码</td>
				<td>
					<input type="text" name="num" value="${menu.num }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">菜名</td>
				<td>
					<input type="text" name="name" value="${menu.name }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">价格</td>
				<td>
					<input type="text" name="price" value="${menu.price }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">图片</td>
				<td>
					<input type="file" name="upload" />
				</td>
			</tr>
			<tr>
				<td class="tableleft">简介</td>
				<td>
					<textarea name="description"><c:out value="${menu.description }"></c:out></textarea>
				</td>
			</tr>
			
			<tr>
				<td class="tableleft">是否特色菜</td>
				<td>
					<input type="radio" name="isSpecial" value="1" checked/> 是
					<input type="radio" name="isSpecial" value="0" /> 否
				</td>
			</tr>
			<tr>
				<td class="tableleft">所属菜系</td>
				<td>
					<select name="cuisineId">
						<option>请选择所属菜系</option>
						<c:forEach items="${cuisineList }" var="cuisine">
							<option value="${cuisine.id }">${cuisine.name }</option>
						</c:forEach>
					</select>
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
			window.location.href = "menu_list.action";
		});
	});
</script>
</html>