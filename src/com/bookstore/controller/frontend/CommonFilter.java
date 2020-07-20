package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

/**
 * Servlet Filter implementation class CommonFilter
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	private CategoryDAO categoryDao;
	
    public CommonFilter() {
        categoryDao = new CategoryDAO();
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		String endPartURI = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		Boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;
		
		if ((!loggedIn && endPartURI.startsWith("/view_profile")) || endPartURI.startsWith("/frontend") ) {
			httpResponse.sendRedirect(httpRequest.getContextPath());
		} else {
			if (!endPartURI.startsWith("/admin") && !endPartURI.startsWith("/js") && !endPartURI.startsWith("/css")) {
				List<Category> listCategories = categoryDao.listAll();
				request.setAttribute("listCategories", listCategories);
			}
			chain.doFilter(request, response);
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
