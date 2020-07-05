<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Internal Server Error</title>
<link rel="stylesheet" href="../css/style.css" />
<script src="../js/jquery-3.1.1.min.js"></script>
</head>
<body>
	<div align="center">
		<div>
			<img alt="Book Store" src="${pageContext.request.contextPath}/images/BookstoreAdminLogo.png" />
		</div>
		<div>
			<h2>Sorry, the requested page could not be found.</h2>
		</div>
		<div>
			<button onclick="goBack()">Go Back</button>
		</div>
	</div>
</body>
</html>
<script>
function goBack() {
	window.history.back();
}
</script>