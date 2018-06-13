package com.zyl.bicycle.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import util.HbmUtil;

public class BaseDao<T> {
   public void insert(T object){
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
   public void delate(T object){
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
   public void update(T object){
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
   
   public List<T> list(String hql){
	   Session s=HbmUtil.getSession();
	   try {
		s.beginTransaction();
		   List<T> list=s.createQuery(hql).list();
		   return list;
	} finally{
		s.getTransaction().commit();
		s.close();
	}
	}
   public T findOnly(Class <? extends T> clazz,Long id){
		 Session s=HbmUtil.getSession();
		 try {
			s.beginTransaction();
			return (T) s.get(clazz, id);
		} finally{
		  s.getTransaction().commit();
		  s.close();
		}
   }
}
