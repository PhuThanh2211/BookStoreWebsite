package com.bookstore.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {

	private static ReviewDAO reviewDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(3);
		
		Customer customer = new Customer();
		customer.setCustomerId(5);
		
		review.setBook(book);
		review.setCustomer(customer);
		review.setRating(4);
		review.setHeadline("Deep dive in Java Framework");
		review.setComment("You will understand advantages and drawbacks of Spring through this book!!!");
		
		Review newReview = reviewDao.create(review);
		
		assertNotNull(newReview.getReviewId());
	}
	
	@Test
	public void testGetReview() {
		Review theReview = reviewDao.get(1);
		
		assertTrue(theReview != null);
	}
	
	@Test
	public void testUpdateReview() {
		int reviewId = 2;
		Review theReview = reviewDao.get(reviewId);
		
		theReview.setRating(4);
		Review updatedReview = reviewDao.update(theReview);
		
		assertEquals(updatedReview.getRating(), 4);
	}
	
	@Test
	public void testDeleteReview() {
		int reviewId = 1;
		reviewDao.delete(reviewId);
		
		Review theReview = reviewDao.get(reviewId);
		assertNull(theReview);
	}
	
	@Test
	public void testListAllReviews() {
		List<Review> listReviews = reviewDao.listAll();
		
		assertTrue(listReviews.size() == 1);
	}

	@Test
	public void testCountAllReviews() {
		long countReviews = reviewDao.count();
		
		assertEquals(countReviews, 1);
	}
	
	@Test
	public void testCountByCustomer() {
		int customerId = 7;
		
		long countReviews = reviewDao.countReviewsByCustomer(customerId);
		
		assertEquals(countReviews, 2);
	}
	
	@Test
	public void findByCustomerAndBook() {
		int customerId = 2;
		int bookId = 3;
		
		Review theReview = reviewDao.findByCustomerAndBook(customerId, bookId);
		
		assertNotNull(theReview);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

}
