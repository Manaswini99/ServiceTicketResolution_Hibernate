<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.ws.rs.client.*"%>
<%@page import="javax.ws.rs.core.*"%>
<%@page import="org.glassfish.jersey.client.ClientConfig"%>
<%@page import="java.util.*"%>
<%@page import="pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
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
<form   method="post" action="UserServlet?varname=issues" >
 <h1 align="center" >Sevice Ticket Resolution</h1>
 <table>
 <h2 align="center" ></h2>
<h3>Select Issue</h3>
<select name="department">
<% 
for(DepartmentBean department: departments){
	out.println("<option value="+department.getDepartment_id()+">"+department.getDepartment_name()+"</option>");
}
%>
</select>
<h3>Description:</br><input type="text" name="issue" /></h3>
<h3>Requested End Date</h3><input type="date" name="date" />
 <h3>Ticket Priority</h3>
 <select name="priority">
  <option value="low">Low</option>
  <option value="medium">Medium</option>
  <option value="high">High</option>
  </select></br>
  <input type="submit" value="submit" />
</table>
</form>

</body>
</html>