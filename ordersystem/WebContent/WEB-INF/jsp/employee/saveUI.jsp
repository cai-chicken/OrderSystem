<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<c:if test="${employee.id == null }">
		<form action="employee_add.action" method="post">
	</c:if>
	<c:if test="${employee.id != null }">
		<form action="employee_edit.action" method="post">
	</c:if>
		<input type="hidden" name="id" value="${employee.id }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">编号<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="num" value=${employee.num }>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">姓名<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="name" value=${employee.name }>
				</td>
			</tr>
			<tr>
				<td class="tableleft">登录名<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="loginName" value=${employee.loginName }>
				</td>
			</tr>
			<tr>
				<td class="tableleft">性别<span style="color: red;">*</span></td>
				<td>
					<c:if test="${employee.sex == '男' || employee.sex == null}">
						<input type="radio" name="sex" value="男" checked> &nbsp;男&nbsp;&nbsp;
						<input type="radio" name="sex" value="女" > &nbsp;女
					</c:if>
					<c:if test="${employee.sex == '女' }">
						<input type="radio" name="sex" value="男"> &nbsp;男&nbsp;&nbsp;
						<input type="radio" name="sex" value="女" checked> &nbsp;女
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="tableleft">出生年月日</td>
				<td>
					<input type="text" name="birthday" value=${employee.birthday }>
				</td>
			</tr>
			<tr>
				<td class="tableleft">家庭地址</td>
				<td>
					<input type="text" name="address" value=${employee.address }>
				</td>
			</tr>
			<tr>
				<td class="tableleft">手机号码<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="phoneNumber" value=${employee.phoneNumber }>
				</td>
			</tr>
			<tr>
				<!--要做些修改，使用复选框列出所有的角色-->
				<td class="tableleft">所属角色</td>
				<td>
					<input type="text" name="roles" />
				</td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
	$(function() {
		$('#backid').click(function() {
			window.location.href = "employee_list.action";
		});
	});
</script>
</html>