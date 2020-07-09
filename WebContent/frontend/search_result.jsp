<jsp:directive.include file="header.jsp" />

	<div align="center">
	
		<c:if test="${fn:length(listBooks) == 0 }">
			<h2>No Results for "${keyword }"</h2>
		</c:if>
		
		<c:if test="${fn:length(listBooks) > 0 }">
			<div align="left" style="width: 80%; margin:0 auto">
				<center>
					<h2>Results for "${keyword }"</h2>
				</center>
				<c:forEach items="${listBooks }" var="book">
					<div>
						<div style="display: inline-block; margin: 20px; width: 10%">
							<div align="left">
								<a href="view_book?id=${book.bookId }&name=${book.title}">
									<img src="data:image/jpg;base64,${book.base64Image }" width="128" height="164" />
								</a>
							</div>
						</div>
						
						<div style="display: inline-block; margin: 20px; vertical-align: top; width: 60%" align="left">
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
						
						<div style="display: inline-block; margin: 20px; vertical-align: top;">
							<h3>$${book.price }</h3>
							<h3><a href="">Add To Cart</a></h3>
						</div>
					</div>
				</c:forEach>
			</div>	
		</c:if>
		
	</div>
	
<jsp:directive.include file="footer.jsp" />