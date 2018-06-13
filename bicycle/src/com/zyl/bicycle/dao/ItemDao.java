package com.zyl.bicycle.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.zyl.bicycle.domain.Bike;
import com.zyl.bicycle.domain.Item;
import com.zyl.bicycle.domain.Order;

import util.HbmUtil;
public class ItemDao {
	public void insert(Bike bike,Order order,Item item){
		   Session s=HbmUtil.getSession();
		   try {
			 s.beginTransaction();
			  item.setBike(bike);
			  item.setOrder(order);
			  s.save(item);
			   s.getTransaction().commit();
		} catch (HibernateException e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			s.close();
		}
	   }
	 public void delate(Item i){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   session.delete(i);
			   session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	   }
	 public void update(Item i){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   session.update(i);
			   session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().commit();
			e.printStackTrace();
		}finally{
			session.close();
		}
	   }
	 public List<Item> list(String hql){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   List<Item> set= session.createQuery(hql).list();
			   return set;
		} finally{
			session.getTransaction().commit();
			session.close();
		}

	   }
	 public List<Item> listOfOrder(Long id){
		 Session session=HbmUtil.getSession();
		 try {
			session.beginTransaction();
			 List<Item> list=session.createQuery("from Item i where i.order.id=?").setLong(0, id).list();
			 return list;
		} finally{
			session.getTransaction().commit();
			session.close();
		}
	 }
}
