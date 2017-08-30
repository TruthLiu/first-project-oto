<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>O2O[C] - Login</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${ctx}/assets/css/libs/bootstrap.min.css">
        <link rel="stylesheet" href="${ctx}/assets/css/views/loginPage/form-elements.css">
        <link rel="stylesheet" href="${ctx}/assets/css/views/loginPage/login.css">
    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
            
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>O2O[C]</strong> Login</h1>
                            <div class="description">
                                
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                            <div class="form-top">
                                <h3>Login to our site</h3>
                                <p>Enter your username and password to log on:</p>
                            </div>
                            
                            <c:if test="${errorMsg != null}">
                                <div class="form-top">
                                    <div class="alert alert-danger" role="alert">${errorMsg}</div>
                                </div>
                            </c:if>
                            
                            <div class="form-bottom">
                                <form role="form" action="api/login" method="post" class="login-form">
                                    <div class="form-group">
                                        <label class="sr-only" for="form-username">Username</label>
                                        <input type="text" name="cname" placeholder="Username..." class="form-username form-control" id="form-username">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-password">Password</label>
                                        <input type="password" name="cpwd" placeholder="Password..." class="form-password form-control" id="form-password">
                                    </div>
                                    <div class="form-group row">
                                         <div class="col-md-6">
                                             <button type="submit" class="btn btn-success">Sign in</button>
                                         </div>
                                         
                                         <div class="col-md-6">
                                             <a href="register"><button type="button" class="btn btn-primary">Sign up</button></a>
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
        <script src="${ctx}/assets/js/libs/jquery.min.js"></script>
        <script src="${ctx}/assets/js/libs/bootstrap.min.js"></script>
        <script src="${ctx}/assets/js/libs/jquery.backstretch.min.js"></script>
        <script src="${ctx}/assets/js/views/login.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>