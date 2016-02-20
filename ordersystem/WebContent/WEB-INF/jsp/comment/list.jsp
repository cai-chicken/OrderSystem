<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
</head>
<body>
	<form class="form-inline definewidth m20" action="comment_list.action" method="get">
		所属菜单名称：
		<select name="menuId">
			<option value="0">请选择所属菜名</option>
			<c:forEach items="${menuList }" var="menu">
				<c:choose>
					<c:when test="${mId == menu.id }">
						<option value="${menu.id }" selected="selected">${menu.name }</option>
					</c:when>
					<c:otherwise>
						<option value="${menu.id }">${menu.name }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>&nbsp;&nbsp;
		评论星级：
		<select name="star">
			<option value="0">请选择评论星级</option>
			<option value="1">1星(不喜欢)</option>
			<option value="2">2星(还好)</option>
			<option value="3">3星(好吃)</option>
			<option value="4">4星(很好吃)</option>
			<option value="5">5星(超级好吃)</option>
		</select>
		<!--查询按钮-->
		<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>所属菜单名称</th>
				<th>评论者</th>
				<th>评论时间</th>
				<th>评论星级</th>
				<th>评价描述</th>
			</tr>
		</thead>
		<c:forEach items="${pageBean.recordList }" var="comment">
		<tr>
			<td>${comment.menu.name }</td>
			<td>${comment.user.name }</td>
			<td>${comment.commentTime }</td>
			<td>${comment.star }星</td>
			<td>${comment.description }</td>
		</tr>
		</c:forEach>
	</table>
	<!--分页-->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
<script type="text/javascript" >
	function goToPage( pageNum ){
		/* pageNum为当前页，将被BaseAction中的成员变量接收 */
		/* window.location.href = "/ordersystem/comment_list.action?pageNum=" + pageNum; */
		$(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
		document.forms[0].submit(); 
	}
</script>
</html>