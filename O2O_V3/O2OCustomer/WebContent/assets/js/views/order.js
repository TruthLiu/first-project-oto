$(function() {
    var commentModal = $("#comment-modal");
    var complaintModal = $("#complaint-modal");
    var confirmModal = $("#confirm-modal");
    
    var commentStars = $("#comment-stars");
    var commentContent = $("#comment-content");
    var sendCommentBtn = $("#send-comment-btn");
    var sendComplaintBtn = $("#send-complaint-btn");
    var confirmReceivedBtn = $("#confirm-received-btn");
    
    var commentForm = $("#comment-form");
    var complaintForm = $("#complaint-form");
    
    $("body").on("shown.bs.popover", ".show-complaint-modal-btn, .show-comment-modal-btn", function() {
        var _this = $(this);
        setTimeout(function() {
            _this.popover("destroy");
        }, 2000);
    });
    
    
    $("body").on("click", ".show-comment-modal-btn", function() {
        commentModal.modal("show");
    });
    
    $("body").on("click", ".show-complaint-modal-btn", function() {
        complaintModal.modal("show");
    });
    
    $("body").on("click", ".show-confirm-modal-btn", function() {
        confirmModal.modal("show");
    });
    
    // Star selector
    $("#comment-form > h4 > i").on("click", function() {
        var _this = $(this);
        var ptr = $(this);
        var stars = 0;
        while (ptr.length > 0) {
            ptr.removeClass(function(index, clazz) { 
                return clazz.replace(/(^|\s)+glyphicon\s+/, ''); 
            });
            ptr.addClass("glyphicon-star");
            ptr = ptr.prev();
            stars++;
        }
        ptr = _this.next();
        while (ptr.length > 0) {
            ptr.removeClass(function(index, clazz) { 
                return clazz.replace(/(^|\s)+glyphicon\s+/, ''); 
            });
            ptr.addClass("glyphicon-star-empty");
            ptr = ptr.next();
        }
        
        _this.parent().next().prop("value", stars);
    });
    
    // confirm received button
    confirmReceivedBtn.on("click", function() {
        j.ajax(
            "POST",
            contextPath + "/api/confirm",
            "oid=" + orderId,
            null,
            null,
            function(response) {
                if (response.code === 1) {
                    location.reload();
                }
            }
        )
    });
    
    // send comment button
    sendCommentBtn.on("click", function() {
        j.ajax(
            "POST",
            contextPath + "/api/comment",
            commentForm.serialize(),
            null,
            null,
            function(response) {
                if (response.code === 1) {
                    commentModal.modal("hide");
                    $(".show-comment-modal-btn").popover({
                        placement: "top",
                        container: "body",
                        html: true,
                        content: "Comment success!",
                        trigger: "manual"
                    }).popover("show");
                    setTimeout(function() {
                        location.reload();
                    }, 3000);
                }
            }
        );
    });
    
    // send complaint button
    sendComplaintBtn.on("click", function() {
        j.ajax(
            "POST",
            contextPath + "/api/complaint",
            complaintForm.serialize(),
            null,
            null,
            function(response) {
                if (response.code === 1) {
                    complaintModal.modal("hide");
                    $(".show-complaint-modal-btn").popover({
                        placement: "top",
                        container: "body",
                        html: true,
                        content: "Complaint success!",
                        trigger: "manual"
                    }).popover("show");
                    setTimeout(function() {
                        location.reload();
                    }, 3000);
                }
            }
        );
    });

});