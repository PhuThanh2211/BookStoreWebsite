<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer != null }">
				Edit Customer
			</c:if>
			<c:if test="${customer == null }">
				Create New Customer
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${customer == null }">
		<form action="create_customer" method="POST" id="customerForm">
		</c:if>
		<c:if test="${customer != null }">
		<form action="update_customer" method="POST" id="customerForm">
		<input type="hidden" name="customerId" value="${customer.customerId }" />
		</c:if>
			<table class="form">
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" name="email" value="${customer.email }" id="email" size="45"/></td>
				</tr>	
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullName" value="${customer.fullname }" id="fullName" size="45"/></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" name="password" id="password" size="45"/></td>
				</tr>
				<tr>
					<td align="right">Confirm Password:</td>
					<td align="left"><input type="password" name="confirmPassword" id="confirmPassword" size="45"/></td>
				</tr>
				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" name="phone" value="${customer.phone }" id="phone" size="45"/></td>
				</tr>
				<tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" name="address" value="${customer.address }" id="address" size="45"/></td>
				</tr>
				<tr>
					<td align="right">City:</td>
					<td align="left"><input type="text" name="city" value="${customer.city }" id="city" size="45"/></td>
				</tr>
				<tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" name="country" value="${customer.country }" id="country" size="45"/></td>
				</tr>
				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input type="text" name="zipCode" value="${customer.zipcode }" id="zipCode" size="45"/></td>
				</tr>
			
				<tr>
					<td colspan="2" align="center">
						<button class="spaceButton" type="submit">Save</button>
						<button type="button" id="cancelButton">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		
		$("#customerForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				
				fullName: "required",
				
				<c:if test="${customer == null }">
				password: "required",
				</c:if>
				
				confirmPassword: {
					<c:if test="${customer == null }">
					required: true,
					</c:if>
					
					equalTo: "#password"
				},
				
				
				phone: "required",
				address: "required",
				city: "required",
				zipCode: "required",
				country: "required",
				
			},
		
			messages: {
				email: {
					required: "Please enter email address",
					email: "Please enter a valid email address"
				},
				
				fullName: "Please enter full name",
				
				<c:if test="${customer == null }">
				password: "Please enter password",
				</c:if>
				
				confirmPassword: {
					<c:if test="${customer == null }">
					required: "Please confirm password",
					</c:if>
					
					equalTo: "Confirm password does not match password"
				},
				
				phone: "Please enter phone number",
				address: "Please enter address",
				city: "Please enter city",
				zipCode: "Please enter zip code",
				country: "Please enter country",
			}		
		});
	});
	
	$("#cancelButton").click(function() {
		window.location = "${pageContext.request.contextPath}/admin/list_customers";
	});
</script>









