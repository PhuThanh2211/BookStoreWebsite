<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">My Order History</h2>
	</div>
	
	<c:if test="${fn:length(listOrders) == 0 }">
		<div align="center">
			<h2>You have not placed any orders</h2>
		</div>	
	</c:if>
	
	<c:if test="${fn:length(listOrders) > 0 }">
		<div align="center">
			<table border="1" cellpadding="10">
				<tr>
					<th>Index</th>
					<th>Quantity</th>
					<th>Total Amount</th>
					<th>Order Date</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="order" items="${listOrders }" varStatus="theCount">
				<tr>
					<td align="center">${theCount.index + 1 }</td>
					<td>${order.bookCopies }</td>
					<td><fmt:formatNumber value="${order.total }" type="currency" /></td>
					<td>${order.orderDate }</td>
					<td>${order.status }</td>
					<td>
						<a href="view_order?id=${order.orderId }"><i>View Details</i></a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	
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

