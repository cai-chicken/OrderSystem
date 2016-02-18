<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>菜系销售情况</title>
	<!-- 引入 ECharts 文件 -->
    <script src="${pageContext.request.contextPath}/style/Js/echarts.min.js"></script>
    <style type="text/css">
		.count{
		width: 800px;
		height: 400px;
		margin: 10px auto;
		}
	</style>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div class="count" id="count"></div>
    <div class="count" id="historyCount"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var count = echarts.init(document.getElementById('count'));
		var historyCount = echarts.init(document.getElementById('historyCount'));
        
		var xAxisData = ${xAxisData};
		var seriesData = ${seriesData};
		var historyCountData = ${historyCountData};
		
        // 指定图表的配置项和数据
        var option1 = {
            title: {
                text: '今日销售前五的菜系情况'
            },
            tooltip: {},
            legend: {},
            xAxis: {
                data: xAxisData
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: seriesData
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        count.setOption(option1);
        
        var option2 = {
        	    title : {
        	        text: '各个菜系的历史销售情况'
        	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    legend: {},
        	    series : [
        	        {
        	            name: '历史销售数量',
        	            type: 'pie',
        	            radius : '55%',
        	            center: ['50%', '60%'],
        	            data:historyCountData,
        	            itemStyle: {
        	                emphasis: {
        	                    shadowBlur: 10,
        	                    shadowOffsetX: 0,
        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        	                }
        	            }
        	        }
        	    ]
        	};
        historyCount.setOption(option2);
    </script>
</body>
</html>