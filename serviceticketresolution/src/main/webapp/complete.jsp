<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket Completed</title>
</head>
<style>
body {
text-align: center;
}
</style>
<body>
	<%
		int ticketid = (int) session.getAttribute("ticketid");
	%>
	<form method="post"
		action="ServiceEngineerServlet?varname=completedticket">
		<h2> Alloted Ticket</h2>
		<label><%=ticketid%></label>&nbsp;&nbsp;
				<input type="submit" name="complete">
			
	</form>
</body>
</html>