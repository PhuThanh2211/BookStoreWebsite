package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.HashGeneration;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserService {
	private UserDAO userDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private static final String ERROR_MESSAGE_PAGE = "message.jsp";
	private static final String USER_LIST_PAGE = "user_list.jsp";
	private static final String USER_FORM_PAGE = "user_form.jsp";
	
	public UserService(HttpServletRequest request, HttpServletResponse response) {
		userDao = new UserDAO();
		this.request = request;
		this.response = response;
	}
	
	public void listAllUsers() throws ServletException, IOException {
		listAllUsers(null);
	}
	
	public void listAllUsers(String message) throws ServletException, IOException {
		List<Users> listUsers = userDao.listAll();
		
		request.setAttribute("listUsers", listUsers);
		if (message != null) {
			request.setAttribute("message", message);
		}
		forwardToPage(USER_LIST_PAGE);	
	}
	
	public void createUser() throws ServletException, IOException {
		String fullName = request.getParameter("fullName").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		
		Users existedUser = userDao.findByEmail(email);
		if (existedUser != null) {
			String message = "Could not create user. A user with email " + email + " already existed";
			
			request.setAttribute("message", message);
			forwardToPage(ERROR_MESSAGE_PAGE);
			return;
			
		} else {
			String encryptedPassword = HashGeneration.generateMD5(password); 
			Users theUser = new Users(email, fullName, encryptedPassword);
			userDao.create(theUser);
			String message = "New user created successfully";
			
			listAllUsers(message);
		}
		
		
	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users theUser = userDao.get(userId);
		
		if (theUser != null) {
			request.setAttribute("user", theUser);
			forwardToPage(USER_FORM_PAGE);
		} else {
			String message = "Could not find user with ID " + userId;
			request.setAttribute("message", message);
			forwardToPage(ERROR_MESSAGE_PAGE);
			return;
		}
	}
	
	private void forwardToPage(String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String fullName = request.getParameter("fullName").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		
		Users userByEmail = userDao.findByEmail(email);
		Users userById = userDao.get(userId);
		
		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update user. User with email " + email + " already existed";
			request.setAttribute("message", message);
			forwardToPage(ERROR_MESSAGE_PAGE);
			return;
		} else {
			Users theUser = null;
			if (password.isEmpty()) {
				theUser = new Users(userId, email, fullName);
			} else {
				theUser = new Users(userId, email, fullName, password);
				theUser.setPassword(HashGeneration.generateMD5(password));
			}
			userDao.update(theUser);
			
			String message = "User has been updated successfully";
			listAllUsers(message);
		}
		
	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users theUser = userDao.get(userId);
		
		if (theUser != null && userId != 1) {
			userDao.delete(userId);
			String message = "User has been deleted successfully";
			listAllUsers(message);
		} else {
			String message = "Could not find user with ID " + userId;
			if (userId == 1) {
				message = "The default admin user account cannot be deleted";
			}
			
			request.setAttribute("message", message);
			forwardToPage(ERROR_MESSAGE_PAGE);
			return;
		}
		
	}

	public void checkLogin() throws ServletException, IOException {
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		
		String encryptedPassword = HashGeneration.generateMD5(password);
		boolean isLogin = userDao.checkLogin(email, encryptedPassword);
		if (isLogin) {
			String name = userDao.findByEmail(email).getFullName();
			request.getSession().setAttribute("username", name);
			
			response.sendRedirect(request.getContextPath() + "/admin/");
		} else {
			String message = "Login Failed!!!";
			request.setAttribute("message", message);
			
			forwardToPage("login.jsp");
		}
	}

	public void logout() throws ServletException, IOException {
		HttpSession sessionInfo = request.getSession();
		sessionInfo.removeAttribute("username");
		
		response.sendRedirect(request.getContextPath() + "/admin/login");
	}
	
}
