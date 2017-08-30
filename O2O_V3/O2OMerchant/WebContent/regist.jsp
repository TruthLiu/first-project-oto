<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../file/base/base.jsp" %>
<head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Merchant Regist</title>

        <!-- CSS 
       <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="file/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="file/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="file/css/form-elements.css">
        <link rel="stylesheet" href="file/css/style.css">
-->
<link rel="stylesheet" type="text/css" href="file/css/sweetalert.css">
    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>O2O[M]</strong> Regist Form</h1>
                            <div class="description">
                            	
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Sign up your message</h3>
                            		
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
                            <span class="error-span" id="errorSpan"></span>
			                    <form id="registForm" role="form" action="" method="post" class="regist-form" enctype="multipart/form-data">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">mAccount</label>
			                        	<input type="text" name="mAccount"  placeholder="Account..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">pwd</label>
			                        	<input type="password" name="pwd"  placeholder="Password..." class="form-password form-control" id="form-password">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">pwd2</label>
			                        	<input type="password" name="pwd2"  placeholder="Confirm Password..." class="form-password form-control" id="form-2password">
			                        </div>
			                        <div class="form-group">
			                    		<label class="sr-only" for="form-username">mName</label>
			                        	<input type="text" name="mName"  placeholder="Username..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">address</label>
			                        	<input type="text" name="address"  placeholder="Address..." class="form-password form-control" id="form-address">
			                        </div>
			                        <div class="form-group">
			                        <input type="file" id="imgCard" name="imgCard"  placeholder="ID Card Image..." title="ID Card Image..." class="btn-primary"> 
			                        </div>
			                        <div class="form-group">
			                        	<input type="file" id="imgHead" name="imgHead"  placeholder="Head Image..." title="Head Image..." class="btn-primary"> 
			                        </div>
			                        <div class="form-group row">
			                             <button type="sumbit" class="btn btn-success"  />Sure</button>
			                        </div>
			                        
			                    </form>
		                    </div>
                        </div>
                    </div>
                  
                </div>
            </div>
            
        </div>


        <!-- Javascript -->
        <script src="file/js/jquery.backstretch.min.js"></script>
        <script src="file/js/regist.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>
</html>