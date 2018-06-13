package com.zyl.bicycle.servlet;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyl.bicycle.bean.PageBean;
import com.zyl.bicycle.dao.BikeDao;

public class SearchPageServlet extends HttpServlet {
	private PageBean pb=new PageBean();
	private BikeDao bd=new BikeDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          request.setCharacterEncoding("utf-8");
          String action=request.getParameter("action");
          int xypage=1;
          if("pageChange1".equals(action)){
          String page=request.getParameter("changepage1");
          xypage=Integer.parseInt(page);
          pb.setCurrentpage(xypage);
          request.setAttribute("page", xypage);
          request.getRequestDispatcher("/servicermd.jsp").forward(request, response);
          }else if("before1".equals(action)){
        	  int page=pb.getCurrentpage();
        	  if(pb.getCurrentpage()>1){
        		  page--;
        	  }
        	  pb.setCurrentpage(page);
        	  request.setAttribute("page", pb.getCurrentpage());
        	  request.getRequestDispatcher("/servicermd.jsp").forward(request, response);
          }else if("next1".equals(action)){
        	  int page=pb.getCurrentpage();
        	  if(page<pb.servicerPage()){
        		  page++;
        	  }
        	  pb.setCurrentpage(page);
        	  request.setAttribute("page", pb.getCurrentpage());
        	  request.getRequestDispatcher("/servicermd.jsp").forward(request, response);
          }
          String baction=request.getParameter("baction");
          if("pageChange2".equals(baction)){
              String page=request.getParameter("changepage2");
            xypage=Integer.parseInt(page);
              pb.setCurrentpage(xypage);
              request.setAttribute("bikepage", xypage);
              request.getRequestDispatcher("/bikemd.jsp").forward(request, response);
              }else if("before2".equals(baction)){
            	  int page=pb.getCurrentpage();
            	  if(pb.getCurrentpage()>1){
            		  page--;
            	  }
            	  pb.setCurrentpage(page);
            	  request.setAttribute("bikepage", pb.getCurrentpage());
            	  request.getRequestDispatcher("/bikemd.jsp").forward(request, response);
              }else if("next2".equals(baction)){
            	  int page=pb.getCurrentpage();
            	  if(page<pb.bikePage()){
            		  page++;
            	  }
            	  pb.setCurrentpage(page);
            	  request.setAttribute("bikepage", pb.getCurrentpage());
            	  request.getRequestDispatcher("/bikemd.jsp").forward(request, response);
              }
          if("pageChange4".equals(action)){
              String page=request.getParameter("changepage4");
              xypage=Integer.parseInt(page);
              pb.setCurrentpage(xypage);
              request.setAttribute("page", xypage);
              request.getRequestDispatcher("/pagination.jsp").forward(request, response);
              }else if("before4".equals(action)){
            	  int page=pb.getCurrentpage();
            	  if(pb.getCurrentpage()>1){
            		  page--;
            	  }
            	  pb.setCurrentpage(page);
            	  request.setAttribute("page", pb.getCurrentpage());
            	  request.getRequestDispatcher("/pagination.jsp").forward(request, response);
              }else if("next4".equals(action)){
            	  int page=pb.getCurrentpage();
            	  if(page<pb.servicerPage()){
            		  page++;
            	  }
            	  pb.setCurrentpage(page);
            	  request.setAttribute("page", pb.getCurrentpage());
            	  request.getRequestDispatcher("/pagination.jsp").forward(request, response);
              }
           
	}


}
