<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="reader.IReader"
	import="reader.ReaderFactory" import="java.io.PrintWriter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Directory contetnt</title>
</head>

<style>
body {
	background-color: #FFDAB9
}
</style>
<body>

	<%
		IReader reader = ReaderFactory.getInstance("n");
		reader.showContent(request.getParameter("directory"));

		PrintWriter writer = response.getWriter();
		String result = "<font size=\"4\" face=\"Comic Sans MS\">" + reader.getResult() + "</font>";
		writer.println(result.replace(System.getProperty("line.separator"), "<br>"));
	%>

</body>
</html>