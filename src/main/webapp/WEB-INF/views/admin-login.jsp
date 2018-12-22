<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="container-fluid">
		  <div class="navbar-header">
	      	<a class="navbar-brand" href="#">Weclome To Admin Page</a>
	   	  </div>
	  </div>
	</nav>
	
	<div class="container" style="margin-top:100px">
	<form action="login-admin.htm" method="POST">
		<!-- form action="admin.htm" method="post"-->
		<h4>Please input username and password</h4>
			<input type="text" name="username" placeholder="User Name"><br>
		  	<input type="password" name="password" placeholder="Password"><br>
		  	<input type="submit" value="Login"/>
	</form>
	</div>
</body>
</html>