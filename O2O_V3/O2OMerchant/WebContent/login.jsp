<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../file/base/base.jsp" %>
  <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Merchant Login</title>

        <!-- CSS -->
    
        <link rel="stylesheet" href="${ctx}file/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${ctx}file/css/form-elements.css">
        <link rel="stylesheet" type="text/css" href="${ctx}file/css/sweetalert.css">
<title>Login</title>
</head>
  <body>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>O2O[M]</strong> Login Form</h1>
                            <div class="description">
                            	
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Login to our site</h3>
                            		<p>Enter your username and password to log on:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
                            <span class="error-span">${errorMsg}</span>
			                    <form role="form" action="" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">Account</label>
			                        	<input type="text" name="mAccount"  placeholder="Account..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">Password</label>
			                        	<input type="password" name="pwd"  placeholder="Password..." class="form-password form-control" id="form-password">
			                        </div>
			                        <div class="form-group row">
			                             <div class="col-md-6">
			                                 <button type="submit" class="btn btn-success">Sign in</button>
			                       
			                             </div>
			                             <div class="col-md-6">
                                             <a href="regist.jsp"><button type="button" class="btn btn-primary">Sign up</button></a>
                                         </div>
			                        </div>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>


        <!-- Javascript -->
        <script src="${ctx}file/js/jquery.backstretch.min.js"></script>
        
        <script src="${ctx}file/js/login.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>
</html>