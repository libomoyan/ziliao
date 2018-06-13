<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zyl.bicycle.dao.*" %>
<%@ page import="com.zyl.bicycle.domain.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 	<% 
	   String username=(String)session.getAttribute("user");
	   if(username==null)
	   {
	%>
	     <jsp:forward page="customerzc.jsp"/>
	<%
	   }
	%>
	<title>定单详情</title>
	<script language="javascript" src="script/trim.js"></script>
	<link href="script/style.css" rel="stylesheet" type="text/css"/>
	<script language="javascript">
		function qingkong()
	    {  
	        document.all.kehutel.value="";
	        document.all.time.value="";
	    }
	    function checkAddGoods()
	    {
	   		var kehutel = document.all.kehutel.value;
	   		var time= document.all.time.value;
	   		if(kehutel=="")
	   		{
	   			alert("客户电话不能为空！！");
	   			return;
	   		}
	   		if(time=="")
	   		{
	   			alert("租期不能为空！！");
	   			return;
	   		}
	   		document.form.submit();
	    }
	</script>
</head>

<body style="background:url('img/top/bg.gif')">
<form action="YuDingServlet" name="form" method="post">
<table border="0" align="center" style="border:2px solid #6daafc" bgcolor="#d2e9ff">
<tr><td>
<table border="0">
<tr>
  <td align="center" colspan="2" style="font-size:1.2em"> 定单详细信息  
  </td>
</tr>
<tr>
<td colspan="2">
	<table cellpadding="2" bgcolor="#d2e9ff" cellspacing="1" border="0">
	<tr>
	<td bgcolor="#d2e9ff">客户电话</td>
	<td bgcolor="#d2e9ff"><input name="kehutel" id="kehutel" size="20" type="text"></td>
	</tr>
	<tr>
	  <td bgcolor="#d2e9ff">预定日期：</td>
	</tr>
	<tr>
	  <td>年：</td><td><select name="year">
	                    <%for(int i=2050;i>0;i--){ %>
	                     <option value="<%=i %>"><%=i %></option>
	                     <%} %>
	                   </select></td>
	  <td>月:</td><td><select name="month">
	                    <%for(int i=12;i>0;i--){ %>
	                     <option value="<%=i %>"><%=i %></option>
	                     <%} %>
	                   </select></td>
	 <td>日：</td><td><select name="day">
	                    <%for(int i=30;i>0;i--){ %>
	                     <option value="<%=i %>"><%=i %></option>
	                     <%} %>
	                   </select></td>                
	</tr>
	<tr>
	<td bgcolor="#d2e9ff">租期（天）</td>
	<td bgcolor="#d2e9ff"><input name="time" id="time" size="20" type="text"></td>
	</tr>
	<tr>
	 <td bgcolor="#d2e9ff">选择服务站归还</td>
	 <td bgcolor="#d2e9ff"><select name="servicer">
	                        <%ServicerDao sd=new ServicerDao();
	                          List<Servicer> list=sd.list("from Servicer s");
	                          for(Servicer s : list){ %>
                           <option value="<%=s.getId() %>"><%=s.getName() %></option>
                           <%} %>
                           </select></td>
	</tr>
	</table>
</td>
</tr>
<tr>
		<td align="right"><img border="0" src="img/other/tj1.png" style="cursor:hand" onclick="checkAddGoods()"/>
			<input name="action" type="hidden" value="save"/>
		</td>
		<td><a href="#" onclick="qingkong()"><img border="0" src="img/other/qk.gif" style="cursor:hand" /></a></td>
</tr>
</table>
</form>
</td></tr></table>
</body>
<% 
   String msg=(String)request.getAttribute("msg");
   if(msg!=null)
   {
%>
	<script>
	alert('<%=msg%>');
	</script>   
<%
   }
%>
</html>
