<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
	</div>
	
	<div align="center">
		<hr width="60%"/>
		<h2 class="pageheading">Quick Actions:</h2>
		<b>
			<a href="new_book">New Book</a> &nbsp;
			<a href="user_form.jsp">New User</a> &nbsp;
			<a href="category_form.jsp">New Category</a> &nbsp;
			<a href="customer_form.jsp">New Customer</a>
		</b>
	</div>
	
	<div align="center">
		<hr width="60%"/>
		<h2 class="pageheading">Recent Sales:</h2>
		<table border="1" cellpadding="10">
			<tr>
				<th>Order ID</th>
				<th>Ordered By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
			</tr>
			
			<c:forEach var="order" items="${listRecentOrders }" varStatus="theCount">
				<tr>
					<td align="center">
						<a href="view_order?id=${order.orderId }">${order.orderId }</a>
					</td>
					<td>${order.customer.fullname }</td>
					<td align="center">${order.bookCopies }</td>
					<td><fmt:formatNumber value="${order.total }" type="currency" /></td>
					<td>${order.paymentMethod }</td>
					<td>${order.status }</td>
					<td>${order.orderDate }</td>
				</tr>
			</c:forEach>
		</table>
		<br/>
	</div>
	
	<div align="center">
		<hr width="60%"/>
		<h2 class="pageheading">Recent Review:</h2>
		<table border="1" cellpadding="10">
			<tr>
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
			</tr>
			
			<c:forEach var="review" items="${listRecentReviews }" varStatus="theCount">
				<tr>
					<td>${review.book.title }</td>
					<td align="center">${review.rating }</td>
					<td>
						<a href="edit_review?id=${review.reviewId }">${review.headline }</a>
					</td>
					<td>${review.customer.fullname }</td>
					<td>${review.reviewTime }</td>
				</tr>
			</c:forEach>
		</table>
		<br/>
	</div>
	
	<div align="center">
		<hr width="60%"/>
		<h2 class="pageheading">Statistics:</h2>
		<b>
			Total Users: ${totalUsers } &nbsp;&nbsp;&nbsp;&nbsp;
			Total Books: ${totalBooks } &nbsp;&nbsp;&nbsp;&nbsp;
			Total Customers: ${totalCustomers } &nbsp;&nbsp;&nbsp;&nbsp;
			Total Reviews: ${totalReviews } &nbsp;&nbsp;&nbsp;&nbsp;
			Total Orders: ${totalOrders }
		</b>
		<hr width="60%"/>
	</div>
<jsp:directive.include file="footer.jsp" />