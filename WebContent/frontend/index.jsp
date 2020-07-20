<jsp:directive.include file="header.jsp" />
	<div class="center">
		<h3>This is main content</h3>
		
		<h2>New Books:</h2>
		<c:forEach items="${listNewBooks }" var="book">
			<div class="book">
				<div>
					<a href="view_book?id=${book.bookId }&name=${book.title}">
						<img src="data:image/jpg;base64,${book.base64Image }" class="book_small" />
					</a>
				</div>
				<div>
					<a href="view_book?id=${book.bookId }&name=${book.title}">
						<b>${book.title }</b>
					</a>
				</div>
			
				<div>Rating *****</div>
				<div><i>by ${book.author }</i></div>
				<div><b>$${book.price }</b></div>
			</div>
		</c:forEach>
		
		<div class="next_row">
			<h2>Best-Selling Books:</h2>
		</div>
		
		<div class="next_row">
			<h2>Most-favored Books:</h2>
		</div>			
	</div>
<jsp:directive.include file="footer.jsp" />