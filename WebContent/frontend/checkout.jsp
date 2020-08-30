<jsp:directive.include file="header.jsp" />
	<div align="center">
		
		<c:set var="cart" value="${sessionScope['cart'] }" />
		
		<c:if test="${cart.totalItems == 0 }">
			<h2 class="pageheading">There's no items in your cart</h2>
		</c:if>
		
		<c:if test="${cart.totalItems > 0 }">
			<div>
				<div>
					<h2>
						Review Your Order Detail 
						<a href="view_cart"><i>Edit</i></a>
					</h2>
					<table border="1">
						<tr>
							<th>No</th>
							<th colspan="2">Book</th>
							<th>Author</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Sub Total</th>
						</tr>
						
						<c:forEach items="${cart.items }" var="item" varStatus="status">
						<tr>
							<td>${status.index + 1 }</td>
							<td>
								<img src="data:image/jpg;base64,${item.key.base64Image }" class="book_small" />
								&nbsp;&nbsp;
							</td>
							<td>
								<span id="book_title">${item.key.title }</span>
							</td>
							<td>${item.key.author }</td>
							<td>
								<fmt:formatNumber value="${item.key.price }" type="currency" />
							</td>
							<td>
								<input type="text" name="quantity${status.index + 1 }" value="${item.value }" size="5" readonly="readonly" />
							</td>
							<td>
								<fmt:formatNumber value="${item.value * item.key.price }" type="currency" />
							</td>
						</tr>
						</c:forEach>
						
						<tr>
							<td colspan="5" align="right">
								<b><i>TOTAL:</i></b>
							</td>
							<td>
								<b>${cart.totalQuantity } book(s)</b>
							</td>
							<td>
								<b><fmt:formatNumber value="${cart.totalAmount }" type="currency" /></b>
							</td>
						</tr>
					</table>
					
					<form id="orderForm" action="place_order" method="POST">
						<h2>Your Shipping Information:</h2>
						
						<table>
							<tr>
								<td>Recipient Name:</td>
								<td>
									<input type="text" name="recipientName" value="${loggedCustomer.fullname }"
								</td>
							</tr>
							<tr>
								<td>Recipient Phone:</td>
								<td>
									<input type="text" name="recipientPhone" value="${loggedCustomer.phone }"
								</td>
							</tr>
							<tr>
								<td>Street Address:</td>
								<td>
									<input type="text" name="address" value="${loggedCustomer.address }"
								</td>
							</tr>
							<tr>
								<td>City: </td>
								<td>
									<input type="text" name="city" value="${loggedCustomer.city }"
								</td>
							</tr>
							<tr>
								<td>Zip Code:</td>
								<td>
									<input type="text" name="zipcode" value="${loggedCustomer.zipcode }"
								</td>
							</tr>
							<tr>
								<td>Country:</td>
								<td>
									<input type="text" name="country" value="${loggedCustomer.country }"
								</td>
							</tr>
						</table>
						
						<h2>Payment:</h2>
						Choose your payment method:
						&nbsp;&nbsp;&nbsp;
						<select name="paymentMethod">
							<option value="Cash On Delivery">Cash On Delivery</option>
						</select>
						
						<br/><br/><br/><br/>
						<div>
							<table class="normal">
								<tr>
									<td><button>Place Order</button></td>
									<td><button type="button" id="continueShopping">Continue Shopping</button></td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</div>
		</c:if>
		
	</div>
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		$("#orderForm").validate({
			rules: {
				recipientName: "required",
				recipientPhone: "required",
				address: "required",
				city: "required",
				zipcode: "required",
				country: "required",
			},
		
			messages: {
				recipientName: "Please enter recipient name",
				recipientPhone: "Please enter phone number",
				address: "Please enter address",
				city: "Please enter city",
				zipcode: "Please enter zip code",
				country: "Please enter country",
			}		
		});
		
		$("#continueShopping").click(function() {
			window.location = "${pageContext.request.contextPath}";
		});
	});
</script>





















