<jsp:directive.include file="header.jsp" />
	
	<div align="center">
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
				<td colspan="2">
					<h3>${message }</h3>
				</td>
			</tr>
		</table>
	</div>
	
<jsp:directive.include file="footer.jsp" />