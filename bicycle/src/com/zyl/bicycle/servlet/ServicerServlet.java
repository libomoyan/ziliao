package com.zyl.bicycle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyl.bicycle.dao.ServicerDao;
import com.zyl.bicycle.domain.Servicer;

public class ServicerServlet extends HttpServlet {
	private ServicerDao sd=new ServicerDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 String action=request.getParameter("action");
		 if("add".equals(action)){
			 String name=request.getParameter("servicername");
	           String address=request.getParameter("serviceraddress");
	           String tel=request.getParameter("servicertel");
	           List<Servicer> list=sd.list("from Servicer s");
	           boolean b=false;
	           String msg="";
	           for(Servicer s : list){
	        	   if(s.getName().equals(name)){
	        		   b=true;
	        	   }
	           }
	           if(b){
	        	   msg="�˷���վ�Ѵ���";
	        	   request.setAttribute("msg", msg);
	        	   request.getRequestDispatcher("/servicermanage.jsp").forward(request, response);
	           }else{
	        	   Servicer s=new Servicer(name,address,tel);
	        	   sd.insert(s);
	        	   msg="��ӳɹ�";
	        	   request.setAttribute("msg", msg);
	        	   request.getRequestDispatcher("/servicermanage.jsp").forward(request, response);
	           }
		 }else  if("updateservicer".equals(action)){
			 String name=request.getParameter("servicername");
	         String address=request.getParameter("serviceraddress");
	         String tel=request.getParameter("servicertel");
	         Servicer s=sd.findOfName(name);
	         s.setAddress(address);
	         s.setTel(tel);
	         sd.update(s);
	         request.setAttribute("updatemsg", "�޸ĳɹ�");
	         request.getRequestDispatcher("/servicermd.jsp").forward(request, response);
	         }else if("deleteservicer".equals(action)){
	        	 String name=request.getParameter("servicername");
	        	 Servicer s=sd.findOfName(name);
	        	 sd.delate(s);
	        	 request.setAttribute("deletemsg", "ɾ���ɹ�");
	             request.getRequestDispatcher("/servicermd.jsp").forward(request, response);
	         }
	}

}
