<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="controller.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
<form action="LoginServlet"   method="post">
		<label for="username"><b>Username</b></label> <input type="text"
			name="username" placeholder="Enter Username" /></br>
		</br> <label for="password"><b>Password</b></label> <input type="password"
			name="password" placeholder="Enter Password" /></br>
		</br> <input type="submit" name="submit" value="login" />
	</form>
</body>
</html> 