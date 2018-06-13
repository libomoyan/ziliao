package com.zyl.bicycle.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyl.bicycle.bean.PageBean;
import com.zyl.bicycle.dao.OrderDao;

public class AdminServlet extends HttpServlet {
    private PageBean pb=new PageBean();
    private OrderDao od=new OrderDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession(true);
         request.setCharacterEncoding("utf-8");
         String action=request.getParameter("action");
         if("alldd".equals(action)){
        	 pb.setCurrentpage(1);
        	 int count=od.count();
        	 pb.setTotalpage(pb.page(count));
        	 pb.setSort(1);
         }else if("yfsdd".equals(action)){
        	 pb.setCurrentpage(1);
        	 int count=od.countOfOrdercheck("已接收");
        	 pb.setTotalpage(pb.page(count));
        	 pb.setSort(2);
         }else if("wfsdd".equals(action)){
        	 pb.setCurrentpage(1);
        	 int count=od.countOfOrdercheck("未接收");
        	 pb.setTotalpage(pb.page(count));
        	 pb.setSort(3);
         }
        	 String ddaction=request.getParameter("ddaction");
        	 if("pageChange1".equals(ddaction)){
        		 String page=request.getParameter("changepage1");
                 int xypage=Integer.parseInt(page);
                 pb.setCurrentpage(xypage); 
        	 }else if("before1".equals(ddaction)){
        		 int page=pb.getCurrentpage();
             	  if(pb.getCurrentpage()>1){
             		  page--;
             	  }
             	  pb.setCurrentpage(page);
        	 }else if("next1".equals(ddaction)){
        		  int page=pb.getCurrentpage();
              	  if(page<pb.getTotalpage()){
              		  page++;
              	  }
              	  pb.setCurrentpage(page);
        	 }
            session.setAttribute("page", pb);
            request.getRequestDispatcher("/dingdanshow.jsp").forward(request, response);
    
	}


}
