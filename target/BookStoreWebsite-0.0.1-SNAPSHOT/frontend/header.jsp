<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>

<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/jquery-ui.min.css" />

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery-ui.min.js"></script>

</head>
<body>
	<div align="center">
		<div>
			<img alt="Book Store" src="alo/${pageContext.request.contextPath}/images/BookstoreLogo.png" />
		</div>
		
		<div>
			<input type="text" name="keyword" size="50" />
			<input type="button" value="Search" />
			
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="login">Sign in</a> | 
			<a href="register">Register</a> |
			<a href="login">Cart</a>
		</div>
		<div>&nbsp;</div>
		<div>
			<c:forEach var="category" items="${listCategory }" varStatus="status">
				<a href="view_category?id=${category.categoryId }">
					<font size="+1"><b><c:out value="${category.name }" /></b></font>
				</a>
				<c:if test="${not status.last }">
					&nbsp; | &nbsp;
				</c:if>
			</c:forEach>
		</div>
	</div>