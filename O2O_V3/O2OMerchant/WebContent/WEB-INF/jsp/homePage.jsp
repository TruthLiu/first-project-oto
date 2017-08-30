<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../file/base/base.jsp" %>
<title>Insert title here</title>
</head>

<frameset rows="61px,*" frameborder="0">
	<frame id="logo" name="logo" src="${ctx}merchant/sec/toolbar" />
	<frameset cols="240px,*" frameborder="0" style="background-color:#FA8072">
		<frame id="left" name="left" src="${ctx}merchant/sec/menuList" scrolling="no" frameborder="0" framspacing="0" noresize />
		<frame id="right" name="right" src="" scrolling="auto" frameborder="0" framspacing="0" noresize/>
	</frameset>
</frameset>


</html>