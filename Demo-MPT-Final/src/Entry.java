import java.util.Date;

import com.capgemini.OrderNotFoundException;
import com.capgemini.dao.OrderDAO;
import com.capgemini.dao.OrderDAOImpl;
import com.capgemini.dto.OrderDTO;

public class Entry {

	
	public static void main(String[] args) throws OrderNotFoundException {
		
		
		OrderDAO daoRef = new OrderDAOImpl();
		
	/*	
		OrderDTO order = new OrderDTO();
		order.setOrderDate(new Date());
//		order.setOrderID(1);
		
		daoRef.createOrder(order);*/
		

		OrderDTO order = daoRef.findById(1);
		
		System.out.println(order.getOrderID() + "/" + order.getOrderDate());
		
		
		
		
		
		
		
		
		
		
	}
	
}
