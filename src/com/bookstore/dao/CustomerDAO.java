package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	@Override
	public Customer create(Customer customer) {
		customer.setRegisterDate(new Date());
		return super.create(customer);
	}

	@Override
	public Customer update(Customer customer) {
		customer.setRegisterDate(new Date());
		return super.update(customer);
	}

	@Override
	public Customer get(Object customerId) {
		return super.find(Customer.class, customerId);
	}

	@Override
	public void delete(Object customerId) {
		super.delete(Customer.class, customerId);
	}

	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
	}
	
	public Customer findByEmail(String email) {
		List<Customer> listCustomers = super.findWithNamedQuery("Customer.findByEmail", "email", email);
		
		if (listCustomers.size() > 0) {
			return listCustomers.get(0);
		}
		
		return null;
	} 

	public Customer checkLogin(String email, String password) {
		Map<String, Object> mapParams = new HashMap<>();
		mapParams.put("email", email);
		mapParams.put("password", password);
		
		List<Customer> listCustomers = super.findWithNamedQuery("Customer.checkLogin", mapParams);
		if (listCustomers.size() > 0) {
			return listCustomers.get(0);
		}
		
		return null;
	}
}
