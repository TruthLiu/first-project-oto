<%@page import="javax.management.loading.MLet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../file/base/base.jsp" %>
<link rel="stylesheet" href="${ctx}file/bootstrap/css/style.css" >
<title>Insert title here</title>
<script type="text/javascript">
	function test(date,obj) {
		parent.right.location.href="${ctx}"+obj;
	}
	
	function url(){
		var url;
		url = "merchant/sec/message";
		parent.right.location.href="${ctx}"+url;
	}
		
</script>

<style type="text/css">
span{
	color: #FFFFFF;
}


</style>
</head>

<body onload="url()" class="sticky-header">

<div style="height:100%;width:100%;overflow-x:hidden;overflow-y:scroll;position:absolute;">
	<table height="100%">
		<tr>
			<td width="120px" valign="top" class="left_menu">
				<table>
					<tr>
					<section>
						<ul id="nav nav-pills nav-stacked custom-nav">
						<c:forEach items="${menuList}" var="ml"  varStatus="status">
						<c:if test="${status.index==0}">
							<li class="active" >
								<span class="fo" style="cursor:Pointer" onclick="test(this,'${ml.url}');">
								<i class="fa fa-home"><img src="${ctx}${ml.img}" /></i>
								${ml.itemName}
								</span>
							</li>
						</c:if>
						<c:if test="${sessionScope.merchant.status==1}">
							<c:if test="${status.index!=0}">
								<li class="menu-list">
									<span class="fo" style="cursor:Pointer" onclick="test(this,'${ml.url}');">
									<i class="fa fa-home"><img src="${ctx}${ml.img}" /></i>${ml.itemName}
									</span>
								</li>
							</c:if>
						
						</c:if>
						
						</c:forEach>
						</ul>
						</section>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>

</body>
</html>