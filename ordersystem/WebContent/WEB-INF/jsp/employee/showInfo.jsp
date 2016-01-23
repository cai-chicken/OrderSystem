<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人信息</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<td width="10%" class="tableleft">姓名</td>
			<td>
				<input type="text" readonly="readonly" name="name" value="${employeeLogin.name }"/>
			</td>
		</tr>
		<tr>
			<td class="tableleft">登录名</td>
			<td>
				<input type="text" readonly="readonly" name="loginName" value="${employeeLogin.loginName }"/>
			</td>
		</tr>
		<tr>
			<td class="tableleft">性别</td>
			<td>
				<input type="text" name="sex" readonly="readonly" value="${employeeLogin.sex }"/>
			</td>
		</tr>
		<tr>
			<td class="tableleft">出生年月日</td>
			<td>
				<input type="text" name="birthday" readonly="readonly" value="${employeeLogin.birthday }"/>
			</td>
		</tr>
		<tr>
			<td class="tableleft">家庭地址</td>
			<td>
				<input type="text" name="address" readonly="readonly" value="${employeeLogin.address }"/>
			</td>
		</tr>
		<tr>
			<td class="tableleft">手机号码</td>
			<td>
				<input type="text" name="phoneNumber" readonly="readonly" value="${employeeLogin.phoneNumber }"/>
			</td>
		</tr>
		<tr>
			<!--要做些修改，使用复选框列出所有的角色-->
			<td class="tableleft">所属角色</td>
			<td>
				<c:forEach items="${roleList }" var="role">
					<input type="text" name="roles" readonly="readonly" value="${role.name }"/>&nbsp;
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="tableleft"></td>
			<td>
				<button type="button" class="btn btn-primary" id="modifyMyInfo">修改个人资料</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-success" id="modifyMyPwd">修改密码</button>
			</td>
		</tr>
	</table>
</body>
<script>
	$(function () {
		$('#modifyMyInfo').click(function(){
			window.location.href="employee_editUI.action?id=${employeeLogin.id}";
		});
		$('#modifyMyPwd').click(function(){
			/* 因为改变的是当前用户的信息，所以不需要传入id */
			window.location.href="employee_modifyPwdUI.action";
		});
    });
</script>
</html>