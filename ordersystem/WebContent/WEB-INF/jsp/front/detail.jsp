<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>菜单详情页</title>
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
        <div class="nav clearfix">
            <div class="nav_r clearfix" style="margin: 0 auto;float: none;">
                <div class="nav_pic clearfix">
                    <div class="nav_picl">
                        <img src="../${menu.image }" height="130"  alt="" />
                    </div>
                    <div class="nav_picr">
                        <p><span class="span1">菜名:</span><span class="span2">${menu.name }</span></p>
                        <p><span class="span1">好评率：</span><span class="span2" style="color: red;font-weight: bold;font-family: 'Microsoft Yahei'">${menu.positiveRate }%</span></p>
                        <p><span class="span1">价格：</span><span class="span2" style="color: red;font-weight: bold;font-family: 'Microsoft Yahei'">${menu.price }元</span></p>
                        <p><span class="span1">下单参数：</span><span class="span2">(1)是否放辣椒；(2)是否打包带走；(3)支付方式</span></p>
                        <p class="btn"><span>提交订单</span></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="main clearfix">
            <%-- <div class="main_l">
                <div class="main_lt">食材内容食材内容食材内容食材内容食材内容食材内容食材内容食材内容食材内容</div>
                <div class="main_lb">${menu.cuisine.name }</div>
            </div> --%>
            <div class="main_r" style="text-align: center;">
            	<div class="main_lt">主要食材</div>
                <div class="main_lt">${menu.mainFood }</div>
                <div class="main_lb">${menu.cuisine.name }</div>
                <div class="main_lb">${menu.cuisine.description }</div>
            </div>
        </div>
        <form action="/ordersystem/front/front_menuDetail.action" id="myform" method="post">
        <input type="hidden" name="menuId" value="${menu.id }">
        <div class="say_table" style="margin: 0 auto">
            <p style="text-align: center;color: red;font-weight: bold;font-family: 'Microsoft Yahei'">评论列表</p>
            <ul class="ul1">
                <li style="width: 100px">评论者姓名</li>
                <li style="width: 50px">评论星级</li>
                <li style="width: 200px">评论时间</li>
                <li style="width: 450px">评论描述</li>
            </ul>
            <c:forEach items="${pageBean.recordList }" var="comment">
	            <ul class="ul2">
	                <li style="width: 100px">${comment.user.name }</li>
	                <li style="width: 50px">${comment.star }星</li>
	                <li style="width: 200px">${comment.commentTime }</li>
	                <li style="width: 450px">${comment.description }</li>
	            </ul>
            </c:forEach>
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