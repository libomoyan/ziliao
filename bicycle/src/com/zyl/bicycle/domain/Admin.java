package com.zyl.bicycle.domain;

public class Admin {
   private Long id;
   private String name;
   private String password;
   private String level;
   
public Admin() {
	super();
}
public Admin(String name, String password,String level) {
	super();
	this.name = name;
	this.password = password;
	this.level = level;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
   
}
