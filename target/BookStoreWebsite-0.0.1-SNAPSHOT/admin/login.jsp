<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="../css/style.css" />
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<div align="center">
		<h1>Book Store Administration</h1>
		<h2 class="pageheading">Admin Login</h2>
		
		<c:if test="${message != null }">
		<h4 class="message">${message }</h4>
		</c:if>
		
		<form id="formLogin" action="login" method="post">
			<table class="form">
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="20"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#formLogin").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				password: "required"
			},
			
			messages: {
				email: {
					required: "Please enter email",
					email: "Please enter an valid email address"
				},
				password: "Please enter password"
			},
		});
		
		$("#cancelButton").click(function() {
			history.go(-1);
		})
	});
</script>





















