<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入jquery验证框架 -->
<%-- <script type="text/javascript" src="/WEB-INF/script/jquery.js"></script>
<script type="text/javascript" src="/WEB-INF/script/jquery_validate/jquery.validate.js"></script>
<script type="text/javascript" src="/WEB-INF/script/jquery_validate/jquery.metadata.js"></script>
<script type="text/javascript">
	$(function(){
		$("#myForm").validate();
	})
</script> --%>
<title>图书操作</title>
</head>
<body>
	<s:form action="book_%{id == null?'add':'edit'}" onsubmit="return validate_form(this)">
		<s:hidden name="id" />
		<table border="1px" style="margin: 0 auto; width: 450px">
			<tr>
				<th colspan="2" style="background-color: plum"><s:if
						test="id == null">
						增加图书
					</s:if> <s:else>编辑图书</s:else></th>
			</tr>
			<tr>
				<td>书名<span style="color: red">(*)</span></td>
				<td><s:textfield name="name"></s:textfield></td>
			</tr>
			<tr>
				<td>作者<span style="color: red">(*)</span></td>
				<td><s:textfield name="author"></s:textfield></td>
			</tr>
			<tr>
				<td>出版社<span style="color: red">(*)</span></td>
				<td><s:textfield name="pubHome"></s:textfield></td>
			</tr>
			<tr>
				<td>出版日期<span style="color: red">(*)</span></td>
				<td><s:textfield name="pubDate" id="publisDatetime" onblur="check()"></s:textfield>(yyyy-MM-dd)</td>
			</tr>
			<tr>
				<td>页数</td>
				<td><s:textfield name="count"></s:textfield></td>
			</tr>
			<tr>
				<td>价格</td>
				<td><s:textfield name="price"></s:textfield></td>
			</tr>
			<tr>
				<td>内容摘要</td>
				<td><s:textarea name="content"></s:textarea></td>
			</tr>
			<tr>
				<th colspan="2" style="margin: 0 auto"><input type="submit"
					value="提交" /> <input type="button" value="返回"
					onclick="javascript:history.go(-1);"></th>
			</tr>
		</table>
	</s:form>
</body>
<script type="text/javascript">
	/* 非空字段验证 */
	function validate_required(field, alerttxt) {
		with (field) {
			if (value == null || value == "") {
				alert(alerttxt);
				return false
			} else {
				return true
			}
		}
	}
	//判断日期类型是否为YYYY-MM-DD格式的类型  
	/* function check(){
		var a = /^(\d{4})-(\d{2})-(\d{2})$/
		if (!a.test(document.getElementById("publisDatetime").value)) { 
			alert("日期格式不正确!") 
			return false 
		} 
		else { 
			return true
		}
	} */
	function check(){   
        var date = document.getElementById("publisDatetime").value;
        var result = date.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
        if(result==null)
        {
            alert("请输入正确的日期格式");
            return false
        } else {
        	return true
        }
	}
	
	function validate_form(thisform) {
		with (thisform) {
			if (validate_required(name, "书名不能为空") == false) {
				name.focus();
				return false
			} else if (validate_required(author, "作者不能为空") == false) {
				author.focus();
				return false
			} else if (validate_required(pubHome, "出版社不能为空") == false) {
				pubHome.focus();
				return false
			} else if (validate_required(pubDate, "日期格式错误") == false) {
				pubDate.focus();
				return false
			} 
		}
	}
</script>
</html>