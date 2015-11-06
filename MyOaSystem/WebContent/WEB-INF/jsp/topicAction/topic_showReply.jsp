<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>查看主题：新手发帖</title>
	<%@include file="/WEB-INF/jsp/public/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
	<script language="javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		$(function(){
			var fck = new FCKeditor("content");
			fck.Width = "90%";
			fck.ToolbarSet = "bbs";
			fck.BasePath = "${pageContext.request.contextPath}/fckeditor/";
			fck.ReplaceTextarea();
		});
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 查看主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--内容显示-->	
<div id="MainArea">
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="width: 98%">
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_list">论坛</s:a>
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_showTopic">客服常见问题</s:a>
			<font class="MenuPoint"> &gt;&gt; </font>
			帖子阅读
			<span style="margin-left:30px;"><s:a action="topic_addUI">
				<img align="absmiddle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png"/></s:a>
			</span>
		</div>
		
		<div class="ForumPageTableBorder dataContainer" datakey="replyList">
		
			<!--显示主题标题等-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
				<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>本帖主题：新手发帖</b></td>
					<td class="ForumPageTableTitle" align="right" style="padding-right:12px;">
						<a class="detail" href="${pageContext.request.contextPath}/BBS_Reply/saveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />回复</a>
						<a href="moveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />移动到其他版块</a>
						<a href="#" onClick="return confirm('要把本主题设为精华吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_hot.gif" />精华</a>
						<a href="#" onClick="return confirm('要把本主题设为置顶吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_top.gif" />置顶</a>
						<a href="#" onClick="return confirm('要把本主题设为普通吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_comm.gif" />普通</a>
					</td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="4"></td></tr>
			</table>

			<!-- ~~~~~~~~~~~~~~~ 显示主帖 ~~~~~~~~~~~~~~~ -->
			<div class="ListArea">
				<table border="0" cellpadding="0" cellspacing="1" width="100%">
					<tr>
						<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
							<!--作者头像-->
							<div class="AuthorPhoto">
								<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" 
									onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
							</div>
							<!--作者名称-->
							<div class="AuthorName">管理员</div>
						</td>
						<td align="center">
							<ul class="TopicFunc">
								<!--操作列表-->
								<li class="TopicFuncLi">
									<a class="detail" href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</a>
									<a class="detail" href="#" onClick="return confirm('确定要删除本帖吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</a>
								</li>
								<!-- 文章表情与标题 -->
								<li class="TopicSubject">
									<img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face1.gif"/>
									新手发帖
								</li>
							</ul>
						</td>
					</tr>
					<tr><!-- 文章内容 -->
						<td valign="top" align="center">
							<div class="Content">好好学习，天天向上！</div>
						</td>
					</tr>
					<tr><!--显示楼层等信息-->
						<td class="Footer" height="28" align="center" valign="bottom">
							<ul style="margin: 0px; width: 98%;">
								<li style="float: left; line-height:18px;"><font color=#C30000>[楼主]</font>
									2007-08-17 15:18
								</li>
								<li style="float: right;"><a href="javascript:scroll(0,0)">
									<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
								</li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


			<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
			<div class="ListArea template">
				<table border="0" cellpadding="0" cellspacing="1" width="100%">
					<tr>
						<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
							<!--作者头像-->
							<div class="AuthorPhoto">
								<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" 
									onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
							</div>
							<!--作者名称-->
							<div class="AuthorName">管理员</div>
						</td>
						<td align="center">
							<ul class="TopicFunc">
								<!--操作列表-->
								<li class="TopicFuncLi">
									<a class="detail" href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</a>
									<a class="detail" href="#" onClick="return confirm('确定要删除本帖吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</a>
								</li>
								<!-- 文章表情与标题 -->
								<li class="TopicSubject">
									<img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/${reply.faceIcon}"/>
									${reply.title}
								</li>
							</ul>
						</td>
					</tr>
					<tr><!-- 文章内容 -->
						<td valign="top" align="center">
							<div class="Content">${reply.content}</div>
						</td>
					</tr>
					<tr><!--显示楼层等信息-->
						<td class="Footer" height="28" align="center" valign="bottom">
							<ul style="margin: 0px; width: 98%;">
								<li style="float: left; line-height:18px;"><font color=#C30000>[${reply.floor}楼]</font>
									2007-08-17 15:18
								</li>
								<li style="float: right;"><a href="javascript:scroll(0,0)">
									<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
								</li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
		</div>

		<!--分页信息-->
		<div id=PageSelectorBar>
			<div id=PageSelectorMemo>
				页次：7/13页 &nbsp;
				每页显示：30条 &nbsp;
				总记录数：385条
			</div>
			<div id=PageSelectorSelectorArea>
				<!--
				<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage2.png"/>
				-->
				<a href="javascript:void(0)" title="首页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/></a>
				
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">3</span>
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">4</span>
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">5</span>
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">6</span>
				<span class="PageSelectorNum PageSelectorSelected">7</span>
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">8</span>
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">9</span>
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">10</span>
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">11</span>
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(2);">12</span>
				
				<!--
				<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage2.png"/>
				-->
				<a href="#" title="尾页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/></a>
				
				转到：
				<input onFocus="this.select();" maxlength="2" class="inputStyle" type="text" value="1" name="currPage" tabindex="0"/>
				<input type="submit" name="goBtn" value="Go" class="MiddleButtonStyle" />
			</div>
		</div>

		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>快速回复</b></td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="3"></td>
				</tr>
			</table>
		</div>
	</center>
			
	<!--快速回复-->
	<div class="QuictReply">
	<form action="">
		<div style="padding-left: 3px;">
			<table border="0" cellspacing="1" width="98%" cellpadding="5" class="TableStyle">
				<tr height="30" class="Tint">
					<td width="50px" class="Deep"><b>标题</b></td>
					<td class="no_color_bg">
						<input type="text" name="title" class="InputStyle" value="回复：昨天发现在表单里删除的图片" style="width:90%"/>
					</td>
				</tr>
				<tr height="30" class="Tint">
					<td width="50px" class="Deep"><b>表情</b></td>
					<td class="no_color_bg"><div class="InputContent">
						<!-- 显示表情符号 -->
						<!--现在在设计单选框(radio)和复选框(checkbox)时都会给选择文字加上label增大选择范围，以提高用户体验。
							但在给单独的图片加label时无法成功。
							解决方法是：给图片img标签加上disabled即可。-->
						<table cellpadding="0" border="0" cellspacing="0">
							<tr>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="1" id="r_1"/>
									<label for="r_1"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face1.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="2" id="r_2"/>
									<label for="r_2"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face2.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="3" id="r_3"/>
									<label for="r_3"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face3.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="4" id="r_4"/>
									<label for="r_4"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face4.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="5" id="r_5"/>
									<label for="r_5"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face5.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="6" id="r_6"/>
									<label for="r_6"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face6.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="7" id="r_7"/>
									<label for="r_7"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face7.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="8" id="r_8"/>
									<label for="r_8"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face8.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="9" id="r_9"/>
									<label for="r_9"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face9.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="10" id="r_10"/>
									<label for="r_10"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face10.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="11" id="r_11"/>
									<label for="r_11"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face11.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="12" id="r_12"/>
									<label for="r_12"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face12.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="13" id="r_13"/>
									<label for="r_13"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face13.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="faceIcon" value="14" id="r_14"/>
									<label for="r_14"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face14.gif" align="absmiddle" disabled="true"/></label>
								</td>
							</tr>
						</table></div>
					</td>
				</tr>
				<tr class="Tint" height="200">
					<td valign="top" rowspan="2" class="Deep"><b>内容</b></td>
					<td valign="top" class="no_color_bg">
						<textarea name="content" style="width: 95%; height: 300px"></textarea>
					</td>
				</tr>
				<tr height="30" class="Tint">
					<td class="no_color_bg" colspan="2" align="center">
						<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
	</div>
</div>

<div class="Description">
	说明：<br />
	1，主帖只在第一页显示。<br />
	2，只有是管理员才可以进行“移动”、“编辑”、“删除”、“精华”、“置顶”的操作。<br />
	3，删除主帖，就会删除所有的跟帖（回复）。<br />
</div>

</body>
</html>
