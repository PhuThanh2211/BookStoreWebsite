package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewService {
	private ReviewDAO reviewDao;
	private BookDAO bookDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private static final String REVIEW_LIST_PAGE = "review_list.jsp";
	private static final String REVIEW_FORM_PAGE = "review_form.jsp";
	private static final String REVIEW_DONE_PAGE = "frontend/review_done.jsp";
	private static final String REVIEW_INFO_PAGE = "frontend/review_info.jsp";
	
	public ReviewService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		bookDao = new BookDAO();
		reviewDao = new ReviewDAO();
	}
	
	public void listAllReviews() throws ServletException, IOException {
		listAllReviews(null);
	}
	
	public void listAllReviews(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDao.listAll();
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listReviews", listReviews);
		CommonUtility.forwardToPage(REVIEW_LIST_PAGE, request, response);
	}

	public void editReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("id")));
		Review theReview = reviewDao.get(reviewId);
		
		if (theReview == null) {
			String message = "Could not find review with ID " + reviewId;
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
		request.setAttribute("review", theReview);
		CommonUtility.forwardToPage(REVIEW_FORM_PAGE, request, response);
	}

	public void updateReview() throws ServletException, IOException {
		boolean isDataChange = false;
		int reviewId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("reviewId")));
		String headline = CommonUtility.checkNull(request.getParameter("headline"));
		String comment = CommonUtility.checkNull(request.getParameter("comment"));
		String message = "The review has been updated successfully";
		
		Review existedReview = reviewDao.get(reviewId);
		if (!headline.equals(existedReview.getHeadline())) {
			existedReview.setHeadline(headline);
			isDataChange = true;
		}
		
		if (!comment.equals(existedReview.getComment())) {
			existedReview.setComment(comment);
			isDataChange = true;
		}
		
		if (isDataChange) {
			reviewDao.update(existedReview);
		}
		
		listAllReviews(message);
 	}

	public void deleteReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("id")));
		Review theReview = reviewDao.get(reviewId);
		String message = "The review has been deleted successfully";
		
		if (theReview == null) {
			message = "Could you find review with ID " + reviewId + ", or it might have been deleted by another admin";
			CommonUtility.showMessageBackEnd(message, request, response);
			return;
		}
		
		reviewDao.delete(reviewId);
		listAllReviews(message);
	}

	public void showReviewForm() throws ServletException, IOException {
		int bookId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("book_id")));
		Book theBook = bookDao.get(bookId);
		
		HttpSession session = request.getSession();
		session.setAttribute("book", theBook);
		
		Customer loggedCustomer = (Customer) session.getAttribute("loggedCustomer");
		Review existedReview = reviewDao.findByCustomerAndBook(loggedCustomer.getCustomerId(), bookId);
		
		if (existedReview != null) {
			request.setAttribute("review", existedReview);
			CommonUtility.forwardToPage(REVIEW_INFO_PAGE, request, response);
		} else {
			CommonUtility.forwardToPage("frontend/" + REVIEW_FORM_PAGE, request, response);
		}
		
		
	}

	public void submitReview() throws ServletException, IOException {
		int bookId = Integer.parseInt(CommonUtility.checkNull(request.getParameter("bookId")));
		int rating = Integer.parseInt(CommonUtility.checkNull(request.getParameter("rating")));
		String headline = CommonUtility.checkNull(request.getParameter("headline"));
		String comment = CommonUtility.checkNull(request.getParameter("comment"));
		
		Book theBook = bookDao.get(bookId);
		Customer loggedCustomer = (Customer) request.getSession().getAttribute("loggedCustomer");
		
		Review review = new Review();
		review.setBook(theBook);
		review.setCustomer(loggedCustomer);
		review.setRating(rating);
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDao.create(review);
		String message = "Your review has been posted. Thank you!!!";
		
		request.setAttribute("message", message);
		CommonUtility.forwardToPage(REVIEW_DONE_PAGE, message, request, response);
	}
}
