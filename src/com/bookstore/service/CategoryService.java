package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryService {
	private EntityManager entityManager;
	private CategoryDAO categoryDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private static final String ERROR_MESSAGE_PAGE = "message.jsp";
	private static final String CATEGORY_LIST_PAGE = "category_list.jsp";
	private static final String CATEGORY_FORM_PAGE = "category_form.jsp";
	
	public CategoryService(EntityManager entityManager,
			HttpServletRequest request, HttpServletResponse response) {
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		
		categoryDao = new CategoryDAO(entityManager);
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
		
		forwardToPage(CATEGORY_LIST_PAGE);
		
	}
	
	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name").trim();
		
		Category categoryByName = categoryDao.findByName(name);
		if (categoryByName != null) {
			String message = "Could not create category. A category with name " + name + " already existed";
			request.setAttribute("message", message);
			forwardToPage(ERROR_MESSAGE_PAGE);
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
			forwardToPage(CATEGORY_FORM_PAGE);
		} else {
			String message = "Could not find category with ID " + categoryId;
			request.setAttribute("message", message);
			forwardToPage(ERROR_MESSAGE_PAGE);
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
			request.setAttribute("message", message);
			forwardToPage(ERROR_MESSAGE_PAGE);
			return;
		} else {
			categoryById.setName(name);
			categoryDao.update(categoryById);
			
			String message = "Category has been updated successfully";
			listAllCategories(message);
		}
		
	}
	
	private void forwardToPage(String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category deletedCategory = categoryDao.get(categoryId);
		
		if (deletedCategory != null) {
			categoryDao.delete(categoryId);
			String message = "The category has been deleted successfully";
			listAllCategories(message);
		} else {
			String message = "Could not find category with ID " + categoryId + ", or it might have been deleted";
			request.setAttribute("message", message);
			forwardToPage(ERROR_MESSAGE_PAGE);
			return;
		}
		
	}
}
