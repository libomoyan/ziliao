<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zyl.bicycle.bean.*"%>
<%@ page import="com.zyl.bicycle.dao.*" %>
<%@ page import="com.zyl.bicycle.domain.*" %>
<%
   String chaoji=(String)session.getAttribute("chaoji");
   String putongF=(String)session.getAttribute("putong");
   if(chaoji==null&&putongF==null)
   {
     request.setAttribute("msg", "对不起，只有管理员才可以\\n使用此项功能，请登陆！");
%>
     <jsp:forward page="adminlogin.jsp"/>
<%   }
    PageBean pb=(PageBean)session.getAttribute("page");
    List<Order> list=new ArrayList<Order>();
    OrderDao od=new OrderDao();
    if(pb.getSort()==1){
      list=od.listOfPage(pb.getCurrentpage());
    }else if(pb.getSort()==2){
      list=od.listOfPageAndOrdercheck(pb.getCurrentpage(),"已接收");
    }else if(pb.getSort()==3){
      list=od.listOfPageAndOrdercheck(pb.getCurrentpage(),"未接收");
    }
    %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title></title>
  </head>
  
  <body style="background:url('img/top/bg.gif')">
     <table width="1030" cellpadding="2" bgcolor="black" cellspacing="1">
        <tr>
                  	<th bgcolor="#d2e9ff" width="60">定单ID</th>
      			    <th bgcolor="#d2e9ff" width="60">客户ID</th>
				    <th bgcolor="#d2e9ff" width="60">客户电话</th>
				    <th bgcolor="#d2e9ff" width="150">预定拿车时间</th>
				    <th bgcolor="#d2e9ff" width="50">租期</th>
				    <th bgcolor="#d2e9ff" width="80">总租金</th>
				    <th bgcolor="#d2e9ff" width="70">总押金</th>
				    <th bgcolor="#d2e9ff" width="70">查看</th>
				    <th bgcolor="#d2e9ff" width="70">操作</th>        
         </tr>
         <% int i=0;
            for(Order o : list){ 
            i++;
            double price=0;
            double yajin=0;
            for(Item item : o.getItems()){
               price=price+item.getBike().getPrice()*item.getCount();
               yajin=yajin+item.getBike().getYajin()*item.getCount();
            }%>
               <tr align='center' bgcolor="<%=(i%2==0)?"#e4f0ff":"#d2e9ff"%>">
                   <td width="60"><%=o.getId() %></td>
                   <td width="60"><%=o.getClient_name() %></td>
                   <td width="60"><%=o.getKehutel()%></td>
                   <td width="150"><%=o.getCreate_date()%></td>
                   <td width="60"><%=o.getTime() %></td>
                   <td width="60">￥<%=price%>元</td>
                   <td width="60">￥<%=yajin%>元</td>
            <form action="item.jsp">
		      <td align="center" height="20">			      
			      <input type="hidden" name="oid" value="<%= o.getId()%>"/>
			      <input type="hidden" name="action" value="modify1"/>
			      <input type="submit" value="查看"/>
		      </td>
	        </form>
                   <%if("未接收".equals(o.getOrdercheck())){ %>
                     <td width="60"><input type="button" onclick="location='AdminLogoutServlet?action=jieshou&oid=<%=o.getId() %>'" value="接收"/></td>
                     <%}else if("已接收".equals(o.getOrdercheck())){%>
                     <td width="60"><input type="button" onclick="location='AdminLogoutServlet?action=querengh&oid=<%=o.getId() %>'" value="确认归还"/></td>
                      <%} %>
                   </tr>
         <%} %>
     </table>
     <br><br>
     	  <table><tr>
<td><br>
 <% if(pb.getCurrentpage()>1) {%>
<form action="AdminServlet" name="form" method="post">
 
    <img src="img/top/prev.gif" style="cursor:hand" onclick="JavaScript:document.form.submit()">
    <input name="ddaction" type="hidden" value="before1"/>
</form><% } %></td>
<td>
  <form action="AdminServlet" method="post">
      <td align="center" width="200">
      <%if(list!=null&&list.size()!=0)
      {
      %>
      	  <select onchange="this.form.submit()" name="changepage1" >
      	   <% 
      	   		for(int j=1;j<=pb.getTotalpage();j++)
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
       <input name="ddaction" type="hidden" value="pageChange1"/>
      	</td>
      	</form>
</td>
      	<td>
      	  <% if(pb.getCurrentpage()<pb.getTotalpage()){ %><br>
<form action="AdminServlet" name="form1" method="post">
    <img src="img/top/next.gif" style="cursor:hand" onclick="JavaScript:document.form1.submit()">
    <input name="ddaction" type="hidden" value="next1"/>
</form><% } %>
      	</td>
     </tr></table>  
  </body>
</html>

