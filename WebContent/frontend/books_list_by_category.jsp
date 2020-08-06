<jsp:directive.include file="header.jsp" />
	<div class="center">
		<h2>${category.name }</h2>
	
		<div class="book_group" >
			<c:forEach items="${listBooks }" var="book">
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
				
					<div>
						<jsp:directive.include file="book_rating.jsp" />
					</div>
					
					<div><i>by ${book.author }</i></div>
					
					<div><b>$${book.price }</b></div>
				</div>
			</c:forEach>
		</div>
	</div>
<jsp:directive.include file="footer.jsp" />