<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Edit User</title>
<script language = "Javascript">

function validateEmail() {
    var x = document.forms["input"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
}
</script>

</head>
<body>
<h3>Edit User Profile</h3>
<form name="input" method="get" onSubmit="return validateEmail()">
User Name: <input type="text" name="usrname" value="${usr.username}" disabled/> <br>
Nickname: <input type="text" name="nickname" value="${usr.nickName}"/> <br>
First Name: <input type="text" name="firstname" value="${usr.firstName}"/> <br>
Last Name: <input type="text" name="lastname" value="${usr.lastName}"/> <br>
User Email Id: <input type="text" name="email" value="${usr.userEmail}"/> <br><br>
<input type="hidden" name="edit" value="edit" />
<input type="submit" value="Save"/><br>
<a href="search">
	<input type="button" value="Back"/>
</a>
</form>
</body>
</html>
