<jsp:directive.include file="header.jsp" />
<div class="center">
	<table class="book">
		<tr>
			<td colspan="3" align="left">
				<p id="book_title">${book.title }</p> by <span id="author">${book.author }</span>
			</td>
		</tr>

		<tr>
			<td rowspan="2"><img
				src="data:image/jpg;base64,${book.base64Image }" class="book_large" />
			</td>
			<td valign="top" align="left">
				<jsp:directive.include file="book_rating.jsp" />&nbsp;&nbsp;
				<a href="#reviews">${fn:length(book.reviews) } Reviews</a>	
			</td>
			<td valign="top" rowspan="2" width="20%">
				<h2>$${book.price }</h2> <br /> <br />
				<button id="buttonAddToCart">Add to Cart</button>
			</td>
		</tr>

		<tr>
			<td id="description">${book.description }</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>
				<h2>
					<div id="reviews">Customer Reviews</div>
				</h2>
			</td>
			<td colspan="2" align="center">
				<button id="buttonWriteReview">Write a Customer Review</button>
			</td>
		</tr>

		<tr>
			<td colspan="3" align="left">
				<table class="normal">
					<c:forEach items="${book.reviews }" var="review">
						<tr>
							<td>
								<c:forTokens items="${review.ratingReview }" delims="," var="star">
									<c:if test="${star eq 'on' }">
										<img alt="" src="images/rating_on.png">
									</c:if>
	
									<c:if test="${star eq 'off' }">
										<img alt="" src="images/rating_off.png">
									</c:if>
								</c:forTokens>
								- <b>${review.headline }</b>
							</td>
						</tr>
						
						<tr>
							<td>
								by ${review.customer.fullname } on ${review.reviewTime }
							</td>
						</tr>
						
						<tr>
							<td>
								<i>${review.comment }</i>
							</td>
						</tr>
						
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>

	</table>
</div>
<jsp:directive.include file="footer.jsp" />
<script>
$(document).ready(function() {
	$("#buttonWriteReview").click(function() {
		window.location = "write_review?book_id=" + ${book.bookId};
	});
	
	$("#buttonAddToCart").click(function() {
		window.location = "add_to_cart?book_id=" + ${book.bookId};
	});
})
</script>