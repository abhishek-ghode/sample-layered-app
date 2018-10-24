package com.capgemini.dto;

import java.util.Date;

public class OrderDTO {
//	DATA MEMBERS
	private int orderID;
	private Date orderDate;

	
//	PROPERTIES
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public int getOrderID() {
		return orderID;
	}

}
