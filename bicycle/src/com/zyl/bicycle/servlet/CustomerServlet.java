package com.zyl.bicycle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyl.bicycle.dao.ClientDao;
import com.zyl.bicycle.domain.Client;

public class CustomerServlet extends HttpServlet {
    private ClientDao cd=new ClientDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   HttpSession session=request.getSession(true);
           request.setCharacterEncoding("utf-8");
           String action=request.getParameter("action");
           if("zhuce".equals(action)){
        	   String username=request.getParameter("cname");
        	   String password=request.getParameter("cpassword");
        	   String email=request.getParameter("email");
               String hql="from Client c";
               boolean b=true;
               List<Client> list=cd.list(hql);
               for(Client c : list){
            	   if(c.getName().equals(username)){
            		   b=false;
            	   }
               }
               if(b==false){
            	   request.setAttribute("msg", "���û��Ѵ���");
            	   request.getRequestDispatcher("/customerzc.jsp").forward(request, response);
               }else{
            	   Client client=new Client(username,password,email);
            	   cd.insert(client);
            	   request.setAttribute("msg", "ע��ɹ�");
            	   request.getRequestDispatcher("/customerzc.jsp").forward(request, response);
               }
           }else if("logout".equals(action)){
        	   String user=(String)session.getAttribute("user");
        	   if(user==null){
        		   request.setAttribute("msg", "���ȵ�¼");
        		   request.getRequestDispatcher("/customermanage.jsp").forward(request, response);
        	   }else{
        	   request.setAttribute("msg", "ע���ɹ�");
			   request.getSession(true).invalidate();
	    		request.getRequestDispatcher("/pagination.jsp").forward(request, response);
        	   }
		   }else if("denglu".equals(action)){
			   String username=request.getParameter("zname");
			   String password=request.getParameter("zpassword");
			   String msg="";
			   boolean chengong=cd.dengluCheck(username, password);
			   if(chengong==true){
			       session.setAttribute("user",username);
			       request.setAttribute("msg", "��¼�ɹ�");
			       request.getRequestDispatcher("/pagination.jsp").forward(request,response);
			   }else{
			   msg="�û������벻ƥ�䣬��¼ʧ��";
			   request.setAttribute("msg",msg);
			   request.getRequestDispatcher("/customermanage.jsp").forward(request,response);
			   }
		   }else if("resetpwd".equals(action)){
			   String username=(String)session.getAttribute("user");
		          String password=request.getParameter("apwd");
		          Client c=cd.findOfName(username);
		          c.setPassword(password);
		          cd.update(c);
		          request.setAttribute("msg", "�����޸ĳɹ�");
		          request.getRequestDispatcher("/customerpwchange.jsp").forward(request, response);
		   }
	}

}
