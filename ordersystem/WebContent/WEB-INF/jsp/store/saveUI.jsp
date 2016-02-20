<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>本店信息编辑</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/style/Js/file_upload.js"></script>
</head>
<body>
	<c:if test="${store.id == null }">
		<form action="store_add.action" class="myForm" method="post" enctype="multipart/form-data" class="definewidth m20">
	</c:if>
	<c:if test="${store.id != null }">
		<form action="store_edit.action" class="myForm" method="post" enctype="multipart/form-data" class="definewidth m20">
	</c:if>
	<input type="hidden" name="id" value="${store.id }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">店名<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="name" id="name" value="${store.name }" placeholder="请输入店名"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">宣传图片<span style="color: red;">*</span></td>
				<td>
					<input type="file" name="upload" id="upload" onchange="previewImage(this)"/><span style="color: red">图片名称不可以有中文</span>
					<div id="preview">
					    <img id="imghead" width=100 height=100 border=0 alt="no image" src="${store.image }">
					</div>
				</td>
			</tr>
			<tr>
				<td class="tableleft">简介<span style="color: red;">*</span></td>
				<td>
					<textarea name="description" id="description" placeholder="请输入本店描述信息"><c:out value="${store.description }"></c:out></textarea>
				</td>
			</tr>
			<tr>
				<td class="tableleft">是否启用<span style="color: red;">*</span></td>
				<td>
					<input type="radio" name="state" value="1" checked/> &nbsp;是&nbsp;&nbsp;
					<input type="radio" name="state" value="0" /> &nbsp;否
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
			window.location.href = "store_list.action";
		});
	});
	function beforeSubmit(){
		var name = $("#name").val();
		var upload = $("#upload").val();
		var description = $("#description").val();
		if(name == null || name == ""){
			alert("店名不能为空");
			$("#name").focus();
			return ;
		}
		
		if(description == null || description == ""){
			alert("本店信息描述信息不能为空");
			return ;
		}
		
		$(".myForm").submit();
	}
</script>
</html>