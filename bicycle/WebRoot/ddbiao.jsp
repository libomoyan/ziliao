<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zyl.bicycle.domain.*" %>
<%@ page import="com.zyl.bicycle.dao.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<% 
	String user=(String)session.getAttribute("user");
	if(user==null)
	{
		request.getRequestDispatcher("/customermanage.jsp").forward(request,response);
	}
	else
	{ OrderDao od=new OrderDao();
	  List<Order> list=od.listOfClient(user);
     %>
<html>
  <head>
    <title></title>
    <script language="javascript" src="script/trim.js"></script>
    <link href="script/style.css" rel="stylesheet" type="text/css"/>
    <script language="javascript">
       function check(temp,mmf){
      	if(temp=="mod"){
      	
      		mmf.action.value="ordercx";
      	}
      	mmf.submit();
      }
   
    </script>
  </head>
 <body  style="background:url('img/top/bg.gif')">
 <%if(list==null||list.size()==0){ %> 
     <table style="background:url('img/top/bg.gif')" align="center">
	<tr>
		<td>
	    <img src="img/png-0405.png"/>
	  </td>
	  <td> 	
	    <b style="font-size:1.5em">没有定单！！！</b>
	  </td>
	</tr>
</table> 
<%}else{ %>
<table border="0" align="center" width="900">
   <tr>
      <td>
      	<table width="100%" cellpadding="1" cellspacing="1" bgcolor="black" border="0" align="center">
      	<caption style="font-size:1.6em;font-weight:bold">订单信息</caption>
      	<tr bgcolor="#d2e9ff">
      	  <th width="55" align="center" height="30">订单号</th>
      	  <th width="70" align="center" height="30">状态</th>
      	  <th width="100" align="center" height="30">客户ID</th>
      	  <th width="100" align="center" height="30">收货人电话</b></font></th>
      	  <th width="100" align="center" height="30">总租金</th>
      	  <th width="100" align="center" height="30">总押金</th>
      	  <th align="center" height="30">预订拿车时间</th>
      	   <th align="center" height="30">租期</th>
      	  <th width="70" align="center" height="30">查看明细</th>
        </tr>
      	<%	
      	int i=0;
      	double price=0;
      	double yajin=0;
	     for(Order o : list)
		{
		i++;
		for(Item item : o.getItems()){
		  price=price+item.getBike().getPrice()*item.getCount();
		  yajin=yajin+item.getBike().getYajin()*item.getCount();
		}		   
		 %>	     

      <tr bgcolor="<%=(i%2==0)?"#d2e9ff":"#e4f0ff"%>">	  
      	  <td align="center" height="20"><%=o.getId()%></td>
      	  <td align="center" height="20"><%=o.getOrdercheck() %></td>
	      <td height="20"><%= o.getClient_name() %></td>
	      <td height="20"><%= o.getKehutel() %></td>
	      <td align="center" height="20">￥<%= price%>元</td>
	      <td align="center" height="20">￥<%= yajin%>元</td>
	      <td align="center" height="20"><%=o.getCreate_date()%></td>
	      <td align="center" height="20"><%=o.getTime()%></td>
	      <form action="item.jsp">
		      <td align="center" height="20">			      
			      <input type="hidden" name="oid" value="<%= o.getId()%>"/>
			      <input type="hidden" name="action" value="modify1"/>
			      <input type="submit" value="查看"/>
		      </td>
	      </form>
      </tr> 
	    <% 
	   	}
	   	}
	   	}
	    %>
  </table>
</td>
</tr>
</table>
<hr></hr>
</body>
</html>
