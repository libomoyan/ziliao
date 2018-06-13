package com.zyl.bicycle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyl.bicycle.dao.BikeDao;
import com.zyl.bicycle.dao.ServicerDao;
import com.zyl.bicycle.domain.Bike;
import com.zyl.bicycle.domain.Servicer;

public class BikeServlet extends HttpServlet {
	 private BikeDao bd=new BikeDao();
	    private ServicerDao sd=new ServicerDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 String action=request.getParameter("action");
		 if("add".equals(action)){
			 String name=request.getParameter("bikename");
	          String str1=request.getParameter("bikeprice");
	          double price=Double.parseDouble(str1);
	          double yajin=Double.parseDouble(request.getParameter("bikeyajin"));
	          int count=Integer.parseInt(request.getParameter("bikecount"));
	          String url=request.getParameter("bikeurl");
	          String sname=request.getParameter("servicer");
	          Servicer s=sd.findOfName(sname);
	          Bike bike=new Bike(name,price,yajin,count,url);
	          List<Bike> list=bd.list("from Bike b");
	          boolean b=false;
	          for(Bike bicycle : list){
	        	  if(bicycle.getName().equals(name)&&bicycle.getServicer().getName().equals(sname)){
	        		  b=true;
	        	  }
	          }
	          if(!b){
	          bd.insert(bike, s);
	          request.setAttribute("msg", "添加成功");
	          request.getRequestDispatcher("/bikemanage.jsp").forward(request, response);
	          }else{
	        	  request.setAttribute("msg", "此服务站中此类自行车已存在");
	        	  request.getRequestDispatcher("/bikemanage.jsp").forward(request, response);
	          }
		 }else if("updatebike".equals(action)){
			 String name=request.getParameter("bikename");
	          String str1=request.getParameter("bikeprice");
	          double price=Double.parseDouble(str1);
	          String str2=request.getParameter("bikeyajin");
	          double yajin=Double.parseDouble(str2);
	          String str3=request.getParameter("bikecount");
	          int count=Integer.parseInt(str3);
	          String str4=request.getParameter("bikeid");
	          Long id=Long.parseLong(str4);
			 Bike bike=bd.findOfId(id);
       	  bike.setPrice(price);
       	  bike.setYajin(yajin);
       	  bike.setCount(count);
       	  bd.update(bike);
       	  request.setAttribute("updatemsg", "修改成功");
       	  request.getRequestDispatcher("/bikemd.jsp").forward(request, response); 
		 }else if("deletebike".equals(action)){
		      String str4=request.getParameter("bikeid");
	          Long id=Long.parseLong(str4);
			  Bike bike=bd.findOfId(id);
        	  bd.delete(bike);
        	  request.setAttribute("deletemsg", "删除成功");
        	  request.getRequestDispatcher("/bikemd.jsp").forward(request, response);
		 }
	}


}
