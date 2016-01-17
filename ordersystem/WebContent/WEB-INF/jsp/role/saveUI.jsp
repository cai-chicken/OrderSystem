<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<form action="role_${role.id == null ? add:edit }.action" method="post" class="definewidth m20">
		<input type="hidden" name="id" value="${role.id }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">角色名称</td>
				<td>
					<input type="text" name="name" />
				</td>
			</tr>
			<tr>
				<td class="tableleft">角色描述</td>
				<td>
					<textarea name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td class="tableleft">权限</td>
				<td>
					<ul>
						<li>
							<label class='checkbox inline'>
							<input type='checkbox' name='group[]' value='' />菜单管理</label>
							<ul>
								<li>
									<label class='checkbox inline'>
									<input type='checkbox' name='node[]' value='13' />菜单列表</label>
									<li>
										<label class='checkbox inline'>
										<input type='checkbox' name='node[]' value='14' />菜单新增</label>
										<li>
											<label class='checkbox inline'>
											<input type='checkbox' name='node[]' value='15' />菜单编辑</label>
											<li>
												<label class='checkbox inline'>
												<input type='checkbox' name='node[]' value='16' />菜单删除</label>
							</ul>
						</li>
						<li>
							<label class='checkbox inline'>
							<input type='checkbox' name='group[]' value='' />角色</label>
							<ul>
								<li>
									<label class='checkbox inline'>
									<input type='checkbox' name='node[]' value='5' />角色列表</label>
									<li>
										<label class='checkbox inline'>
										<input type='checkbox' name='node[]' value='6' />角色添加</label>
										<li>
											<label class='checkbox inline'>
											<input type='checkbox' name='node[]' value='7' />角色编辑</label>
											<li>
												<label class='checkbox inline'>
												<input type='checkbox' name='node[]' value='8' />角色删除</label>
							</ul>
						</li>
					</ul>
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
			$(':checkbox[name="group[]"]').click(function() {
				$(':checkbox', $(this).closest('li')).prop('checked', this.checked);
			});
			$('#backid').click(function() {
				window.location.href = "role_list.action";
			});
		});
	</script>
</html>