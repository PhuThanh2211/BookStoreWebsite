<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Shopping Cart</h2>
		
		<c:set var="cart" value="${sessionScope['cart'] }" />
		
		<c:if test="${cart.totalItems == 0 }">
			<h2>There's no items in your cart</h2>
		</c:if>
		
		<c:if test="${cart.totalItems > 0 }">
			<div>
				<form action="update_cart" method="POST" id="cartForm">
					<div>
						<table border="1">
							<tr>
								<th>No</th>
								<th colspan="2">Book</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Subtotal</th>
								<th>Action</th>
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
								<td>
									<input type="hidden" name="bookId" value="${item.key.bookId }" />
									<input type="text" name="quantity${status.index + 1 }" value="${item.value }" size="5" />
								</td>
								<td><fmt:formatNumber value="${item.key.price }" type="currency" /></td>
								<td><fmt:formatNumber value="${item.value * item.key.price }" type="currency" /></td>
								<td>
									<a href="remove_from_cart?book_id=${item.key.bookId }">Remove</a>
								</td>
							</tr>
							</c:forEach>
							
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td><b>${cart.totalQuantity } book(s)</b></td>
								<td>Total:</td>
								<td colspan="2">
									<b><fmt:formatNumber value="${cart.totalAmount }" type="currency" /></b>
								</td>
							</tr>
						</table>
					</div>
					
					<div>
						<table class="normal">
							<tr>
								<td><button>Update</button></td>
								<td><button type="button" id="clearCart">Clear Cart</button></td>
								<td><button type="button" id="continueShopping">Continue Shopping</button></td>
								<td><button type="button" id="checkout">Checkout</button></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</c:if>
		
	</div>
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		$("#cartForm").validate({
			rules: {
				<c:forEach items="${cart.items }" var="item" varStatus="status">
					quantity${status.index + 1 }: {
						required: true,
						number: true,
						min: 1,
					},
				</c:forEach>
			},
			
			messages: {
				<c:forEach items="${cart.items }" var="item" varStatus="status">
					quantity${status.index + 1 }: {
						required: "Please enter quantity",
						number: "Quantity must be a number",
						min: "Quantity must be greater than 0"
					},
				</c:forEach>
			},
		});
		
		$("#continueShopping").click(function() {
			window.location = "${pageContext.request.contextPath}";
		});
		
		$("#clearCart").click(function() {
			window.location = "${pageContext.request.contextPath}/clear_cart";
		});
		
		$("#checkout").click(function() {
			window.location = "${pageContext.request.contextPath}/checkout";
		});
	});
</script>





















