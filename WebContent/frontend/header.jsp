<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<div class="center">
		<div>
			<a href="${pageContext.request.contextPath}">
				<img alt="Book Store" src="${pageContext.request.contextPath}/images/BookstoreLogo.png" />
			</a>
		</div>
		
		<div>
			<form action="search" method="GET">
				<input type="text" name="keyword" size="50" />
				<input type="submit" value="Search" />
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				<c:if test="${loggedCustomer == null }">
					<a href="login">Sign in</a> | 
					<a href="register">Register</a> |
				</c:if>
				
				<c:if test="${loggedCustomer != null }">
					<a href="view_profile">Welcome, ${loggedCustomer.fullname }</a> | 
					<a href="view_order">My Orders</a> |
					<a href="logout">Sign Out</a> |
				</c:if>
				
				<a href="login">Cart</a>
			</form>
		</div>
		<div>&nbsp;</div>
		<div>
			<c:forEach var="category" items="${listCategories }" varStatus="status">
				<a href="view_category?id=${category.categoryId }&name=${category.name}">
					<font size="+1"><b><c:out value="${category.name }" /></b></font>
				</a>
				<c:if test="${not status.last }">
					&nbsp; | &nbsp;
				</c:if>
			</c:forEach>
		</div>
	</div>