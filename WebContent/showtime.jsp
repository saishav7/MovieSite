<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Showtimes</title>
</head>
<body>
<h3>Add Show Timings</h3>
<form name="input" method="get">
Movie Title :- 
<select name="movie_title">
  <c:forEach items="${nowShowingMovies}" var="movie">
  	<option value="${movie.title}">${movie.title}</option>
  </c:forEach>
</select>

Cinema Location :-
<select name="cinema_location">
  <c:forEach items="${allCinemas}" var="cinema">
  	<option value="${cinema.location}">${cinema.location}</option>
  </c:forEach>
</select>

Show Timings: <input type="text" name="timings">(Seperate with comma for multiple entries e.g. <i>11:15,15:45,18:15</i>)
<br><br>
<input type="submit" value="Add Shows">
</form>
</body>
</html>