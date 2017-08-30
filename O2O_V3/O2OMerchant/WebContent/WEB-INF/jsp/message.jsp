<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../file/base/base.jsp" %>
<title>Insert title here</title>
<style>
 td{
  padding:10px;
  word-wrap:break-word;
 }
 
 img{
 	width:250px;
 	height:250px;
 }
</style>



</head>
<body>

<div class="containerInfo">
	<div class="row">
		<div id="headDiv" class="col-md-4">
			<img id="imgHeadUrl"  src="${ctx}res/${sessionScope.merchant.imgHead}"  class="img-circle imgHead">
		</div>
		<div class="col-md-8" style="padding-top:10px;padding-left:100px;">
			<table style="text-align:left;font-size:25px;color:#FFFFFF">
				<tr>
					<td>Account:</td>
					<td>${sessionScope.merchant.mAccount}</td>
				</tr>
				<tr id="shopNameTr">
					<td>Shop Name:</td>
					<td>${sessionScope.merchant.mName}</td>
				</tr>
				<tr id="addressTr">
					<td>Address:</td>
					<td>${sessionScope.merchant.address}</td>
				</tr>
				<tr id="stateTr">
					<c:if test="${sessionScope.merchant.status==0}">
						<td>State:</td>
						<td style="color:#FFFF00">Pending</td>
					</c:if>
					<c:if test="${sessionScope.merchant.status==1}">
						<td>State:</td>
						<td style="color:#7CFC00">Pass</td>
					</c:if>
					<c:if test="${sessionScope.merchant.status==2}">
						<td>State:</td>
						<td style="color:#FF4500">Reject <strong></strong></td>
					</c:if>
				</tr>
			</table>
		
		</div>
	</div>
	<div class="row" style="padding-top:70px;">
	<div class="col-md-6">
			<c:if test="${sessionScope.merchant.status!=1}">
				<button class="btn btn-success btn-lg" id="btnState" style="width:300px;">Get State</button> 
			</c:if>		
	</div>
	<div class="col-md-6">
			<c:if test="${sessionScope.merchant.status!=1}">
				<button class="btn btn-primary btn-lg" id="btnApply" style="width:300px;" data-toggle="modal" data-target="#applyModel">Apply Store</button> 
			</c:if>		
	</div>
	
	</div>
</div>

<!-- 更新模态框modal -->
    <div class="modal fade" id="applyModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Update Merchant</h4>
	      </div>
	      <div class="modal-body">
	        <table class="table" id="processMer">
	            <tr>
	                <td>Account：</td>
	                <td>
		                <div class="col-sm-10">
		                	<input type="text" class="form-control" name="mAccount" disabled="disabled"/>
		                </div>
	                </td>
	            </tr>
	            <tr>
	                <td>Name：</td>
	                <td><div class="col-sm-10"><input type="text" class="form-control" name="mName"/></div></td>
	            </tr>
	            <tr>
	                <td>Address：</td>
	                <td><div class="col-sm-10">
	                <input type="text" class="form-control" name="address"/>
	                </div></td>
	            </tr>
	             <tr>
	                <td>ImgCard：</td>
	                <td><div class="col-sm-10"><input type="file" id="imgCard" name="imgCard" class="form-control" /></div></td>
	            </tr>
	            <tr>
	                <td>ImgHead：</td>
	                <td><div class="col-sm-10"><input type="file" class="form-control" id="imgHead" name="imgHead"/></div></td>
	            </tr>
	        </table>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="updateBtn" >Submit</button>
	      </div>
	    </div>
	  </div>
	</div>
<script src="${ctx}file/js/message.js"></script>

</body>
</html>