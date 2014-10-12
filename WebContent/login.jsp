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
<c:set var="logoutMsg" value='<%= request.getAttribute("logoutMessage") %>'/>
<span style="color:#ff0000"><c:if test="${not empty logoutMsg}"><i><c:out value="${logoutMsg}" /></i></c:if></span>
<form name="input" method="post">
<input type="hidden" name="submitLogin">
Username: <input type="text" name="username"><br>
Password: <input type="password" name="password"><br>
<span style="color:#ff0000"><i>${loginFailure}</i></span>
<br>
<input type="submit" value="Login"><br>
</form>
<input type="button" value="Register Now" onClick="location.href='register'"><br><br>
<div style="overflow: auto;overflow-x:'scroll';">
	<h4>NOW SHOWING</h4>
	
	<table width="100%">
	<tr>
	<c:forEach items="${nowShowingMovies}" var="nsMovie">
	<td>
	<table>
		  <tr>
		    <td align="center">${nsMovie.title}</td>
		  </tr>
		  <tr>
		    <td align="center"><img src="${nsMovie.poster}" style="width:240px;height:330px"></td> 
		  </tr>
		  <tr>
		    <td align="center">${nsMovie.ageRating}</td>
		  </tr>
		  <tr>
		    <td align="center">${nsMovie.synopsis}</td>
		  </tr>
	</table>
	<td>
	</c:forEach>
	</tr>
	</table>
</div>
 <br>
<div style="overflow: auto;overflow-x:'scroll';">
	<h4>COMING SOON</h4> 
	
	<table width="100%" >
	<tr>
	<c:forEach items="${comingSoonMovies}" var="csMovie">
	<td>
	<table>
		  <tr>
		    <td align="center">${csMovie.title}</td>
		  </tr>
		  <tr>
		    <td align="center"><img src="${csMovie.poster}" style="width:240px;height:330px"></td> 
		  </tr>
		  <tr>
		    <td align="center">${csMovie.ageRating}</td>
		  </tr>
		  <tr>
		    <td align="center">${csMovie.synopsis}</td>
		  </tr>
	</table>
	<td>
	</c:forEach>
	</tr>
	</table>
</div>

</body>
</html>