<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<form id="reviewForm">
			<table class="form" width="80%">
				<tr>
					<td><h3>You already wrote a review for this book</h3></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.fullname }</h2></td>
				</tr>
				
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				
				<tr>
					<td>
						<span id="book_title">${book.title }</span><br/>
						<img src="data:image/jpg;base64,${book.base64Image }" class="book_large" />
					</td>
					<td>
						<div id="rateYo"></div>
						<br/>
						<input type="text" name="headline" size="60" readonly="readonly" value="${review.headline }"/>
						<br/>
						<br/>						
						<textarea name="comment" rows="10" cols="70" readonly="readonly">${review.comment }</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		$("#rateYo").rateYo({
		  starWidth: "40px",
		  fullStar: true,
		  rating: ${review.rating},
		  readOnly: true,
		});
	});
</script>









