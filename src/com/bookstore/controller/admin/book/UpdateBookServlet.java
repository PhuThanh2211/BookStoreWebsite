package com.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookService;

@WebServlet("/admin/update_book")
@MultipartConfig(
		// The size threshold after which the file will be written to disk
		fileSizeThreshold = 1024 * 10, // 10 KB
		
		// The maximum size allowed for uploaded files
		maxFileSize = 1024 * 300, // 300 KB
		
		// The maximum size allowed for multipart/form-data requests
		maxRequestSize = 1024 * 1024 // 1 MB
)
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateBookServlet() {
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		BookService bookService = new BookService(request, response);
		bookService.updateBook();
	}

}
