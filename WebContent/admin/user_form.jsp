<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${user != null }">
				Edit User
			</c:if>
			<c:if test="${user == null }">
				Create New User
			</c:if>
		</h2>
	</div>
	
	<div align="center">
	
		<c:if test="${user == null }">
		<form action="create_user" method="POST" id="userForm">
		</c:if>
		
		<c:if test="${user != null }">
		<form action="update_user" method="POST" id="userForm">
		<input type="hidden" name="userId" value="${user.userId }">
		</c:if>
		
			<table class="form">
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullName" value="${user.fullName }" id="fullName" size="20"/></td>
				</tr>
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" name="email" value="${user.email }" id="email" size="20"/></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" name="password" id="password" size="20"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button class="spaceButton" type="submit">Save</button>
						<button id="cancelButton">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		$("#userForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				fullName: "required",
				
				<c:if test="${user == null }">
				password: "required"
				</c:if>
				
			},
		
			messages: {
				email: {
					required: "Please enter email",
					email: "Please enter an valid email address"
				},
				fullName: "Please enter full name",
				
				<c:if test="${user == null }">
				password: "Please enter password",
				</c:if>
			}		
		});
	});
	
	$("#cancelButton").click(function() {
		window.location = "${pageContext.request.contextPath}/admin/list_users";
	})
</script>