<%@ page import="com.oocl.vo.OrderVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>O2O[C] - Order</title>
    
    <link rel="stylesheet" href="${ctx}/assets/css/views/base.css">
    <link rel="stylesheet" href="${ctx}/assets/css/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/views/ordersPage/order.css">
    <script type="text/javascript">
        var contextPath = "${ctx}";
        var orderId = "${order.id}";
    </script>
</head>
<body>
    <nav class="navbar navbar-inverse">
        <%@ include file="/assets/templates/nav-nosearch.jsp" %>
    </nav>
   
    <div class="container">
        <a href="${ctx}/order"><h3><span class="glyphicon glyphicon-menu-left"></span> Back to Order List</h3></a>
        
        <!-- An order item template -->
        <c:if test="${order.status == -1}">
            <div class="panel panel-danger">
        </c:if>
        <c:if test="${order.status == 0}">
            <div class="panel panel-warning">
        </c:if>
        <c:if test="${order.status == 1}">
            <div class="panel panel-info">
        </c:if>
        <c:if test="${order.status == 2}">
            <div class="panel panel-primary">
        </c:if>
        <c:if test="${order.status == 3}">
            <div class="panel panel-success">
        </c:if>
        
            <div class="panel-heading">
                <h3 class="panel-title">${order.merchantVO.mName} - <span><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm"/></span></h3>
            </div>
            <div class="panel-body">
                <c:forEach items="${order.itemVO}" var="orderItem" varStatus="st">
                    
                    <div class="panel-float-left">
                        <h3>${orderItem.dishName}<span>X${orderItem.num}</span></h3>
                    </div>
                    <div class="panel-float-right">
                        <h5>Single: ${orderItem.unitPrice}</h5>
                        <h4>Total: ${orderItem.unitPrice * orderItem.num}</h4>
                    </div>

                    <div class="clear"></div>
                    
                    <c:if test="${st.index != order.itemVO.size() - 1}">
                        <hr>
                    </c:if>
                    
                </c:forEach>
               
<!--
                <div class="panel-float-left">
                    <h3>Chicken<span>X3</span></h3>
                </div>
                <div class="panel-float-right">
                    <h5>Single: 5.00</h5>
                    <h4>Total: 15.00</h4>
                </div>
                
                <div class="clear"></div><hr>
                
                <div class="panel-float-left">
                    <h3>Hamburger<span>X1</span></h3>
                </div>
                <div class="panel-float-right">
                    <h5>Single: 5.00</h5>
                    <h4>Total: 5.00</h4>
                </div>
                
                <div class="clear"></div>
-->
                
                
                <hr class="no-margin-bottom">
                
                <div class="panel-float-left">
                    <h3>Total:</h3>
                </div>
                <div class="panel-float-right">
                    <h3>${order.totalPrice}</h3>
                </div>
                
                <div class="clear"></div>
                
                <c:if test="${order.commentVO != null}">
                    <hr class="no-margin-top">
                   
                    <div class="panel-float-left">
                        <p>Comment: ${order.commentVO.content}</p>
                        <c:if test="${order.commentVO.replyVO != null}">
                            <p style="color: green;">Restaurant reply: ${order.commentVO.replyVO.content}</p>
                        </c:if>
                    </div>
                    <div class="panel-float-right">
                        <h3 class="panel-comment-stars">
                            <c:forEach var="i" begin="0" end="4" step="1">
                                <c:if test="${i < order.commentVO.star}">
                                    <i class="glyphicon glyphicon-star"></i>
                                </c:if>
                                <c:if test="${i >= order.commentVO.star}">
                                    <i class="glyphicon glyphicon-star-empty"></i>
                                </c:if>
                            </c:forEach>

<!--
                            <i class="glyphicon glyphicon-star-empty"></i>
                            <i class="glyphicon glyphicon-star-empty"></i>
                            <i class="glyphicon glyphicon-star-empty"></i>
                            <i class="glyphicon glyphicon-star-empty"></i>
                            <i class="glyphicon glyphicon-star-empty"></i>
