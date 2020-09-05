<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Customers Management</h2>
		<h3><a href="new_customer">Create New Customer</a></h3>
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
				<th>Email</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Registerd Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="customer" items="${listCustomers }" varStatus="theCount">
			<tr>
				<td align="center">${theCount.index + 1 }</td>
				<td>${customer.email }</td>
				<td>${customer.firstname }</td>
				<td>${customer.lastname }</td>
				<td>${customer.city }</td>
				<td>${customer.countryName }</td>
				<td><fmt:formatDate pattern='MM/dd/yyyy' value='${customer.registerDate }' /></td>
				<td>
					<a href="edit_customer?id=${customer.customerId }&name=${customer.fullname}">Edit</a> &nbsp
					<a href="javascript:void(0);" class="deleteLink" id="${customer.customerId }">Delete</a>
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
				customerId = $(this).attr("id");
				if (confirm("Are you sure to want to delete the customer with ID " + customerId + "?")) {
					window.location = "delete_customer?id=" + customerId;					
				}
								
			})
		});
	});
</script>

