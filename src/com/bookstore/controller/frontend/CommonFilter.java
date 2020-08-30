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
import com.bookstore.service.CommonUtility;

/**
 * Servlet Filter implementation class CommonFilter
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	private static final String[] loginRequiredURLs = {
			"/view_profile", "/edit_profile", "/write_review", "/checkout", "/list_orders", "/view_order"
	};
	
	private static final String LOGIN_PAGE = "/frontend/login.jsp";
	
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
		
		String requestURL = httpRequest.getRequestURL().toString();
		
		Boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;
		
		if ((!loggedIn && isRequiredLogin(requestURL)) || endPartURI.startsWith("/frontend") ) {
			String parametersGet = httpRequest.getQueryString() != null ? "?" + httpRequest.getQueryString() : "";
			
			session.setAttribute("redirectURL", requestURL.concat(parametersGet));
			CommonUtility.forwardToPage(LOGIN_PAGE, httpRequest, httpResponse);
		} else {
			if (!endPartURI.startsWith("/admin") && !endPartURI.startsWith("/js") && !endPartURI.startsWith("/css")) {
				List<Category> listCategories = categoryDao.listAll();
				request.setAttribute("listCategories", listCategories);
			}
			chain.doFilter(request, response);
		}
	}
	
	private boolean isRequiredLogin(String requestURL) {
		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL) && !requestURL.contains("/admin")) {
				return true;
			}
		}
		
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
