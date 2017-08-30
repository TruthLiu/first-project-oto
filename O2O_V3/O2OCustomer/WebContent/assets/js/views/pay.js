$(function() {
    var ws = WebSocketUtil(MSide.WS_URL, function(e) {
        alert("Pay Page Received: " + e.data);
    });
    
    $(window).on("beforeunload", function() {
        ws.close();
    });
    
//    window.addEventListener("beforeunload", function(e) {
////        alert("closing..");
//        ws.send(JSON.stringify({
//            cId: $("#cid-input").val(),
//            mId: $("#mid-input").val()
//        }), function() {});
//        
//        (e || window.event).returnValue = null;
//        return null;
//    });
    
    var component = new AddressComponent($("#address-list"));
    var customerInfoForm = $("#customer-info-form");
    var addressList = $("#address-list");
    var paySuccessModal = $("#pay-success-modal");
    var payErrorModal = $("#pay-error-modal");
    
    // bindings
    customerInfoForm.on("submit", function(e) {
        e.preventDefault();
    });
    customerInfoForm.find("input").on("focus", function() {
        $(this).removeClass("invalid");
    });
    addressList.on("change", "input[type=\"radio\"]", function() {
        addressList.removeClass("invalid");
    });
    paySuccessModal.on("hidden.bs.modal", function() {
        location.href = contextPath + "/order";
    });
    $("#add-address-btn").on("click", function() {
        component.addAddress($("#new-address-input").val());
    });
    $("body").on("click", ".delete-address-btn", function() {
        var _aid = $(this).parent().parent().attr("data-aid");
        component.deleteAddress(_aid);
    });
    $("#confirm-order-btn").on("click", function() {
        var isFormValid = true;
        customerInfoForm.find("input[name=\"realName\"],input[name=\"phone\"]").each(function() {
            if ($(this).val() == "") {
                isFormValid = false;
                $(this).addClass("invalid");
            }
        });
        // validate address
        if (addressList.html().length == 0) {
            $("#new-address-input").addClass("invalid");
        } else {
            var addressChecked = false;
            addressList.find("input[type=\"radio\"]").each(function() {
                if (this.checked) {
                    addressChecked = true;
                }
            });
            
            if (!addressChecked) {
                addressList.addClass("invalid");
                isFormValid = false;
            }
        }
        
        if (isFormValid) {
            j.ajax(
                "POST",
                contextPath + "/api/pay",
                customerInfoForm.serialize(),
                null,
                null,
                function(response) {
                    if (response.code === 1) {
                        
                        // WebSocket: send to M-Side
                        ws.send(JSON.stringify({
                            cId: $("#cid-input").val(),
//                            cName: $("#cname-input").val(),
                            mId: $("#mid-input").val()
                        }), function() {
                            paySuccessModal.modal("show");
                            
                            // clear cart
                            j.ajax(
                                "POST",
                                contextPath + "/api/cart/clear",
                                null,
                                null,
                                null,
                                function() {}
                            );
                        });
                        
                    } else {
                        payErrorModal.find(".modal-body").text(response.msg);
                        payErrorModal.modal("show");
                    }
                }
            );
        }
    });
    
    // init address list
    component.findAllAddresses();
    
    
    
    function AddressComponent(listGroup) {
        this.model = [];
        this.template = listGroup;
        this.addAddress = function(aname) {
            j.ajax(
                "POST",
                contextPath + "/api/address/",
                "aname=" + aname,
                null,
                null,
                function(response) {
                    if (response.code === 1) {
                        this.model.push({
                            id: response.result.id,
                            aname: aname
                        });
                        _render(this.model);
                    }
                }
            );
        }
        this.findAllAddresses = function() {
            j.ajax(
                "GET",
                contextPath + "/api/address/",
                null,
                null,
                null,
                function(response) {
                    if (response.code === 1) {
                        this.model = response.resultList;
                        _render(this.model);
                    }
                }
            );
            
            // TODO: Ajax
            _render(this.model);
        }
        this.deleteAddress = function(aid) {
            // TODO: Ajax
            j.ajax(
                "DELETE",
                contextPath + "/api/address/" + aid,
                null,
                null,
                null,
                function(response) {
                    if (response.code === 1) {
                        this.model = this.model.filter(function(addr) {
                            return addr.id != aid;
                        });
                        _render(this.model);
                    }
                }
            );
        }
        function _render(data) {
            listGroup.empty();
            data.forEach(function(addr) {
                listGroup.append($.parseHTML(
                    "<li data-aid=\"" + addr.id + "\" class=\"list-group-item\" style=\"position: relative;min-height: 55px;\">"+
                    "    <div class=\"float-left\" style=\"padding-right: 45px;\">"+
                    "        <label><input type=\"radio\" name=\"addr\" value=\"" + addr.aname + "\">&nbsp;" + addr.aname + "</label>"+
                    "    </div>"+
                    "    <div class=\"float-right\" style=\"position: absolute; right: 15px;\">"+
                    "        <button role=\"button\" class=\"btn btn-danger delete-address-btn\"><span class=\"glyphicon glyphicon-trash\"></span></button>"+
                    "    </div>"+
                    "    <div class=\"clear\"></div>"+
                    "</li>"
                ));
                
//                $("<li>").addClass("list-group-item").append(
//                    $("<label>")
//                        .append($("<input>").attr("type", "radio").attr("name", "addr").val(addr.aname))
//                        .append(" " + addr.aname)
//                ).appendTo(listGroup);
            });
        }
    }
});