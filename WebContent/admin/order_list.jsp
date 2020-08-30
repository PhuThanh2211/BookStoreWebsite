<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Book Orders Management</h2>
	</div>
	
	<c:if test="${message != null }">
	<div align="center">
		<h4 class="message">${message }</h4>
	</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="10">
			<tr>
				<th>Index</th>
				<th>Order By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrders }" varStatus="theCount">
			<tr>
				<td align="center">${theCount.index + 1 }</td>
				<td>${order.customer.fullname }</td>
				<td>${order.bookCopies }</td>
				<td><fmt:formatNumber value="${order.total }" type="currency" /></td>
				<td>${order.paymentMethod }</td>
				<td>${order.status }</td>
				<td><fmt:formatDate pattern='MM/dd/yyyy' value='${order.orderDate }' /></td>
				<td>
					<a href="view_order?id=${order.orderId }">Details</a> &nbsp
					<a href="edit_order?id=${order.orderId }">Edit</a> &nbsp
					<a href="javascript:void(0);" class="deleteLink" id="${order.orderId }">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		$(".deleteLink").each(function() {
			$(this).on("click", function() {
				orderId = $(this).attr("id");
				if (confirm("Are you sure to want to delete the order with ID " + orderId + "?")) {
					window.location = "delete_order?id=" + orderId;					
				}
								
			})
		});
	});
</script>

