<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrator System</title>
<style>
	.outDiv {width:600px; height:320px; border:1px solid gray; margin:0 auto; border-radius:16px}
	.btn {margin:2px; width:90px; height:40px; border:#F00 1px; 
		background:#003366; color:#FFF; border-radius:3px; font-size:18px;}
</style>
</head>
<body text-align="center">
<h1 style="text-align: center">Administrator System</h1>
<br/>
<div class="outDiv">
<br/><br/><br><br/>
<div style="margin:0px auto;">
<form action="login" method="post" style="text-align:center; color:#003366; font-size="24px";">
	<table align="center">
	<tr>
		<td>Name:</td>
		<td><input type="text" name="name" /></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td> <input type="text" name="pwd" /></td>
	</tr>
	</table><br/><br/><br>
	<input type="submit" value="Login" class="btn" />
</form>
</div>
</div>
</body>
</html>