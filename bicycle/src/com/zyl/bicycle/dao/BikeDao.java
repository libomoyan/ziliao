package com.zyl.bicycle.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.zyl.bicycle.bean.PageBean;
import com.zyl.bicycle.domain.Bike;
import com.zyl.bicycle.domain.Servicer;

import util.HbmUtil;
;

public class BikeDao {
	public void insert(Bike bike,Servicer servicer){
		   Session s=HbmUtil.getSession();
		   try {
			 s.beginTransaction();
			  bike.setServicer(servicer);
			  s.save(bike);
			   s.getTransaction().commit();
		} catch (HibernateException e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			s.close();
		}
	   }
	   public void delete(Bike bike){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   s.delete(bike);
			   s.getTransaction().commit();
		} catch (HibernateException e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			s.close();
		}
	   }
	   public void update(Bike bike){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   s.update(bike);
			   s.getTransaction().commit();
		} catch (HibernateException e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			s.close();
		}
	   }
	   public Bike findOfName(String name){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   String hql="from Bike b where b.name=?";
			   Bike bike=(Bike)s.createQuery(hql).setString(0, name).uniqueResult();
			  return bike;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
	   public Bike findOfId(Long id){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   Bike bike=(Bike)s.get(Bike.class, id);
			   return bike;
		}finally{
			s.getTransaction();
			s.close();
		}
	   }
	   public List<Bike> list(String hql){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   List<Bike> list=s.createQuery(hql).list();
			   return list;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
	   public int count(){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   Number num=(Number)s.createQuery("select count(b) from Bike b").uniqueResult();
			   int count=num.intValue();
			   return count;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
	   public List<Bike> listOfPage(int page){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   List<Bike> list=s.createQuery("from Bike b").setFirstResult((page-1)*PageBean.getQuantity())
			                                               .setMaxResults(PageBean.getQuantity()).list();
			   return list;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
	   public int countOfServicer(String name){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   Number num=(Number)s.createQuery("select count(b) from Bike b where b.servicer.name=?")
			               .setString(0, name).uniqueResult();
			   int count=num.intValue();
			   return count;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
	   public List<Bike> listOfPageAndServicer(int page,int id){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			 List<Bike> list=s.createQuery(" from Bike b where b.servicer.id=?")
			               .setInteger(0, id).setFirstResult((page-1)*PageBean.getQuantity())
			               .setMaxResults(PageBean.getQuantity()).list();
			   return list;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
	   public List<Bike> listOfServicer(int id){
	   Session s=HbmUtil.getSession();
	   try {
		s.beginTransaction();
		   List<Bike> list=s.createQuery("from Bike b where b.servicer.id=?").setInteger(0, id).list();
		   return list;
	}finally{
		s.getTransaction().commit();
		s.close();
	}
	   } 
	   public int countOfServicer(int id){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   Number num=(Number)s.createQuery("select count(b) from Bike b where b.servicer.id=?")
			              .setInteger(0, id).uniqueResult();
			   int count=num.intValue();
			   return count;
		} finally{
			s.getTransaction().commit();
			s.close();
		}
	   }
	   public boolean panDuan(String bname,String sname){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   Boolean b=false;
			   Number num=(Number)s.createQuery("select count(b) from Bike b where b.name=? and b.servicer.name=?")
			               .setString(0, bname).setString(1, sname).uniqueResult();
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
	   public Bike findOfNameAndServicer(String bname,String sname){
		   Session s=HbmUtil.getSession();
		   try {
			s.beginTransaction();
			   Bike bike=(Bike)s.createQuery("from Bike b where b.name=? and b.servicer.name=?")
			               .setString(0, bname).setString(1, sname).uniqueResult();
			   return bike;
		}finally{
			s.getTransaction().commit();
			s.close();
		}
		   
	   }
}
