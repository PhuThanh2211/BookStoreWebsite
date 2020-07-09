<jsp:directive.include file="header.jsp" />
	<table align="center" style="border: 0; width: 80%; margin:0 auto">
		<tr>
			<td colspan="3" align="left">
				<h2>${book.title }</h2> by ${book.author }
			</td>
		</tr>
		
		<tr>
			<td rowspan="2">
				<img src="data:image/jpg;base64,${book.base64Image }" width="240" height="300" />
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
			<td valign="top" style="text-align: justify;">
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
<jsp:directive.include file="footer.jsp" />