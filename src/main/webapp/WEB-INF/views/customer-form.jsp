<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>User Home Page</title>
</head>
<body>

	<!-- Navigation Part -->
	<!-- Find Friends and Login Status -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">		
			<form class="navbar-form navbar-left" action="findfriends.htm" method="get">
		      <div class="form-group">
		        <input type="text" name="friendName" class="form-control" placeholder="Find friends By Name">
		      </div>
				<input type="submit" class="btn btn-primary" value="Search" />
		      <!--button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button-->
		    </form>
		    
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
	<div class="container">
	<!-- Main Page -->
	<div style="margin-top:55px">
	<h1>Welcome Back! ${customer.fname}</h1>
	</div>
	
	<!-- Publish Blog Part -->
	<div style="margin-left:10px">
	<form:form commandName="article" action="upload.htm" method="post" enctype="multipart/form-data">
		<div class="form-group">
		<label for="article">Write Your Story here:</label><br>
		<label>Time:<c:out value="${date}" /></label> <br />
		<form:input path="content" require="required"/><br/>
		Select photo:<span style="color:red">*</span>
		 <input type="file" name="photo" required/><br/>
		<input type="submit" class="btn btn-primary" value="Upload"/>
		</div>
	</form:form>
	</div>
	<hr>
	<!-- List My Article -->
	<div>
		<h4>My Post</h4>
		<table>
		<tr>
		<!--td>Publish Time</td-->
		<td>Content</td>
		<td></td>
		<td>Picture</td>
		</tr>
		<c:forEach items="${customer.articleList}" var="article">
		<tr>
		<td>	
		<td>${article.content}</td>
		<td><img height="150" width="150" src="/images/${article.filename}" /></td>
		</tr>
   		</c:forEach>
	</table>
	</div>
	<hr>
	<!-- Friend List Part -->
	<div>
	<h4>I have followed</h4>
	<table>
	<c:forEach items="${flist}" var="friend">
	<tr>	
		<td><a href="http://localhost:8080/controller/friendsArticle.htm?fid=${friend.personID}">${friend.fname} ${friend.lname}</a></td>
		<!--td><input type="submit" value="View" class="btn btn-primary"/></td-->
	</tr>
   </c:forEach>
	</table>
	</div>
	
	</div>
</body>
</html>