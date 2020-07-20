<jsp:directive.include file="header.jsp" />
	<div class="center">
		<table class="book">
			<tr>
				<td colspan="3" align="left">
					<p id="book_title">${book.title }</p> 
					by <span id="author">${book.author }</span>
				</td>
			</tr>
			
			<tr>
				<td rowspan="2">
					<img src="data:image/jpg;base64,${book.base64Image }" class="book_large" />
				</td>
				<td valign="top" align="left">
					Rating *****
				</td>
				<td valign="top" rowspan="2" width="20%">
					<h2>$${book.price }</h2>
					<br/><br/>
					<button type="submit">Add to Cart</button>
				</td>
			</tr>
	
			<tr>
				<td id="description">
					${book.description }
				</td>
			</tr>
			
			<tr>
				<td>&nbsp; </td>
			</tr>
			
			<tr>
				<td><h2>Customer Reviews</h2>
				<td rowspan="2" align="center">
					<button>
						Write a Customer Review
					</button>
				</td>
			</tr>
		</table>
	</div>
<jsp:directive.include file="footer.jsp" />