-->
                        </h3>
                    </div>

                    <div class="clear"></div>
                </c:if>
                
            </div>
            <div class="panel-footer">
                <div class="receiver-info">
                    <p>Receiver: ${order.realName} <span>(${order.phone})</span></p>
                    <p>Receive address: ${order.addr}</p>
                </div>
                <div class="order-operations">
                    <p>Status: <span>
                        <c:if test="${order.status == -1}">REJECTED</c:if>
                        <c:if test="${order.status == 0}">PENDING</c:if>
                        <c:if test="${order.status == 1}">ACCEPTED</c:if>
                        <c:if test="${order.status == 2}">DELIVERED</c:if>
                        <c:if test="${order.status == 3}">RECEIVED</c:if>
                    </span></p>
                    
                    <c:if test="${order.status == 2}">
                        <a role="button" class="btn btn-success show-confirm-modal-btn">Confirm</a>
                    </c:if>
                    
                    <c:if test="${order.status == -1 || order.status == 3}">
                        <c:if test="${order.complaintVO == null}">
                            <a role="button" tabindex="0" class="btn btn-warning show-complaint-modal-btn">Complaint</a>
                        </c:if>
                        <c:if test="${order.commentVO == null}">
                            <a role="button" tabindex="0" class="btn btn-primary show-comment-modal-btn">Comment</a>
                        </c:if>
                    </c:if>
                </div>
                <div class="clear"></div>
            </div>
        </div><!-- template end -->
        
        <!-- An order item template -->
<!--
        <div class="panel panel-warning">
            <div class="panel-heading">
                <h3 class="panel-title">KFC - <span>2010-04-05 09:32</span></h3>
            </div>
            <div class="panel-body">
                <div class="panel-float-left">
                    <img src="https://www.baidu.com/img/bd_logo1.png">
                </div>
                <div class="panel-float-right">
                    <h3>Chips<span>X1</span></h3>
                    <p>Snack</p>
                    <h5>Single: 5.00</h5>
                    <h4>Total: 5.00</h4>
                </div>
                
                <div class="clear"></div><hr>
                
                <div class="panel-float-left">
                    <h3>Total:</h3>
                </div>
                <div class="panel-float-right">
                    <h3>20.00</h3>
                </div>
                
            </div>
            <div class="panel-footer">
                <div class="receiver-info">
                    <p>Receiver: Simon <span>(13800138000)</span></p>
                    <p>Receive address: No.3 Street</p>
                </div>
                <div class="order-operations">
                    <p>Status: Pending</p>
                    <button class="btn btn-success show-confirm-modal-btn">Confirm</button>
                </div>
                <div class="clear"></div>
            </div>
        </div> template end 
-->
    </div>
    
    <div id="comment-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Comment</h4>
                </div>
                <div class="modal-body">
                    <form id="comment-form">
                        <input type="hidden" name="oid" value="${order.id}">
                        <input type="hidden" name="mid" value="${order.merchantVO.id}">
                        <h4>Stars:&nbsp;
                            <i class="glyphicon glyphicon-star-empty"></i>
                            <i class="glyphicon glyphicon-star-empty"></i>
                            <i class="glyphicon glyphicon-star-empty"></i>
                            <i class="glyphicon glyphicon-star-empty"></i>
                            <i class="glyphicon glyphicon-star-empty"></i>
                        </h4>
                        <input type="hidden" name="stars" value="0">
                        <p><textarea class="form-control" name="content" placeholder="Enter comment..."></textarea></p>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button id="send-comment-btn" type="button" class="btn btn-primary">Send</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <div id="complaint-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Complaint</h4>
                </div>
                <div class="modal-body">
                    <form id="complaint-form">
                        <input type="hidden" name="oid" value="${order.id}">
                        <input type="hidden" name="mid" value="${order.merchantVO.id}">
                        <p>Choose your reason:</p>
                        <p><input type="radio" name="reason" value="Not clean">&nbsp;Not clean</p>
                        <p><input type="radio" name="reason" value="Too Slow">&nbsp;Too Slow</p>
                        <p><input type="radio" name="reason" value="Bad attitude">&nbsp;Bad attitude</p>
                        <p><textarea class="form-control" name="content" placeholder="Enter your reason..."></textarea></p>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button id="send-complaint-btn" type="button" class="btn btn-warning">Send</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <div id="confirm-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Comfirm?</h4>
                </div>
                <div class="modal-body">
                    <p>Please confirm your dishes has already been received before you click confirm button below.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button id="confirm-received-btn" type="button" class="btn btn-success">Yes. I have received my dish</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <script type="text/javascript" src="${ctx}/assets/js/libs/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/libs/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/utils/ajaxutil.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/views/order.js"></script>
</body>
</html>