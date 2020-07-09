package com.bookstore.controller.admin.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CategoryService;

@WebServlet("/admin/list_categories")
public class ListCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListCategoryServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		CategoryService categoryService = new CategoryService(request, response);
		categoryService.listAllCategories();
	}

}
