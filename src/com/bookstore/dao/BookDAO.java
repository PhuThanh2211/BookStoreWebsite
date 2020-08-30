package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
		
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object bookId) {
		return super.find(Book.class, bookId);
	}

	@Override
	public void delete(Object bookId) {
		super.delete(Book.class, bookId);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}
	
	public Book findByTitle(String title) {
		List<Book> listBooks = super.findWithNamedQuery("Book.findByTitle", "title", title);
		if (listBooks.size() > 0) {
			return listBooks.get(0);
		}
		
		return null;
	}
	
	public List<Book> findByCategory(int categoryId) {	
		return super.findWithNamedQuery("Book.findByCategory", "catId", categoryId);
	}
	
	public List<Book> findNewBooks() {
		return super.findWithNamedQuery("Book.findListNewBooks", 0, 4);
	}

	public List<Book> searchBook(String keyword) {
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
	}
	
	public long countByCategory(int categoryId) {
		return super.countWithNamedQuery("Book.countByCategory", "catId", categoryId);
	}
	
	public List<Book> listBestSellingBook() {
		return super.findWithNamedQuery("OrderDetail.bestSellingBook", 0, 4);
	}
	
	public List<Book> listFavoritedBook() {
		return super.findWithNamedQuery("Review.listFavoritedBook", 0, 4);
	}
}
