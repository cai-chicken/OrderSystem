<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/style/Js/file_upload.js"></script>
</head>
<body>
	<c:if test="${menu.id == null }">
	<form action="menu_add.action" id="myForm" method="post" enctype="multipart/form-data" class="definewidth m20">
	</c:if>
	<c:if test="${menu.id != null }">
	<form action="menu_edit.action" id="myForm" method="post" enctype="multipart/form-data" class="definewidth m20">
	</c:if>
		<input type="hidden" name="id" value="${menu.id }" />
		<table class="table table-bordered table-hover m10">
			<tr>
				<td class="tableleft">编码<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="num" id="num" placeholder="请输入菜单编码" value="${menu.num }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">菜名<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="name" id="name" placeholder="请输入菜名" value="${menu.name }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">价格<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="price" id="price" placeholder="请输入价格" value="${menu.price }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">图片<span style="color: red;">*</span></td>
				<td>
					<input type="file" name="upload" id="upload" onchange="previewImage(this)"/><span style="color: red">图片名称不可以有中文</span>
					<div id="preview">
					    <img id="imghead" width=100 height=100 border=0 alt="no image" src="${menu.image }">
					</div>
				</td>
			</tr>
			<tr>
				<td class="tableleft">简介<span style="color: red;">*</span></td>
				<td>
					<textarea name="description" id="description" placeholder="请输入菜单简介"><c:out value="${menu.description }"></c:out></textarea>
				</td>
			</tr>
			<tr>
				<td class="tableleft">主要食材</td>
				<td>
					<textarea name="mainFood" id="mainFood" placeholder="请输入主要食材"><c:out value="${menu.mainFood }"></c:out></textarea>
				</td>
			</tr>
			<tr>
				<td class="tableleft">做法</td>
				<td>
					<textarea name="method" id="method" placeholder="请输入做法"><c:out value="${menu.method }"></c:out></textarea>
				</td>
			</tr>
			<tr>
				<td class="tableleft">是否特色菜<span style="color: red;">*</span></td>
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
							<c:choose>
								<c:when test="${menu.cuisine.name == cuisine.name }">
									<option value="${cuisine.id }" selected="selected">${cuisine.name }</option>
								</c:when>
								<c:otherwise>
									<option value="${cuisine.id }">${cuisine.name }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
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
			window.location.href = "menu_list.action";
		});
	});
	
	function beforeSubmit(){
		var num = $("#num").val();
		var name = $("#name").val();
		var price = $("#price").val();
		var upload = $("#upload").val();
		var description = $("#description").val();
		
		if(num == null || num == ""){
			alert("请输入菜单编号");
			$("#num").focus();
			return;
		}
		if(name == null || name == ""){
			alert("请输入菜单名称");
			$("#name").focus();
			return;
		}
		if(price == null || price == ""){
			alert("请输入菜单单价");
			$("#price").focus();
			return;
		}
		if(description == null || description == ""){
			alert("请输入菜单描述");
			$("#description").focus();
			return;
		}
		
		$("#myForm").submit();
	}
</script>
</html>