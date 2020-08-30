<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			Edit My Profile
		</h2>
	</div>
	
	<div align="center">
	
		<form action="update_profile" method="POST" id="customerForm">
			<table class="form">
				<tr>
					<td align="right">Email:</td>
					<td align="left"><b>${loggedCustomer.email } (Cannot be changed)</b></td>
				</tr>
					
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullName" value="${loggedCustomer.fullname }" id="fullName" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" name="phone" value="${loggedCustomer.phone }" id="phone" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" name="address" value="${loggedCustomer.address }" id="address" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">City:</td>
					<td align="left"><input type="text" name="city" value="${loggedCustomer.city }" id="city" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" name="country" value="${loggedCustomer.country }" id="country" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input type="text" name="zipCode" value="${loggedCustomer.zipcode }" id="zipCode" size="45"/></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<i>(Leave password fields blank if you don't want to change password)</i>
					</td>
					
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
				
				confirmPassword: {
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
				
				confirmPassword: {
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
		window.location = "${pageContext.request.contextPath}/";
	});
</script>









