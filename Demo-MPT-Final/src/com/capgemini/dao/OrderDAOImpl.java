package com.capgemini.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.capgemini.OrderNotFoundException;
import com.capgemini.dto.OrderDTO;
import com.capgemini.utils.DBUtils;
import com.capgemini.utils.Log4jHTMLLayout;

public class OrderDAOImpl implements OrderDAO {

	private static Logger log = Logger.getLogger(Log4jHTMLLayout.class);
	
	private Connection dbConnection; // ???

	{
		try {
			dbConnection = DBUtils.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int generateNextOrderId() throws SQLException {
		int id = 0;

		String selectQuery = "select order_seq.nextval from dual";

		Statement selectStatement = dbConnection.createStatement();
		ResultSet result = selectStatement.executeQuery(selectQuery);

		result.next();

		id = result.getInt(1);
		return id;
	}

	@Override
	public boolean createOrder(OrderDTO order) {
		String insertQuery = "insert into OrderDetails values(?,?)";
	
		try {
			PreparedStatement insertStatement = dbConnection.prepareStatement(insertQuery);
			
//			insertStatement.setInt(1, order.getOrderID());
			insertStatement.setInt(1, generateNextOrderId());
			Date orderDate = new Date(order.getOrderDate().getTime());
			
			insertStatement.setDate(2, orderDate);
			
			int rows = insertStatement.executeUpdate();
			
			if(rows > 0){
				System.out.println("Row is added into db");
				log.info("Added a row in db now...");
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public OrderDTO findById(int id) throws OrderNotFoundException {

		String selectQuery = "select * from OrderDetails where orderId = ?";

		try {
			PreparedStatement selectStatement = dbConnection.prepareStatement(selectQuery);

			selectStatement.setInt(1, id);

			ResultSet result = selectStatement.executeQuery();

			while (result.next()) {

				int orderId = result.getInt(1);
				Date orderDateSQL = result.getDate(2);

				java.util.Date orderDate = new java.util.Date(orderDateSQL.getTime());

				OrderDTO order = new OrderDTO();
				order.setOrderID(orderId);
				order.setOrderDate(orderDate);

				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderNotFoundException("order not found",e);
		}

		return null;
	}

}
