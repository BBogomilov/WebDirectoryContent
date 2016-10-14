<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="service.DBLoadingService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/DBStatusPage.css">
<title>Database load status</title>
</head>

<body>

	<% if (DBLoadingService.isDBLoaded) { %>
	
	<h1 style="color: #FFF8DC;">Database loaded successfully!</h1>
	<br><br>
	<form action="extractFromDB" method="get">
	<input type="submit" name="seeContent" value="See loaded content">
	</form>
	<form action="home" method="get">
	<input type="submit" name="homeBtn" value="Back to Home page" class="homeBtn">
	</form>
	
	<%	} else { %>
	
	<h1 style="color: #FFF8DC;">Something went wrong with the database loading</h1>
	<br><br>
	<form action="home" method="get">
	<input type="submit" name="homeBtn" value="Back to Home page">
	</form>
	<% } %>
</body>
</html>