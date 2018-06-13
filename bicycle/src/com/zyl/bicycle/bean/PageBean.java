package com.zyl.bicycle.bean;

import com.zyl.bicycle.dao.BikeDao;
import com.zyl.bicycle.dao.ServicerDao;

public class PageBean {
   private int currentpage=1;
   private int totalpage=1;
   private static int quantity=2;
   private int sort;
public int getCurrentpage() {
	return currentpage;
}
public void setCurrentpage(int currentpage) {
	this.currentpage = currentpage;
}
public int getTotalpage() {
	return totalpage;
}
public void setTotalpage(int totalpage) {
   this.totalpage=totalpage;
}
public static int getQuantity() {
	return quantity;
}
public static void setQuantity(int quantity) {
	PageBean.quantity = quantity;
}

 public int getSort() {
	return sort;
}
public void setSort(int sort) {
	this.sort = sort;
}
public int servicerPage(){
		ServicerDao sd=new ServicerDao();
		int page;
		if(sd.count()%this.quantity==0){
			page=sd.count()/this.quantity;
		}else{
			page=sd.count()/this.quantity+1;
		}
		return page;
 }
 public int bikePage(){
	 BikeDao bd=new BikeDao();
	 int page;
	 if(bd.count()%this.quantity==0){
		 page=bd.count()/this.quantity;
	 }else{
		 page=bd.count()/this.quantity+1;
	 }
	 return page;
 }
public int page(int count){
	 BikeDao bd=new BikeDao();
	 int page;
	 if(count%this.quantity==0){
		 page=count/this.quantity;
	 }else{
		 page=count/this.quantity+1;
	 }
	 return page;
}
}
