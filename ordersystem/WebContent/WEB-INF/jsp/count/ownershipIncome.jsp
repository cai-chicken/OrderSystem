<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/public/headFile.jspf" %>
	<!-- 引入 ECharts 文件 -->
    <script src="${pageContext.request.contextPath}/style/Js/echarts.min.js"></script>
	<title>财产收入情况</title>
	<style type="text/css">
		.count{
		width: 800px;
		height: 400px;
		margin: 10px auto;
		}
	</style>
</head>
<body>
	<form class="form-inline definewidth m20" action="count_ownershipIncome.action" method="post">
		年份：
		<select name="currentYear">
			<option value="0">请选择年份</option>
			<c:forEach items="${yearList }" var="y">
				<option value="${y}">${y}</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		月份：
		<select name="currentMonth">
			<option value="0">请选择月份</option>
			<option value="1">1月份</option>
			<option value="2">2月份</option>
			<option value="3">3月份</option>
			<option value="4">4月份</option>
			<option value="5">5月份</option>
			<option value="6">6月份</option>
			<option value="7">7月份</option>
			<option value="8">8月份</option>
			<option value="9">9月份</option>
			<option value="10">10月份</option>
			<option value="11">11月份</option>
			<option value="12">12月份</option>
		</select>&nbsp;&nbsp;

		<button type="submit" class="btn btn-primary">查询</button>
	</form>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div class="count" id="today"></div>
    <div class="count" id="week" style="background-color: yellow"></div>
    <div class="count" id="month"></div>
    <div class="count" id="year"></div>
    
</body>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
	var today = echarts.init(document.getElementById('today'));
	var todayData = ${today};
	
	// 指定图表的配置项和数据
	var option = {
	    title: {
	        text: '今日收入数据'
	    },
	    tooltip: {
	    	trigger: 'axis'
	    },
	    legend: {
	        /* data:['收入'] */
	    },
	    xAxis: {
	        data: ["6:00","9:00","12:00","15:00","18:00","21:00","24:00"]
	    },
	    yAxis: {},
	    series: [{
	        name: '收入',
	        type: 'line',
	        data: todayData,
	        markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
	    }]
	};
	// 使用刚指定的配置项和数据显示图表。
	today.setOption(option);
	
	var month = echarts.init(document.getElementById('month'));
	var monthData = ${month};
	var optionMonth = {
	    title: {
	        text: '该月收入数据'
	    },
	    tooltip: {
	    	trigger: 'axis'
	    },
	    legend: {
	        /* data:['收入'] */
	    },
	    xAxis: {
	        data: ["1号","5号","10号","15号","20号","25号","30号"]
	    },
	    yAxis: {},
	    series: [{
	        name: '收入',
	        type: 'line',
	        data: monthData,
	        markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
	    }]
	};
	month.setOption(optionMonth);
	
	var year = echarts.init(document.getElementById('year'));
	var yearData = ${year};
	var optionYear = {
	    title: {
	        text: '该年收入数据'
	    },
	    tooltip: {
	    	trigger: 'axis'
	    },
	    legend: {
	        /* data:['收入'] */
	    },
	    xAxis: {
	        data: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]
	    },
	    yAxis: {},
	    series: [{
	        name: '收入',
	        type: 'line',
	        data: yearData,
	        markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
	    }]
	};
	year.setOption(optionYear);
</script>
</html>