<jsp:directive.include file="header.jsp" />
	<div class="center">		
		<h2 class="pageheading">New Books:</h2>
		<c:forEach items="${listNewBooks }" var="book">
			<jsp:directive.include file="book_group.jsp" />
		</c:forEach>
		
		<h2 class="pageheading">Best-Selling Books:</h2>
		<c:forEach items="${listBestSellingBooks }" var="book">
			<jsp:directive.include file="book_group.jsp" />
		</c:forEach>
		
		<h2 class="pageheading">Most-favored Books:</h2>
		<c:forEach items="${listFavoritedBooks }" var="book">
			<jsp:directive.include file="book_group.jsp" />
		</c:forEach>
	</div>
<jsp:directive.include file="footer.jsp" />