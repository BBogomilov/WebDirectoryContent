<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/HomePageStyle.css">

<title>Web Directory Content App</title>

<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/homePageJS.js"></script>

</head>

<body>

	<h1 style="color: #D2B48C;">Directory content reader app</h1>
	<form>
		<iframe
			src="http://free.timeanddate.com/clock/i5ee74fs/n238/tlbg11/fn13/fs30/fcfff8dc/tct/pct/ftb/tt0/tw1/tm1/th1"
			class="clock" frameborder="0" width="415" height="36"
			allowTransparency="true"></iframe>
	</form>
	<%
		if (request.getAttribute("inputError") != null) {
	%>
	<h2 style="color: #FF0000; position: relative; left: 10px; top: -40px;"><%=request.getAttribute("inputError")%></h2>
	<%
		}
	%>

	<form id="main-form" name="main-form" class="submitForm" method="post">

		<label for="directory">Directory absolute path: </label> <input
			type="text" name="directory" class="directory" /> <br> <br>
		<br> <input id="extract-submit" type="submit" name="btn1"
			value="Extract content" class="submitBtn" /> <input
			id="loadDB-submit" type="submit" name="btn2" value="Load in DataBase"
			class="DBLoadBtn" /> <br> <label for="squaredFour"
			class="chkbox">Download output</label>

		<div class="squaredFour">
			<input type="checkbox" value="Y" id="squaredFour" name="check" /> <label
				for="squaredFour"></label>
		</div>
	</form>

	<form id="search-form" name="search-form" onsubmit="validate(event);"
		method="get" action="/WebDirCont/search">

		<input id="searchID" class="searchTab" type="text" name="search"
			placeholder="Search file in DataBase.."> <input
			id="submitSearchID" type="submit" name="searchBtn"
			class="searchSubmit" value="Search">
	</form>
	<%
		if (request.getAttribute("results") != null) {
			String[] files = ((String) (request.getAttribute("results"))).split("<br>");
			for (int i = 0; i < files.length; i++) {
	%>
	<font class="searchFont" size="5" face="Comic Sans MS" color="#FFF8DC">
		<%=files[i]%> <br>
	</font>
	<%
		}
		}
	%>


</body>
</html>