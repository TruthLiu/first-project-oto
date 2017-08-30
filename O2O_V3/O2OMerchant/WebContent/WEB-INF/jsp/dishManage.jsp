<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>

<%
	String contexPath = request.getContextPath();
	String basicContextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contexPath + "/";
%>
<c:set var="ctx" value="<%=basicContextPath %>"/>
<link rel="stylesheet" href="${ctx}file/css/sweetalert.css">
<script src="${ctx}file/js/sweetalert.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="/O2OMerchant/file/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/O2OMerchant/file/bootstrap/css/bootstrap-theme.min.css">
<script src="/O2OMerchant/file/js/jquery-3.2.1.min.js"></script>
<script
	src="/O2OMerchant/file/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
        var contextPath = "${ctx}";
</script>
<style type="text/css">
#wra {
	width: 1000px;
	margin:0 auto;
}
table td{
width:200px;
}
</style>
</head>
<body>
	<br>
	<br>
	<div id="wra">
		<form class="form-inline" style="margin-left: 330px;" id="searchfrom">
			<input type="hidden" name="id" value="${merchant.id }"/>
			<select class="form-control" id="dtype">
				<c:forEach items="${dishTypes }" var="dt" varStatus="status">
					<option value="${status.index }">${dt.name }</option>
				</c:forEach>
			</select> <input type="text" class="form-control" id="dname" placeholder="输入菜名">
			<button type="submit" class="btn btn-primary" id="searchDish">搜索</button>
		</form>
		<br>
		<button id="btnAdd" class="btn btn-success" data-toggle="modal"
			data-target="#toAdd" >增加菜品</button>
		<br />
		<br>
	
		<table class="table table-bordered" class="table">
		    <thead>
		       <tr><th>预览</th><th>名称</th><th>类型</th><th>价格</th><th>操作</th></tr>
		    </thead>
		    <tbody id="dishbody">
		        
		    </tbody>
		</table>
	</div>
	<!-- 增加模态框 -->
	<div class="modal fade" id="toAdd" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Process Page</h4>
				</div>
				<div class="modal-body form-group">
					<table id="processMer">
						<form class="form-inline" method="POST" enctype="multipart/form-data">
							<input type="hidden" name="id" value="${merchant.id }"/>
							<div class="form-group">
								<p>
									<label>名称：</label> <input type="text"
										class="form-control" name="dishName" placeholder="菜名">
								</p>
							</div>
							<br>
							<div class="form-group">
								<p>
									<label >类型：</label> <select
										class="form-control" name="dishType">
									
										<option value="1">小吃</option>
										<option value="2">酒水</option>
										<option value="3">主食</option>
										<option value="4">套餐</option>
									</select>
								</p>
							</div>
							<br>
							<div class="form-group">
								<label >价格：</label> <input
									type="number" class="form-control" name="price"
									placeholder="价格">
							</div>
							<br>
							<div class="form-group">
								<label>头像：</label> <input type="file" name="headImg" id="headImg"/>
								<p class="help-block">请上传图片</p>
							</div>
						</form>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="addDish">Add</button>
				</div>
			</div>
		</div>
	</div>
	
		<!-- 修改模态框 -->
	<div class="modal fade" id="toUpdate" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">更新页面</h4>
				</div>
				<div class="modal-body form-group">
					<table id="processMer">
						<form class="form-inline" method="POST" enctype="multipart/form-data">
							<input type="hidden" name="id" value="${merchant.id }"/>
							<input type="hidden" name="image" value=""/>
							<input type="hidden" name="did" value=""/>
							<div class="form-group"> 
								<p>
									<label>名称：</label> <input type="text"
										class="form-control" name="dishName" placeholder="菜名">
								</p>
							</div>
							<br>
							<div class="form-group">
								<p>
									<label >类型：</label> <select
										class="form-control" name="dishType">
										<option value="1">小吃</option>
										<option value="2">酒水</option>
										<option value="3">主食</option>
										<option value="4">套餐</option>
									</select>
								</p>
							</div>
							<br>
							<div class="form-group">
								<label >价格：</label> <input
									type="number" class="form-control" name="price"
									placeholder="价格">
							</div>
							<br>
							<div class="form-group">
								<label>头像：</label> <input type="file" name="headImg" id="headImgUpdate"/>
								<p class="help-block">请上传图片</p>
							</div>
						</form>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="updateDish">Update</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 删除Modal -->
	<div class="modal fade" id="delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Detail Page</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="deleteDishId" value=""/>
					<div class="modal-body">
						<h4>确定删除此条数据？</h4>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="makedelete">确定</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="/O2OMerchant/file/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="/O2OMerchant/file/js/ajaxOperator.js"></script>
	<script type="text/javascript" src="/O2OMerchant/file/js/dish.js"></script>
	
</body>
</html>