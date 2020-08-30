package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	public ReviewDAO() {
		
	}
	
	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}
	
	@Override
	public Review update(Review review) {
		review.setReviewTime(new Date());
		return super.update(review);
	}
	
	@Override
	public Review get(Object reviewId) {
		return super.find(Review.class, reviewId);
	}

	@Override
	public void delete(Object reviewId) {
		super.delete(Review.class, reviewId);
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countAll");
	}
	
	public long countReviewsByCustomer(int customerId) {
		return super.countWithNamedQuery("Review.countByCustomer", "customerId", customerId);
	}
	
	public long countReviewsByBook(int bookId) {
		return super.countWithNamedQuery("Review.countByBook", "bookId", bookId);
	}
	
	public Review findByCustomerAndBook(Integer customerId, Integer bookId) {
		Map<String, Object> mapParams = new HashMap<>();
		mapParams.put("customerId", customerId);
		mapParams.put("bookId", bookId);
		
		List<Review> result = super.findWithNamedQuery("Review.findByCustomerAndBook", mapParams);
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}
	
	public List<Review> listMostRecentReview() {
		return super.findWithNamedQuery("Review.findAll", 0, 3);
	}

}
