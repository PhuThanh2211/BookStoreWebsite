package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

@WebServlet("")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeServlet() {
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String homepage = "frontend/index.jsp";
		CategoryDAO categoryDao = new CategoryDAO(entityManager);
		BookDAO bookDao = new BookDAO(entityManager);
		List<Category> listCategories = categoryDao.listAll();
		List<Book> listNewBooks = bookDao.findNewBooks();
		
		request.setAttribute("listCategories", listCategories);
		request.setAttribute("listNewBooks", listNewBooks);
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
