<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css" />

<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>

</head>
<body>
	<div id="wrap">
	<div id="main">
	<div align="center">
		<div>
			<img alt="Book Store" src="${pageContext.request.contextPath}/images/BookstoreAdminLogo.png" />
		</div>
		<div>
			Welcome, 
			<b class="message"><c:out value="${sessionScope.username }" /></b> | <a href="logout">Logout</a>
		</div>
		<br/>
		<div id="headermenu">
			<div class="menu_item">
				<a href="list_users">
					<img src="${pageContext.request.contextPath}/images/users.png" /><br/>Users 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_categories">
					<img src="${pageContext.request.contextPath}/images/category.png" /><br/>Categories 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_books">
					<img src="${pageContext.request.contextPath}/images/bookstack.png" /><br/>Books 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_customers">
					<img src="${pageContext.request.contextPath}/images/customer.png" /><br/>Customers 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_reviews">
					<img src="${pageContext.request.contextPath}/images/review.png" /><br/>Reviews 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_orders">
					<img src="${pageContext.request.contextPath}/images/order.png" /><br/>Orders 
				</a>
			</div>
		</div>
	</div>