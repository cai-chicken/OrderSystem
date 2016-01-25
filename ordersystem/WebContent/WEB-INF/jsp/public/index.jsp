<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<title>后台管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${pageContext.request.contextPath}/style/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/style/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/style/assets/css/main-min.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div class="header">
			<div class="dl-title">
				<!--<img src="/chinapost/Public/assets/img/top.png">-->
			</div>
			<!--用户名，退出菜单-->
			<div class="dl-log">
				欢迎您，<span class="dl-log-user">${employeeLogin.name }</span>
				<a href="/ordersystem/employee_logout.action" title="退出系统" class="dl-log-quit">[退出]</a>
			</div>
		</div>
		<div class="content">
			<div class="dl-main-nav">
				<div class="dl-inform">
					<div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div>
				</div>
				<ul id="J_Nav" class="nav-list ks-clear">
					<!--一级菜单-->
					<c:forEach items="${topPrivilegeList }" var="topPrivilege">
						<c:if test="${employeeLogin.hasPrivilegeName(topPrivilege.name) }">
							<li class="nav-item dl-selected">
								<div class="nav-item-inner nav-home">${topPrivilege.name }</div>
							</li>
						</c:if>
					</c:forEach>
					<!-- <li class="nav-item dl-selected">
						<div class="nav-item-inner nav-order">菜单管理</div>
					</li>
					<li class="nav-item dl-selected">
						<div class="nav-item-inner nav-order">统计管理</div>
					</li>
					<li class="nav-item dl-selected">
						<div class="nav-item-inner nav-order">其它管理</div>
					</li> -->
				</ul>
			</div>
			<ul id="J_NavContent" class="dl-tab-conten">

			</ul>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/style/assets/js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/style/assets/js/bui-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/style/assets/js/common/main-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/style/assets/js/config-min.js"></script>
		<script>
		/*菜单管理*/
			BUI.use('common/main',function(){
			        var config = 
			        [
						{
							id:'1',
							menu:[
								{
									text:'系统管理',
									items:[
										{
											id:'2',
											text:'员工管理',
											href:'employee/list.html'
										},
										{
											id:'3',
											text:'角色管理',
											href:'role/list.html'
										},
										{
											id:'4',
											text:'个人资料',
											href:'myInfo/show.html'
										}
									]
								}
							]
						},
						{
							id:'6',
							menu:[
								{
									text:'菜单管理',
									items:[
										{
											id:'7',
											text:'菜单管理',
											href:'menu/list.html'
										},
										{
											id:'8',
											text:'评论管理',
											href:'comment/list.html'
										},
										{
											id:'9',
											text:'菜系管理',
											href:'cuisine/list.html'
										},
										{
											id:'10',
											text:'下单成功',
											href:'menu/orderSuccess.html'
										}
									]
								}
							]
						}
					];
			        new PageUtil.MainPage({
			            modulesConfig : config
			        });
			    });
		</script>
		<!--<div style="text-align:center;">
			我的食代点餐后台管理系统	@版权所有
		</div>-->
	</body>

</html>