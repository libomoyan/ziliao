<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="script/div.css" type="text/css" rel=stylesheet>
</head>
<body style="background:url('img/top/bg.gif')">
<center>
<table>
<br><br><br><br><br><br><br><br><br>
<tr CLASS="welcome">
<td><img  src="img/png-1610.png" border="0"></td>
<td><font color="red" size="33"><i><b>欢迎进入自由鸟网上自行车租赁后台管理系统</b></i></font></td>
</tr>
</table>
</center>
</body>
<% 
   String msg=(String)request.getAttribute("msg");
   if(msg!=null)
   {
%>
        <script>
      		alert("<%=msg%>");
      	</script>    
<%
   }
%>
</html>