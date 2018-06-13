package com.zyl.bicycle.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.zyl.bicycle.domain.Client;

import util.HbmUtil;

public class ClientDao {
	   public void insert(Client object){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   s.save(object);
			   s.getTransaction().commit();
		} catch (HibernateException e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			s.close();
		}
	   }
	   public void delate(Client object){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   s.delete(object);
			   s.getTransaction().commit();
		} catch (HibernateException e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			s.close();
		}
	   }
	   public void update(Client object){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			  s.update(object);
			   s.getTransaction().commit();
		} catch (HibernateException e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			s.close();
		} 
	   }
	   
	   public List<Client> list(String hql){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   List<Client> list=s.createQuery(hql).list();
			   return list;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
		}
	  public Client findOfName(String name){
		  Session s=HbmUtil.getSession();
		  try {
			s.beginTransaction();
			  String hql="from Client c where c.name=?";
			  Client c=(Client)s.createQuery(hql).setString(0, name).uniqueResult();
			  return c;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
	  }
	  public boolean dengluCheck(String name,String password){
		  Session s=HbmUtil.getSession();
		  try {
			s.beginTransaction();
			  boolean b=false;
			  String hql="select count(c) from Client c where c.name=? and c.password=?";
			  Number num=(Number)s.createQuery(hql).setString(0, name).setString(1, password).uniqueResult();
			  int count=num.intValue();
			  if(count==1){
				  b=true;
			  }
			  return b;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	  }
}
