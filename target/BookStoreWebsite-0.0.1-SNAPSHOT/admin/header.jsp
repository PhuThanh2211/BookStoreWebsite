<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>

<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/jquery-ui.min.css" />

<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/jquery.validate.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>

</head>
<body>
	<div align="center">
		<div>
			<img alt="Book Store" src="../${pageContext.request.contextPath}/images/BookstoreAdminLogo.png" />
		</div>
		<div>
			Welcome, <c:out value="${sessionScope.username }" /> | <a href="logout">Logout</a>
		</div>
		<br/>
		<div id="headermenu">
			<div class="menu_item">
				<a href="list_users">
					<img src="../images/users.png" /><br/>Users 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_categories">
					<img src="../images/category.png" /><br/>Categories 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_books">
					<img src="../images/bookstack.png" /><br/>Books 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_customers">
					<img src="../images/customer.png" /><br/>Customers 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_reviews">
					<img src="../images/review.png" /><br/>Reviews 
				</a>
			</div>
			<div class="menu_item">
				<a href="list_orders">
					<img src="../images/order.png" /><br/>Orders 
				</a>
			</div>
		</div>
	</div>