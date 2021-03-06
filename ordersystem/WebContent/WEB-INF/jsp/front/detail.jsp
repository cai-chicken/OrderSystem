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
                        <img src="../${menu.image }" height="130" onclick="order('${menu.id}')" alt="no image" id="menuImg"/>
                    </div>
                    <div class="nav_picr">
                        <p><span class="span1">菜名:</span><span class="span2">${menu.name }</span></p>
                        <p><span class="span1">好评率：</span><span class="span2" style="color: red;font-weight: bold;font-family: 'Microsoft Yahei'">
                        		<c:choose>
	                        		<c:when test="${menu.positiveRate == 0 }">
			                        	暂无评论
	                        		</c:when>
	                        		<c:otherwise>
	                        			${menu.positiveRate }%
	                        		</c:otherwise>
	                        	</c:choose>
                        </span></p>
                        <p><span class="span1">价格：</span><span class="span2" style="color: red;font-weight: bold;font-family: 'Microsoft Yahei'">${menu.price }元</span></p>
                        <form id="orderForm" action="/ordersystem/front/front_order.action" method="post">
                        	<input type="hidden" name="menuId" value="${menu.id }">
                        	<input type="hidden" name="isOrder" value="1">
                        	<%-- <input type="hidden" name="chairId" value="${menu.chair.id }"> --%>
	                        <p>
	                        	<span class="span1">下单参数：</span>
	                       	</p>
	                       	<p>
	                        	<span class="span2">(1)辣味：
	                        		<!-- (1)是否放辣椒；(2)是否打包带走；(3)支付方式 -->
	                        		<input type="radio" name="chilli" value="无辣" checked="checked">无辣
	                        		<input type="radio" name="chilli" value="少辣">少辣
	                        		<input type="radio" name="chilli" value="中辣">中辣
	                        		<input type="radio" name="chilli" value="变态辣">变态辣
	                       		</span>
	                       	</p>
	                       	<p>
	                        	<span class="span2">(2)打包：
	                        		<!-- (1)是否放辣椒；(2)是否打包带走；(3)支付方式 -->
	                        		<input type="radio" name="pack" value="否" checked="checked">否
	                        		<input type="radio" name="pack" value="是">是
	                       		</span>
	                       	</p>
                       	</form>
                        <p class="btn" style="float: right;margin-right: 50px;background: red">
                        	<span onclick="submitOrder()">提交订单</span>
                        </p>
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
                <div class="main_lb">做法</div>
                <div class="main_lb">${menu.method }</div>
                <div class="main_lt">${menu.cuisine.name }</div>
                <div class="main_lt">${menu.cuisine.description }</div>
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
/*提交订单*/
function submitOrder(){
	$("#menuImg").attr("src","${pageContext.request.contextPath}/style/front/img/erweima.jpg");
	$("#menuImg").addClass("menuImg");
}
function order(menuId){
	if($("#menuImg").hasClass("menuImg")){
		/* location.href='/ordersystem/front/front_orderSuccess.action?menuId='+menuId; */
		$("#orderForm").submit();
	}
}
</script>
</html>