a<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">Weclome To NUPal</a>
	    </div>
	    <!--
	    <ul class="nav navbar-nav ">
	      <li class="active"><a href="#">Home</a></li>
	      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
	        <ul class="dropdown-menu">
	          <li><a href="#">Page 1-1</a></li>
	          <li><a href="#">Page 1-2</a></li>
	          <li><a href="#">Page 1-3</a></li>
	        </ul>
	      </li>
	      <li><a href="#">Page 2</a></li>
	    </ul>
	    -->
	    <!-- Log in -->
	    <form class="navbar-form navbar-right" action="login.htm" method="post">
	      <div class="form-group">
	        <input type="email" name="email" class="form-control" placeholder="Email">
	      </div>
	      <div class="form-group">
	        <input type="password" name="password" class="form-control" placeholder="Password">
	      </div>
	      <button type="submit" class="btn btn-default">Log in</button>
	    </form>
	  </div>
	</nav>
	  
	  <!-- Register -->
	<div class="container" style="margin-top:50px">
	  <h1>Create Your Own Account</h1>
	  <h4>Nice meeting you!</h4>
	  
	  <h4><span style="color:red">!!!</span>You Must Fill All Fields with <span style="color:red">*</span></h4>
	  <form action="add.htm" method="post">
	  	First Name:<input type="text" name="fname" placeholder="First Name"><br>
	  	Last Name:<input type="text" name="lname" placeholder="Last Name"><br>
	  	<span style="color:red">*</span>Email:<input type="email" name="email" placeholder="Email"><br>
	  	<span style="color:red">*</span>Password:<input type="password" name="password" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
	  	title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
	  	<lable style="color:red">[at least 8 characters with numbers, uppercase and lowercase letters]</lable>
	  	<br />
	  	<span style="color:red">*</span>
	  	<lable>Birthday:</lable>
	  	<select name="birthday_month">
	  		<option value="Null" selected="1">Month</option>
	  		<option value="1">Jan</option>
	  		<option value="2">Feb</option>
	  		<option value="3">Mar</option>
	  		<option value="4">Apr</option>
	  		<option value="5">May</option>
	  		<option value="6">Jun</option>
	  		<option value="7">Jul</option>
	  		<option value="8">Aug</option>
	  		<option value="9">Sep</option>
	  		<option value="10">Oct</option>
	  		<option value="11">Nov</option>
	  		<option value="12">Dec</option>
	  	</select>
	  	
	  	<select name="birthday_day">
	  		<option value="Null" selected="1">Day</option>
	  		<option value="1">1</option>
	  		<option value="2">2</option>
	  		<option value="3">3</option>
	  		<option value="4">4</option>
	  		<option value="5">5</option>
	  		<option value="6">6</option>
	  		<option value="7">7</option>
	  		<option value="8">8</option>
	  		<option value="9">9</option>
	  		<option value="10">10</option>
	  	</select>
	  	
	  	<select name="birthday_year">
	  		<option selected="1">Year</option>
	  		<option value="2017">2017</option>
	  		<option value="2016">2016</option>
	  		<option value="2015">2015</option>
	  		<option value="2014">2014</option>
	  		<option value="2013">2013</option>
	  		<option value="2012">2012</option>
	  	</select>
	  	
	  	<br>
	  	<lable>Gender:</lable>
        <input type="radio" name="gender" value="female" checked> Female
        <input type="radio" name="gender" value="male"> Male<br>
        
        <input type="submit" class="btn btn-primary" value="Create Account">
	  </form>
	</div>
	
	
</body>
</html>
