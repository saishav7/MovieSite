<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>

<h3>Hello <%= session.getAttribute("firstname")%>!</h3>
<h4>Your search results:</h4>
<table>
  <tr>
    <th>Title</th>
    <th>Poster</th> 
    <th>Director</th>
    <th>Genre</th>
    <th>Release Date</th>
    <th>Star Cast</th>
    <th>Synopsis</th>
    <th>Rating</th>
  </tr>
  <c:forEach items="${movieDetails}" var="dtMovie">
	  <tr>
	    <td>${dtMovie.title}</td>
	    <td><img src="${dtMovie.poster}" style="width:304px;height:228px"></td> 
	    <td>${dtMovie.director}</td>
	    <td>${dtMovie.genre}</td>
	    <td>${dtMovie.releasedate}</td>
	    <td>${dtMovie.starcast}</td>
	    <td>${dtMovie.synopsis}</td>
	    <td>${dtMovie.ageRating}</td>
	  </tr>
  </c:forEach>
</table><br><br>
<table>
  <tr>
    <th>Nick Name</th>
    <th>Comment</th>
    <th>Rating</th>
  </tr>
  <c:forEach items="${movieComments}" var="mvComments">
	  <tr>
	    <td>${mvComments.usrnname}</td>
	    <td>${mvComments.cmnt}</td>
	    <td>${mvComments.rating}</td>
	  </tr>
  </c:forEach>
</table><br><br>
Enter Release Date to search for cinemas playing this movie<br><br> 
Release Date: <input type="text" name="releaseDate"><br>
<table>
  <tr>
    <th>Cinemas</th>
    <th>Timings</th>
  </tr>
  <c:forEach items="${movieCinemas}" var="mvCinemas">
	  <tr>
	    <td>${mvComments.cinemaLocation}</td>
	    <td>${mvComments.timings}</td>
	  </tr>
  </c:forEach>
</table>
<a href="book">Book</a>
</body>
</html>