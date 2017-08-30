<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../../file/base/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx}file/css/comment.css">
</head>
<body>
	<div class="container" align="center">
		<h1 style="margin-top: 50px; margin-bottom: 20px;">Complaint List</h1>
		<div>
			<form class="form-inline">
			<div class="form-group" style="form-inline">
				<label>Time from: </label>
				<input type="date" name="from" class="form-control" style="width:30%"/>&nbsp;
				<strong>to: </strong>
				<input type="date" name="to" class="form-control" style="width:30%"/>&nbsp;&nbsp;
				<button id="search" class="btn btn-primary">Search</button><br/>
			</div>
			</form><br/><br/>
			
			<div id="tab_div">
				<table id="complaintTab" class="table table-striped" style="width:800px; font-size:18px;border-collapse:separate;
    border-spacing:0 0.5rem;"></table>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}file/js/complaint.js"></script>
</body>
</html>