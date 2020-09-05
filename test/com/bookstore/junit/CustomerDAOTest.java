package com.bookstore.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.HashGeneration;
import com.bookstore.entity.Customer;

public class CustomerDAOTest {

	private static CustomerDAO customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDAO();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("magina@dota.com");
		customer.setFirstname("Anti Mage");
		customer.setLastname("Valve");
		customer.setAddressLine1("Blink Away");
		customer.setAddressLine2("IceFrog");
		customer.setCity("ShangHai");
		customer.setState("ShangHai");
		customer.setCountry("China");
		customer.setPhone("123456789");
		customer.setZipcode("200000");
		customer.setPassword("1234");
		
		Customer newCustomer = customerDao.create(customer);
		
		assertTrue(newCustomer.getCustomerId() > 0);
	}

	@Test
	public void testUpdateCustomer() {
		int customerId = 13;
		Customer customer = customerDao.get(customerId);
		
		String encodePassword = HashGeneration.generateMD5(customer.getPassword());
		customer.setPassword(encodePassword);
		Customer updatedCustomer = customerDao.update(customer);
		
		assertNotNull(updatedCustomer);
	}

	@Test
	public void testGetCustomer() {
		int customerId = 1;
		Customer customer = customerDao.get(customerId);
		
		assertNotNull(customer);
	}

	@Test
	public void testDeleteObject() {
		int customerId = 1;
		customerDao.delete(customerId);
		
		assertNull(customerDao.get(customerId));
	}

	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDao.listAll();
		
		for (Customer customer : listCustomers) {
			System.out.println(customer.getFirstname());
		}
		
		assertTrue(listCustomers.size() > 0);
	}

	@Test
	public void testCount() {
		long countCustomer = customerDao.count();
		
		assertEquals(countCustomer, 2);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "ymir@dota.com";
		Customer customer = customerDao.findByEmail(email);
		
		assertNotNull(customer);
	}
	
	@Test
	public void checkLogin() {
		String email = "tresdin@dota.com";
		String password = "1234";
		Customer customer = customerDao.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

}
