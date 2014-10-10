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
    <th>Genre</th> 
    <th>Star Cast</th> 
    <th>Rating</th>
  </tr>
  <c:forEach items="${searchMovies}" var="srMovie">
	  <tr>
	    <td><a href="searchDetails">${srMovie.title}</a><input type="hidden" name="title" value="${srMovie.title}"></td>
	    <td><img src="${srMovie.poster}" style="width:154px;height:128px"></td> 
	    <td>${srMovie.genre}</td>
	    <td>${srMovie.starcast}</td>
	    <td>${srMovie.ageRating}</td>
	    <td><a href="searchDetails">Details</a></td>
	  </tr>
  </c:forEach>
</table>
</body>
</html>