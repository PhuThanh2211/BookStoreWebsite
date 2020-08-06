package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.service.CommonUtility;

@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int bookId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("book_id")));
		
		Object objCart = request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart = null;
		
		if (objCart != null && objCart instanceof ShoppingCart) {
			shoppingCart = (ShoppingCart) objCart;
		} else {
			shoppingCart = new ShoppingCart();
		}
		
		BookDAO bookDao = new BookDAO();
		Book theBook = bookDao.get(bookId);
		
		shoppingCart.addItem(theBook);
		request.getSession().setAttribute("cart", shoppingCart);
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
