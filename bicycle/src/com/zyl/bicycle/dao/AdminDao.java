package com.zyl.bicycle.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import util.HbmUtil;

import com.zyl.bicycle.domain.Admin;


public class AdminDao {
	   public void insert(Admin object){
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
	   public void delate(Admin object){
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
	   public void update(Admin object){
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
	   
	   public List<Admin> list(String hql){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   List<Admin> list=s.createQuery(hql).list();
			   return list;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
		}
	  public Admin findOfName(String name){
		  Session s=HbmUtil.getSession();
		  try {
			s.beginTransaction();
			  String hql="from Admin a where a.name=?";
			  Admin c=(Admin)s.createQuery(hql).setString(0, name).uniqueResult();
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
			  String hql="select count(a) from Admin a where a.name=? and a.password=?";
			  Number num=(Number)s.createQuery(hql).setString(0, name).setString(1, password).uniqueResult();
			  int count=num.intValue();
			  if(count==1){
				  b=true;
			  }
			  return b;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
	  }
}
