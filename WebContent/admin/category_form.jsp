<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${category != null }">
				Edit Category
			</c:if>
			<c:if test="${category == null }">
				Create New Category
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${category == null }">
		<form action="create_category" method="POST" id="categoryForm">
		</c:if>
		<c:if test="${category != null }">
		<form action="update_category" method="POST" id="categoryForm">
		<input type="hidden" name="categoryId" value="${category.categoryId }">
		</c:if>
			<table class="form">
				<tr>
					<td align="right">Category Name:</td>
					<td align="left"><input type="text" name="name" value="${category.name }" id="name" size="20"/></td>
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
		$("#categoryForm").validate({
			rules: {
				name: "required",
			},
			
			messages: {
				name: "Please enter category name",
			},
		});
		
		$("#cancelButton").click(function() {
			window.location = "${pageContext.request.contextPath}/admin/list_categories";
		})
	});
		
</script>