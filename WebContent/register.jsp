<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>New User</title>

<script language = "Javascript">

function emailcheck(str) {
    var at="@";
    var dot=".";
    var lat=str.indexOf(at);
    var lstr=str.length;
    var ldot=str.indexOf(dot);

    if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr || str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
    alert("Invalid E-mail ID");
    return false;
    }

    if (str.indexOf(at,(lat+1))!=-1 || str.indexOf(" ")!=-1){
    alert("Invalid E-mail ID");
    return false;
    }

    if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot || str.indexOf(dot,(lat+2))==-1){
    alert("Invalid E-mail ID");
    return false;
    }

    //alert("valid E-mail ID");
    return true ;
}

function ValidateEmail(){
	var emailID=document.input.eml;
	var nam=document.input.nam;
	var pwd=document.input.pwd;
	var namlen=nam.value.length;
	var pwdlen=pwd.value.length;
	
	if ((nam.value==null)||(nam.value=="")){
		alert("Please Enter your User Name. Should be at least 8 characters long.");
		nam.focus();
		return false;
	}
	if ((pwd.value==null)||(pwd.value=="")){
		alert("Please Enter your Password. Should be at least 8 characters long.");
		pwd.focus();
		return false;
	}
	if (pwdlen<8){
		alert("Please Enter a Password which is at least 8 characters long.");
		pwd.focus();
		return false;
	}
	if ((emailID.value==null)||(emailID.value=="")){
		alert("Please Enter your Email Address");
		emailID.focus();
		return false;
	}
	if (emailcheck(emailID.value)==false){
		emailID.value="";
		emailID.focus();
		return false;
	}
	return true;
}
</script>

</head>
<body>
<h3>Register New User</h3>
<form name="input" method="post" action="#" onSubmit="return ValidateEmail()">
User Name: <input type="text" name="nam"/> <br>
Password: <input type="password" name="pwd" /> <br> <br>
User Email Id: <input type="text" name="eml"/> <br>

<input type="submit" value="Register User">
</form>
</body>
</html>