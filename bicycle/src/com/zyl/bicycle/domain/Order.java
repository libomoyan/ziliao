package com.zyl.bicycle.domain;

import java.util.HashSet;
import java.util.Set;

public class Order {
   private Long id;
   private String client_name;
   private String servicer_name;
   private int time;
   private Set<Item> items;
   private String ordercheck;
   private String kehutel;
   private String create_date;
public Order( String client_name,String servicerName, int time,
		      String ordercheck,String kehutel,String create_time) {
	super();
	this.client_name=client_name;
	this.servicer_name = servicerName;
	this.time = time;
	this.ordercheck=ordercheck;
	this.kehutel=kehutel;
    this.create_date=create_time;
    this.items=new HashSet<Item>();
}
public Order() {
	super();
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getServicer_name() {
	return servicer_name;
}
public void setServicer_name(String servicerName) {
	servicer_name = servicerName;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}

public Set<Item> getItems() {
	return items;
}
public void setItems(Set<Item> items) {
	this.items = items;
}

public String getOrdercheck() {
	return ordercheck;
}
public void setOrdercheck(String ordercheck) {
	this.ordercheck = ordercheck;
}

public String getClient_name() {
	return client_name;
}
public void setClient_name(String clientName) {
	client_name = clientName;
}
public String getKehutel() {
	return kehutel;
}
public void setKehutel(String kehutel) {
	this.kehutel = kehutel;
}
public String getCreate_date() {
	return create_date;
}
public void setCreate_date(String createTime) {
	create_date = createTime;
}

   
}
