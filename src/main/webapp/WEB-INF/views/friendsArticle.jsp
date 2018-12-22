<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">		
		    <ul class="nav navbar-nav navbar-right">
		      <li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">${customer.fname} ${customer.lname}
		        <span class="caret"></span></a>
		        <ul class="dropdown-menu">
		          <!-- li><a href="#">Setting</a></li-->
		          <li><a href="logout.htm">Log out</a></li>
		        </ul>
		      </li>
		    </ul>
		 </div>
	</nav>

    <div class="container" style="margin-top:50px">
		<h4>${friend.fname} ${friend.lname}'s Page</h4>
		<table>
		<tr>
		<!--td>Publish Time</td-->
		<td>Content</td>
		<td></td>
		<td>Picture</td>
		</tr>
		<c:forEach items="${friend.articleList}" var="article">
		<tr>
		<td>	
		<td>${article.content}</td>
		<td><img height="150" width="150" src="/images/${article.filename}" /></td>
		</tr>
   		</c:forEach>
	</table>
	</div>

</body>
</html>