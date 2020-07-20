package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDao;
	private CategoryDAO categoryDao;
	
	private static final String BOOK_LIST_PAGE = "book_list.jsp";
	private static final String BOOK_FORM_PAGE = "book_form.jsp";
	private static final String BOOK_LIST_BY_CATEGORY_PAGE = "frontend/books_list_by_category.jsp";
	private static final String BOOK_DETAIL_PAGE = "frontend/book_detail.jsp";
	private static final String SEARCH_RESULT_PAGE = "frontend/search_result.jsp";
	
	public BookService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.bookDao = new BookDAO();
		this.categoryDao = new CategoryDAO();
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
		CommonUtility.forwardToPage(BOOK_LIST_PAGE, request, response);
	}

	public void showBookForm() throws ServletException, IOException {
		List<Category> listCategories = categoryDao.listAll();
		
		request.setAttribute("listCategories", listCategories);
		CommonUtility.forwardToPage(BOOK_FORM_PAGE, request, response);
	}

	public void createBook() throws ServletException, IOException {
		String title = request.getParameter("title").trim();
		
		Book bookByTitle = bookDao.findByTitle(title);
		if (bookByTitle != null) {
			String message = "Could not create new book because the title " + title + " already exists";
			CommonUtility.showMessageBackEnd(message, request, response);
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
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
		List<Category> listCategories = categoryDao.listAll();
		request.setAttribute("book", theBook);
		request.setAttribute("listCategories", listCategories);
		
		CommonUtility.forwardToPage(BOOK_FORM_PAGE, request, response);
		
	}

	public void updateBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		
		Book existBook = bookDao.get(bookId);
		Book bookByTitle = bookDao.findByTitle(title);
		if (bookByTitle != null && !bookByTitle.getTitle().equals(existBook.getTitle())) {
			String message = "Could not update book because there's another book having same title";
			CommonUtility.showMessageBackEnd(message, request, response);
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
			CommonUtility.showMessageBackEnd(message, request, response);
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
			CommonUtility.showMessageFrontEnd(message, request, response);
			return;
		}
		
		List<Book> listBooks = bookDao.findByCategory(categoryId);
		
		request.setAttribute("category", existCategory);
		request.setAttribute("listBooks", listBooks);
		CommonUtility.forwardToPage(BOOK_LIST_BY_CATEGORY_PAGE, request, response);
	}

	public void viewBookDetail() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		String bookTitle = request.getParameter("name").trim();
		Book existedBook = bookDao.get(bookId);
		if (existedBook == null) {
			String message = "Sorry, the book " + bookTitle + " is not available.";
			CommonUtility.showMessageFrontEnd(message, request, response);
			return;
		}
		
		request.setAttribute("book", existedBook);
		CommonUtility.forwardToPage(BOOK_DETAIL_PAGE, request, response);
	}

	public void searchBook() throws ServletException, IOException {
		String keyword = request.getParameter("keyword").trim();
		List<Book> listSearchedBook = null;
		
		if (keyword.equals("")) {
			listSearchedBook = bookDao.listAll();
		} else {
			listSearchedBook = bookDao.searchBook(keyword);
		}
		
		request.setAttribute("listBooks", listSearchedBook);
		request.setAttribute("keyword", keyword);
		CommonUtility.forwardToPage(SEARCH_RESULT_PAGE, request, response);
	}
}
