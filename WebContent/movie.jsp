<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Movie</title>
</head>
<body>
<h3>Add New Movie</h3>
<h4><i>(Please select poster first)</i></h4>
<form name="input" method="get">
Poster: <input type="button" name="uploadPoster" value="Upload" onClick="location.href='poster'">${message}<br>
Title: <input type="text" name="title"><br>
Starcast: <input type="text" name="starcast"><br>
Genre:<select name="genre">
	  	<option value="Romance">Romance</option>
	  	<option value="Thriller">Thriller</option>
	  	<option value="Horror">Horror</option>
	  	<option value="Comedy">Comedy</option>
	  	<option value="Drama">Drama</option>
	  	<option value="Biopic">Biopic</option>
	  	<option value="Action">Action</option>
	</select><br>
Director: <input type="text" name="director"><br>
Short Synopsis: <input type="text" name="synopsis"><br>
Age Rating: <input type="text" name="rating"><br>
Release Date: <input type="text" name="releaseDate"><i>(dd/mm/yyyy) e.g.10/11/2014</i> <br><br> 
<input type="hidden" name="movieForm" value="movieForm">
<input type="submit" value="Add Movie">
</form>
</body>
</html>