package com.bookstore.service;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;

public class CommonUtility {
	
	public static void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public static void forwardToPage(String page, String message, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("message", message);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public static void showMessageFrontEnd(String message, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		forwardToPage("frontend/message.jsp", message, request, response);
	}
	
	public static void showMessageBackEnd(String message, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		forwardToPage("message.jsp", message, request, response);
	}
	
	public static String checkNull(String value) {
		String result = value == null ? "" : value.trim();
		
		return result;
	}
	
	public static String html2Text(String html) {
		return Jsoup.parse(html).text();
	}
	
	public static void generateAllCountries(HttpServletRequest request) {
		String[] countryCodes = Locale.getISOCountries();
		
		Map<String, String> mapCountries = new TreeMap<>();
		
		for (String countryCode : countryCodes) {
			Locale locale = new Locale("", countryCode);
			String code = locale.getCountry();
			String name = locale.getDisplayCountry();
			
			mapCountries.put(name, code);
		}
		
		request.setAttribute("mapCountries", mapCountries);
	}
	
}
