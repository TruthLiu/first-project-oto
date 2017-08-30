<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../../file/base/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="${ctx}file/css/comment.css">
</head>
<body>
    <div class="container">
        <h3 style="margin-top: 50px; margin-bottom: 20px;">Comment Manager</h3>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active" >
                <a href="#new" aria-controls="new" role="tab" data-toggle="tab">Unanswered Comments</a>
            </li>
            <li role="presentation">
                <a href="#all" aria-controls="all" role="tab" data-toggle="tab">All Comments</a>
            </li>
        </ul>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="new">
                <ul class="list-group" id="newUl">
                    <li class="list-group-item">
                        <div class="float-left">
                            <p>User01: Very excellent!</p>
                        </div>
                        <div class="float-right">
                            <p>2010-02-03 09:08</p>
                            <button class="reply-btn btn btn-success">Reply</button>
                        </div>
                        <div class="clear"></div>
                    </li>
                    <li class="list-group-item">
                        <div class="float-left">
                            User01: Very excellent!
                        </div>
                        <div class="float-right">
                            <button class="reply-btn btn btn-success">Reply</button>
                        </div>
                        <div class="clear"></div>
                    </li>
                    <li class="list-group-item">
                        <div class="float-left">
                            User01: Very bad!
                        </div>
                        <div class="float-right">
                            <button class="reply-btn btn btn-success">Reply</button>
                        </div>
                        <div class="clear"></div>
                    </li>
                </ul>
            </div>

            <div role="tabpanel" class="tab-pane" id="all">
                <ul class="list-group" id="allUl">
                    <li class="list-group-item">
                        <div class="float-left">
                            <p>User01: Very excellent!</p>
                            <p>
                                <i class="glyphicon glyphicon-star"></i>
                                <i class="glyphicon glyphicon-star"></i>
                                <i class="glyphicon glyphicon-star"></i>
                                <i class="glyphicon glyphicon-star-empty"></i>
                                <i class="glyphicon glyphicon-star-empty"></i>
                            </p>
                            <p style="color: green;">Replied: Known your request. Will promote next time.</p>
                            
                        </div>
                        <div class="float-right">
                            <p>2010-02-03 09:08</p>
                        </div>
                        <div class="clear"></div>
                    </li>
                    <li class="list-group-item">
                        <div class="float-left">
                           <p> User01: Very bad!  没回复<p>
                            <p>
                                <i class="glyphicon glyphicon-star"></i>
                                <i class="glyphicon glyphicon-star"></i>
                                <i class="glyphicon glyphicon-star"></i>
                                <i class="glyphicon glyphicon-star-empty"></i>
                                <i class="glyphicon glyphicon-star-empty"></i>
                            </p>
                        </div>
                        <div class="float-right">
                            <button class="reply-btn btn btn-success">Reply</button>
                        </div>
                        <div class="clear"></div>
                    </li>
                    
                    
                </ul>
            </div>
        </div>
    </div>
    
    <div id="reply-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Reply Comment</h4>
                </div>
                <input type="hidden" id="coId" value="" />
                <div class="modal-body">
                    <textarea class="form-control" placeholder="Enter reply..." id="replyArea"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success" id="btnReply" data-dismiss="modal">Reply</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <script type="text/javascript" src="${ctx}file/js/comment.js"></script>
</body>
</html>