<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,pojo.*"%>
<%@page import="com.assesment.serviceticketresolution.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
form {
	background-color: powderblue;
	text-align: center;
	border-style: solid;
	margin-top: 100px;
	margin-bottom: 100px;
	margin-right: 150px;
	margin-left: 80px;
}

td {
	text-align: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service Engineer</title>
</head>
<body>
	<form method="post">
		<h1 align="center">Service Ticket Resolution</h1>
		<h2>
			<%
				out.println("Welcome " + session.getAttribute("user"));
			%>
		</h2>
		
			 <a href="AdminServlet?varname=addengineer">Add ServiceEngineer</a>|
			<a	href="AdminServlet?varname=adduser">Add User</a></br></br>
			<% ArrayList<ServiceEngineer> list = (ArrayList<ServiceEngineer>) request.getAttribute("engineers");
			if(list.size()>0)
			{%>
		<table border="1" align="center">
			<thead>
				<tr>
					<th>Service Engineer</th>
					<th>Tickets Worked</th>
					<th>Department</th>
					<th>Action</th>
				</tr>
			</thead>
			<%} %>
			<%
			if(list.size()>0)	{
			for (ServiceEngineer bean : list) {
			%>
			<tr>
				<td><%=bean.getServiceengineer()%></td>
				<td><%=bean.getNo_of_tickets_worked()%></td>
				<td><%=bean.getDepartment().getDepartment_name()%></td>
				<td><a href="AdminServlet?varname=delete&id=<%=bean.getServiceengineer()%>">delete</a></td>
			</tr>
			<%}
				}
			%>
		</table><br><br>
		
		
		

	</form>
</body>
</html>