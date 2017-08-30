<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>O2O[C] - ${ merchant.mName }</title>
    
    <link rel="stylesheet" href="${ctx}/assets/css/views/base.css">
    <link rel="stylesheet" href="${ctx}/assets/css/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/views/restaurantPage/restaurant.css">
    <script type="text/javascript">
        var contextPath = "${ctx}";
        var dtypeList = [];
        <c:forEach items="${ dishTypes }" var="t">
            dtypeList.push("${t.name}");
        </c:forEach>
    </script>
</head>
<body>
    <div class="mask close-cart-menu"></div>
    <input name="mid" type="hidden" value="${merchant.id}">
   
    <nav class="navbar navbar-inverse">
        <%@ include file="/assets/templates/nav.jsp" %>
    </nav>
    
    <div class="jumbotron">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-8">
                    <h1>${ merchant.mName }</h1>
                    <p>Address: ${ merchant.address }</p>
                    <p class="header-stars">Stars:
                    <c:forEach var="i" begin="0" end="4" step="1">
                        <c:if test="${i < merchantStar}">
                            <i class="glyphicon glyphicon-star"></i>
                        </c:if>
                        <c:if test="${i >= merchantStar}">
                            <i class="glyphicon glyphicon-star-empty"></i>
                        </c:if>
                    </c:forEach>
<!--
                    <%
                        double stars = (Double) request.getAttribute("merchantStar");
                        for (int i = 0; i < 5; i++) {
                            if (i < stars) {
                                out.write("<i class=\"glyphicon glyphicon-star\"></i>");
                            } else {
                                out.write("<i class=\"glyphicon glyphicon-star-empty\"></i>");
                            }
                        }
                    %>
-->
                    </p>
                </div>
                <div class="col-sm-12 col-md-4">
                    <img class="shop-logo" src="${ctx}/res/${ merchant.imgHead }">
                </div>
            </div>
            
        </div>
    </div>
    
    <div class="container">
        <div id="main-content">
            <div class="row">
              
                <div class="col-md-8 col-sm-12">
<!--
                    <div class="col-sm-12 col-md-6" data-did="ztssssnmlgb">
                        <div class="thumbnail">
                            <div class="dish-img">
                                <img src="https://www.baidu.com/img/bd_logo1.png">
                                <button class="btn btn-warning add-to-cart"><span class="glyphicon glyphicon-plus"></span>Add to Cart</button>
                            </div>
                            <div class="dish-info">
                                <div class="caption">
                                    <h3>Hey</h3>
                                    <p>Food</p>
                                    <h4>10.00</h4>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
-->

                    <c:forEach items="${ dishes }" var="d">
                        <div class="col-sm-12 col-md-6" data-did="${ d.id }">
                            <div class="thumbnail">
                                <div class="dish-img">
                                    <img src="${ ctx }/res/${ d.dImage }">
                                    <button class="btn btn-warning add-to-cart"><span class="glyphicon glyphicon-plus"></span>Add to Cart</button>
                                </div>
                                <div class="dish-info">
                                    <div class="caption">
                                        <h3>${ d.dName }</h3>
                                        <p>${ dishTypes.get(d.dType).name }</p>
                                        <h4>${ d.dPrice }</h4>
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                
                <div class="col-md-4 col-sm-12">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Comments</h3>
                        </div>
                        <div class="panel-body">
                          
                            <c:if test="${comments.size() == 0}">Nothing here!</c:if>
                           
                            <c:forEach items="${comments}" var="comm" varStatus="st">
                                <div>
                                    <p>
                                        <c:forEach var="i" begin="0" end="4" step="1">
                                            <c:if test="${i < comm.star}">
                                                <i class="glyphicon glyphicon-star"></i>
                                            </c:if>
                                            <c:if test="${i >= comm.star}">
                                                <i class="glyphicon glyphicon-star-empty"></i>
                                            </c:if>
                                        </c:forEach>
                                    </p>
                                    <p>${comm.content}</p>
                                    <c:if test="${comm.replyVO != null}">
                                        <p style="color: green;">Reply: ${comm.replyVO.content}</p>
                                    </c:if>
                                </div>
                                
                                <c:if test="${st.index != comments.size() - 1}">
                                    <hr>
                                </c:if>
                            </c:forEach>
                           
<!--
                            <div>
                                <p>我觉得OK</p>
                                <p style="text-align: right;"><i>--Mike</i></p>
                                <p style="color: green;">Reply: 我也觉</p>
                            </div>
                            <hr>
                            <div>
                                <p>我觉得可以</p>
                                <p style="text-align: right;"><i>--Jack</i></p>
                            </div>
                            <hr>
                            <div>
                                <p>我觉得不行</p>
                                <p style="text-align: right;"><i>--Tom</i></p>
                            </div>
                            <hr>
                            <div>
                                <p>阿汤真的很严格</p>
                                <p style="text-align: right;"><i>--Johnson</i></p>
                            </div>
-->
                            
                            
                        </div>
                    </div>
                </div>
               
                
            </div>
        </div>
        <footer></footer>
        <div id="fixed-btns">
            <button id="open-cart-btn" class="btn btn-success btn-lg"><i class="glyphicon glyphicon-shopping-cart"></i></button>
        </div>
        <div id="cart-menu">
            <div>
                <a href="javascript:void(0);" class="close-cart-menu"><span class="glyphicon glyphicon-menu-left"></span> Back</a>
            </div>
            
            <h1 style="position: absolute; top: 40px; left: 80px;">Nothing in cart!</h1>
           
            <ul id="cart-list" class="list-group">
                <c:forEach items="${ cart }" var="cartItem">
                    <li class="list-group-item" data-did="${cartItem.value.dId}">
                        <div class="cart-item-img">
                            <img src="${ctx}/res/${cartItem.value.dimgpath}">
                        </div>
                        <div class="cart-item-info">

                            <div class="caption">
                                <h4>${cartItem.value.dName}</h4>
                                <p>Single: <strong class="single-price">${cartItem.value.price}</strong></p>
                                <p>Total: <strong class="total-price">${cartItem.value.price * cartItem.value.count}</strong></p>
                            </div>
                        </div>
                        <div class="cart-item-ops">
                            <button class="btn btn-danger delete-cart-item"><span class="glyphicon glyphicon-trash"></span></button>
                            <div class="input-group">
                                <a href="javascript:void(0);" class="cart-minus input-group-addon"><span class="glyphicon glyphicon-minus"></span></a>
                                <input type="text" class="form-control" value="${cartItem.value.count}">
                                <a href="javascript:void(0);" class="cart-plus input-group-addon"><span class="glyphicon glyphicon-plus"></span></a>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </li>
                </c:forEach>
            </ul>
            
            <div>
                <div class="panel panel-default">
                    <div class="panel-body">
                        Total Price: <strong>0.00</strong>
                    </div>
                </div>
                <a id="pay-the-bill-btn" href="${ctx}/pay" role="button" class="btn btn-success">Pay the Bill</a>
            </div>
        </div>
    </div>
    
    <div id="login-required-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">You are not logined yet</h4>
                </div>
                <div class="modal-body">
                    Please login first.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <a href="${ctx}/login" role="button" class="btn btn-primary">Login</a>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <script type="text/javascript" src="${ctx}/assets/js/libs/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/libs/jquery-ui.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/libs/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/views/restaurant.js"></script>
</body>
</html>