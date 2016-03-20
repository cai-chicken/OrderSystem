<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/front/Css/base.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/front/Css/index.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/style/front/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/style/front/js/index.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		var cuisineId = $(".all_menu").val();
    		if(cuisineId == null || cuisineId == ""){
    			$("#cuisine").addClass("all_menu");
    		}
    	})
    </script>
</head>
<body>
    <div class="wrap">
        <div class="txt_show clearfix">
            <div class="txt_l">
            	<span style="font-size:20px;font-weight:bold;font-family:'Microsoft Yahei'">${store.name }</span>
            </div>
            <div class="txt_r">
                <div class="txt_con">
                    <span>${store.description }</span>
                </div>
            </div>
        </div>
        <div class="row_one clearfix">
            <div class="introduct" id="introduct">${msg }</div>
            <div class="menu" id="orderMenu" onclick="gotoOrderMenu()">已点菜单</div>
        </div>
        <div class="banner clearfix">
            <ul>
            	<c:forEach items="${menus }" var="menu" varStatus="status">
	                <li id="menuImg${status.index }"><a href="#"><img src="../${menu.image }" width="1900" height="380" /></a></li>
            	</c:forEach>
            </ul>
            <ol id="ol">
            	<c:forEach items="${menus }" var="menu" varStatus="status">
            		<c:choose>
            			<c:when test="${status.index == 0 }">
			                <li class="current">${menu.name }</li>
            			</c:when>
            			<c:otherwise>
            				<li>${menu.name }</li>
            			</c:otherwise>
            		</c:choose>
            	</c:forEach>
            </ol>
            <span class="leftBtn">&lt;</span>
            <span class="rightBtn">&gt;</span>
        </div>
        <div class="nav clearfix">
            <div class="nav_l">
                <!-- <span class="all_menu" onclick="myformSubmit(0)">所有菜单</span> -->
                <ul>
                	<li id="cuisine" value="0" onclick="myformSubmit(this,0)">所有菜单</li>
                	<c:forEach items="${cuisines }" var="cuisine">
                		<c:choose>
                			<c:when test="${cuisine.id == menuParam.cuisineId }">
		                		<li value="${cuisine.id }" class="all_menu" onclick="myformSubmit(this,0)">${cuisine.name }</li>
                			</c:when>
                			<c:otherwise>
		                		<li value="${cuisine.id }" onclick="myformSubmit(this,0)">${cuisine.name }</li>
                			</c:otherwise>
                		</c:choose>
                	</c:forEach>
                    <!-- <li>粤系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>-->
                </ul>
            </div>
            <div class="nav_r clearfix">
            <form action="/ordersystem/front/front_toIndex.action" method="post" id="myform">
                <div class="nav_rt clearfix">
                	<div class="nav_rtname">菜名
                    	<input type="text" name="menuName" value="${menuParam.menuName }" id="menuName" style="width: 160px;height: 30px;border:1px solid #8e8e92" placeholder="请输入菜名">
                    </div>
                    <div class="nav_rtname">好评率</div>
                    <select name="sort" id="sort" class="set_one">
                        <option value="0">请选择排序方式</option>
                        <c:if test="${menuParam.sort == 'desc' }">
							<option value="desc" selected="selected">降序</option>
							<option value="asc">升序</option>
                        </c:if>
                        <c:if test="${menuParam.sort == 'asc' }">
							<option value="desc">降序</option>
							<option value="asc" selected="selected">升序</option>
                        </c:if>
                        <c:if test="${menuParam.sort == '0' }">
							<option value="desc">降序</option>
							<option value="asc">升序</option>
                        </c:if>
                    </select>
                    <div class="nav_rtname">价格
                    	<input type="text" id="price1" value="${menuParam.price1 }" name="price1" style="width: 80px;height: 30px;border:1px solid #8e8e92">
                    	-
                    	<input type="text" id="price2" value="${menuParam.price2 }" name="price2" style="width: 80px;height: 30px;border:1px solid #8e8e92">
                    </div>
                    <div class="menu" onclick="myformSubmit(this,-1)" style="width:60px;line-height: 30px;text-align: center;">搜索</div>
                </div>
                </form>
                <!-- <div class="nav_pic clearfix">
                    <div class="nav_picl">
                        <img src="" height="130"  alt="" onclick="javascript:location.href='detail.html'"/>
                    </div>
                    <div class="nav_picr">
                        <p><span class="span1">菜名:</span><span class="span2">好吃你就多吃点</span></p>
                        <p><span class="span1">好评率：</span><span class="span2">88%</span></p>
                        <p><span class="span1">价格：</span><span class="span2">32元</span></p>
                        <p><span class="span1">下单参数：</span><span class="span2">这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜</span></p>
                    </div>
                </div> -->
                <c:forEach items="${pageBean.recordList }" var="menu">
	                <div class="nav_pic"  style="cursor: pointer;" onclick="javascript:location.href='/ordersystem/front/front_menuDetail.action?menuId=${menu.id}'">
	                    <div class="nav_picl">
	                        <img src="../${menu.image }" height="130"  alt="" onclick="javascript:location.href='detail.html'"/>
	                    </div>
	                    <div class="nav_picr">
	                        <p><span class="span1">菜名:</span><span class="span2">${menu.name }</span></p>
	                        <p><span class="span1">好评率：</span>
	                        	<c:choose>
	                        		<c:when test="${menu.positiveRate == 0 }">
			                        	<span class="span2">暂无评论</span>
	                        		</c:when>
	                        		<c:otherwise>
	                        			<span class="span2">${menu.positiveRate }%</span>
	                        		</c:otherwise>
	                        	</c:choose>
	                        </p>
	                        <p><span class="span1">价格：</span><span class="span2">${menu.price }元</span></p>
	                        <p><span class="span1">菜品简介：</span><span class="span2">${menu.description }</span></p>
	                    </div>
	                </div>
                </c:forEach>
            </div>
        </div>
        <div class="paging clearfix">
	        <!--分页-->
			<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
        </div>
    </div>
</body>
<script type="text/javascript">
function gotoOrderMenu(){
	var msg = $("#introduct").html();
	if("当前没有顾客点餐，现在我们只为您服务。" == msg){
		alert("您当前没有点餐。");
	} else {
		location.href="/ordersystem/front/front_orderSuccessList.action";
	}
}
function goToPage( pageNum ){
	$("#myform").append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
	myformSubmit(null,-1);
}
function myformSubmit(obj,num){
	var cuisineId = $(".all_menu").val();
	if(num != -1){
		$(obj).addClass('all_menu').siblings().removeClass('all_menu');
		$("#myform").append("<input type='hidden' name='cuisineId' value='" + obj.value +"'>");
	} else {
		if(cuisineId == null || cuisineId == ""){
			cuisineId = 0;
		}
		$("#myform").append("<input type='hidden' name='cuisineId' value='" + cuisineId +"'>");
	}
	/* $("#myform").append("<input type='hidden' name='cuisineId' value='" + obj.value +"'>");  */
	$("#myform").submit();
}
</script>
</html>