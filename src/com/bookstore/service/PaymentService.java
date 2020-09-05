package com.bookstore.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentService {
	
	private static final String PAYMENT_REVIEW_PAGE = "payment_review.jsp";
	private static final String CLIENT_ID = "AenGZ5lJBPlOhjpB-g8GCZ2g4mNI2QuEqd9-6MAkiShwtE58LeDU_wZSfNT1Og1vmE92USLD_7Q21ZaS";
	private static final String CLIENT_SECRET = "EDRTqYAt5-oL5e026_16j83_5Z9FaSOPwE9tGzHoDcy2Mohf8ER8zXb2JO4sriwp-PH-Fi8qGb60CDHm";
	private String mode = "sandbox";
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public PaymentService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void authorizePayment(BookOrder order) throws ServletException, IOException {
		// Get payer information
		Payer payer = getPayerInformation(order);
		
		// Get redirect URLs
		RedirectUrls redirectURLs = getRedirectURLs();
		
		// Get transaction details
		List<Transaction> transactions = getTransactionInformation(order);
		
		// Request Payment
		Payment requestPayment = new Payment();
		requestPayment.setPayer(payer)
						.setRedirectUrls(redirectURLs)
						.setIntent("authorize")
						.setTransactions(transactions);
		
		System.out.println("===== REQUEST PAYMENT: =====");
		System.out.println(requestPayment);
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);
		
		try {
			Payment authorizedPayment = requestPayment.create(apiContext);
			
			System.out.println("===== AUTHORIZED PAYMENT: =====");
			System.out.println(authorizedPayment);
			
			String approvalURL = getApprovalURL(authorizedPayment);
			
			if (approvalURL != null && !approvalURL.isEmpty()) {
				response.sendRedirect(approvalURL);
			}
			
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			throw new ServletException("Error in authorizing payment.");
		}
		
		// Get approval link
		
		// Redirect to Paypal's payment page
	}
	
	private String getApprovalURL(Payment authorizedPayment) {
		String approvalURL = null;
		
		List<Links> listLinks = authorizedPayment.getLinks();
		
		for (Links link : listLinks) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalURL = link.getHref();
				break;
			}
		}
		
		return approvalURL;
	}
	
	private List<Transaction> getTransactionInformation(BookOrder order) {
		Transaction transaction = new Transaction();
		transaction.setDescription("Book orderd on Evergreen Books");
		
		// Get amount details
		Amount amount = getAmountDetails(order);
		transaction.setAmount(amount);
		
		ItemList itemList = new ItemList();
		
		// Shipping address (Recipient Info)
		ShippingAddress shippingAddress = getRecipientInformation(order);
		itemList.setShippingAddress(shippingAddress);
		
		List<Item> paypalItems = new ArrayList<>();
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			Book book = orderDetail.getBook();
			int quantity = orderDetail.getQuantity();
			
			Item paypalItem = new Item();
			paypalItem.setCurrency("USD")
						.setName(book.getTitle())
						.setQuantity(String.valueOf(quantity))
						.setPrice(String.format("%.2f" ,book.getPrice()));
			
			paypalItems.add(paypalItem);
		}
		
		itemList.setItems(paypalItems);
		transaction.setItemList(itemList);
		
		List<Transaction> listTransaction = new ArrayList<>();
		listTransaction.add(transaction);
		
		return listTransaction;
	}
	
	private ShippingAddress getRecipientInformation(BookOrder order) {
		ShippingAddress shippingAddress = new ShippingAddress();
		String recipientName = order.getFirstname() + " " + order.getLastname();
		shippingAddress.setRecipientName(recipientName)
						.setPhone(order.getPhone())
						.setLine1(order.getAddressLine1())
						.setLine2(order.getAddressLine2())
						.setCity(order.getCity())
						.setState(order.getState())
						.setCountryCode(order.getCountry())
						.setPostalCode(order.getZipcode());
		
		return shippingAddress;
	}
	
	private Payer getPayerInformation(BookOrder order) {
		Payer payer = new Payer();
		payer.setPaymentMethod("Paypal");
		
		Customer theCustomer = order.getCustomer();
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName(theCustomer.getFirstname());
		payerInfo.setLastName(theCustomer.getLastname());
		payerInfo.setEmail(theCustomer.getEmail());
		
		payer.setPayerInfo(payerInfo);
		
		return payer;
	}
	
	private RedirectUrls getRedirectURLs() {
		String requestURL = request.getRequestURL().toString();
		String requestURI = request.getRequestURI();
		String baseURL = requestURL.replace(requestURI, "").concat(request.getContextPath());
		
		RedirectUrls redirectURLs = new RedirectUrls();
		String cancelURL = baseURL.concat("/view_cart");
		String returnURL = baseURL.concat("/review_payment");
		
		redirectURLs.setCancelUrl(cancelURL);
		redirectURLs.setReturnUrl(returnURL);
		
		return redirectURLs;
	}
	
	private Amount getAmountDetails(BookOrder order) {
		Details details = new Details();
		details.setShipping(String.format("%.2f" ,order.getShippingFee()));
		details.setTax(String.format("%.2f" ,order.getTax()));
		details.setSubtotal(String.format("%.2f" ,order.getSubtotal()));
		
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setDetails(details);
		
		amount.setTotal(String.format("%.2f" ,order.getTotal()));
		
		return amount;
	}

	public void reviewPayment() throws ServletException {
		String paymentId = CommonUtility.checkNull(request.getParameter("paymentId"));
		String payerId = CommonUtility.checkNull(request.getParameter("PayerID"));
		
		if (paymentId.isEmpty() || payerId.isEmpty()) {
			throw new ServletException("Error in display payment review.");
		}
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);
		
		try {
			Payment payment = Payment.get(apiContext, paymentId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			
			request.setAttribute("payer", payerInfo);
			request.setAttribute("transaction", transaction);
			request.setAttribute("shippingAddress", shippingAddress);
			CommonUtility.forwardToPage(PAYMENT_REVIEW_PAGE, request, response);
			
		} catch (PayPalRESTException | IOException e) {
			e.printStackTrace();
			throw new ServletException("Error in getting payment details from Paypal.");
		}
	}
}
