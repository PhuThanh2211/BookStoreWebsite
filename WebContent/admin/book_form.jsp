<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${book != null }">
				Edit Book
			</c:if>
			<c:if test="${book == null }">
				Create New Book
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${book == null }">
		<form action="create_book" method="POST" id="bookForm" enctype="multipart/form-data">
		</c:if>
		<c:if test="${book != null }">
		<form action="update_book" method="POST" id="bookForm" enctype="multipart/form-data">
		<input type="hidden" name="bookId" value="${book.bookId }" />
		</c:if>
			<table class="form">
				<tr>
					<td align="right">Category:</td>
					<td align="left">
						<select name="category">
							<c:forEach items="${listCategories }" var="category">
								<c:if test="${category.categoryId eq book.category.categoryId}">
									<option value="${category.categoryId }" selected="selected">
										${category.name }
									</option>
								</c:if>
								
								<c:if test="${category.categoryId ne book.category.categoryId}">
									<option value="${category.categoryId }">
										${category.name }
									</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">Title:</td>
					<td align="left"><input type="text" name="title" value="${book.title }" id="title" size="20"/></td>
				</tr>
				<tr>
					<td align="right">Author:</td>
					<td align="left"><input type="text" name="author" value="${book.author }" id="author" size="20"/></td>
				</tr>
				<tr>
					<td align="right">ISBN:</td>
					<td align="left"><input type="text" name="isbn" value="${book.isbn }" id="isbn" size="20"/></td>
				</tr>
				<tr>
					<td align="right">Publish Date:</td>
					<td align="left">
						<input type="text" name="publishDate" id="publishDate" size="20"
						value="<fmt:formatDate pattern='MM/dd/yyyy' value='${book.publishDate }' />" />
					</td>
				</tr>
				<tr>
					<td align="right">Book Image:</td>
					<td align="left">
						<input type="file" name="bookImage" id="bookImage" size="20"/><br/>
						<img id="thumbnail" alt="Image Preview" style="width: 20%; margin-top: 10px" 
							src="data:image/jpg;base64, ${book.base64Image }"
						/>
					</td>
				</tr>
				<tr>
					<td align="right">Price:</td>
					<td align="left"><input type="text" name="price" value="${book.price }" id="price" size="20"/></td>
				</tr>
				<tr>
					<td align="right">Description:</td>
					<td align="left">
						<textarea rows="5" cols="50" name="description" id="description">
							${book.description }
						</textarea>
					</td>
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
		$("#publishDate").datepicker({
			changeMonth: true,
		    changeYear: true
		});
		
		$("#bookImage").change(function() {
			showImageThumbnail(this);
		});
		
		$("#bookForm").validate({
			rules: {
				category: "required",
				title: "required",
				author: "required",
				isbn: "required",
				publishDate: "required",
				
				<c:if test="${book == null }">
				bookImage: "required",
				</c:if>
				
				price: "required",
				description: "required"
				
			},
		
			messages: {
				category: "Please enter category of book",
				title: "Please enter title of book",
				author: "Please enter author of book",
				isbn: "Please enter isbn of book",
				publishDate: "Please choose publish date of book",
				
				<c:if test="${book == null }">
				bookImage: "Please upload image of book",
				</c:if>
				
				price: "Please enter price of book",
				description: "Please enter description of book"
			}		
		});
	});
	
	$("#cancelButton").click(function() {
		window.location = "${pageContext.request.contextPath}/admin/list_books";
	});
	
	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$("#thumbnail").attr("src", e.target.result);
		};
		reader.readAsDataURL(file);
	}
</script>









