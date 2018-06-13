package com.zyl.bicycle.domain;

public class Bike {
   private Long id;
   private String name;
   private double price;
   private double yajin;
   private int count;
   private String pic_url;
   private Servicer servicer;
public Bike(String name, double price, double yajin, int count, String picUrl
		) {
	super();
	this.name = name;
	this.price = price;
	this.yajin = yajin;
	this.count = count;
	this.pic_url = picUrl;
	this.servicer=new Servicer();

}
public Bike() {
	super();
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
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public double getYajin() {
	return yajin;
}
public void setYajin(double yajin) {
	this.yajin = yajin;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public String getPic_url() {
	return pic_url;
}
public void setPic_url(String picUrl) {
	pic_url = picUrl;
}
public Servicer getServicer() {
	return servicer;
}
public void setServicer(Servicer servicer) {
	this.servicer = servicer;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + count;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((pic_url == null) ? 0 : pic_url.hashCode());
	long temp;
	temp = Double.doubleToLongBits(price);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((servicer == null) ? 0 : servicer.hashCode());
	temp = Double.doubleToLongBits(yajin);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Bike other = (Bike) obj;
	if (count != other.count)
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (pic_url == null) {
		if (other.pic_url != null)
			return false;
	} else if (!pic_url.equals(other.pic_url))
		return false;
	if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
		return false;
	if (servicer == null) {
		if (other.servicer != null)
			return false;
	} else if (!servicer.equals(other.servicer))
		return false;
	if (Double.doubleToLongBits(yajin) != Double.doubleToLongBits(other.yajin))
		return false;
	return true;
}
   
}
