<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Shopping Cart</h2>
		
		<c:set var="cart" value="${sessionScope['cart'] }" />
		
		<c:if test="${cart.totalItems == 0 }">
			<h2>There's no items in your cart</h2>
		</c:if>
		
		<c:if test="${cart.totalItems > 0 }">
			<div>
				<form>
					<table border="1">
						<tr>
							<th>No</th>
							<th colspan="2">Book</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th>
								<a href=""><b>Clear Cart</b></a>
							</th>
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
							<td>${item.value }</td>
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
				</form>
			</div>
		</c:if>
		
	</div>
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		$("#formLogin").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				password: "required"
			},
			
			messages: {
				email: {
					required: "Please enter email",
					email: "Please enter an valid email address"
				},
				password: "Please enter password"
			},
		});
	});
</script>





















