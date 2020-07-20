<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Customer Login</h2>
		
		<c:if test="${message != null }">
		<h4 class="message">${message }</h4>
		</c:if>
		
		<form id="formLogin" action="login" method="post">
			<table class="form">
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="20"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>
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





















