<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>O2O[C] - Welcome!</title>
    
    <link rel="stylesheet" href="${ctx}/assets/css/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/views/base.css">
    <link rel="stylesheet" href="${ctx}/assets/css/views/homePage/home.css">
    <script type="text/javascript">
        var contextPath = "${ctx}";
    </script>
</head>
<body>
    <nav class="navbar navbar-inverse">
        <%@ include file="/assets/templates/nav.jsp" %>
    </nav>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-md-4 pull-right">
                <c:if test="${recommends != null && recommends.size() != 0}">
                    <div class="carousel slide" id="carousel-example-generic" data-ride="carousel"> 
                        <ol class="carousel-indicators">
                            <c:forEach items="${recommends}" varStatus="st">
                                <c:if test="${st.index == 0}">
                                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                </c:if>
                                <c:if test="${st.index != 0}">
                                    <li data-target="#carousel-example-generic" data-slide-to="${st.index}"></li>
                                </c:if>
                            </c:forEach>
            <!--
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li> 
                            <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li> 
                            <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li> 
            -->
                        </ol> 
                        <div class="carousel-inner" role="listbox">
                            <c:forEach items="${recommends}" var="rec" varStatus="st">
                                <c:if test="${st.index == 0}">
                                    <a href="restaurant/${rec.mid}" class="item active">
                                </c:if>
                                <c:if test="${st.index != 0}">
                                    <a href="restaurant/${rec.mid}" class="item">
                                </c:if>
                                    <img src="${ctx}/res/${rec.img}" data-holder-rendered="true" /> 
                                    <div class="carousel-caption">
                                        <h3>${rec.dName}</h3>
                                        <p>${rec.mName}</p>
                                    </div>
                                </a>
                            </c:forEach>

                        </div> 
                        <a href="#carousel-example-generic" class="left carousel-control" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a> 
                        <a href="#carousel-example-generic" class="right carousel-control" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span> </a> 
                    </div>
                </c:if>
            </div>
            
            <div class="col-md-8">
                <div id="main-content">
                    <div class="row">
                        <c:forEach items="${merchants}" var="m">
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <a href="restaurant/${m.id}">
                                    <div class="thumbnail">
                                        <img src="${ctx}/res/${m.imgHead}">
                                        <div class="caption">
                                            <h3>${m.mName}</h3>
                                            <p>Address: ${m.address}</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <div id="fixed-btns"></div>
    </div>
    
    <script type="text/javascript" src="${ctx}/assets/js/libs/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/libs/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/views/home.js"></script>
</body>
</html>