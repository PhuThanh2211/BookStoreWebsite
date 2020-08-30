<jsp:directive.include file="header.jsp" />

	<form action="update_order" method="POST" id="orderForm">
		<div align="center">
			<h2 class="pageheading">Edit Order ID: ${order.orderId }</h2>
			<table>
				<tr>
					<td>Ordered By:</td>
					<td>${order.customer.fullname }</td>
				</tr>
				<tr>
					<td>Order Date:</td>
					<td>${order.orderDate }</td>
				</tr>
				<tr>
					<td>Recipient Name:</td>
					<td>
						<input type="text" name="recipientName" value="${order.recipientName }" size="45" />
					</td>
				</tr>
				<tr>
					<td>Recipient Phone:</td>
					<td>
						<input type="text" name="recipientPhone" value="${order.recipientPhone }" size="45" />
					</td>
				</tr>
				<tr>
					<td>Ship To:</td>
					<td>
						<input type="text" name="shippingAddress" value="${order.shippingAddress }" size="45" />
					</td>
				</tr>
				<tr>
					<td>Payment Method:</td>
					<td>
						<select name="paymentMethod">
							<option value="Cash On Delivery">Cash On Delivery</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Order Status:</td>
					<td>
						<select name="orderStatus">
							<option value="Processing" <c:if test="${order.status eq 'Processing' }"> selected='selected'</c:if> >
								Processing
							</option>
							<option value="Shipping" <c:if test="${order.status eq 'Shipping' }"> selected='selected'</c:if> >
								Shipping
							</option>
							<option value="Delivered" <c:if test="${order.status eq 'Delivered' }"> selected='selected'</c:if> >
								Delivered
							</option>
							<option value="Completed" <c:if test="${order.status eq 'Completed' }"> selected='selected'</c:if> >
								Completed
							</option>
							<option value="Cancelled" <c:if test="${order.status eq 'Cancelled' }"> selected='selected'</c:if> >
								Cancelled
							</option>
						</select>
					</td>
				</tr>
			</table>
			
			<h2 class="pageheading">Ordered Books:</h2>
			<table border="1">
				<tr>
					<th>Index</th>
					<th>Book</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Sub Total</th>
					<th>Actions</th>
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
					<td>
						<input type="hidden" name="price" value="${orderDetail.book.price }" />
						<fmt:formatNumber value="${orderDetail.book.price }" type="currency" />
					</td>
					<td>
						<input type="hidden" name="bookId" value="${orderDetail.book.bookId }" />
						<input type="text" name="quantity${theCount.index + 1 }" value="${orderDetail.quantity }" size="5" />
					</td>
					<td><fmt:formatNumber value="${orderDetail.subtotal }" type="currency" /></td>
					<td><a href="remove_book_from_order?id=${orderDetail.book.bookId }">Remove</a></td>
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
					<td></td>
				</tr>
			</table>
		</div>
	
		<div align="center">
			<br/>
			<button type="button" id="addBookPopUp">Add Books</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button>Save</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" id="cancelOrder" >Cancel</button>
		</div>
	</form>
	
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		$("#addBookPopUp").click(function() {
			var width = 800;
			var height = 400;
			var left = (screen.width - width) / 2;
			var top = (screen.height - height) / 2;
			
			window.open("add_book_form", "_blank", 
					"width=" + width + ", height=" + height + ", top=" + top + ", left=" + left);
		});
		
		$("#cancelOrder").click(function() {
			window.location = "${pageContext.request.contextPath}/admin/list_orders";
		});
		
		$("#orderForm").validate({
			rules: {
				recipientName: "required",
				recipientPhone: "required",
				shippingAddress: "required",
				<c:forEach items="${order.orderDetails }" var="book" varStatus="status">
					quantity${status.index + 1 }: {
						required: true,
						number: true,
						min: 1,
					},
				</c:forEach>
			},
			
			messages: {
				recipientName: "Please enter recipient name",
				recipientPhone: "Please enter recipient phone",
				shippingAddress: "Please enter shipping address",
				<c:forEach items="${order.orderDetails  }" var="book" varStatus="status">
					quantity${status.index + 1 }: {
						required: "Please enter quantity",
						number: "Quantity must be a number",
						min: "Quantity must be greater than 0"
					},
				</c:forEach>
			},
		});
	});
</script>

