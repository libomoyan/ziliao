<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zyl.bicycle.dao.*" %>
<%@ page import="com.zyl.bicycle.domain.*" %>

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
	        document.all.bikename.value="";
	        document.all.bikeprice.value="";
	        document.all.bikeyajin.value="";
	        document.all.bikecount.value="";
	        document.all.bikeurl.value="";
	    }
	    function checkAddGoods()
	    {
	   		var bikename = document.all.bikename.value;
	   		var bikeprice = document.all.bikeprice.value;
	   		var bikeyajin= document.all.bikeyajin.value;
	   		var bikecount=document.all.bikecount.value;
	   		var bikeurl=document.all.bikeurl.value;
	   		
	   		if(bikename=="")
	   		{
	   			alert("自行车名称不能为空！！");
	   			return;
	   		}
	   		if(bikeprice=="")
	   		{
	   			alert("租金不能为空！！");
	   			return;
	   		}
	   		if(bikeyajin=="")
	   		{
	   			alert("押金不能为空！！");
	   			return;
	   		}
	   		if(bikecount=="")
	   		{
	   			alert("数量不能为空！！");
	   			return;
	   		}
	   		if(bikeurl=="")
	   		{
	   			alert("Url链接不能为空！！");
	   			return;
	   		}
	   		document.form.submit();
	    }
	</script>
</head>

<body style="background:url('img/top/bg.gif')">
<form action="BikeServlet" name="form" method="post">
<table border="0" align="center" style="border:2px solid #6daafc" bgcolor="#d2e9ff">
<tr><td>
<table border="0">
<tr>
  <td align="center" colspan="2" style="font-size:1.2em"> 自行车详细信息  
  </td>
</tr>
<tr>
<td colspan="2">
	<table cellpadding="2" bgcolor="#d2e9ff" cellspacing="1" border="0">
	<tr>
	<td bgcolor="#d2e9ff">自行车名称</td>
	<td bgcolor="#d2e9ff"><input name="bikename" id="bikename" size="60" type="text"></td>
	</tr>
	<tr>
	<td bgcolor="#d2e9ff">租金（天）</td>
	<td bgcolor="#d2e9ff"><input name="bikeprice" id="bikeprice" size="20" type="text"></td>
	</tr>
	<tr>
	<td bgcolor="#d2e9ff">押金</td>
	<td bgcolor="#d2e9ff"><input name="bikeyajin" id="bikeyajin" size="20" type="text"></td>
	</tr>
	<tr>
	<td bgcolor="#d2e9ff">数量</td>
	<td bgcolor="#d2e9ff"><input name="bikecount" id="bikecount" size="20" type="text"></td>
	</tr>
	<tr>
    <td bgcolor="#d2e9ff">Url链接</td>
	<td bgcolor="#d2e9ff"><input name="bikeurl" id="bikeurl" size="60" type="text"></td>
	</tr>
	<tr>
	 <td bgcolor="#d2e9ff">所属服务站</td>
	 <td bgcolor="#d2e9ff"><select name="servicer">
	                        <%ServicerDao sd=new ServicerDao();
	                          List<Servicer> list=sd.list("from Servicer s");
	                          for(Servicer s : list){ %>
                           <option value="<%=s.getName() %>"><%=s.getName() %></option>
                           <%} %>
                           </select></td>
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
