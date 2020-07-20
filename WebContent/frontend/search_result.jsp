<jsp:directive.include file="header.jsp" />

	<div align="center">
	
		<c:if test="${fn:length(listBooks) == 0 }">
			<h2>No Results for "${keyword }"</h2>
		</c:if>
		
		<c:if test="${fn:length(listBooks) > 0 }">
			<div class="book_group">
				<center>
					<h2>Results for "${keyword }"</h2>
				</center>
				<c:forEach items="${listBooks }" var="book">
					<div>
						<div id="search_image">
							<div align="left">
								<a href="view_book?id=${book.bookId }&name=${book.title}">
									<img src="data:image/jpg;base64,${book.base64Image }" class="book_small" />
								</a>
							</div>
						</div>
						
						<div id="search_description">
							<div>
								<h2>
									<a href="view_book?id=${book.bookId}&name=${book.title}">
										<b>${book.title }</b>
									</a>
								</h2>
							</div>
							
							<div>Rating *****</div>
							
							<div>
								<i>by ${book.author}</i>
							</div>
							
							<div>
								<p>${fn:substring(book.description, 0, 100) }...</p>
							</div>
						</div>
						
						<div id="search_price">
							<h3>$${book.price }</h3>
							<h3><a href="">Add To Cart</a></h3>
						</div>
					</div>
				</c:forEach>
			</div>	
		</c:if>
		
	</div>
	
<jsp:directive.include file="footer.jsp" />