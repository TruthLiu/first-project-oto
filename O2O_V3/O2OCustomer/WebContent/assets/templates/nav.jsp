<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${ctx}">O2O[C]</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <ul class="nav navbar-nav navbar-right">
            <li id="search-dtype" class="dropdown" data-index="0">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">所有 <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:forEach items="${ dishTypes }" var="t" varStatus="status">
                        <li><a href="#" class="dish-type-selector" data-index="${status.index}">${ t.name }</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li>
                <form id="search-form" class="navbar-form navbar-left" role="search">
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="Search" style="margin-top: 1px;margin-bottom: -1px;">
<!--                        <a href="javascript:void(0)" class="input-group-addon">-->
                        <span class="input-group-addon glyphicon glyphicon-search"></span>
<!--                        </a>-->
                    </div>
                </form>
            </li>

            <c:if test="${loginCustomer.cname == null}">
                <li>
                    <a href="${ctx}/login">Login</a>
                </li>
            </c:if>
            <c:if test="${loginCustomer.cname != null}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${loginCustomer.cname} <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${ctx}/order">My Orders</a>
                            <form action="${ctx}/api/logout" method="post">
                                <button type="submit">Logout</button>
                            </form>
                        </li>
                    </ul>
                </li>
            </c:if>

        </ul>
    </div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->