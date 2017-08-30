<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>O2O[C] - Pay</title>
    
    <link rel="stylesheet" href="${ctx}/assets/css/views/base.css">
    <link rel="stylesheet" href="${ctx}/assets/css/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/views/payPage/pay.css">
    <script type="text/javascript">
        var contextPath = "${ctx}";
    </script>
</head>
<body>
    <nav class="navbar navbar-inverse">
        <%@ include file="/assets/templates/nav-nosearch.jsp" %>
    </nav>
   
    <div class="container">
        <h2>Pay</h2>
        <div class="row">
            <div class="col-md-8 col-xs-12">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title">${merchant.mName}</h3>
                    </div>
                    <div class="panel-body">
                       
                        <c:forEach items="${ cart }" var="cartItem" varStatus="st">
                            <div data-did="${cartItem.value.dId}">
                                <div class="float-left">
                                    <img src="${ctx}/res/${cartItem.value.dimgpath}">
                                </div>
                                <div class="float-right">
                                    <h3>${cartItem.value.dName}<span>X${cartItem.value.count}</span></h3>
                                    <h5>Single: ${cartItem.value.price}</h5>
                                    <h4>Total: ${cartItem.value.price * cartItem.value.count}</h4>
                                </div>

                                <div class="clear"></div>

                                <c:if test="${st.index != cart.size() - 1}"><hr></c:if>
                            </div>
                            
                        </c:forEach>
                       
                       
<!--
                        <div class="float-left">
                            <img src="https://www.baidu.com/img/bd_logo1.png">
                        </div>
                        <div class="float-right">
                            <h3>Chicken<span>X3</span></h3>
                            <p>Snack</p>
                            <h5>Single: 5.00</h5>
                            <h4>Total: 15.00</h4>
                        </div>

                        <div class="clear"></div><hr>

                        <div class="float-left">
                            <img src="https://www.baidu.com/img/bd_logo1.png">
                        </div>
                        <div class="float-right">
                            <h3>Hamburger<span>X1</span></h3>
                            <p>Snack</p>
                            <h5>Single: 5.00</h5>
                            <h4>Total: 5.00</h4>
                        </div>

                        <div class="clear"></div>
-->

                    </div>

                </div><!-- template end -->
            </div>
            <div class="col-md-4 col-xs-12">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Customer Info</h3>
                    </div>
                    <div class="panel-body">
                        <form id="customer-info-form">
                            <input id="cid-input" type="hidden" value="${loginCustomer.id}">
                            <input id="cname-input" type="hidden" value="${loginCustomer.cname}">
                            
                            <input id="mid-input" type="hidden" name="mid" value="${merchant.id}">
                            <input type="hidden" name="totalPrice" value="${totalPrice}">
                            
                            <p><input type="text" class="form-control" name="realName" placeholder="Enter your name..."></p>
                            <p><input type="number" class="form-control" name="phone" placeholder="Enter your phone..."></p>
                            <p style="margin-top: 20px;">Choose address:</p>
                            <div>
                                <div style="position: relative; margin-bottom: 10px;">
                                    <div style="position: absolute; left: 0; right: 45px;">
                                        <input id="new-address-input" type="text" class="form-control" placeholder="Enter new address...">
                                    </div>
                                    <div style="float: right; width: 40px;">
                                        <a id="add-address-btn" role="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span></a>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                                
                                <ul id="address-list" class="list-group">
<!--
                                    <li class="list-group-item" style="position: relative;">
                                        <div class="float-left" style="padding-right: 45px;">
                                            <label><input type="radio" name="addr" value="Addr1">&nbsp;Addr1 is a very long address which is very long, well I think it is too long</label>
                                        </div>
                                        <div class="float-right" style="position: absolute; right: 15px;">
                                            <button role="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></button>
                                        </div>
                                        <div class="clear"></div>
                                        
                                    </li>
-->
                                </ul>
                            </div>
                            
                            <hr style="margin-bottom: 0;">
                            <div class="float-left">
                                <h3>Total:</h3>
                            </div>
                            <div class="float-right">
                                <h3>${totalPrice}</h3>
                            </div>

                            <div class="clear"></div>

                            <a id="confirm-order-btn" role="button" class="btn btn-success">Confirm order</a>
<!--                            <input type="submit" role="button" class="btn btn-success" value="Confirm order">-->
                        </form>

                    </div>
                </div>
            </div>
        </div> 
        
    </div>
    
    <div id="pay-success-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Success!</h4>
                </div>
                <div class="modal-body">
                    You order has been paid.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <div id="pay-error-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Error!</h4>
                </div>
                <div class="modal-body">
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <script type="text/javascript" src="${ctx}/assets/js/libs/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/libs/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/utils/ajaxutil.js"></script>
    
    <!-- WebSocket Config -->
    <script type="text/javascript" src="${ctx}/assets/js/utils/websocketutil.js"></script>
    
    <script type="text/javascript" src="${ctx}/assets/js/views/pay.js"></script>
</body>
</html>