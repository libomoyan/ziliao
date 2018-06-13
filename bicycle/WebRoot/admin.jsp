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
    <title>后台管理</title>
  </head>
  
  <frameset rows="20%,*"> 
	<frame name="topFrame" scrolling="NO" noresize width="20%" src="admintop.jsp" >
	<frameset cols="18%,*"> 
		<frame name="leftFrame" scrolling="no" noresize src="adminleft.jsp">
		<frame name="mainFrame" scrolling="YES" src="Welcome.jsp">
	</frameset>
</frameset> 
  <body>
    This is my JSP page. <br>
  </body>
</html>
