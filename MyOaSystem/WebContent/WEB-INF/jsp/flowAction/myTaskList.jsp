<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>待我审批</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body> 

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 待我审批
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<!-- <form>
	<input type="hidden" name="pageNum" value="1" />
</form> -->

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="250">标题</td>
				<td width="115">申请人</td>
				<td width="115">申请日期</td>
				<td>相关操作</td>
			</tr>
		</thead>		
		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
        <s:iterator value="#taskViewList">
			<tr class="TableDetail1 template">
				<td><s:a action="flow_approveUI?applicationId=%{application.id}&taskId=%{task.id}&applicationTemplateId=%{application.applicationTemplate.id}">${application.title}</s:a></td>
				<td>${application.applicant.name}&nbsp;</td>
				<td><s:date name="application.applyTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
				<td><s:a action="flow_approveUI?applicationId=%{application.id}&taskId=%{task.id}&applicationTemplateId=%{application.applicationTemplate.id}">审批处理</s:a>
					<s:a action="flow_approveHistory?applicationId=%{application.id}">查看流转记录</s:a>
				</td>
			</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>

<!--分页信息-->

<div class="Description">
	说明：<br />
	1，这里列出的所有的表单状态都为"审批中"。
</div>

</body>
</html>
