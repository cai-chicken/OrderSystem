<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改密码</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<form action="employee_modifyPwd.action" method="post" id="myForm">
		<input type="hidden" name="id" value="${employeeLogin.id }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">原密码</td>
				<td>
					<input type="password" name="oldPwd" id="oldPwd"/>
					<span style="color: red">${errorPwd}</span>
				</td>
			</tr>
			<tr>
				<td class="tableleft">新密码</td>
				<td>
					<input type="password" name="password" id="password"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">确认密码</td>
				<td>
					<input type="password" name="newPwd" id="newPwd"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="button" class="btn btn-primary" onclick="javascript:beforeSubmit()">修改</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid" id="backid">返回个人资料</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
	$(function() {
		$('#backid').click(function() {
			window.location.href = "employee_showInfo.action";
		});
	});
	function beforeSubmit(){
		var oldPwd = $("#oldPwd").val();
		var password = $("#password").val();
		var newPwd = $("#newPwd").val();
		if(oldPwd == null || oldPwd == ""){
			alert("请输入原密码！");
			$("#oldPwd").focus();
			return;
		}
		if(password == null || password == ""){
			alert("请输入新密码！");
			$("#password").focus();
			return;
		}
		if(newPwd == null || newPwd == ""){
			alert("请输入确认密码！");
			$("#newPwd").focus();
			return;
		}
		if(password != newPwd){
			alert("新密码和确认密码不同！");
			return;
		}
		$("#myForm").submit();
	}
</script>
</html>