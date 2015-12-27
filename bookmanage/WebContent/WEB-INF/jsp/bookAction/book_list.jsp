<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书列表</title>
</head>
<body>
	${userType} <s:a action="user_logout">退出登录</s:a>
	<br><br>
	<table border="1px">
		<thead>
			<tr>
				<th colspan="8">图书详细信息列表</th>
			</tr>
			<tr style="font-weight: bold;">
				<td>书名</td>
				<td>作者</td>
				<td>出版社</td>
				<td>出版日期</td>
				<td>页数</td>
				<td>价格</td>
				<td>内容摘要</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="recordList" status="status">
				<!-- 奇数行有背景色 -->
				<s:if test="#status.Even">
					<tr >
						<td>${name }</td>
						<td>${author }</td>
						<td>${pubHome }</td>
						<td>${pubDate }</td>
						<td>${count }</td>
						<td>${price }</td>
						<td>${content }</td>
						<td>
							<s:a action="book_editUI?id=%{id}">修改</s:a>
							<s:a action="book_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr style="background-color: plum">
						<td>${name }</td>
						<td>${author }</td>
						<td>${pubHome }</td>
						<td>${pubDate }</td>
						<td>${count }</td>
						<td>${price }</td>
						<td>${content }</td>
						<td>
							<s:a action="book_editUI?id=%{id}">修改</s:a>
							<s:a action="book_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
						</td>
					</tr>
				</s:else>
			</s:iterator>
		</tbody>
		<tr>
			<th colspan="8">
				<!-- 分页 -->
				<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
			</th>
		</tr>
	</table>
</body>
</html>