package com.zyl.bicycle.domain;

public class Item {
   private Long id;
   private Order order;
   private Bike bike;
   private int count;
public Item(int count) {
	super();
	this.count = count;
}
public Item() {
	super();
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Order getOrder() {
	return order;
}
public void setOrder(Order order) {
	this.order = order;
}
public Bike getBike() {
	return bike;
}
public void setBike(Bike bike) {
	this.bike = bike;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bike == null) ? 0 : bike.hashCode());
	result = prime * result + count;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((order == null) ? 0 : order.hashCode());
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
	Item other = (Item) obj;
	if (bike == null) {
		if (other.bike != null)
			return false;
	} else if (!bike.equals(other.bike))
		return false;
	if (count != other.count)
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (order == null) {
		if (other.order != null)
			return false;
	} else if (!order.equals(other.order))
		return false;
	return true;
}
   
}
