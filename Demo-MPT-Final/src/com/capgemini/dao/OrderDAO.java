package com.capgemini.dao;

import com.capgemini.OrderNotFoundException;
import com.capgemini.dto.OrderDTO;

public interface OrderDAO {

	public boolean createOrder(OrderDTO order);
	public OrderDTO findById(int id) throws OrderNotFoundException;
	
}
