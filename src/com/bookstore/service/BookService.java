package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDao;
	private CategoryDAO categoryDao;
	
	private static final String BOOK_LIST_PAGE = "book_list.jsp";
	private static final String BOOK_FORM_PAGE = "book_form.jsp";
	private static final String MESSAGE_PAGE = "message.jsp";
	private static final String BOOK_LIST_BY_CATEGORY_PAGE = "frontend/books_list_by_category.jsp";
	private static final String BOOK_DETAIL_PAGE = "frontend/book_detail.jsp";
	
	public BookService(EntityManager entityManager, 
			HttpServletRequest request, HttpServletResponse response) {
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		this.bookDao = new BookDAO(entityManager);
		this.categoryDao = new CategoryDAO(entityManager);
	}
	
	public void listAllBooks() throws ServletException, IOException {
		listAllBooks(null);
	}
	
	public void listAllBooks(String message) throws ServletException, IOException {
		List<Book> listBooks = bookDao.listAll();
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listBooks", listBooks);
		forwardToPage(BOOK_LIST_PAGE);
	}
	
	private void forwardToPage(String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void showBookForm() throws ServletException, IOException {
		List<Category> listCategories = categoryDao.listAll();
		
		request.setAttribute("listCategories", listCategories);
		forwardToPage(BOOK_FORM_PAGE);
	}

	public void createBook() throws ServletException, IOException {
		String title = request.getParameter("title").trim();
		
		Book bookByTitle = bookDao.findByTitle(title);
		if (bookByTitle != null) {
			String message = "Could not create new book because the title " + title + " already exists";
			request.setAttribute("message", message);
			forwardToPage(MESSAGE_PAGE);
			return;
		}
		
		Book newBook = new Book();
		readBookFields(newBook);
		Book theBook = bookDao.create(newBook);
		if (theBook.getBookId() > 0) {
			String message = "New book created successfully";
			listAllBooks(message);
		}
		
	}

	public void editBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id").trim());
		Book theBook = bookDao.get(bookId);
		if (theBook == null) {
			String message = "Could not find book with ID " + bookId;
			request.setAttribute("message", message);
			forwardToPage(MESSAGE_PAGE);
			return;
		}
		
		List<Category> listCategories = categoryDao.listAll();
		request.setAttribute("book", theBook);
		request.setAttribute("listCategories", listCategories);
		
		forwardToPage(BOOK_FORM_PAGE);
		
	}

	public void updateBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		
		Book existBook = bookDao.get(bookId);
		Book bookByTitle = bookDao.findByTitle(title);
		if (bookByTitle != null && !bookByTitle.getTitle().equals(existBook.getTitle())) {
			String message = "Could not update book because there's another book having same title";
			request.setAttribute("message", message);
			forwardToPage(MESSAGE_PAGE);
			return;
		}
		
		readBookFields(existBook);
		bookDao.update(existBook);
		String message = "Book has been updated successfully";
		listAllBooks(message);
	}
	
	private void readBookFields(Book book) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title").trim();
		String author = request.getParameter("author").trim();
		String isbn = request.getParameter("isbn").trim();
		String description = request.getParameter("description").trim();
		float price = Float.parseFloat(request.getParameter("price"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
			
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy");
		}
		
		Category category = categoryDao.get(categoryId);
		Part part = request.getPart("bookImage");
		
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageByte = new byte[(int) size];
			
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageByte);
			inputStream.close();
			book.setImage(imageByte);
		}
		
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setPublishDate(publishDate);
		book.setCategory(category);
	}

	public void deleteBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book existBook = bookDao.get(bookId);
		if (existBook == null) {
			String message = "Could not find book with ID " + bookId + ", or it might have been deleted";
			request.setAttribute("message", message);
			forwardToPage(MESSAGE_PAGE);
			return;
		}
		
		bookDao.delete(bookId);
		String message = "The book deleted successfully";
		listAllBooks(message);
	}

	public void listBooksByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		String categoryName = request.getParameter("name").trim();
		Category existCategory = categoryDao.get(categoryId);
		if (existCategory == null) {
			String message = "Sorry, the category " + categoryName + " is not available";
			request.setAttribute("message", message);
			forwardToPage("frontend/" + MESSAGE_PAGE);
			return;
		}
		
		List<Book> listBooks = bookDao.findByCategory(categoryId);
		
		request.setAttribute("listBooks", listBooks);
		forwardToPage(BOOK_LIST_BY_CATEGORY_PAGE);
	}

	public void viewBookDetail() {
		
		
	}
}