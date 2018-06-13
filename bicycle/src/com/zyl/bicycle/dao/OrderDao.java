package com.zyl.bicycle.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.zyl.bicycle.bean.PageBean;
import com.zyl.bicycle.domain.Order;

import util.HbmUtil;



public class OrderDao {
	 public void insert(Order o){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   session.save(o);
			   session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}  
	   }
	 public void delate(Order o){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   session.delete(o);
			   session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	   }
	 public void update(Order o){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   session.update(o);
			   session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().commit();
			e.printStackTrace();
		}finally{
			session.close();
		}
	   }
	 public List<Order> list(String hql){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   List<Order> set= session.createQuery(hql).list();
			   return set;
		} finally{
			session.getTransaction().commit();
			session.close();
		}

	   }
	 public List<Order> listOfCheck(String check){
		 Session s=HbmUtil.getSession();
		 try {
			s.beginTransaction();
			 List<Order> list=s.createQuery("from Order o where o.check=?").setString(0, check).list();
			 return list;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	 }
	 public List<Order> listOfClient(String name){
		 Session s=HbmUtil.getSession();
		 try {
			s.beginTransaction();
			 List<Order> list=s.createQuery("from Order o where o.client_name=?").setString(0, name).list();
			 return list;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
	 }
	 public Order findOfId(Long id){
		 Session s=HbmUtil.getSession();
		 try {
			s.beginTransaction();
			Order order=(Order)s.createQuery("from Order o where o.id=?").setLong(0, id).uniqueResult();
			return order;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
	 }
	 public int countOfOrdercheck(String str){
		 Session s=HbmUtil.getSession();
		 try {
			s.beginTransaction();
			 Number num=(Number)s.createQuery("select count(o) from Order o where o.ordercheck=?")
			            .setString(0, str).uniqueResult();
			 int count=num.intValue();
			 return count;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	 }
	 public int count(){
		 Session s=HbmUtil.getSession();
		 try {
			s.beginTransaction();
			 Number num=(Number)s.createQuery("select count(o) from Order o ")
			            .uniqueResult();
			 int count=num.intValue();
			 return count;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	 }
	   public List<Order> listOfPage(int page){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   List<Order> list=s.createQuery("from Order o").setFirstResult((page-1)*PageBean.getQuantity())
			                                               .setMaxResults(PageBean.getQuantity()).list();
			   return list;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
	   public List<Order> listOfPageAndOrdercheck(int page,String str){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			 List<Order> list=s.createQuery(" from Order o where o.ordercheck=?")
			               .setString(0, str).setFirstResult((page-1)*PageBean.getQuantity())
			               .setMaxResults(PageBean.getQuantity()).list();
			   return list;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
}
