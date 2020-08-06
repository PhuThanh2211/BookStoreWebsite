<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<form action="submit_review" method="POST" id="reviewForm">
			<table class="form" width="80%">
				<tr>
					<td><h2>Your Reviews</h2></td>
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
						<input type="hidden" id="rating" name="rating" />
						<input type="hidden" name="bookId" value="${book.bookId }" />
						<br/>
						<input type="text" name="headline" size="60" placeholder="Headline or summary for your review (required)"/>
						<br/>
						<br/>						
						<textarea name="comment" rows="10" cols="70" placeholder="Write your review details..."></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="3" align="center">
						<button class="spaceButton" type="submit">Save</button>
						&nbsp;&nbsp;
						<button type="button" id="cancelButton">Cancel</button>
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
		  onSet: function (rating, rateYoInstance) {
			  $("#rating").val(rating);
		  }
		});
		
		$("#reviewForm").validate({
			rules: {
				headline: "required",
				comment: "required",
			},
		
			messages: {
				headline: "Please enter headline",
				comment: "Please enter comment",
			},		
		});
	});
	
	$("#cancelButton").click(function() {
		window.history.go(-1);
	});
</script>









