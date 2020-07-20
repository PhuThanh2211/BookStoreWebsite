package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryService {
	private CategoryDAO categoryDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private static final String CATEGORY_LIST_PAGE = "category_list.jsp";
	private static final String CATEGORY_FORM_PAGE = "category_form.jsp";
	
	public CategoryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		categoryDao = new CategoryDAO();
	}
	
	public void listAllCategories() throws ServletException, IOException {
		listAllCategories(null);
	}
	
	public void listAllCategories(String message) throws ServletException, IOException {
		List<Category> listCategories = categoryDao.listAll();
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listCategories", listCategories);
		
		CommonUtility.forwardToPage(CATEGORY_LIST_PAGE, request, response);
		
	}
	
	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name").trim();
		
		Category categoryByName = categoryDao.findByName(name);
		if (categoryByName != null) {
			String message = "Could not create category. A category with name " + name + " already existed";
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		} else {
			Category category = new Category(name);
			categoryDao.create(category);
			String message = "New category created successfully";
			listAllCategories(message);
		}
	}
	
	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category theCategory = categoryDao.get(categoryId);
		
		if (theCategory != null) {
			request.setAttribute("category", theCategory);
			CommonUtility.forwardToPage(CATEGORY_FORM_PAGE, request, response);
		} else {
			String message = "Could not find category with ID " + categoryId;
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
		
	}
	
	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("name").trim();
		
		Category categoryByName = categoryDao.findByName(name);
		Category categoryById = categoryDao.get(categoryId);
		
		if (categoryByName != null && categoryByName.getCategoryId() != categoryById.getCategoryId()) {
			String message = "Could not upadte category. A category with name " + name + " already existed";
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		} else {
			categoryById.setName(name);
			categoryDao.update(categoryById);
			
			String message = "Category has been updated successfully";
			listAllCategories(message);
		}
		
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category deletedCategory = categoryDao.get(categoryId);
		
		BookDAO bookDao = new BookDAO();
		long numOfBooks = bookDao.countByCategory(categoryId);
		String message = "The category has been deleted successfully";
		
		if (deletedCategory != null) {
			if (numOfBooks > 0) {
				message = "Could not delete the category " + deletedCategory.getName() + " because it contains some books";
			} else {
				categoryDao.delete(categoryId);
			}
			
			listAllCategories(message);
			
		} else {
			message = "Could not find category with ID " + categoryId + ", or it might have been deleted";
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
	}
}
