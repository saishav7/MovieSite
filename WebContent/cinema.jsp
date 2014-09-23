<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Cinema</title>
</head>
<body>
<h3>Add New Cinema</h3>
<form name="input" method="get">
Location: <input type="text" name="location">
Seating Capacity: <input type="text" name="seatingCap"><br><br>
Amenities :-<br><br>
<input type="checkbox" name="amenities" value="atms">ATMs<br>
<input type="checkbox" name="amenities" value="widescreen">Widescreen<br>
<input type="checkbox" name="amenities" value="snackbar">Snack Bar<br>
<input type="checkbox" name="amenities" value="restaurant">Restaurant<br><br>
<input type="submit" value="Add Cinema">
</form>
</body>
</html>