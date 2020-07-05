package com.bookstore.controller.admin.book;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.BookService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/update_book")
@MultipartConfig(
		// The size threshold after which the file will be written to disk
		fileSizeThreshold = 1024 * 10, // 10 KB
		
		// The maximum size allowed for uploaded files
		maxFileSize = 1024 * 300, // 300 KB
		
		// The maximum size allowed for multipart/form-data requests
		maxRequestSize = 1024 * 1024 // 1 MB
)
public class UpdateBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public UpdateBookServlet() {
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		BookService bookService = new BookService(entityManager, request, response);
		bookService.updateBook();
	}

}
