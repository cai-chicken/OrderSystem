<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <div class="introduct">你前面还有俩位顾客在排队，请耐心等候</div>
            <div class="menu" onclick="javascript:location.href='index.html'">首页</div>
        </div>
        <div class="arguments">
            <div class="arguments_t">下单参数的显示</div>
            <ul>
                <li>菜名</li>
                <li>对应的桌号</li>
            </ul>
            <ul>
                <li>是否放辣椒</li>
                <li>是否带走</li>
            </ul>
            <ul>
                <li>支付方式</li>
                <li>所属菜系</li>
            </ul>
            <ul>
                <li>下单时间</li>
                <li>下单时间</li>
            </ul>
        </div>
        <div class="banner headline" style="background: red">
            <div class="big_box">
                <div class="big_boxt">标题</div>
                <textarea name="" id="" cols="30" rows="10" placeholder="这是内容区"></textarea>
            </div>
            <div class="big_box">
                <div class="big_boxt">标题</div>
                <textarea name="" id="" cols="30" rows="10" placeholder="这是内容区"></textarea>
            </div>
            <!-- 
            <div class="big_box">
                <div class="big_boxt">标题</div>
                <textarea name="" id="" cols="30" rows="10" placeholder="这是内容区"></textarea>
            </div> -->
            <span class="leftBtn">&lt;</span>
            <span class="rightBtn">&gt;</span>
        </div>
         <div class="paging clearfix">
            <div>
                <a href="javascript:;">1</a>
                <a href="javascript:;">2</a>
                <a href="javascript:;">下一页</a>
            </div>
        </div>
    </div>
</body>
</html>