<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="javax.ws.rs.client.*"%>
<%@page import="javax.ws.rs.core.*"%>
<%@page import="org.glassfish.jersey.client.ClientConfig"%>
<%@page import="java.util.*"%>
<%@page import="com.assesment.serviceticketresolution.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Users</title>
</head>
<style>
body {
	border-style: solid;
	border-width: medium;
	
}

h2,table {
	align:center;
	text-align: center;
	color: black
}
</style>
<body>
	<h2>Average Time Taken</h2>
	<br>
	<br>
	<center>
		<table border="1" >
			<thead>
				<tr>
					<th>Service Engineer Name</th>
					<th>Average Time</th>
				</tr>
			</thead>
			<%
			List<String> list = (List<String>)request.getAttribute("EngineersSeverity");
			for (int i=0;i<list.size();i+=2) {
				out.println("<tr><td>"+list.get(i)+"</td><td>"+list.get(i+1)+"</td></tr>");				}			
			%>
		</table></center>
		</body>
		</html>