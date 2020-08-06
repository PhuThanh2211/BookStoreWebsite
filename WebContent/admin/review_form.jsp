<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${review != null }">
				Edit review
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${review != null }">
		<form action="update_review" method="POST" id="reviewForm">
		<input type="hidden" name="reviewId" value="${review.reviewId }" />
		</c:if>
			<table class="form">
				<tr>
					<td align="right">Book:</td>
					<td align="left"><b>${review.book.title }</b></td>
				</tr>
				
				<tr>
					<td align="right">Rating:</td>
					<td align="left"><b>${review.rating }</b></td>
				</tr>
				
				<tr>
					<td align="right">Customer:</td>
					<td align="left"><b>${review.customer.fullname }</b></td>
				</tr>
				
				<tr>
					<td align="right">Headline:</td>
					<td align="left"><input type="text" name="headline" value="${review.headline }" id="headline" size="60"/></td>
				</tr>
				
				<tr>
					<td align="right">Comment:</td>
					<td align="left">
						<textarea rows="5" cols="50" name="comment" id="comment">
							${review.comment }
						</textarea>
					</td>
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
		window.location = "${pageContext.request.contextPath}/admin/list_reviews";
	});
</script>









