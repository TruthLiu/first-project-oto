<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../../file/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="${ctx}file/css/comment.css">
</head>
<body>
<div class="container">
        <h3 style="margin-top: 50px; margin-bottom: 20px;">Order Manager</h3>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active" >
                <a href="#wait" aria-controls="wait" role="tab" data-toggle="tab">Waiting Comments</a>
            </li>
            <li role="presentation">
                <a href="#receive" aria-controls="receive" role="tab" data-toggle="tab">Received Comments</a>
            </li>
            <li role="presentation">
                <a href="#reject" aria-controls="reject" role="tab" data-toggle="tab">Reject Comments</a>
            </li>
        </ul>

		<!-- wait order -->
        <div class="tab-content panel-group">
            <div role="tabpanel" class="tab-pane active" id="wait">
                <ul class="list-group" id="waitUl">
                    <li class="list-group-item">
                        <div class="float-left">
                            <h2>User01</h2>
                            <p>$12.23</p>
                            <p>唐家湾南方软件园</p>
                            <a data-toggle="collapse" data-parent="#accordion" 
				  				 href="#collapseOne">
								Detail
							</a>
                        </div>
                        <div class="float-right">
                            <p>2010-02-03 09:08</p>
                            <button  class="yes-btn btn btn-success">Yes</button>
                            <button  class="no-btn btn btn-danger">No</button>
                        </div>
                        
                        <div class="clear"></div>
                        <div id="collapseOne" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table table-striped">
									<thead>
										<td>DishName</td>
										<td>Num</td>
										<td>Price</td>
									</thead>
									<tbody>
										<tr>
											<td>猪脚饭</td>
											<td>10</td>
											<td>$12</td>
										</tr>
										<tr>
											<td>蒸饺</td>
											<td>5</td>
											<td>$6</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
                    </li>
                </ul>
            </div>
			<!-- receive order -->
             
            <div role="tabpanel" class="tab-pane" id="receive">
                <ul class="list-group" id="receiveUl">
                    <li class="list-group-item">
                        <div class="float-left">
                            <h2>User02</h2>
                            <p>$33.23</p>
                            <p>唐家湾东方海外</p>
                            <a data-toggle="collapse" data-parent="#accordion" 
				  				 href="#collapseTwo">
								Detail
							</a>
                        </div>
                        <div class="float-right">
                            <p>2010-02-03 09:08</p>
                            <button class="send-btn btn btn-success">Send</button>
                        </div>
                        
                        <div class="clear"></div>
                        <div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table table-striped">
									<thead>
										<td>DishName</td>
										<td>Num</td>
										<td>Price</td>
									</thead>
									<tbody>
										<tr>
											<td>烧鸭饭</td>
											<td>10</td>
											<td>$12</td>
										</tr>
										<tr>
											<td>水饺</td>
											<td>5</td>
											<td>$6</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
                    </li>
                </ul>
            </div>
             <div role="tabpanel" class="tab-pane" id="reject">
                <ul class="list-group" id="rejectUl">
                    <li class="list-group-item">
                        <div class="float-left">
                            <h2>User03</h2>
                            <p>$33.23</p>
                            <p>唐家湾东方海外</p>
                            <a data-toggle="collapse" data-parent="#accordion" 
				  				 href="#collapseThree">
								Detail
							</a>
                        </div>
                        <div class="float-right">
                            <p>2010-02-03 09:08</p>
                            <h3 style="color:red">Reject</h3>
                            
                        </div>
                        
                        <div class="clear"></div>
                        <div id="collapseThree" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table table-striped">
									<thead>
										<td>DishName</td>
										<td>Num</td>
										<td>Price</td>
									</thead>
									<tbody>
										<tr>
											<td>烧鸭饭</td>
											<td>10</td>
											<td>$12</td>
										</tr>
										<tr>
											<td>水饺</td>
											<td>5</td>
											<td>$6</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
                    </li>
                </ul>
            </div>
      
    </div>
<script type="text/javascript" src="${ctx}file/js/order.js"></script>
</body>
</html>