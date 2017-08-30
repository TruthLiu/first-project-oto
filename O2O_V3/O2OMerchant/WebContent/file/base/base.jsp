<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>

<%
	String contexPath = request.getContextPath();
	String basicContextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contexPath + "/";
%>


<c:set var="ctx" value="<%=basicContextPath %>"/>
<link rel="stylesheet" href="${ctx}file/css/style.css">
<link rel="stylesheet" href="${ctx}file/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}file/css/sweetalert.css">
<script src="${ctx}file/js/sweetalert.min.js"></script>
<script src="${ctx}file/js/jquery-3.2.1.min.js"></script>
<script src="${ctx}file/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}file/js/ajaxfileupload.js"></script>
<script src="${ctx}file/js/ajaxOperator.js"></script>

<script type="text/javascript">
        var contextPath = "${ctx}";
</script>

<style type="text/css">
body{
background:#E8E8E8;

}
</style>