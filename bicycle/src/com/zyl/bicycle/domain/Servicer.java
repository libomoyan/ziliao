package com.zyl.bicycle.domain;

import java.util.HashSet;
import java.util.Set;


public class Servicer {
   private Integer id;
   private String name;
   private String address;
   private String tel;
   private Set<Bike> bikes;
public Servicer(String name, String address, String tel) {
	super();
	this.name = name;
	this.address = address;
	this.tel = tel;
	this.bikes=new HashSet();
}
public Servicer() {
	super();
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public Set<Bike> getBikes() {
	return bikes;
}
public void setBikes(Set<Bike> bikes) {
	this.bikes = bikes;
}
   
}
