<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search for movies</title>
</head>
<body>

<form name="search" method="post" action="search">
<h3>Hello <%= request.getSession(false).getAttribute("username").toString()%>!</h3>
<a href="editUser">Edit Profile</a><br><br>
Enter Movie Title <input type="text" name="title"><br><br>
OR Search on a<br><br>
Genre:<select name="genre">
  	<option value="All">All</option>
  	<option value="Romance">Romance</option>
  	<option value="Thriller">Thriller</option>
  	<option value="Horror">Horror</option>
  	<option value="Comedy">Comedy</option>
  	<option value="Drama">Drama</option>
  	<option value="Biopic">Biopic</option>
  	<option value="Action">Action</option>
	</select><br><br>
<input type="submit" value="Search"><br><br>
</form><br>
<a href="">My Selection</a>
</body>
</html>