package com.zyl.bicycle.servlet;

import java.io.IOException;


import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyl.bicycle.dao.BikeDao;
import com.zyl.bicycle.dao.ItemDao;
import com.zyl.bicycle.dao.OrderDao;
import com.zyl.bicycle.dao.ServicerDao;
import com.zyl.bicycle.domain.Bike;
import com.zyl.bicycle.domain.Item;
import com.zyl.bicycle.domain.Order;

public class YuDingServlet extends HttpServlet {
   private Map<Long,Integer> cart=new HashMap<Long,Integer>();
    private BikeDao bd=new BikeDao();
    private OrderDao od=new OrderDao();
    private ItemDao itd=new ItemDao(); 
    private ServicerDao sd=new ServicerDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action=request.getParameter("action");
		HttpSession session=request.getSession(true);
		session.setAttribute("cart", cart);
		request.setCharacterEncoding("utf-8");
		if("buy".equals(action)){
          String str=request.getParameter("bid");
          Long id=Long.parseLong(str);
          Bike bike=bd.findOfId(id);
          if(bike.getCount()<=0){
        	  request.setAttribute("msg", "库存不够");
        	  request.getRequestDispatcher("/bikeshow.jsp").forward(request, response);
          }else{
        	  cart.put(id, 1);
        	  request.setAttribute("msg", "添加成功");
        	  request.getRequestDispatcher("/bikeshow.jsp").forward(request, response);
          }
		}else if("delete".equals(action)){
			String str=request.getParameter("bikeid");
			Long id=Long.parseLong(str);
			Map<Long,Integer> cart=(Map<Long,Integer>)session.getAttribute("cart");
			cart.remove(id);
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
			
		}else if("change".equals(action)){
			Map<Long,Integer> cart=(Map<Long,Integer>)session.getAttribute("cart");
			String str=request.getParameter("bikeid");
			String str1=request.getParameter("gnum");
			Long id=Long.parseLong(str);
			int changecount=Integer.parseInt(str1);
			Bike bike=bd.findOfId(id);
			if(changecount>bike.getCount()){
				request.setAttribute("msg", "预定数量超出库存量");
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}else{
				cart.put(id, changecount);
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
		}else if("jiezhang".equals(action)){
			Map<Long,Integer> cart=(Map<Long,Integer>)session.getAttribute("cart");
			String username=(String)session.getAttribute("user");
			if(username==null){
				this.getServletConfig().getServletContext().getRequestDispatcher("/customermanage.jsp").forward(request,response);
				return;
			}
			List<Bike> bikes=new ArrayList<Bike>();
			for(Long id : cart.keySet()){
				Bike bike=bd.findOfId(id);
				bikes.add(bike);
			}
			for(Bike b : bikes){
			    if(b.getCount()<cart.get(b.getId())){
			    	request.setAttribute("msg", "预定数量超出库存量");
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
			    }else{
				b.setCount(b.getCount()-cart.get(b.getId()));
				bd.update(b);
				request.getRequestDispatcher("/ordermessage.jsp").forward(request, response);
			}
			}
		}else if("save".equals(action)){
			String str2=request.getParameter("servicer");
			int servicerid=Integer.parseInt(str2);
			String servicer=sd.findOfId(servicerid).getName();
			String kehutel=request.getParameter("kehutel");
			String str=request.getParameter("time");
			int time=Integer.parseInt(str);
			String username=(String)session.getAttribute("user");
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String day=request.getParameter("day");
			String create_time=year+"-"+month+"-"+day;
			Order order=new Order(username,servicer,time,"未接收",kehutel,create_time);
			od.insert(order);
			Map<Long,Integer> cart=(Map<Long,Integer>)session.getAttribute("cart");
			for(Long id : cart.keySet()){
				Bike bike=bd.findOfId(id);
				Item item=new Item(cart.get(id));
				item.setBike(bike);
				item.setOrder(order);
				itd.insert(bike, order, item);
			}
			cart.clear();
			request.setAttribute("msg", "定单提交成功");
			request.getRequestDispatcher("/pagination.jsp").forward(request, response);
		}
		
	}

}
