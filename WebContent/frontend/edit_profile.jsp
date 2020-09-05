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
					<td align="right">First Name:</td>
					<td align="left"><input type="text" name="firstname" value="${loggedCustomer.firstname }" id="firstname" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Last Name:</td>
					<td align="left"><input type="text" name="lastname" value="${loggedCustomer.lastname }" id="lastname" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" name="phone" value="${loggedCustomer.phone }" id="phone" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Address Line 1:</td>
					<td align="left"><input type="text" name="address1" value="${loggedCustomer.addressLine1 }" id="address1" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Address Line 2:</td>
					<td align="left"><input type="text" name="address2" value="${loggedCustomer.addressLine2 }" id="address2" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">City:</td>
					<td align="left"><input type="text" name="city" value="${loggedCustomer.city }" id="city" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">State:</td>
					<td align="left"><input type="text" name="state" value="${loggedCustomer.state }" id="state" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input type="text" name="zipCode" value="${loggedCustomer.zipcode }" id="zipCode" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Country:</td>
					<td align="left">
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
				
				firstname: "required",
				lastname: "required",
				
				confirmPassword: {
					equalTo: "#password"
				},
				
				phone: "required",
				address1: "required",
				address2: "required",
				city: "required",
				state: "required",
				zipCode: "required",
			},
		
			messages: {
				email: {
					required: "Please enter email address",
					email: "Please enter a valid email address"
				},
				
				firstname: "Please enter first name",
				lastname: "Please enter last name",
				
				confirmPassword: {
					equalTo: "Confirm password does not match password"
				},
				
				phone: "Please enter phone number",
				address1: "Please enter address line 1",
				address2: "Please enter address line 2",
				city: "Please enter city",
				state: "Please enter state",
				zipCode: "Please enter zip code",
			}		
		});
	});
	
	$("#cancelButton").click(function() {
		window.location = "${pageContext.request.contextPath}/";
	});
</script>









