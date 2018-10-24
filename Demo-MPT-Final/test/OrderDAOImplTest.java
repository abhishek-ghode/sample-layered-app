import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.capgemini.OrderNotFoundException;
import com.capgemini.dao.OrderDAO;
import com.capgemini.dao.OrderDAOImpl;
import com.capgemini.dto.OrderDTO;

public class OrderDAOImplTest {

	private OrderDAO daoRef;
	
	@Before
	public void setup(){
		System.out.println("DAO instantiated");
		daoRef = new OrderDAOImpl();
	}
	
	@After
	public void tearDown(){
		System.out.println("DAO cleaned");
		daoRef = null;
	}
	
	
	@Test
	public void testFindByID() throws OrderNotFoundException{
		
		int id = 2;
		OrderDTO order = daoRef.findById(id);
		
		Assert.assertNotNull(order);
		Assert.assertEquals(id, order.getOrderID());
	}
	
	@Test
	public void testCreateOrder(){
		OrderDTO order = new OrderDTO();
		order.setOrderDate(new Date());
		
		Assert.assertTrue(daoRef.createOrder(order));
		
		
	}
	
	
	
	
	
	@Test
	@Ignore
	public void testStringOperations(){
		String message = "Hello, world!";
		
		String message2 = "Hello, world";
		
//		assert (message.equals(message2));
		
		Assert.assertEquals(message, message2);
	}
	
	
	
	
}
