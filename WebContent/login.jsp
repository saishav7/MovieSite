<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Cinema</title>
</head>
<body>
<h3>Welcome to AU Movies</h3>
<form name="input" method="post">
<input type="hidden" name="submitLogin">
Username: <input type="text" name="username"><br>
Password: <input type="password" name="password"><br><br>
<input type="submit" value="Login"><br>
</form>
<input type="button" value="Register Now" onClick="location.href='register'"><br><br>

<h4>NOW SHOWING</h4>
<table>
  <tr>
    <th>Title</th>
    <th>Poster</th> 
    <th>Rating</th>
  </tr>
  <c:forEach items="${nowShowingMovies}" var="nsMovie">
	  <tr>
	    <td>${nsMovie.title}</td>
	    <td><img src="${nsMovie.poster}" style="width:304px;height:228px"></td> 
	    <td>${nsMovie.ageRating}</td>
	  </tr>
  </c:forEach>
</table>
 <br>
<h4>COMING SOON</h4> 
<table>
  <tr>
    <th>Title</th>
    <th>Poster</th> 
    <th>Rating</th>
  </tr>
  <c:forEach items="${comingSoonMovies}" var="csMovie">
	  <tr>
	    <td>${csMovie.title}</td>
	    <td><img src="${csMovie.poster}" style="width:304px;height:228px"></td> 
	    <td>${csMovie.ageRating}</td>
	  </tr>
  </c:forEach> 
</table>

</body>
</html>