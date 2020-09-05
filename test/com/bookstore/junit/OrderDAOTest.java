package com.bookstore.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

class OrderDAOTest {
	
	private static OrderDAO orderDao;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		orderDao = new OrderDAO();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		orderDao.close();
	}

	@Test
	void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer theCustomer = new Customer();
		theCustomer.setCustomerId(15);
		
		order.setCustomer(theCustomer);
		order.setAddressLine1("123 Doi Cung, VietNam");
		order.setAddressLine2("Valve");
		order.setFirstname("Phu Thanh Tran");
		order.setLastname("IceFrog");
		order.setPhone("123456789");
		order.setCity("Hue Province");
		order.setState("Hue Province");
		order.setZipcode("52000");
		order.setCountry("VN");
		order.setPaymentMethod("Paypal");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(new Book(5));
		orderDetail.setQuantity(1);
		orderDetail.setSubtotal(27.33f);
		orderDetail.setBookOrder(order);
		orderDetails.add(orderDetail);
		
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setBook(new Book(6));
		orderDetail2.setQuantity(2);
		orderDetail2.setSubtotal(66.58f);
		orderDetail2.setBookOrder(order);
		orderDetails.add(orderDetail2);
		
		order.setOrderDetails(orderDetails);
		order.setTax(6.8f);
		order.setShippingFee(2.0f);
		order.setSubtotal(66.58f);
		order.setTotal(102.81f);
		
		
		BookOrder savedOrder = orderDao.create(order);
		
		assertNotNull(savedOrder);
	}

	@Test
	void testUpdateBookOrderShippingAddress() {
		int orderId = 8;
		BookOrder theOrder = orderDao.get(orderId);
		theOrder.setAddressLine1("123 South Street, USA");
		
		BookOrder updatedOrder = orderDao.update(theOrder);
		
		assertEquals(theOrder.getAddressLine1(), updatedOrder.getAddressLine1());
	}
	
	@Test
	void testUpdateBookOrderDetails() {
		int orderId = 3;
		BookOrder theOrder = orderDao.get(orderId);
		
		Iterator<OrderDetail> iterator = theOrder.getOrderDetails().iterator();
		
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == 2) {
				orderDetail.setQuantity(4);
				orderDetail.setSubtotal(125);
			}
			
		}
		
		BookOrder updatedOrder = orderDao.update(theOrder);
		
		iterator = updatedOrder.getOrderDetails().iterator();
		int actualQuantity = 0;
		float actualSubtotal = 0.0f;
		
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == 2) {
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubtotal();
			}
		}
		
		assertEquals(4, actualQuantity);
		assertEquals(125, actualSubtotal, 0.0f);
	}

	@Test
	void testGet() {
		int orderId = 10;
		BookOrder theOrder = orderDao.get(orderId);
		
		System.out.println(theOrder.getAddressLine1());
		System.out.println(theOrder.getAddressLine2());
		System.out.println(theOrder.getFirstname());
		System.out.println(theOrder.getLastname());
		System.out.println(theOrder.getPhone());
		System.out.println(theOrder.getCountryName());
		System.out.println(theOrder.getCity());
		System.out.println(theOrder.getState());
		System.out.println(theOrder.getZipcode());
		System.out.println(theOrder.getPaymentMethod());
		System.out.println(theOrder.getShippingFee());
		System.out.println(theOrder.getTax());
		System.out.println(theOrder.getSubtotal());
		System.out.println(theOrder.getTotal());
		
		assertEquals(2, theOrder.getOrderDetails().size());
	}

	@Test
	void testDeleteObject() {
		int orderId = 4;
		orderDao.delete(orderId);
		
		BookOrder order = orderDao.get(orderId);
		assertNull(order);
	}

	@Test
	void testListAll() {
		List<BookOrder> listOrder = orderDao.listAll();
		
		for (BookOrder order : listOrder) {
			System.out.println(order.getFirstname() + " - " + order.getCustomer().getFirstname());
			
			for (OrderDetail orderDetail : order.getOrderDetails()) {
				System.out.println(orderDetail.getBook().getTitle() + " - " + orderDetail.getQuantity());
			}
			
			System.out.println();
		}
		
		assertTrue(listOrder.size() == 3);
	}
	
	@Test
	void testListOrdersByCustomer() {
		int customerId = 10;
		List<BookOrder> listOrders = orderDao.listOrdersByCustomer(customerId);
		
		assertEquals(2, listOrders.size());
	}

	@Test
	void testCount() {
		long countOrder = orderDao.count();
		
		assertEquals(3, countOrder);
	}
	
	@Test
	void testByIdAndCustomer() {
		int orderId = 5;
		int customerId = 7;
		
		BookOrder theOrder = orderDao.get(orderId, customerId);
		
		assertNotNull(theOrder);
	}

}
