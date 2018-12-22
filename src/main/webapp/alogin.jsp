<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	<div class="container" style="margin-top:50px">
	  <h3>User Management</h3>
	  <div class="row">
	  
	    <div class="col-md-12">
	     <form>
	     	<label for="keyword">Content : </label>
            <input type="text" id="keyword" name="keyword" size="20" placeholder="Search ...."><br>
            
            <input type="radio" id="gender" name="choice" value="gender" checked>Gender
           
            <input type="radio" id="email" name="choice" value="email">Email <br />
            <button type="button" onclick="myFunction(this.form.keyword.value,this.form.choice.value)" >Search</button>
	     </form> 
	    </div>
	    
	    <div class="col-md-12" id="users"> 
	      <table>
	      <tr>
	      <td>User Id</td>
	      <td>User Name</td>
	      <td>Email</td>
	      <td>Gender</td>
	      <td>Birthday</td>
	      </tr>
			<c:forEach items="${userList}" var="user">
			<tr id="${user.personID}">	
			    <td><c:out value="${user.personID}"/> </td>
				<td><c:out value=" ${user.fname} ${user.lname}"/></td>
				<td><c:out value=" ${user.email}"/></td>
				<td><c:out value="${user.gender}"/></td>
				<td><c:out value="${user.birth}"/> </td>
				<td><input type="button" class="delete" value="Delete" onclick="deleteRow(${user.personID})" />    </td>
			</tr>
  			</c:forEach>
	     </table>
	    </div>	
	        
	  </div>
	  
	  <h3>Report Process</h3>
	  <!-- show the messages which are reported more than 10 times-->>
	</div>
	
	<script>
	
	function myFunction(keyword,choice){
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.onreadystatechange=function() {
		if (xmlHttp.readyState==4 && xmlHttp.status==200) 
		document.getElementById("users").innerHTML=xmlHttp.responseText; 
		};
		xmlHttp.open("GET","admin-search.htm?keywords="+keyword+"&option="+choice,true);
		xmlHttp.send(); 
	}
	
	function deleteRow(uid) {
		
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
			//alert("test!");
			 // ask confirmation 
			confirm("Are you sure you want to delete this entry?");
			$('"#'+uid+'"').remove();
			//jQuery("#"+fid).remove();
		}
	}
	
	xmlHttp.open("GET","deleteUser.htm?cusid="+uid,true);
	xmlHttp.send();  
    }
	</script>
	
</body>
</html>