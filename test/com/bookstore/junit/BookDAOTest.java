package com.bookstore.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest {
	
	private static BookDAO bookDao;
	private static CategoryDAO categoryDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDao = new BookDAO(entityManager);
		categoryDao = new CategoryDAO(entityManager);
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		Category category = categoryDao.get(1);
		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("Are you looking for a deeper understanding of the Java™ programming language");
		newBook.setIsbn("0321356683");
		newBook.setPrice(38.87f);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);
		newBook.setCategory(category);
		
		String imagePath = "C:\\Phu_Thanh\\Personal\\Learning\\Java\\dummy-data-books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book theBook = bookDao.create(newBook);
		
		assertTrue(theBook != null);
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existedBook = new Book();
		existedBook.setBookId(1);
		Category category = categoryDao.get(1);
		existedBook.setTitle("Effective Java (2nd Edition)");
		existedBook.setAuthor("Joshua Bloch");
		existedBook.setDescription("Are you looking for a deeper understanding of the Java™ programming language");
		existedBook.setIsbn("0321356683");
		existedBook.setPrice(40.99f);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		existedBook.setPublishDate(publishDate);
		existedBook.setCategory(category);
		
		String imagePath = "C:\\Phu_Thanh\\Personal\\Learning\\Java\\dummy-data-books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existedBook.setImage(imageBytes);
		
		Book theBook = bookDao.update(existedBook);
		
		assertEquals(existedBook.getTitle(), theBook.getTitle());
	}
	
	@Test
	public void testDeleteBookSuccess() {
		int bookId = 1;
		bookDao.delete(bookId);
		
		assertTrue(true);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		int bookId = 100;
		bookDao.delete(bookId);
		
		assertTrue(true);
	}
	
	@Test
	public void testGetBookFail() {
		int bookId = 100;
		Book theBook = bookDao.get(bookId);
		
		assertNull(theBook);
	}
	
	@Test
	public void testGetBookSuccess() {
		int bookId = 2;
		Book theBook = bookDao.get(bookId);
		
		assertNotNull(theBook);
	}
	
	@Test
	public void testListBooks() {
		List<Book> listBooks = bookDao.listAll();
		
		assertTrue(!listBooks.isEmpty());
	}
	
	@Test
	public void testCountBook() {
		long count = bookDao.count();
		List<Book> listBooks = bookDao.listAll();
		
		assertEquals(count, listBooks.size());
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking in Java";
		Book theBook = bookDao.findByTitle(title);
		
		assertNull(theBook);
	}
	
	@Test
	public void testFindByTitleExist() {
		String title = "Effective Java (2nd Edition)";
		Book theBook = bookDao.findByTitle(title);
		
		assertEquals(theBook.getAuthor(), "Joshua Bloch");
	}
	
	@Test
	public void testFindByCategory() {
		int categoryId = 1;
		List<Book> listBooks = bookDao.findByCategory(categoryId);
		
		assertTrue(listBooks.size() > 0);
	}
	
	@Test
	public void findNewBooks() {
		List<Book> listNewBooks = bookDao.findNewBooks();
		
		assertEquals(listNewBooks.size(), 4);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
}
