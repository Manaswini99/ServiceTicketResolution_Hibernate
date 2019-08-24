<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="javax.ws.rs.client.*"%>
<%@page import="javax.ws.rs.core.*"%>
<%@page import="org.glassfish.jersey.client.ClientConfig"%>
<%@page import="java.util.*"%>
<%@page import="com.assesment.serviceticketresolution.*,pojo.*"%>
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

h2 {
	text-align: center;
	color: black
}
</style>
<body>
	<h2>Tickets Raised</h2>
	<br>
	<br>
	<form method="get">
		<table border="1" >
			<thead>
			<h3>Solved Tickets</h3>
				<tr>

					<th>Ticket Id</th>
					<th>Issue Date</th>
					<th>Department</th>
					<th>Requested EndDate</th>
					<th>Completed Date</th>
					
					
				</tr>
			</thead>


			<%
				ArrayList<TicketBean> list = (ArrayList<TicketBean>)request.getAttribute("tickets");
			for (TicketBean bean : list) {
				if(bean.getStatus().equals("completed"))
				{
 %>
			<tr>

				<td><%=bean.getTicketid()%></td>
				<td><%=bean.getStart_date()%></td>
				<td><%=bean.getDepartment().getDepartment_name()%></td>
				<td><%=bean.getRequestedend_date()%></td>
				<td><%=bean.getCompleted_date()%></td>
			</tr>
			<%
				}
			}
			%>
		</table>
		<table border="1" >
			<thead>
			<h3>Unsolved Tickets</h3>
				<tr>

					<th>Ticket Id</th>
					<th>Issue Date</th>
					<th>Department</th>
					<th>Requested EndDate</th>
					</tr>
			</thead>


			<%
				ArrayList<TicketBean> unsolved = (ArrayList<TicketBean>)request.getAttribute("tickets");
				
			for (TicketBean bean : unsolved) {
				if(bean.getStatus().equals("ongoing")||bean.getStatus().equals("pending"))
				{
 %>
			<tr>

				<td><%=bean.getTicketid()%></td>
				<td><%=bean.getStart_date()%></td>
				<td><%=bean.getDepartment().getDepartment_name()%></td>
				<td><%=bean.getRequestedend_date()%></td>
				
			</tr>
			<%
				}
			}
			%>
		</table>
</body>
</html>