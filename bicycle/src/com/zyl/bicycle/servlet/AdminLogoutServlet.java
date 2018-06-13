package com.zyl.bicycle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyl.bicycle.dao.AdminDao;
import com.zyl.bicycle.dao.BikeDao;
import com.zyl.bicycle.dao.OrderDao;
import com.zyl.bicycle.dao.ServicerDao;
import com.zyl.bicycle.domain.Admin;
import com.zyl.bicycle.domain.Bike;
import com.zyl.bicycle.domain.Item;
import com.zyl.bicycle.domain.Order;
import com.zyl.bicycle.domain.Servicer;

public class AdminLogoutServlet extends HttpServlet {
    private OrderDao od=new OrderDao();
    private ServicerDao sd=new ServicerDao();
    private BikeDao bd=new BikeDao();
    private AdminDao ad=new AdminDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action").trim();
		if("logout".equals(action)){
		request.getSession(true).invalidate();
		this.getServletContext().getRequestDispatcher("/adminlogin.jsp").forward(request,response);
		return;	
		}else if("jieshou".equals(action)){
			String oid=request.getParameter("oid");
			Long id=Long.parseLong(oid);
			Order order=od.findOfId(id);
			order.setOrdercheck("�ѽ���");
			od.update(order);
			request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
		}else if("querengh".equals(action)){
			String str=request.getParameter("oid");
			Long id=Long.parseLong(str);
			Order order=od.findOfId(id);
			Set<Item> items=order.getItems();
			String servicername=order.getServicer_name();
			Servicer servicer=sd.findOfName(servicername);
			for(Item item : items){
				String bname=item.getBike().getName();
				boolean b=bd.panDuan(bname, servicername);
				if(b==true){
					Bike bike=bd.findOfNameAndServicer(bname, servicername);
					bike.setCount(bike.getCount()+item.getCount());
					bd.update(bike);
				}else{
					Bike oldbike=item.getBike();
					Bike newbike =new Bike(oldbike.getName(),oldbike.getPrice(),oldbike.getYajin()
							      ,item.getCount(),oldbike.getPic_url());
					bd.insert(newbike, servicer);
				}
		    }
			od.delate(order);
			request.getRequestDispatcher("/dingdanshow.jsp").forward(request, response);
		}else if("mandenglu".equals(action)){
			String adminname=request.getParameter("mmname");
	           String password=request.getParameter("mmpassword");
	           String msg="";
	           String level=null;
	           boolean chenggong=ad.dengluCheck(adminname, password);
			    if(chenggong){
			    	Admin a=ad.findOfName(adminname);
			    	session.setAttribute("admin", adminname);
			    	if("����".equals(a.getLevel())){
			    		session.setAttribute("chaoji","chaoji");
			    	}else if("��ͨ".equals(a.getLevel())){
			    		 session.setAttribute("putong", "putong");
			    	}
			    	request.getRequestDispatcher("admin.jsp").forward(request,response);
			     }else{
			   msg="�û������벻ƥ�䣬��¼ʧ��";
			   request.setAttribute("msg",msg);
			   request.getRequestDispatcher("/customermanage.jsp").forward(request,response);
			   }
		}else if("tianjia".equals(action)){
			boolean b=false;
			String msg="";
	        String adminname=request.getParameter("mname");
	        String password=request.getParameter("mpassword");
	        String quanxian=request.getParameter("quanxian");
	        String level="����";
	        if("��ͨ".equals(quanxian)){
	        	level="��ͨ";
	        }
	        String hql="from Admin a";
	        List<Admin> list=ad.list(hql);
	        for(Admin a : list){
	        	if(adminname.equals(a.getName())){
	        		b=true;
	        	}
	        }
	        if(b){
	        	msg="�ù���Ա�Ѵ���";
	        	request.setAttribute("msg", msg);
	        	request.getRequestDispatcher("/adminzc.jsp").forward(request, response);
	        }else{
	        	Admin admin=new Admin(adminname,password,level);
	        	ad.insert(admin);
	        	msg="ע��ɹ�";
	        	request.setAttribute("msg", msg);
	        	request.getRequestDispatcher("/adminzc.jsp").forward(request, response);
	        }
		}else if("mandelete".equals(action)){
			 String adminname=request.getParameter("mname");
	          String hql="from Admin a where a.name=?";
	          Admin admin=ad.findOfName(adminname);
	          if(admin.getLevel().equals("����")){
	        	  request.setAttribute("msg", "����ɾ����������Ա");
	        	  request.getRequestDispatcher("/admindelate.jsp").forward(request, response);
	          }else{
	        	  ad.delate(admin);
	        	  request.setAttribute("msg", "ɾ���ɹ�");
	        	  request.getRequestDispatcher("/admindelate.jsp").forward(request, response);
	          }
		}else if("resetpwd".equals(action)){
	         String name=request.getParameter("aname");
	         String password=request.getParameter("apwd");
	         Admin admin=ad.findOfName(name);
	         admin.setPassword(password);
	         ad.update(admin);
	         request.setAttribute("msg", "�޸ĳɹ�");
	         request.getRequestDispatcher("/adminchangepw.jsp").forward(request, response);
		}
	}


}
