<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

	<table border="1">
		<thead>
			<h3> Ongoing Ticket</h3>
			<tr>

				<th>Ticket Id</th>
				<th>Issue Date</th>
				<th>Department</th>
				<th>Requested EndDate</th>
				<th>Completed Date</th>
				<th>Customer</th>
				<th>Priority</th>
				<th>Status</th>
				

			</tr>
		</thead>


		<%
				ArrayList<TicketBean> list = (ArrayList<TicketBean>)request.getAttribute("tickets");
				
			for (TicketBean bean : list) {
				if(bean.getStatus().equals("ongoing"))
				{
 %>
		<tr>

			<td><%=bean.getTicketid()%></td>
			<td><%=bean.getStart_date()%></td>
			<td><%=bean.getDepartment().getDepartment_name()%></td>
			<td><%=bean.getRequestedend_date()%></td>
			<td><%=bean.getCompleted_date()%></td>
			<td><%=bean.getCustomer() %></td>
			<td><%=bean.getAcutal_priority()%></td>
			<td><%=bean.getStatus() %></td>
			
		</tr>
		<%
				}
			}
				%>
				</table>
	
	
	<table border="1">
		<thead>
			<h3>Tickets</h3>
			<tr>

				<th>Ticket Id</th>
				<th>Issue Date</th>
				<th>Department</th>
				<th>Requested EndDate</th>
				<th>Completed Date</th>
				<th>Customer</th>
				<th>Priority</th>
				<th>Status</th>
				

			</tr>
		</thead>


		<%
				ArrayList<TicketBean> ongoing = (ArrayList<TicketBean>)request.getAttribute("tickets");
				
			for (TicketBean bean : list) {
				if(bean.getStatus().equals("pending")|| bean.getStatus().equals("completed"))
				{
 %>
		<tr>

			<td><%=bean.getTicketid()%></td>
			<td><%=bean.getStart_date()%></td>
			<td><%=bean.getDepartment().getDepartment_name()%></td>
			<td><%=bean.getRequestedend_date()%></td>
			<td><%=bean.getCompleted_date()%></td>
			<td><%=bean.getCustomer() %></td>
			<td><%=bean.getAcutal_priority()%></td>
			<td><%=bean.getStatus() %></td>
			
		</tr>
		<%
				}
			}
				%>
				</table><br><br>
				
				<form method="post" action="ViewStatisticsServlet?varname=changepriority">
				<label for="ticket"><b>Ticket ID</b></label><input type="text" name="ticket" placeholder="Enter ticket_id"/>
				<select name="newpriority">
				<option value="low">Low</option>
  				<option value="medium">Medium</option>
  				<option value="high">High</option>
				</select>&nbsp;
				<input type="submit" name="submit" value="set"/>
				</form>
</body>
</html>