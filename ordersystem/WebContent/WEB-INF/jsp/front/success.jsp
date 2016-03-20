<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>下单成功页</title>
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
        <div class="arguments">
            <div class="arguments_t">下单参数的显示</div>
            <ul>
                <li>菜名：<span class="displayDetail">${menu.name }</span></li>
                <li>对应的桌号：<span class="displayDetail">${menu.chair.num }</span></li>
            </ul>
            <ul>
                <li>辣味：<span class="displayDetail">${menu.chilli }</span></li>
                <li>打包：<span class="displayDetail">${menu.pack }</span></li>
            </ul>
            <ul>
                <li>下单时间：<span>${menu.orderTime}</span></li>
                <li>所属菜系：<span>${menu.cuisine.name }</span></li>
            </ul>
            <ul>
                <li>价格：<span class="displayDetail">${menu.price }元</span></li>
                <li>好评率：<span>${menu.positiveRate }%</span></li>
            </ul>
        </div>
        <div class="banner headline" id="list" style="height: 200px">
        	<c:forEach items="${knowledges }" var="knowledge" varStatus="status">
        		<c:choose>
        			<c:when test="${status.index == 0 }">
			            <div class="big_box" id="knowledge${status.index }" style="width: 800px;margin: 0 auto;" hidden="hidden">
			                <div class="big_boxt">${knowledge.name }</div>
			                <textarea name="" id="" cols="30" rows="10" placeholder="这是内容区">${knowledge.description }</textarea>
			            </div>
        			</c:when>
        			<c:otherwise>
        				<div class="big_box" id="knowledge${status.index }" style="width: 800px;margin: 0 auto;" >
			                <div class="big_boxt">${knowledge.name }</div>
			                <textarea name="" id="" cols="30" rows="10" placeholder="这是内容区">${knowledge.description }</textarea>
			            </div>
        			</c:otherwise>
        		</c:choose>
            </c:forEach>
            <span class="leftBtn">&lt;</span>
            <span class="rightBtn">&gt;</span>
        </div>
    </div>
</body>
<script type="text/javascript">
	var num = 0;
	$('.leftBtn').click(
		function(e) {
			var length = $(".big_box").length;
			num--;
			if (num < 0) {
				num = length-1;
			}
			$("#knowledge"+num).siblings().attr("hidden","hidden");
			$("#knowledge"+num).removeAttr("hidden");
		});
	$('.rightBtn').click(
			function(e) {
				var length = $(".big_box").length;
				num++;
				if (num > (length-1)) {
					num = 0;
				}
				$("#knowledge"+num).siblings().attr("hidden","hidden");
				$("#knowledge"+num).removeAttr("hidden");
			});
</script>
</html>