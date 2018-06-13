<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zyl.bicycle.dao.*" %>
<%@ page import="com.zyl.bicycle.domain.*" %>

<html>
<head>
  
<body style="background:url('img/top/bg.gif')">
   <center>
       <table>
          <tr><td>
          	&nbsp;&nbsp;
            <a id="B" href="#" onclick="check(document.all.B,document.all.BB)"><img border="0" 
            src="img/-.png"/></a><font>选择服务站</font>
          </td></tr>
          <%ServicerDao sd=new ServicerDao(); 
            List<Servicer> list=sd.list("from Servicer s");
            if(list!=null){
             for(Servicer s : list){%>
          	      <tr><td>
	          	  &nbsp;&nbsp;&nbsp;&nbsp;
	          	  <img border="0" src="img/fav.gif"/><a href="BikeSortPageServlet?servicer=<%=s.getId()%>" 
	          	  target="mainFrame"><font><%=s.getName() %></font></a>
	          	  </td></tr>
	          <%}
	          } %>
       </table>
   </center>
</body>
</html>