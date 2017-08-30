<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>O2O[C] - Error</title>
    
    <link rel="stylesheet" href="${ctx}/assets/css/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/views/errorPage/error.css">
</head>
<body>
    <div class="page">
        <div class="error">
            <h1 class="header">
                O2O[C]
            </h1>
            <div class="content">
                <p>
                    <strong>Oops! 404</strong>
                </p>
                <p>The page you are requesting is not found.</p>
                <hr>
                <p>
                    <a href="javascript:history.back();">Go Back</a>
                </p>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="${ctx}/assets/js/libs/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/libs/bootstrap.min.js"></script>
</body>
</html>