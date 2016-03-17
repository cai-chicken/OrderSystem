<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/front/Css/base.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/front/Css/index.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/style/front/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/style/front/js/index.js"></script>
</head>
<body>
    <div class="wrap">
        <div class="txt_show clearfix">
            <div class="txt_l"><input type="text" class="txt_l" value="${store.name }"/></div>
            <div class="txt_r">
                <div class="txt_con">
                    <span>${store.description }</span>
                </div>
            </div>
        </div>
        <div class="row_one clearfix">
            <div class="introduct">${msg }</div>
            <div class="menu" onclick="javascript:location.href='menu.html'">已点菜单</div>
        </div>
        <div class="banner clearfix">
            <ul>
                <li><a href="#"><img src="" width="1900" height="380" /></a></li>
            </ul>
            <ol>
                <li class="current">红烧肉</li>
                <li>牛肉</li>
                <li>鸡肉</li>
                <li>鸭肉</li>
                <li>红焖肉</li>
            </ol>
            <span class="leftBtn">&lt;</span>
            <span class="rightBtn">&gt;</span>
        </div>
        <div class="nav clearfix">
            <div class="nav_l">
                <span class="all_menu">所有菜单</span>
                <ul>
                    <li>粤系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>
                    <li>浙系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>
                    <li>粤系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>
                    <li>浙系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>
                    <li>粤系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>
                    <li>浙系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>
                    <li>粤系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>
                    <li>浙系
                        <div class="hidee">
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                            <div>青菜</div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="nav_r clearfix">
                <div class="nav_rt clearfix">
                    <div class="nav_rtname">粤菜</div>
                    <select name="" id="" class="set_one">
                        <option value="">青菜</option>
                        <option value="">青菜</option>
                        <option value="">青菜</option>
                    </select>
                     <select name="" id="" class="set_one">
                        <option value="">1元</option>
                        <option value="">2元</option>
                        <option value="">3元</option>
                    </select>
                    <div class="search">
                        <span></span>
                        <input type="text" />
                    </div>
                </div>
                <div class="nav_pic clearfix">
                    <div class="nav_picl">
                        <img src="" height="130"  alt="" onclick="javascript:location.href='detail.html'"/>
                    </div>
                    <div class="nav_picr">
                        <p><span class="span1">菜名:</span><span class="span2">好吃你就多吃点</span></p>
                        <p><span class="span1">好评率：</span><span class="span2">88%</span></p>
                        <p><span class="span1">价格：</span><span class="span2">32元</span></p>
                        <p><span class="span1">下单参数：</span><span class="span2">这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜</span></p>
                    </div>
                </div>
                <div class="nav_pic">
                    <div class="nav_picl">
                        <img src="" height="130"  alt="" onclick="javascript:location.href='detail.html'"/>
                    </div>
                    <div class="nav_picr">
                        <p><span class="span1">菜名:</span><span class="span2">好吃你就多吃点</span></p>
                        <p><span class="span1">好评率：</span><span class="span2">88%</span></p>
                        <p><span class="span1">价格：</span><span class="span2">32元</span></p>
                        <p><span class="span1">菜品简介：</span><span class="span2">这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜这是好吃的菜</span></p>
                    </div>
                </div>
            </div>
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