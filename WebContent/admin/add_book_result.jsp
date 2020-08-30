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
		<h2>The book <i>${book.title }</i> has been added to the order ID <b>${order.orderId }</b></h2>
		<button type="button" id="closePopup" onclick="javascript: self.close();">Close</button>
	</div>
</body>
</html>
<script>
	window.onunload = refreshParent;
    function refreshParent() {
        window.opener.location.reload();
    }
</script>


