<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
		<h3><a href="category_form.jsp">Create New Category</a></h3>
	</div>
	
	<c:if test="${message != null }">
	<div align="center">
		<h4 class="message"><i>${message }</i></h4>
	</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="10">
			<tr>
				<th>Index</th>
				<th>Category Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="category" items="${listCategories }" varStatus="theCount">
			<tr>
				<td>${theCount.index + 1 }</td>
				<td>${category.name }</td>
				<td>
					<a href="edit_category?id=${category.categoryId }">Edit</a> &nbsp
					<a href="javascript:void(0);" class="deleteLink" id="${category.categoryId }">Delete</a>
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
			categoryId = $(this).attr("id");
			if (confirm("Are you sure to want to delete the category with ID " + categoryId + "?")) {
				window.location = "delete_category?id=" + categoryId;					
			}
							
		})
	});
});
</script>

