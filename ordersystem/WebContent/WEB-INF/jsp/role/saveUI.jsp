<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/Js/jquery_treeview/jquery.treeview.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/style/Js/jquery_treeview/jquery.treeview.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tree").treeview();
	});
</script>
</head>
<body>
	<c:if test="${role.id == null }">
		<form action="role_add.action" id="myForm" method="post" class="definewidth m20">
	</c:if>
	<c:if test="${role.id != null }">
		<form action="role_edit.action" id="myForm" method="post" class="definewidth m20">
	</c:if>
		<input type="hidden" name="id" value="${role.id }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">角色名称<span style="color: red;">*</span></td>
				<td>
					<input type="text" name="name" id="name" placeholder="请输入角色名称" value="${role.name }"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">角色描述<span style="color: red;">*</span></td>
				<td>
					<textarea name="description" id="description" placeholder="请输入角色描述"><c:out value="${role.description }"></c:out></textarea>
				</td>
			</tr>
			<tr>
				<td class="tableleft">权限</td>
				<td>
					<ul id="tree">
						<!-- 父级权限 -->
						<c:forEach items="${topPrivilegeList }" var="parentPrivilege">
							<li style="display: inline-block;width: 800px;">
								<label class='checkbox inline'>
								<input type='checkbox' name='privilegeIds' value='${parentPrivilege.id }' 
									<c:forEach items="${privilegeIds }" var="privilegeId">
										<c:if test="${parentPrivilege.id == privilegeId }">
										checked="checked"
										</c:if>
									</c:forEach> 
								/>${parentPrivilege.name }</label>
								<ul style="list-style: none;">
								<!-- 子级权限 -->
								<c:forEach items="${parentPrivilege.children }" var="childrenPrivilege">
									<li >
										<label class='checkbox inline'>
										<input type='checkbox' name='privilegeIds' value='${childrenPrivilege.id }' 
											<c:forEach items="${privilegeIds }" var="privilegeId">
												<c:if test="${childrenPrivilege.id == privilegeId }">
												checked="checked"
												</c:if>
											</c:forEach>
										/>${childrenPrivilege.name }</label>
										<ul style="list-style: none;">
										<!-- 第三极权限 -->
										<c:forEach items="${childrenPrivilege.children }" var="children">
											<li>
												<label class='checkbox inline'>
												<input type='checkbox' name='privilegeIds' value='${children.id }' 
													<c:forEach items="${privilegeIds }" var="privilegeId">
														<c:if test="${children.id == privilegeId }">
														checked="checked"
														</c:if>
													</c:forEach>
												/>${children.name }</label>
											</li>
										</c:forEach>
										</ul>
									</li>
								</c:forEach>
								</ul>
							</li>
						</c:forEach>
						
					</ul>
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
			$(':checkbox[name="privilegeIds"]').click(function() {
				$(':checkbox', $(this).closest('li')).prop('checked', this.checked);
				// 当选中一个权限时，也要选中所有的直接上级权限
				if(this.checked == true){
					$(this).parents("li").children("input").attr("checked", true);
				}
			});
			$('#backid').click(function() {
				window.location.href = "role_list.action";
			});
		});
		function beforeSubmit(){
			var name = $("#name").val();
			var description = $("#description").val();
			if(name == null || name == ""){
				alert("请输入角色名称");
				$("#name").focus();
				return;
			}
			if(description == null || description == ""){
				alert("请输入角色描述");
				return ;
			}
			
			$("#myForm").submit();
		}
	</script>
</html>