<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zyl.bicycle.dao.*" %>
<%@ page import="com.zyl.bicycle.domain.*" %>
<%@ page import="com.zyl.bicycle.bean.*"%>
<%
   String chaoji=(String)session.getAttribute("chaoji");
   String putongF=(String)session.getAttribute("putong");
   if(chaoji==null&&putongF==null)
   {
     request.setAttribute("msg", "对不起，只有管理员才可以\\n使用此项功能，请登陆！");
%>
     <jsp:forward page="adminlogin.jsp"/>
<%   }
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
    <script language="javascript" src="script/trim.js"></script>
    <link href="script/style.css" rel="stylesheet" type="text/css"/>
    <script language="javascript">
	function checkdd(tmf,taction){
  		var serviceraddress = document.getElementById('serviceraddress').value;
   		var servicertel = document.getElementById('servicertel').value;
   		if(serviceraddress=="")
   		{
   			alert("服务站地址不能为空！！");
   			return;
   		}
   		if(servicertel=="")
   		{
   			alert("服务站电话不能为空！");
   			return;
   		}
   		taction.value="updateservicer";	   		
      	tmf.submit();
    } 
     function delSubmit(tmf,taction)
    {
	   		taction.value="deleteservicer";
      		tmf.submit();    	
    }
    </script>
  </head>
  
  <body style="background:url('img/top/bg.gif')">
     <table width="830" cellpadding="2" bgcolor="black" cellspacing="1">
        <tr>
                  	<th bgcolor="#d2e9ff" width="60">服务站ID</th>
      			    <th bgcolor="#d2e9ff" width="150">服务站名称</th>
				    <th bgcolor="#d2e9ff" width="250">服务站地址</th>
				    <th bgcolor="#d2e9ff" width="150">服务站电话</th>
				    <th bgcolor="#d2e9ff" width="70">修改</th>
				    <th bgcolor="#d2e9ff" width="70">删除</th>          
         </tr>
         <% int i=0;
            for(Servicer s : list){ 
            i++;%>
            <form action="ServicerServlet" method="post" name="mf" id="mf<%=i %>">
               <input id="action<%=i%>" type="hidden" name="action" value="updateservicer"/>
                   <input type="hidden" name="servicerid" value="<%=s.getId() %>"/>
                   <input type="hidden" name="servicername" value="<%=s.getName()%>"/>
                   <tr align='center' bgcolor="<%=(i%2==0)?"#e4f0ff":"#d2e9ff"%>">
                   <td width="60"><%=s.getId() %></td>
                   <td width="150"><%=s.getName() %></td>
                   <td><input type="text" name="serviceraddress" id="serviceraddress" value="<%=s.getAddress() %>"/></td>
                   <td><input type="text" name="servicertel" id="servicertel" value="<%=s.getTel() %>"/></td>
                   <td align="center" height="20">
                      <input type="button" value="修改" onclick="checkdd(document.getElementById('mf<%=i%>'),
                                                                 document.getElementById('action<%=i%>'));"/>
                   </td>
                   <td>
                      <input type="button" value="删除" onclick="delSubmit(document.getElementById('mf<%=i%>'),
                                                                  document.getElementById('action<%=i%>'));"/>
                   </td>
                   </tr>
            </form>
         <%} %>
     </table>
     <br><br>
     	  <table><tr>
<td><br>
 <% if(pb.getCurrentpage()>1) {%>
<form action="SearchPageServlet" name="form" method="post">
 
    <img src="img/top/prev.gif" style="cursor:hand" onclick="JavaScript:document.form.submit()">
    <input name="action" type="hidden" value="before1"/>
</form><% } %></td>
<td>
  <form action="SearchPageServlet" method="post">
      <td align="center" width="200">
      <%if(list!=null&&list.size()!=0)
      {
      %>
      	  <select onchange="this.form.submit()" name="changepage1" >
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
       <input name="action" type="hidden" value="pageChange1"/>
      	</td>
      	</form>
</td>
      	<td>
      	  <% if(pb.getCurrentpage()<totalpage){ %><br>
<form action="SearchPageServlet" name="form1" method="post">
    <img src="img/top/next.gif" style="cursor:hand" onclick="JavaScript:document.form1.submit()">
    <input name="action" type="hidden" value="next1"/>
</form><% } %>
      	</td>
     </tr></table>  
  </body>
</html>
<%
   String updatemsg=(String)request.getAttribute("updatemsg");
   String deletemsg=(String)request.getAttribute("deletemsg");
   if(updatemsg!=null)
   {
%>
   <script>
     alert('<%=updatemsg%>');
   </script>
<%
   request.setAttribute("updatemsg",null);
   }
   if(deletemsg!=null){
%>
 	<script>
     alert('<%=deletemsg%>');
   </script>
  <%
  	request.setAttribute("deletemsg",null);
   }
    %>
