package com.zyl.bicycle.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.zyl.bicycle.domain.Servicer;

import util.HbmUtil;
public class ServicerDao {
	 public void insert(Servicer s){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   session.save(s);
			   session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}  
	   }
	 public void delate(Servicer s){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   session.delete(s);
			   session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	   }
	 public void update(Servicer s){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   session.update(s);
			   session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().commit();
			e.printStackTrace();
		}finally{
			session.close();
		}
	   }
	 public List<Servicer> list(String hql){
		   Session session=HbmUtil.getSession();
		   try {
			session.beginTransaction();
			   List<Servicer> set= session.createQuery(hql).list();
			   return set;
		} finally{
			session.getTransaction().commit();
			session.close();
		}

	   }
	 public Servicer findOfId(int id){
		  Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   Servicer bike=(Servicer)s.get(Servicer.class, id);
			   return bike;
		}finally{
			s.getTransaction();
			s.close();
		}
	 }
	   public Servicer findOfName(String name){
			  Session s=HbmUtil.getSession();
			  try {
				s.beginTransaction();
				  String hql="from Servicer s where s.name=?";
				  Servicer servicer=(Servicer)s.createQuery(hql).setString(0, name).uniqueResult();
				  return servicer;
			}finally{
				s.getTransaction().commit();
				s.close();
			}
		   }
		public List<Servicer> listOfPage(int page){
			Session s=HbmUtil.getSession();
			try {
				s.beginTransaction();
				List<Servicer> list=s.createQuery("from Servicer s")
				                    .setFirstResult((page-1)*2).setMaxResults(2).list();
				return list;
			}finally{
				s.getTransaction().commit();
				s.close();
			}
		}
		public int count(){
			Session s=HbmUtil.getSession();
			try {
				s.beginTransaction();
				Number num=(Number)s.createQuery("select count(*) from Servicer s").uniqueResult();
				int count=num.intValue();
				return count;
			} finally{
				s.getTransaction().commit();
				s.close();
			}
		}

}
