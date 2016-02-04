<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>编辑员工信息</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<c:if test="${employee.id == null }">
		<form action="employee_add.action" id="myForm" method="post">
	</c:if>
	<c:if test="${employee.id != null }">
		<form action="employee_edit.action" id="myForm" method="post">
	</c:if>
		<input type="hidden" name="id" value="${employee.id }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">编号<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="num" id="num" placeholder="请输入员工编号" value="${employee.num }">
				</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">姓名<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="name" id="name" placeholder="请输入员工姓名" value="${employee.name }">
				</td>
			</tr>
			<tr>
				<td class="tableleft">登录名<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="loginName" id="loginName" placeholder="请输入登录名" value="${employee.loginName }">
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
					<input type="text" name="birthday" value="${employee.birthday }">
				</td>
			</tr>
			<tr>
				<td class="tableleft">家庭地址</td>
				<td>
					<input type="text" name="address" value="${employee.address }">
				</td>
			</tr>
			<tr>
				<td class="tableleft">手机号码<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="phoneNumber" id="phoneNumber" placeholder="请输入手机号" value="${employee.phoneNumber }">
				</td>
			</tr>
			<tr>
				<!--要做些修改，使用复选框列出所有的角色-->
				<td class="tableleft">所属角色</td>
				<td>
					<c:forEach items="${roleList }" var="role">
						<div>
							<input type="checkbox" name="roleIds" value="${role.id }" id="role_${role.id }" style="float: left;" 
								<c:forEach items="${roleIds }" var="roleId">
									<c:if test="${role.id == roleId }">
									checked="checked"
									</c:if>
								</c:forEach>
							/>
							<label for="role_${role.id }" style="float: left;">${role.name }</label>
						</div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="button" class="btn btn-primary" onclick="beforeSubmit()">保存</button>&nbsp;&nbsp;
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
	function beforeSubmit(){
		var num = $("#num").val();
		var name = $("#name").val();
		var loginName = $("#loginName").val();
		var phoneNumber = $("#phoneNumber").val();
		
		if(num == null || num == ""){
			alert("请输入员工编号");
			$("#num").focus();
			return;
		}
		if(name == null || name == ""){
			alert("请输入员工姓名");
			$("#name").focus();
			return;
		}
		if(loginName == null || loginName == ""){
			alert("请输入登录名");
			$("#loginName").focus();
			return;
		}
		if(phoneNumber == null || phoneNumber == ""){
			alert("请输入手机号");
			$("#phoneNumber").focus();
			return;
		}
		
		$("#myForm").submit();
	}
</script>
</html>