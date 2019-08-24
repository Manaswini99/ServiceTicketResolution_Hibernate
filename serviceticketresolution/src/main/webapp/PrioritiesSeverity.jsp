<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Priorities Severity</title>
</head>
<body>
<%@ include file="ServiceEngineer.jsp"%> 
<br><br> 
<% ArrayList<String> list = (ArrayList<String>)request.getAttribute("priorityperseverities");
%>
<p><%out.print("Average Timetaken for low Priority tickets:"+list.get(0));%></br>
<%out.print("Average Timetaken for Medium Priority tickets:"+list.get(1));%></br>
<%out.print("Average Timetaken for High Priority tickets:"+list.get(2));%></br>
</p><br><br>
</body>
</html>