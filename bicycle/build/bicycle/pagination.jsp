<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zyl.bicycle.dao.*" %>
<%@ page import="com.zyl.bicycle.domain.*" %>
<%@ page import="com.zyl.bicycle.bean.*"%>


<%  
    PageBean pb=new PageBean();
    Integer xypage=(Integer)request.getAttribute("page"); 
    if(xypage!=null){
    pb.setCurrentpage(xypage);
    }
    ServicerDao sd=new ServicerDao();
    List<Servicer> list=sd.listOfPage(pb.getCurrentpage());
    int totalpage=pb.servicerPage();
    %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title></title>
  </head>
  
  <body style="background:url('img/top/bg.gif')">
     <table width="1030" cellpadding="2" bgcolor="black" cellspacing="1">
         
         <% 
            for(Servicer s : list){ 
           %>
             <table width="100%">
             <tr><td>服务站ID：<%=s.getId() %></td></tr>
             <tr><td>服务站名称：<%=s.getName() %></td></tr>
             <tr><td>服务站地址：<%=s.getAddress() %></td></tr>
             <tr><td>服务站电话：<%=s.getTel()%></td></tr>   
         </table>
         <tr></tr>
         <tr></tr>
         <tr></tr>
         <hr color="black" size="1"/>
         <%} %>
     </table>
     <br><br>
     	  <table><tr>
<td><br>
 <% if(pb.getCurrentpage()>1) {%>
<form action="SearchPageServlet" name="form" method="post">
 
    <img src="img/top/prev.gif" style="cursor:hand" onclick="JavaScript:document.form.submit()">
    <input name="action" type="hidden" value="before4"/>
</form><% } %></td>
<td>
  <form action="SearchPageServlet" method="post">
      <td align="center" width="200">
      <%if(list!=null&&list.size()!=0)
      {
      %>
      	  <select onchange="this.form.submit()" name="changepage4" >
      	   <% 
      	   		for(int j=1;j<=totalpage;j++)
      	   		{
      	   		String flag1 = "";
      	   			if(j==pb.getCurrentpage())
      	   			{
      	   				flag1 = "selected";
      	   			}
      	    %>
      	    	<option value="<%=j%>" <%= flag1 %>>第<%=j%>页</option>
      	    <% 
      	    	}
      	     %>
      	  </select>        
      <%
      }
      %>
       <input name="action" type="hidden" value="pageChange4"/>
      	</td>
      	</form>
</td>
      	<td>
      	  <% if(pb.getCurrentpage()<totalpage){ %><br>
<form action="SearchPageServlet" name="form1" method="post">
    <img src="img/top/next.gif" style="cursor:hand" onclick="JavaScript:document.form1.submit()">
    <input name="action" type="hidden" value="next4"/>
</form><% } %>
      	</td>
     </tr></table>  
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
