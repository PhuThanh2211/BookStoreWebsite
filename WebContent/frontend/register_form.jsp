<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			Register As A Customer
		</h2>
	</div>
	
	<div align="center">
		<form action="register_customer" method="POST" id="customerForm">
			<jsp:directive.include file="../common/customer_form.jsp" />
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
				address1: "Please enter address line 1",
				address2: "Please enter address line 2",
				city: "Please enter city",
				state: "Please enter state",
				zipCode: "Please enter zip code",
			}		
		});
	});
	
	$("#cancelButton").click(function() {
		window.location = "${pageContext.request.contextPath}";
	});
</script>









