package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String[] arrayStringBookIds = request.getParameterValues("bookId");
		int[] bookQuantities = new int[arrayStringBookIds.length];
		
		for (int i = 1; i <= arrayStringBookIds.length; i++) {
			int quantity = Integer.parseInt(request.getParameter("quantity" + i));
			bookQuantities[i - 1] = quantity;
		}
		
		int[] bookIds = Arrays.stream(arrayStringBookIds).mapToInt(Integer::parseInt).toArray();
		
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		cart.updateCart(bookIds, bookQuantities);
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
