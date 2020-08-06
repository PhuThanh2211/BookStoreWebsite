package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.service.CommonUtility;

@WebServlet("/remove_from_cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SHOPPING_CART_PAGE = "frontend/shopping_cart.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int bookId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("book_id")));
		
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		
		shoppingCart.removeItem(new Book(bookId));
		
		CommonUtility.forwardToPage(SHOPPING_CART_PAGE, request, response);
	}

}
