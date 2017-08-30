<%@page import="com.oocl.pojo.Order"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>O2O[C] - Order List</title>
    
    <link rel="stylesheet" href="${ctx}/assets/css/views/base.css">
    <link rel="stylesheet" href="${ctx}/assets/css/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/views/ordersPage/orderlist.css">
    <script type="text/javascript">
        var contextPath = "${ctx}";
        var loginCustomerId = "${loginCustomer.id}";
    </script>
</head>
<body>
    <nav class="navbar navbar-inverse">
        <%@ include file="/assets/templates/nav-nosearch.jsp" %>
    </nav>
   
    <div class="container">
        <h2>Orders</h2>
        
        <div id="order-accepted-notification" class="alert alert-success" role="alert">HAHAHA</div>
        <div id="order-rejected-notification" class="alert alert-danger" role="alert">HAHAHA</div>
        
        <ul class="list-group">
            <c:forEach items="${orders}" var="o">
                <c:if test="${o.status == -1}">
                    <li class="list-group-item item-danger">
                </c:if>
                <c:if test="${o.status == 0}">
                    <li class="list-group-item item-warning">
                </c:if>
                <c:if test="${o.status == 1}">
                    <li class="list-group-item item-info">
                </c:if>
                <c:if test="${o.status == 2}">
                    <li class="list-group-item item-primary">
                </c:if>
                <c:if test="${o.status == 3}">
                    <li class="list-group-item item-success">
                </c:if>
                    <a href="${ctx}/order/${o.id}">
                        <div class="float-left">
                            <h3>${o.merchantVO.mName}</h3>
                        </div>
                        <div class="float-right">
                            <p><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd HH:mm"/></p>
                            <h4>Status: <span>
                                <c:if test="${o.status == -1}">REJECTED</c:if>
                                <c:if test="${o.status == 0}">PENDING</c:if>
                                <c:if test="${o.status == 1}">ACCEPTED</c:if>
                                <c:if test="${o.status == 2}">DELIVERED</c:if>
                                <c:if test="${o.status == 3}">RECEIVED</c:if>
                            </span></h4>
                            <h4>Price: ${o.totalPrice}</h4>
                        </div>
                        <div class="clear"></div>
                    </a>
                </li>
            </c:forEach>
<!--
            <li class="list-group-item">
                <a href="${ctx}/order/1">
                    <div class="float-left">
                        <h3>KFC</h3>
                    </div>
                    <div class="float-right">
                        <p>2010-04-05 09:34</p>
                        <h4>Status: Pending</h4>
                        <h4>30.00</h4>
                    </div>
                    <div class="clear"></div>
                </a>
            </li>
            
            <li class="list-group-item">
                <a href="${ctx}/order/1">
                    <div class="float-left">
                        <h3>KFC</h3>
                    </div>
                    <div class="float-right">
                        <p>2010-04-05 09:34</p>
                        <h4>Status: Pending</h4>
                        <h4>30.00</h4>
                    </div>
                    <div class="clear"></div>
                </a>
            </li>
            
            <li class="list-group-item">
                <a href="${ctx}/order/1">
                    <div class="float-left">
                        <h3>KFC</h3>
                    </div>
                    <div class="float-right">
                        <p>2010-04-05 09:34</p>
                        <h4>Status: Pending</h4>
                        <h4>30.00</h4>
                    </div>
                    <div class="clear"></div>
                </a>
            </li>
-->
        </ul>
    </div>
    
    <script type="text/javascript" src="${ctx}/assets/js/libs/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/libs/bootstrap.min.js"></script>
    
    <!-- WebSocket Config -->
    <script type="text/javascript" src="${ctx}/assets/js/utils/websocketutil.js"></script>
    
    <script type="text/javascript" src="${ctx}/assets/js/views/orderlist.js"></script>
</body>
</html>