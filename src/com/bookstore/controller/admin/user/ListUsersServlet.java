package com.bookstore.controller.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.UserService;

@WebServlet("/admin/list_users")
public class ListUsersServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public ListUsersServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		UserService userService = new UserService(entityManager, request, response);
		userService.listAllUsers();
	}

}
