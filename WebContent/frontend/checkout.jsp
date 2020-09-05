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
							<td colspan="7" align="right">
								<p>Number of Copies: ${cart.totalQuantity }</p>
								<p>Subtotal: <fmt:formatNumber value="${cart.totalAmount }" type="currency" /></p>
								<p>Tax: <fmt:formatNumber value="${tax }" type="currency" /></p>
								<p>Shipping Fee: <fmt:formatNumber value="${shippingFee }" type="currency" /></p>
								<p><b>TOTAL: <fmt:formatNumber value="${total }" type="currency" /></b></p>
							</td>
						</tr>
					</table>
					
					<form id="orderForm" action="place_order" method="POST">
						<h2 class="pageheading">Your Shipping Information:</h2>
						
						<table>
							<tr>
								<td>First Name:</td>
								<td>
									<input type="text" name="firstname" value="${loggedCustomer.firstname }" />
								</td>
							</tr>
							
							<tr>
								<td>Last Name:</td>
								<td>
									<input type="text" name="lastname" value="${loggedCustomer.lastname }" />
								</td>
							</tr>
							
							<tr>
								<td>Phone:</td>
								<td>
									<input type="text" name="phone" value="${loggedCustomer.phone }" />
								</td>
							</tr>
							
							<tr>
								<td>Address Line 1:</td>
								<td>
									<input type="text" name="address1" value="${loggedCustomer.addressLine1 }" />
								</td>
							</tr>
							
							<tr>
								<td>Address Line 2:</td>
								<td>
									<input type="text" name="address2" value="${loggedCustomer.addressLine2 }" />
								</td>
							</tr>
							
							<tr>
								<td>City: </td>
								<td>
									<input type="text" name="city" value="${loggedCustomer.city }" />
								</td>
							</tr>
							
							<tr>
								<td>State: </td>
								<td>
									<input type="text" name="state" value="${loggedCustomer.state }" />
								</td>
							</tr>
							
							<tr>
								<td>Zip Code:</td>
								<td>
									<input type="text" name="zipcode" value="${loggedCustomer.zipcode }" />
								</td>
							</tr>
							
							<tr>
								<td>Country:</td>
								<td>
									<select name="country" id="country">
										<c:forEach items="${mapCountries }" var="country">
											<option value="${country.value }"
												<c:if test="${loggedCustomer.country eq country.value }">
													selected="selected"
												</c:if> >
												${country.key }
											</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
						
						<h2 class="pageheading">Payment:</h2>
						Choose your payment method:
						&nbsp;&nbsp;&nbsp;
						<select name="paymentMethod">
							<option value="Cash On Delivery">Cash On Delivery</option>
							<option value="Paypal">Paypal or Credit Cart</option>
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
				firstname: "required",
				lastname: "required",
				phone: "required",
				address1: "required",
				address2: "required",
				city: "required",
				state: "required",
				zipcode: "required",
			},
		
			messages: {
				firstname: "Please enter first name",
				lastname: "Please enter last name",
				phone: "Please enter phone",
				address1: "Please enter address line 1",
				address2: "Please enter address line 2",
				city: "Please enter city",
				state: "Please enter state",
				zipcode: "Please enter zip code",
				country: "Please enter country",
			}		
		});
		
		$("#continueShopping").click(function() {
			window.location = "${pageContext.request.contextPath}";
		});
	});
</script>





















