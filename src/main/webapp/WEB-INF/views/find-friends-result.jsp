<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<title>Insert title here</title>
</head>
<body>
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
	
<p style="margin-top:60px">People</p>
<ul>
<c:forEach items="${friendList}" var="friend">
<li id="${friend.personID}">
   <c:out value="${friend.fname} ${friend.lname}"/> 
   <button type="button" onclick="myFunction(${friend.personID},${customer.personID})">AddFriend</button><br />
</li>
</c:forEach>
</ul>

<script>
function myFunction(fid,cusid){
	var xmlHttp;
	try{
		xmlHttp = new XMLHttpRequest();
	}catch(e){
	try{
		xmlHttp = new ActiveXObject("Msxm12.XMLHTTP");
	}catch(e){
		try{
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}catch(e){
			alert("Your brower does not support AJAX!");
			return false;
		}
	}
	}
	
	xmlHttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			alert("You have add a new friend!");
			jQuery("#"+fid).remove();
		}
	};
	
	xmlHttp.open("GET","addfriends.htm?cusid="+cusid+"&friendid="+fid,true);
	xmlHttp.send();
}

</script>
</body>
</html>