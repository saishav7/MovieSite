<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Add comments</title>
</head>
<body>
<h3>Hello <%= request.getSession(false).getAttribute("firstname").toString()%>!</h3>
<h3>Add comments for movie ${movie}</h3>
<form name="input" method="get" action="addComment">
<input type="hidden" name="cTitle" value="${movie}"><br>
Comment: <input type="text" name="comment"><br>
Rating (Please enter a value from 1 to 5, 5 being the highest): <input type="text" name="rating"><br><br>
<input type="submit" value="Add Comment">
</form>
</body>
</html>