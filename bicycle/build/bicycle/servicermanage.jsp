<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 	<% 
	   String chaoji=(String)session.getAttribute("chaoji");
	   String putongF=(String)session.getAttribute("putong");
	   if(chaoji==null&&putongF==null)
	   {
	     request.setAttribute("msg", "对不起，只有管理员才可以\\n使用此项功能，请登陆！");
	%>
	     <jsp:forward page="adminlogin.jsp"/>
	<%
	   }
	%>
	<title>自行车管理</title>
	<script language="javascript" src="script/trim.js"></script>
	<link href="script/style.css" rel="stylesheet" type="text/css"/>
	<script language="javascript">
		function qingkong()
	    {  
	        document.all.servicername.value="";
	        document.all.serviceraddress.value="";
	        document.all.servicertel.value="";
	    }
	    function checkAddGoods()
	    {
	   		var servicername = document.all.servicername.value;
	   		var address = document.all.serviceraddress.value;
	   		var tel = document.all.servicertel.value;
	   		
	   		if(servicername=="")
	   		{
	   			alert("服务站名称不能为空！！");
	   			return;
	   		}
	   		if(address=="")
	   		{
	   			alert("服务站地址不能为空！！");
	   			return;
	   		}
	   		if(tel=="")
	   		{
	   			alert("服务站电话不能为空！！");
	   			return;
	   		}
	   		document.form.submit();
	    }
	</script>
</head>

<body style="background:url('img/top/bg.gif')">
<form action="ServicerServlet" name="form" method="post">
<table border="0" align="center" style="border:2px solid #6daafc" bgcolor="#d2e9ff">
<tr><td>
<table border="0">
<tr>
  <td align="center" colspan="2" style="font-size:1.2em">
    服务站详细信息 
  </td>
</tr>
<tr>
<td colspan="2">
	<table cellpadding="2" bgcolor="#d2e9ff" cellspacing="1" border="0">
	<tr>
	<td bgcolor="#d2e9ff">服务站名称</td>
	<td bgcolor="#d2e9ff"><input name="servicername" id="servicername" size="60" type="text"></td>
	</tr>
	<tr>
	<td bgcolor="#d2e9ff">服务站地址</td>
	<td bgcolor="#d2e9ff"><input name="serviceraddress" id="serviceraddress" size="60" type="text"></td>
	</tr>
	<td bgcolor="#d2e9ff">服务站电话</td>
	<td bgcolor="#d2e9ff"><input name="servicertel" id="servicertel" size="60" type="text"></td>
	</tr>
	</table>
</td>
</tr>
<tr>
		<td align="right"><img border="0" src="img/other/tjsp.gif" style="cursor:hand" onclick="checkAddGoods()"/>
			<input name="action" type="hidden" value="add"/>
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
