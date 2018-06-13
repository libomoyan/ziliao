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
  </head>
<body style="background:url('img/top/bg.gif')">
<center>i&nbsp; 
 
<br><br>
<table border="0" width="500" style="border:1px solid #6daafc" align="center">
  <tr>
    <td>
		<table border="0" align="center" width="100%" bgcolor="black" cellspacing="1">
		<caption style="font-size:1.5em;font-weight:bold">管理员管理</caption>
          <tr>
		    <th bgcolor="#d2e9ff">管理员名</th>
		    <th bgcolor="#d2e9ff">管理员密码</th>
		    <th bgcolor="#d2e9ff">管理员权限</th>
		    <th bgcolor="#d2e9ff">删除</th>           
          </tr> 
      	<%
		AdminDao ad=new AdminDao();
		String hql="from Admin a";
		List<Admin> list=ad.list(hql);
		for(Admin a : list)
		{
			
       %>
  	  
	       <tr align='center'>
	       		<td bgcolor="#d2e9ff"><%= a.getName() %></td>
	       		<td bgcolor="#d2e9ff"><%= a.getPassword() %></td>
	       		<td bgcolor="#d2e9ff"><%= a.getLevel() %></td>
	       		<form action="AdminLogoutServlet">
	       		  <td width="100" align="center" bgcolor="#d2e9ff">
				    <input type="submit" value="删除"/>
				    <input type="hidden" name="mname" value="<%=a.getName()%>"/>
				    <input type="hidden" name="action" value="mandelete"/>
				  </td>	
	       		</form>
	      </tr>
       <% 
       	} 
        %>
		</table>
		</td>
		</tr>
        <tr>
        	<td bgcolor="#d2e9ff" align="center">
          	<a href="Welcome.jsp">
		        	<img border="0" id="qd"
							 onmousedown="document.all.qd.src='img/dlfh/qd1.gif'" 
					    	 onmousemove="document.all.qd.src='img/dlfh/qd2.gif'" 
					    	 onmouseout="document.all.qd.src='img/dlfh/qd3.gif'"
					    	 src="img/dlfh/qd1.gif" style="cursor:hand" />
					  </a>
        	</td>
        </tr>
		</table>         
    </center>
  </body>
</html>

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
