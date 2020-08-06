package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.HashGeneration;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;

public class CustomerService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDao;
	private ReviewDAO reviewDao;
	
	private static String CUSTOMER_LIST_PAGE = "customer_list.jsp";
	private static String CUSTOMER_FORM_PAGE = "customer_form.jsp";
	private static String CUSTOMER_LOGIN_PAGE = "frontend/login.jsp";
	private static String CUSTOMER_PROFILE_PAGE = "frontend/customer_profile.jsp";
	private static String EDIT_PROFILE_PAGE = "frontend/edit_profile.jsp";
	
	public CustomerService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.customerDao = new CustomerDAO();
		this.reviewDao = new ReviewDAO();
	}
	
	public void listAllCustomers() throws ServletException, IOException {
		listAllCustomers(null);
	}
	
	public void listAllCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomers = customerDao.listAll();
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listCustomers", listCustomers);
		CommonUtility.forwardToPage(CUSTOMER_LIST_PAGE, request, response);
	}

	public void createCustomer() throws ServletException, IOException {
		String email = CommonUtility.checkNull(request.getParameter("email"));
		Customer existedCustomer = customerDao.findByEmail(email);
		
		if (existedCustomer != null) {
			String message = "Could not create new customer: the email " + email +
					" is already registered by another customer.";
			listAllCustomers(message);
			return;
		}
		
		Customer newCustomer = new Customer();
		getCustomerFromFormData(newCustomer);
		customerDao.create(newCustomer);
		String message = "New customer created successfully";
		listAllCustomers(message);
	}

	public void editCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("id")));
		String customerName = request.getParameter("name").trim();
		Customer editedCustomer = customerDao.get(customerId);
		
		if (editedCustomer == null) {
			String message = "Could not find customer " + customerName;
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		} 
		
		request.setAttribute("customer", editedCustomer);
		CommonUtility.forwardToPage(CUSTOMER_FORM_PAGE, request, response);
		
	}

	public void updateCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("customerId")));
		String email = request.getParameter("email");
		
		Customer customerById = customerDao.get(customerId);
		Customer customerByEmail = customerDao.findByEmail(email);
		String message = "The customer has been updated successfully";
		
		if (customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
			message = "Could not update the customer " + customerById.getFullname()
						+ " because there is a customer having same email!!!";	
		} else {
			getCustomerFromFormData(customerById);
			customerDao.update(customerById);
		}
		
		listAllCustomers(message);
		
	}

	public void deleteCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("id")));
		Customer theCustomer = customerDao.get(customerId);
		
		String message = "The customer has been deleted successfully!!!"; 
		
		if (theCustomer == null) {
			message = "Could not find customer with ID " + customerId
					+ ", or it has been deleted by another admin";
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
		long countReviewsByCustomer = reviewDao.countReviewsByCustomer(customerId);
		if (countReviewsByCustomer > 0) {
			message = "Could not delete customer with ID " + customerId + " because he/she posted reviews for books";
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
//		customerDao.delete(customerId);
		listAllCustomers(message);
	}

	public void registerCustomer() throws ServletException, IOException {
		String email = CommonUtility.checkNull(request.getParameter("email"));
		Customer customerByEmail = customerDao.findByEmail(email);
		
		String message = "You have registerd successfully. Thank you!!!"
				+ " <br/> <a href='login'>Click Here To Login</a>";
		
		if (customerByEmail != null) {
			message = "Could not register. The email "
					+ email + " is already registered by another customer";
		} else {
			Customer newCustomer = new Customer();
			getCustomerFromFormData(newCustomer);
			customerDao.create(newCustomer);
		}
		
		CommonUtility.showMessageFrontEnd(message, request, response);
	}
	
	private void getCustomerFromFormData(Customer customer) {
		String fullName = CommonUtility.checkNull(request.getParameter("fullName"));
		String email = CommonUtility.checkNull(request.getParameter("email"));
		String password = CommonUtility.checkNull(request.getParameter("password"));
		String phone = CommonUtility.checkNull(request.getParameter("phone"));
		String address = CommonUtility.checkNull(request.getParameter("address"));
		String city = CommonUtility.checkNull(request.getParameter("city"));
		String country = CommonUtility.checkNull(request.getParameter("country"));
		String zipCode = CommonUtility.checkNull(request.getParameter("zipCode"));

		customer.setFullname(fullName);		
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setZipcode(zipCode);
		
		if (!(customer.getCustomerId() != null && password.isEmpty())) {
			String encryptedPassword = HashGeneration.generateMD5(password);
			customer.setPassword(encryptedPassword);
		}
		
		if (email == null || !email.isEmpty()) {
			customer.setEmail(email);
		}
	}

	public void showLoginForm() throws ServletException, IOException {
		CommonUtility.forwardToPage(CUSTOMER_LOGIN_PAGE, request, response);
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		
		String encryptedPassword = HashGeneration.generateMD5(password);
		Customer theCustomer = customerDao.checkLogin(email, encryptedPassword);
		if (theCustomer == null) {
			String message = "Login Failed!!! Please check your email and password";
			CommonUtility.forwardToPage(CUSTOMER_LOGIN_PAGE, message, request, response);
			return;
			
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("loggedCustomer", theCustomer);
		
		Object objRedirectURL = session.getAttribute("redirectURL");
		if (objRedirectURL != null) {
			String redirectURL = (String) objRedirectURL;
			session.removeAttribute("redirectURL");
			response.sendRedirect(redirectURL);
		} else {
			CommonUtility.forwardToPage(CUSTOMER_PROFILE_PAGE, request, response);
		}
	}
	
	public void doLogout() throws IOException {
		request.getSession().removeAttribute("loggedCustomer");
		
		response.sendRedirect(request.getContextPath());
	}

	public void editProfileCustomer() throws ServletException, IOException {
		CommonUtility.forwardToPage(EDIT_PROFILE_PAGE, request, response);
	}

	public void updateProfileCustomer() throws IOException, ServletException {
		Customer theCustomer = (Customer) request.getSession().getAttribute("loggedCustomer");
		
		getCustomerFromFormData(theCustomer);
		customerDao.update(theCustomer);
		
		CommonUtility.forwardToPage(CUSTOMER_PROFILE_PAGE, request, response);
	}
}
