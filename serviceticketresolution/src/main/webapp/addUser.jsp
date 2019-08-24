<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add User</title>
</head>
<body>
<form method="post" action="AdminServlet?varname=addinguser">
<h2 align="center" >Sevice Ticket Resolution</h2>
<table align="center">
<tr><td>Enter Name of Engineer</td></tr>
<tr><td><input type="text" name="newname"/></td></tr>
<tr><td>Enter new Password</td></tr>
<tr><td><input type="password" name="newpassword"/></td></tr>
<tr><td><input type="submit" name="Add"/></td></tr>
</table>
</form>
</body>
</html>