<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.ws.rs.client.*,pojo.*"%>
<%@page import="javax.ws.rs.core.*"%>
<%@page import="org.glassfish.jersey.client.ClientConfig"%>
<%@page import="java.util.*"%>
<%@page import="com.assesment.serviceticketresolution.*"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"></head>
<style>table
{
	text-align: center;
}
td{
	text-align:left;	
}</style>

<title>Add Engineer</title>

<body>
<%
Client client = ClientBuilder.newClient(new ClientConfig());
String apiURL = "http://localhost:8080/serviceticketresolution/webapi/myresource";
WebTarget webTarget = client.target(apiURL).path("deptlist");
Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
GenericType<ArrayList<DepartmentBean>> gType = new GenericType<ArrayList<DepartmentBean>>() {
};
Response clientResponse = invocationBuilder.get();
ArrayList<DepartmentBean> departments = clientResponse.readEntity(gType);
%>

<form method="post" action="AdminServlet?varname=addeng">
<h2 align="center" >Sevice Ticket Resolution</h2>
<table align="center">
<tr><td>Enter Name of Engineer</td></tr>
<tr><td><input type="text" name="name"/></td></tr>
<tr><td>Enter new Password</td></tr>
<tr><td><input type="password" name="password"/></td></tr>
<tr><td>Select Area of Expertise</td></tr>
<tr><td><select name="department">
<% 

for(DepartmentBean department: departments){
	out.println("<option value="+department.getDepartment_id()+">"+department.getDepartment_name()+"</option>");
}
 %></td></tr>

<tr><td><input type="submit" name="Add"/></td></tr>
</table>
</form>
<form action="Admin.jsp">
<table align="center">
	<tr><td><input type="submit" value="back"><br><br></td></tr>
	</form>
	</table>
</body>
</html>