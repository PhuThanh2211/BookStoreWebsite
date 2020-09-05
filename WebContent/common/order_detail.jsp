<table>
	<tr>
		<td>Ordered By:</td>
		<td>${order.customer.fullname }</td>
	</tr>
	
	<tr>
		<td>Order Status:</td>
		<td>${order.status }</td>
	</tr>
	
	<tr>
		<td>Order Date:</td>
		<td>${order.orderDate }</td>
	</tr>
	
	<tr>
		<td>Payment Method:</td>
		<td>${order.paymentMethod }</td>
	</tr>
	
	<tr>
		<td>Book Copies:</td>
		<td>${order.bookCopies }</td>
	</tr>
	
	<tr>
		<td>Total Amount:</td>
		<td><fmt:formatNumber value="${order.total }" type="currency" /></td>
	</tr>
</table>	
	
<h2 class="pageheading">Recipient Information</h2>
<table>
	<tr>
		<td>First Name:</td>
		<td>${order.firstname }</td>
	</tr>
	
	<tr>
		<td>Last Name:</td>
		<td>${order.lastname }</td>
	</tr>
	
	<tr>
		<td>Phone:</td>
		<td>${order.phone }</td>
	</tr>
	
	<tr>
		<td>Address Line 1:</td>
		<td>${order.addressLine1 }</td>
	</tr>
	
	<tr>
		<td>Address Line 2:</td>
		<td>${order.addressLine2 }</td>
	</tr>
	
	<tr>
		<td>City:</td>
		<td>${order.city }</td>
	</tr>
	
	<tr>
		<td>Status:</td>
		<td>${order.status }</td>
	</tr>
	
	<tr>
		<td>Country:</td>
		<td>${order.countryName }</td>
	</tr>
	
	<tr>
		<td>Zipcode:</td>
		<td>${order.zipcode }</td>
	</tr>
</table>

<h2 class="pageheading">Ordered Books:</h2>
<table border="1">
	<tr>
		<th>No</th>
		<th>Book</th>
		<th>Author</th>
		<th>Price</th>
		<th>Quantity</th>
		<th>Sub Total</th>
	</tr>
	
	<c:forEach var="orderDetail" items="${order.orderDetails }" varStatus="theCount">
	<tr>
		<td align="center">${theCount.index + 1 }</td>
		<td>
			<img style="vertical-align: middle" src="data:image/jpg;base64,${orderDetail.book.base64Image }" class="book_small" />
			&nbsp;&nbsp;&nbsp;
			<span id="book_title">${orderDetail.book.title }</span>
		</td>
		<td>${orderDetail.book.author }</td>
		<td><fmt:formatNumber value="${orderDetail.book.price }" type="currency" /></td>
		<td>${orderDetail.quantity }</td>
		<td><fmt:formatNumber value="${orderDetail.subtotal }" type="currency" /></td>
	</tr>
	</c:forEach>
	
	<tr>
		<td colspan="6" align="right">
			<p>Subtotal: <fmt:formatNumber value="${order.subtotal }" type="currency" /></p>
			<p>
				Tax: <fmt:formatNumber value="${order.tax }" type="currency" />
			</p>
			<p>
				Shipping Fee: <fmt:formatNumber value="${order.shippingFee }" type="currency" />
			</p>
			<p>TOTAL: <b><fmt:formatNumber value="${order.total }" type="currency" /></b></p>
		</td>
	</tr>
</table>