<jsp:directive.include file="header.jsp" />
	<c:if test="${order == null }">
		<div align="center">
			<h2 class="pageheading">Sorry, you are not authorized to view this order</h2>
		</div>
	</c:if>
	
	<c:if test="${order != null }">
		<div align="center">
			<h2 class="pageheading">Your Order Detail ID ${order.orderId }</h2>
		</div>
		
		<div align="center">
			<h2>Order Overview</h2>
			<table>
				<tr>
					<td>Ordered By:</td>
					<td>${order.customer.fullname }</td>
				</tr>
				<tr>
					<td>Book Copies:</td>
					<td>${order.bookCopies }</td>
				</tr>
				<tr>
					<td>Total Amount:</td>
					<td><fmt:formatNumber value="${order.total }" type="currency" /></td>
				</tr>
				<tr>
					<td>Recipient Name:</td>
					<td>${order.recipientName }</td>
				</tr>
				<tr>
					<td>Recipient Phone:</td>
					<td>${order.recipientPhone }</td>
				</tr>
				<tr>
					<td>Ship To:</td>
					<td>${order.shippingAddress }</td>
				</tr>
				<tr>
					<td>Payment Method:</td>
					<td>${order.paymentMethod }</td>
				</tr>
				<tr>
					<td>Order Status:</td>
					<td>${order.status }</td>
				</tr>
				<tr>
					<td>Order Date:</td>
					<td>${order.orderDate }</td>
				</tr>
			</table>
			
			<h2>Ordered Books:</h2>
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
					<td colspan="4" align="right">
						<b><i>TOTAL:</i></b>
					</td>
					<td>
						<b>${order.bookCopies } book(s)</b>
					</td>
					<td>
						<b><fmt:formatNumber value="${order.total }" type="currency" /></b>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
<jsp:directive.include file="footer.jsp" />

