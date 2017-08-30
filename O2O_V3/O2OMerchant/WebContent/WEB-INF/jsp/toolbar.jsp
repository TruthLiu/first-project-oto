<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../file/base/base.jsp" %>





<script type="text/javascript">
	function test(obj) {
		parent.location.href="${ctx}"+obj;
	}
	
	

		var ws = new WebSocket("ws://10.222.29.186:8080/O2OMerchant/websocket/order");
	
		ws.onmessage=function(event){
		console.log("You have a message");
		alert("New message");
	}

	
		
</script>

</head>
<body>
	<div class="toolbar"></div>
	<nav class="navbar navbar-inverse" style="margin-bottom:0px;">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<h1 class="navbar-brand toolbarH1">
				Merchant : <strong>${sessionScope.merchant.mName}</strong>
			</h1>


		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">


		


			<ul class="nav navbar-nav navbar-right">
						<li><a onclick="test('/merchant/sec/logout')" style="color:#FFFFFF">Logout</a></li>
					
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>




</body>
</html>