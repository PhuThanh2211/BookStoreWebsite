package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderService {
	private OrderDAO orderDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private static final String ORDER_LIST_PAGE = "order_list.jsp";
	private static final String ORDER_FORM_PAGE = "order_form.jsp";
	private static final String ORDER_DETAIL_PAGE = "order_detail.jsp";
	private static final String ADD_BOOK_TO_ORDER_RESULT_PAGE = "add_book_result.jsp";
	private static final String ORDER_CHECKOUT_PAGE = "frontend/checkout.jsp";
	private static final String ORDER_FRONTEND_LIST_PAGE = "frontend/order_list.jsp";
	private static final String ORDER_FRONTEND_DETAIL_PAGE = "frontend/order_detail.jsp";
	
	public OrderService(HttpServletRequest request, HttpServletResponse	response) {
		this.request = request;
		this.response = response;
		
		orderDao = new OrderDAO();
	}
	
	public void listAllOrders() throws ServletException, IOException {
		listAllOrders(null);
	}
	
	public void listAllOrders(String message) throws ServletException, IOException {
		List<BookOrder> listOrders = orderDao.listAll();
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listOrders", listOrders);
		
		CommonUtility.forwardToPage(ORDER_LIST_PAGE, request, response);
		
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		
		BookOrder theOrder = orderDao.get(orderId);
		if (theOrder == null) {
			String message = "Could not find order with ID " + orderId;
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
		request.setAttribute("order", theOrder);
		CommonUtility.forwardToPage(ORDER_DETAIL_PAGE, request, response);
		
	}

	public void showCheckoutForm() throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart sessionCart = (ShoppingCart) session.getAttribute("cart");
		
		// Tax is 10% of subtotal
		float tax = sessionCart.getTotalAmount() * 0.1f;
		
		// Shipping Fee is 1.0 USD per copy
		float shippingFee = sessionCart.getTotalQuantity() * 1.0f;
		
		// Total = subtotal + tax + shipping fee
		float total = sessionCart.getTotalAmount() + tax + shippingFee;
		
		session.setAttribute("tax", tax);
		session.setAttribute("shippingFee", shippingFee);
		session.setAttribute("total", total);
		
		CommonUtility.generateAllCountries(request);
		CommonUtility.forwardToPage(ORDER_CHECKOUT_PAGE, request, response);
	}

	public void placeOrder() throws ServletException, IOException {
		String paymentMethod = CommonUtility.checkNull(request.getParameter("paymentMethod"));
		BookOrder theOrder = readBookOrderInfo();
		
		if (paymentMethod.equals("Paypal")) {
			PaymentService paymentService = new PaymentService(request, response);
			paymentService.authorizePayment(theOrder);
			
		} else { // Cash On Delivery
			placeOrderCOD(theOrder);
		}
		
	}
	
	private BookOrder readBookOrderInfo() {
		String firstname = CommonUtility.checkNull(request.getParameter("firstname"));
		String lastname = CommonUtility.checkNull(request.getParameter("lastname"));
		String phone = CommonUtility.checkNull(request.getParameter("phone"));
		String addressLine1 = CommonUtility.checkNull(request.getParameter("address1"));
		String addressLine2 = CommonUtility.checkNull(request.getParameter("address2"));
		String city = CommonUtility.checkNull(request.getParameter("city"));
		String state = CommonUtility.checkNull(request.getParameter("state"));
		String zipcode = CommonUtility.checkNull(request.getParameter("zipcode"));
		String country = CommonUtility.checkNull(request.getParameter("country"));
		String paymentMethod = CommonUtility.checkNull(request.getParameter("paymentMethod"));
		
		HttpSession session = request.getSession();
		Customer loggedCustomer = (Customer) session.getAttribute("loggedCustomer");
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> cartItems = shoppingCart.getItems();
		
		Iterator<Book> iterator = cartItems.keySet().iterator();
		Set<OrderDetail> orderDetails = new HashSet<>();
		BookOrder order = new BookOrder();
		
		while (iterator.hasNext()) {
			Book book = iterator.next();
			int quantity = cartItems.get(book);
			float subtotal = quantity * book.getPrice();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			
			orderDetails.add(orderDetail);
		}
		
		Float tax = (Float) session.getAttribute("tax");
		Float shippingFee = (Float) session.getAttribute("shippingFee");
		Float total = (Float) session.getAttribute("total");
		
		order.setFirstname(firstname);
		order.setLastname(lastname);
		order.setPhone(phone);
		order.setAddressLine1(addressLine1);
		order.setAddressLine2(addressLine2);
		order.setCity(city);
		order.setState(state);
		order.setZipcode(zipcode);
		order.setCountry(country);
		order.setPaymentMethod(paymentMethod);
		order.setCustomer(loggedCustomer);
		order.setSubtotal(shoppingCart.getTotalAmount());
		order.setTax(tax);
		order.setShippingFee(shippingFee);
		order.setTotal(total);
		order.setOrderDetails(orderDetails);
		
		return order;
	}

	private void placeOrderCOD(BookOrder order) throws ServletException, IOException {
		orderDao.create(order);
		
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		
		shoppingCart.clear();
		session.removeAttribute("tax");
		session.removeAttribute("shippingFee");
		session.removeAttribute("total");
		
		String message = "Thank you. Your order has been received. We will delivery your books within a few days.";
		CommonUtility.showMessageFrontEnd(message, request, response);
	}

	public void listOrderByCustomer() throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loggedCustomer = (Customer) session.getAttribute("loggedCustomer");
		
		List<BookOrder> listOrdersByCustomer = orderDao.listOrdersByCustomer(loggedCustomer.getCustomerId());
		request.setAttribute("listOrders", listOrdersByCustomer);
		
		CommonUtility.forwardToPage(ORDER_FRONTEND_LIST_PAGE, request, response);
	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Customer loggedCustomer = (Customer) session.getAttribute("loggedCustomer");
		
		BookOrder theOrder = orderDao.get(orderId, loggedCustomer.getCustomerId());
		
		request.setAttribute("order", theOrder);
		CommonUtility.forwardToPage(ORDER_FRONTEND_DETAIL_PAGE, request, response);
	}

	public void showEditOrderForm() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		
		Object isPendingBookToOrder = session.getAttribute("pendingBookToOrder");
		if (isPendingBookToOrder != null) {
			session.removeAttribute("pendingBookToOrder");
		} else {
			BookOrder theOrder = orderDao.get(orderId);
			session.setAttribute("order", theOrder);
			
			if (theOrder == null) {
				String message = "Could not find order with ID " + orderId;
				CommonUtility.showMessageBackEnd(message, request, response);
				return;
			}				
		}
		
		CommonUtility.generateAllCountries(request);
		CommonUtility.forwardToPage(ORDER_FORM_PAGE, request, response);
	}

	public void addBookToOrder() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		boolean isTheBookExistedInOrder = false;
		
		BookDAO bookDao = new BookDAO();
		Book addedBook = bookDao.get(bookId);
		
		HttpSession session = request.getSession();
		BookOrder theOrder = (BookOrder) session.getAttribute("order");
		float subtotal = quantity * addedBook.getPrice();
		
		Iterator<OrderDetail> iterator = theOrder.getOrderDetails().iterator();
		while(iterator.hasNext()) {
			OrderDetail orderDetail =  iterator.next();
			if (orderDetail.getBook().getBookId() == bookId) {
				orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
				orderDetail.setSubtotal(orderDetail.getSubtotal() + subtotal);
				
				isTheBookExistedInOrder = true;
			}
		}
		
		if (!isTheBookExistedInOrder) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(addedBook);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			orderDetail.setBookOrder(theOrder);
			
			theOrder.getOrderDetails().add(orderDetail);
		}
		
		theOrder.setSubtotal(theOrder.getSubtotal() + subtotal);
		theOrder.setTotal(theOrder.getSubtotal() + theOrder.getShippingFee() + theOrder.getTax());
		
		session.setAttribute("pendingBookToOrder", true);
		
		request.setAttribute("book", addedBook);
		CommonUtility.forwardToPage(ADD_BOOK_TO_ORDER_RESULT_PAGE, request, response);
		
	}

	public void removeBookFromOrder() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		BookOrder sessionOrder = (BookOrder) session.getAttribute("order");
		int sizeOrderDetails = sessionOrder.getOrderDetails().size();
		
		Iterator<OrderDetail> iterator = sessionOrder.getOrderDetails().iterator();
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == bookId) {
				if (sizeOrderDetails > 1) {
					float subtotalAmount = sessionOrder.getSubtotal() - orderDetail.getSubtotal();
					sessionOrder.setSubtotal(subtotalAmount);
					sessionOrder.setTotal(sessionOrder.getTotal() - orderDetail.getSubtotal());
				} else {
					sessionOrder.setSubtotal(0.0f);
					sessionOrder.setShippingFee(0.0f);
					sessionOrder.setTax(0.0f);
					sessionOrder.setTotal(0.0f);
				}
				
				session.setAttribute("pendingBookToOrder", true);
				iterator.remove();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/edit_order?id=" + sessionOrder.getOrderId());
		
	}

	public void updateOrder() throws ServletException, IOException {
		String firstname = CommonUtility.checkNull(request.getParameter("firstname"));
		String lastname = CommonUtility.checkNull(request.getParameter("lastname"));
		String phone = CommonUtility.checkNull(request.getParameter("phone"));
		String addressLine1 = CommonUtility.checkNull(request.getParameter("address1"));
		String addressLine2 = CommonUtility.checkNull(request.getParameter("address2"));
		String city = CommonUtility.checkNull(request.getParameter("city"));
		String state = CommonUtility.checkNull(request.getParameter("state"));
		String zipcode = CommonUtility.checkNull(request.getParameter("zipcode"));
		String country = CommonUtility.checkNull(request.getParameter("country"));
		String orderStatus = CommonUtility.checkNull(request.getParameter("orderStatus"));
		String paymentMethod = CommonUtility.checkNull(request.getParameter("paymentMethod"));
		
		Float shippingFee = Float.parseFloat(request.getParameter("shippingFee"));
		Float tax = Float.parseFloat(request.getParameter("tax"));
		
		String[] arrayBookId = request.getParameterValues("bookId");
		String[] arrayPrice = request.getParameterValues("price");
		String[] arrayQuantity = new String[arrayBookId.length];
		
		HttpSession session = request.getSession();
		BookOrder sessionOrder = (BookOrder) session.getAttribute("order");
		float totalAmount = 0.0f;
		
		sessionOrder.setFirstname(firstname);
		sessionOrder.setLastname(lastname);
		sessionOrder.setPhone(phone);
		sessionOrder.setAddressLine1(addressLine1);
		sessionOrder.setAddressLine2(addressLine2);
		sessionOrder.setCity(city);
		sessionOrder.setState(state);
		sessionOrder.setZipcode(zipcode);
		sessionOrder.setCountry(country);
		sessionOrder.setStatus(orderStatus);
		sessionOrder.setPaymentMethod(paymentMethod);
		
		sessionOrder.setShippingFee(shippingFee);
		sessionOrder.setTax(tax);
		
		for (int i = 0; i < arrayQuantity.length; i++) {
			arrayQuantity[i] = request.getParameter("quantity" + (i + 1));
		}
		
		Set<OrderDetail> orderDetails = sessionOrder.getOrderDetails();
		orderDetails.clear();
		
		for (int i = 0; i < arrayQuantity.length; i++) {
			int bookId = Integer.parseInt(arrayBookId[i]);
			int quantity = Integer.parseInt(arrayQuantity[i]);
			float price = Float.parseFloat(arrayPrice[i]);
			float subtotal = quantity * price;
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(new Book(bookId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			orderDetail.setBookOrder(sessionOrder);
			
			orderDetails.add(orderDetail);
			totalAmount += subtotal;
		}
		
		sessionOrder.setSubtotal(totalAmount);
		totalAmount += shippingFee;
		totalAmount += tax;
		sessionOrder.setTotal(totalAmount);
		
		orderDao.update(sessionOrder);
		session.removeAttribute("order");
		
		String message = "The order has been updated successfully";
		listAllOrders(message);
	}

	public void deleteOrder() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		
		String message = "The order ID " + orderId + " has been deleted successfully";
		BookOrder theOrder = orderDao.get(orderId);
		if (theOrder == null) {
			message = "Could not find order ID " + orderId + ", or it might have been deleted by another admin";
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
		orderDao.delete(orderId);
		listAllOrders(message);
	}
}
