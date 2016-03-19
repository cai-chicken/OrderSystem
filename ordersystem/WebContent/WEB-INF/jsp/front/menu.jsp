<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>已点菜单页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/front/Css/base.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/front/Css/index.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/style/front/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/style/front/js/index.js"></script>
</head>
<body>
    <div class="wrap">
        <div class="row_one clearfix">
            <div class="introduct">${msg }</div>
            <div class="menu" style="width:60px;line-height: 30px;text-align: center;"  
            	onclick="javascript:location.href='/ordersystem/front/front_toIndex.action'">首页</div>
        </div>
        <form action="/ordersystem/front/front_orderSuccessList.action" id="myform">
	        <div class="nav clearfix">
	            <div class="nav_r clearfix" style="margin: 0 auto;float: none;">
	            <c:forEach items="${pageBean.recordList }" var="menu">
	                <div class="nav_pic clearfix" style="cursor: pointer;" onclick="javascript:location.href='/ordersystem/front/front_menuDetail.action?menuId=${menu.id}'">
	                    <div class="nav_picl">
	                        <img src="../${menu.image }" height="130"  alt=""/>
	                    </div>
	                    <div class="nav_picr">
	                        <p><span class="span1">菜名:</span><span class="span2" style="color: red;font-weight: bold;font-family: 'Microsoft Yahei'">${menu.name}</span></p>
	                        <p><span class="span1">价格：</span><span class="span2">${menu.price }元</span></p>
	                        <p><span class="span1">状态：</span><span class="span2" style="color: red;font-weight: bold;font-family: 'Microsoft Yahei'">${menu.status }</span></p>
	                        <p><span class="span1">菜单简介：</span><span class="span2">${menu.description }</span></p>
	                    </div>
	                </div>
	            </c:forEach>
	            </div>
	        </div>
        </form>
        <div class="paging clearfix">
	        <!--分页-->
			<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
        </div>
    </div>
</body>
<script type="text/javascript">
function goToPage( pageNum ){
	$("#myform").append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
	$("#myform").submit();
}
</script>
</html>