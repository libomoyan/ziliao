<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zyl.bicycle.dao.*"  %>
<%@ page import="com.zyl.bicycle.domain.*"  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%String username=(String)session.getAttribute("user");
  String chaoji=(String)session.getAttribute("chaoji");
  String putong=(String)session.getAttribute("putong");
  if(username==null&&chaoji==null&&putong==null){
    request.getRequestDispatcher("/customermanage.jsp").forward(request,response);
  }else{
    String str=request.getParameter("oid");
    Long oid=Long.parseLong(str);
    OrderDao od=new OrderDao();
    Order order=od.findOfId(oid);
    ItemDao itd=new ItemDao();
    List<Item> list=itd.listOfOrder(oid);
    double price=0;
    double yajin=0;
    for(Item item : list){
     price=price+item.getBike().getPrice()*item.getCount();
     yajin=yajin+item.getBike().getYajin()*item.getCount();
    }
    %>
<html>
  <head>
    <link href="script/style.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="script/trim.js"></script>    
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
    
	      <hr></hr>
	   
<table style="border:1px solid #6daafc" cellspacing="5" bgcolor="#d2e9ff">
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
  					<td colspan="3" align="center" height="40" border="1">
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						<b style="font-size:1.5em"><%= order.getId()%>号订单</b>&nbsp;
  						<b style="font-size:1.2em">(<%= order.getOrdercheck() %>)</b>
  					</td>
				</tr>
				<tr>
				  <td width="350" align="left" height="20" border="0"><b>预定拿车时间:</b><%=order.getCreate_date()  %></td>
				  <td width="350" align="left" height="20" border="0"><b>租期:</b><%=order.getTime()  %></td>
				  <td width="350" align="left" height="20" border="0"><b>客户电话:</b><%=order.getKehutel()  %></td>
				  <td width="350" align="left" height="20" border="0"><b>退还服务站:</b><%= order.getServicer_name() %></td>
				  <td>&nbsp;</td>
				</tr>
				<tr>
			        <td width="350" align="left" height="20" border="0"><b>总租金:</b>
				  ￥<%= price%>元</td>
				  <td width="350" align="left" height="20" border="0"><b>总押金:</b>
				  ￥<%= yajin%>元</td>
				  <td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" cellpadding="1" cellspacing="1" bgcolor="black">
	      		<form action="CustomerServlet" method="post">
				    <input id="aa" type="hidden" name="action" value="orderxiugai"/>
				    <input name="orderidname" type="hidden" value="<%= order.getId()%>">
		      <tr>
		        <th bgcolor="#d2e9ff" width="200" align="center" height="30">自行车ID</th>
			    <th bgcolor="#d2e9ff" width="200" align="center" height="30">自行车名称</th>
			    <th bgcolor="#d2e9ff" width="200" align="center" height="30">所属服务站</th>
			    <th bgcolor="#d2e9ff" width="200" align="center" height="30">租金</th>
			    <th bgcolor="#d2e9ff" width="200" align="center" height="30">押金</th> 
			    <th bgcolor="#d2e9ff" width="200" align="center" height="30">预定数量</th> 
		      </tr>
			<% 
			for(Item item : list)
			{
				
			%>	<tr align="center">
	       		<td bgcolor="#d2e9ff" bgcolor="white" width="200" align="center" height="20"><%=item.getBike().getId() %></td>
	       		<td bgcolor="#d2e9ff" width="200" align="center" height="20">
	       		<%= item.getBike().getName() %> </td>
	       		<td bgcolor="#d2e9ff" bgcolor="white" width="200" align="center" height="20"><%=item.getBike().getServicer().getName() %></td>
	       		<td bgcolor="#d2e9ff" bgcolor="white" width="200" align="center" height="20">￥<%=item.getBike().getPrice()%>元</td>
	       		<td bgcolor="#d2e9ff" bgcolor="white" width="200" align="center" height="20">￥<%=item.getBike().getYajin()%>元</td>
	       		<td bgcolor="#d2e9ff" bgcolor="white" width="200" align="center" height="20"><%=item.getCount() %></td>
	      </tr>
          <% 
        	} 
       	} %>
       	</form>
       	</table>
       	<br/>
</td>
</tr>
</table>
  <table align="center">
  <tr>

  </table>
	        <hr></hr>


  </body>
</html>
