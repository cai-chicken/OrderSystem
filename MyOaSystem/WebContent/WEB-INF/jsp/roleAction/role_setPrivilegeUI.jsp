<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>配置权限</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf"%>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/file.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />

<script type="text/javascript">
	// 选择所有
	function selectAll(checkedValue) {
		$("input[type=checkbox][name=resourceIdList]").attr("checked",
				checkedValue);
	}

	function doChecked(liID, checkedValue) {
		// 当前点击的checkbox元素所在的li元素
		var jLi = $("#" + liID);

		// 选中所有的直属下级。（children()方法是筛选下一级，find()是筛选所有后代）
		jLi.children("ul").find("input[type=checkbox]").attr("checked",
				checkedValue);

		// 选中或取消选中直属上级
		if (checkedValue) { // checkedValue是boolean型，表示是否选中了当前复选框
			// 如果当前是选中，则选中所有的直属上级
			jLi.parents("li").children("input[type=checkbox]").attr("checked",
					checkedValue);
		} else {
			// 如果当前是取消选中，并且同级中没有被选中的项，则也取消上级的选中状态
			var jCheckedSibingCB = jLi.siblings("li").children(
					"input[type=checkbox]:checked");
			if (jCheckedSibingCB.size() == 0) {
				var jCheckboxInput = jLi.parent("ul").prev("label").prev(
						"input[type=checkbox]");
				jCheckboxInput.attr("checked", checkedValue);

				// 递归操作每一层直属上级
				var jParentLi = jCheckboxInput.parent("li");
				if (jParentLi.size() > 0) {
					doChecked(jParentLi.attr("id"), checkedValue);
				}
			}
		}
	}

	$(function() {
		$("#tree").treeview();
	});
</script>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				配置权限
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<s:form action="role_setPrivilege">
			<s:hidden name="id" />
			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					正在为【${role.name}】配置权限
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<!--表头-->
						<thead>
							<tr align="LEFT" valign="MIDDLE" id="TableTitle">
								<td width="300px" style="padding-left: 7px;">
									<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
									<input type="CHECKBOX" id="cbSelectAll"
									onClick="selectAll(this.checked)" /> <label for="cbSelectAll">全选</label>
								</td>
							</tr>
						</thead>

						<!--显示数据列表-->
						<tbody id="TableData">
							<tr class="TableDetail1">
								<!-- 显示权限树 -->
								<td>
									<ul id='tree'>
										<li id='li_45'><input type='checkbox'
											name='resourceIdList' id='cb_45'
											onclick='doChecked("li_45", this.checked)' /> <label
											for='cb_45'><span class='folder' id='45'>系统管理</span></label>
											<ul>
												<li id='li_46'><input type='checkbox'
													name='resourceIdList' id='cb_46'
													onclick='doChecked("li_46", this.checked)' /> <label
													for='cb_46'><span class='folder' id='46'>部门管理</span></label>
													<ul>
														<li id='li_128'><input type='checkbox'
															name='resourceIdList' id='cb_128'
															onclick='doChecked("li_128", this.checked)' /> <label
															for='cb_128'><span class='folder' id='128'>部门列表</span></label>
														</li>
													</ul></li>
											</ul></li>
									</ul>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" /> <a
					href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>

	<div class="Description">
		说明：<br /> 1，选中一个权限时：<br /> &nbsp;&nbsp;&nbsp;&nbsp; a，应该选中 他的所有直系上级。<br />
		&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br /> 2，取消选择一个权限时：<br />
		&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择 他的所有直系下级。<br />
		&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并递归向上做这个操作。<br />

		3，全选/取消全选。<br /> 4，默认选中当前岗位已有的权限。<br />
	</div>

</body>
</html>
