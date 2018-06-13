package com.zyl.bicycle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyl.bicycle.bean.PageBean;
import com.zyl.bicycle.dao.BikeDao;

public class BikeSortPageServlet extends HttpServlet {
    private PageBean pb=new PageBean();
    private BikeDao bd=new BikeDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  HttpSession session=request.getSession(true);
		 String sort=request.getParameter("servicer");
         if(sort!=null){
      	  int id=Integer.parseInt(sort);
      	  if(id!=pb.getSort()){
      		  pb.setCurrentpage(1);
      	  }
      	  pb.setSort(id);
      	  int count=bd.countOfServicer(id);
      	  pb.setTotalpage(pb.page(count));
        }
      	  String showaction=request.getParameter("showaction");
            if("pageChange3".equals(showaction)){
                String page=request.getParameter("changepage3");
                int xypage=Integer.parseInt(page);
                pb.setCurrentpage(xypage);
                }else if("before3".equals(showaction)){
              	  int page=pb.getCurrentpage();
              	  if(pb.getCurrentpage()>1){
              		  page--;
              	  }
              	  pb.setCurrentpage(page);
                }else if("next3".equals(showaction)){
              	  int page=pb.getCurrentpage();
              	  if(page<pb.getTotalpage()){
              		  page++;
              	  }
              	  pb.setCurrentpage(page);
                }
            session.setAttribute("buypage", pb);
            request.getRequestDispatcher("/bikeshow.jsp").forward(request, response);
        
	}


}
