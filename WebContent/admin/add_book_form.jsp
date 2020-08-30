<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/richtext.min.css" />
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.richtext.min.js"></script>
</head>
<body>
	<div align="center">
		<h2 class="pageheading">Add Book To Order ID: ${order.orderId }</h2>
		<form action="add_book_to_order" method="POST">
			<table class="normal">
				<tr>
					<td>Select a book:</td>
					<td>
						<select name="bookId">
							<c:forEach items="${listBooks }" var="book" varStatus="count">
								<option value="${book.bookId }">${book.title } - ${book.author }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
					
				<tr>
					<td>Number of Copies:</td>
					<td>
						<select name="quantity">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<button>Add</button>
						<button type="button" id="cancelAddBook">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<script>
	$("#cancelAddBook").click(function() {
		var daddy = window.self;
		daddy.opener = window.self;
		daddy.close();
	});
</script>


