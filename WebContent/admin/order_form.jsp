<jsp:directive.include file="header.jsp" />

	<form action="update_order" method="POST" id="orderForm">
		<div align="center">
			<h2 class="pageheading">Edit Order ID: ${order.orderId }</h2>
			<h2 class="pageheading">Order Overview:</h2>
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
					<td>Payment Method:</td>
					<td>
						<select name="paymentMethod">
							<option value="Cash On Delivery"
								<c:if test="${order.paymentMethod eq 'Cash On Delivery'}" >selected="selected"</c:if> >
								Cash On Delivery
							</option>
							<option value="Paypal"
								<c:if test="${order.paymentMethod eq 'Paypal'}" >selected="selected"</c:if> >
								Paypal or Credit Card
							</option>
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
				
			<h2 class="pageheading">Recipient Information:</h2>
			<table>
				<tr>
					<td>First Name:</td>
					<td>
						<input type="text" name="firstname" value="${order.firstname }" size="45" />
					</td>
				</tr>
				
				<tr>
					<td>Last Name:</td>
					<td>
						<input type="text" name="lastname" value="${order.lastname }" size="45" />
					</td>
				</tr>
				
				<tr>
					<td>Phone:</td>
					<td>
						<input type="text" name="phone" value="${order.phone }" size="45" />
					</td>
				</tr>
				
				<tr>
					<td>Address Line 1:</td>
					<td>
						<input type="text" name="address1" value="${order.addressLine1 }" size="45" />
					</td>
				</tr>
				
				<tr>
					<td>Address Line 2:</td>
					<td>
						<input type="text" name="address2" value="${order.addressLine2 }" size="45" />
					</td>
				</tr>
				
				<tr>
					<td>City:</td>
					<td>
						<input type="text" name="city" value="${order.city }" size="45" />
					</td>
				</tr>
				
				<tr>
					<td>State:</td>
					<td>
						<input type="text" name="state" value="${order.state }" size="45" />
					</td>
				</tr>
				
				<tr>
					<td>Zipcode:</td>
					<td>
						<input type="text" name="zipcode" value="${order.zipcode }" size="45" />
					</td>
				</tr>
				
				<tr>
					<td>Country:</td>
					<td>
						<select name="country" id="country">
							<c:forEach items="${mapCountries }" var="country">
								<option value="${country.value }"
									<c:if test="${order.country eq country.value }">
										selected="selected"
									</c:if> >
									${country.key }
								</option>
							</c:forEach>
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
					<td colspan="7" align="right">
						<p>Subtotal: <fmt:formatNumber value="${order.subtotal }" type="currency" /></p>
						<p>Tax: <input type="text" name="tax" id="tax" size="5" value="${order.tax }" /></p>
						<p>Shipping Fee: <input type="text" name="shippingFee" id="shippingFee" size="5" value="${order.shippingFee }" /></p>
						<p>TOTAL: <b><fmt:formatNumber value="${order.total }" type="currency" /></b></p>
					</td>
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
				firstname: "required",
				lastname: "required",
				phone: "required",
				address1: "required",
				address2: "required",
				city: "required",
				state: "required",
				zipcode: "required",
				<c:forEach items="${order.orderDetails }" var="book" varStatus="status">
					quantity${status.index + 1 }: {
						required: true,
						number: true,
						min: 1,
					},
				</c:forEach>
					
				tax: {
					required: true,
					number: true,
					min: 0,
				},
				shippingFee: {
					required: true,
					number: true,
					min: 0,
				},
			},
			
			messages: {
				firstname: "Please enter first name",
				lastname: "Please enter last name",
				phone: "Please enter phone",
				address1: "Please enter address line 1",
				address2: "Please enter address line 2",
				city: "Please enter city",
				state: "Please enter state",
				zipcode: "Please enter zipcode",
				<c:forEach items="${order.orderDetails  }" var="book" varStatus="status">
					quantity${status.index + 1 }: {
						required: "Please enter quantity",
						number: "Quantity must be a number",
						min: "Quantity must be greater than 0"
					},
				</c:forEach>
					
				tax: {
					required: "Please enter tax",
					number: "Tax must be a number",
					min: "Tax must be equal or greater than 0"
				},
				shippingFee: {
					required: "Please enter shipping fee",
					number: "Shipping fee must be a number",
					min: "Shipping fee must be equal or greater than 0"
				},
			},
		});
	});
</script>

