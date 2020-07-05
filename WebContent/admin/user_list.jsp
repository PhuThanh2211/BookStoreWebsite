<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Users Management</h2>
		<h3><a href="user_form.jsp">Create New User</a></h3>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message"><i>${message}</i></h4>
	</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="10">
			<tr>
				<th>Index</th>
				<th>Full Name</th>
				<th>Email</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listUsers }" varStatus="theCount">
			<tr>
				<td>${theCount.index + 1 }</td>
				<td>${user.fullName }</td>
				<td>${user.email }</td>
				<td>
					<a href="edit_user?id=${user.userId }">Edit</a> &nbsp
					<a href="javascript:void(0);" class="deleteLink" id="${user.userId }">Delete</a>
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
				userId = $(this).attr("id");
				if (confirm("Are you sure to want to delete the user with ID " + userId + "?")) {
					window.location = "delete_user?id=" + userId;					
				}
								
			})
		});
	});
</script>

