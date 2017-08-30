$(function() {
//    var dtypeList = [ "所有", "酒水", "主食", "小吃", "套餐" ];
    
    // usual components
    var mask = $(".mask");
    var midInput = $("input[name=\"mid\"]");
    var searchDtype = $("#search-dtype");
    var cartMenu = $("#cart-menu");
    var fixedBtns = $("#fixed-btns");
    var loginRequiredModal = $("#login-required-modal");
    var payTheBillBtn = $("#pay-the-bill-btn");
    var isAnimating = false;
    
    // ops
    var dishOp = DishOperator($("#main-content>.row>div:first-child"));
    var cartOp = CartOperator($("#cart-list"));
    var cartAjax = CartAjaxManager();
    
    // init cart
//    cartAjax.loadCart();
    cartOp.updateCounts();
    
    // search funcs
    $(".dish-type-selector").click(function() {
        var prt = $(this).parent().parent().parent();
        prt.attr("data-index", $(this).attr("data-index"));
        prt.children(":first-child").html($(this).text() + " <span class=\"caret\"></span>");
    });
    
    $("#search-form").submit(function() {
        var dtype = searchDtype.attr("data-index");
        var keyword = $(this).find("input").val();
        var mid = midInput.val();
        
        // AJAX: search dishes
        $.ajax({
            url: contextPath + "/api/search/dish",
            type: "POST",
            data: JSON.stringify({
                mid: mid,
                dtype: dtype,
                keyword: keyword
            }),
            headers: {"Content-Type": "application/json"},
            dataType: "json"
        }).done(function(response) {
            if (response && response.code === 1) {
                dishOp.clear();
                response.resultList.forEach(function(item) {
                    dishOp.addDish(item.id, item.dName, item.dType, item.dPrice, item.dImage);
                });
            }
        });
        
        return false;
    });
    
    // cart funcs
    // main content: show cart
    $("#open-cart-btn").click(function() {
        cartMenu.animate({ right: 0 }, 200);
        
        mask.css({ display: "block" });
        mask.animate({ opacity: 0.5 }, 200);
    });
    
    // AJAX: minus
    $("body").on("click", ".cart-minus", function() {
        var _this = $(this);
        if (_this.hasClass("mouse-disabled")) {
            return;
        }
        
        // AJAX: reduce in cart
        var did = _this.parent().parent().parent().attr("data-did");
        cartAjax.reduceCartItem(did, function() {
            var inputBox = _this.next();
            var newVal = parseInt(inputBox.val()) - 1;
            if (newVal <= 0) {
                _this.addClass("mouse-disabled");
            }
            inputBox.val(newVal);
            cartOp.updateCounts();
        });
        
    });
    
    // AJAX: plus
    $("body").on("click", ".cart-plus", function() {
        var _this = $(this);
        // AJAX: add in cart
        var did = _this.parent().parent().parent().attr("data-did");
        cartAjax.increaseCartItem(did, function() {
            var inputBox = _this.prev();
            var minusBtn = inputBox.prev();
            minusBtn.removeClass("mouse-disabled");
            inputBox.val(parseInt(inputBox.val()) + 1);
            cartOp.updateCounts();
        });
    });
    
    // AJAX: delete
    $("body").on("click", ".delete-cart-item", function() {
        var _this = $(this);
        var did = _this.parent().parent().attr("data-did");
        cartAjax.deleteCartItem(did, function() {
            var cartItem = _this.parent().parent();
            cartItem.remove();
            cartOp.updateCounts();
        });
        
    });

    
    // main content: hide cart
    $(".close-cart-menu").click(function() {
        cartMenu.animate({ right: "-400px" }, 200);
        
        mask.animate({ opacity: 0 }, 200, function() {
            mask.css({ display: "none" });
        });
    });
    
    // main content "Add to Cart" button onclick
    // AJAX: add to cart
    $("body").on("click", ".add-to-cart", function() {
        if (isAnimating) {
            return;
        }
        
        isAnimating = true;
        
        var img = $(this).prev();
        var imgOffset = img.offset();
        
        var imgClone = img.clone();
        imgClone.css({
            position: "fixed",
            width: img.css("width"),
            height: img.css("height"),
            top: imgOffset.top - $(window).scrollTop(),
            left: imgOffset.left - $(window).scrollLeft()
        });
        $("#main-content").append(imgClone);
        
        imgClone.animate({
            opacity: 0.5,
            width: fixedBtns.width(),
            height: fixedBtns.height(),
            left: fixedBtns.css("left"),
            top: fixedBtns.css("top")
        }, 1000, function() {
            imgClone.remove();
            
            // AJAX: Add to Cart
            var did = img.parent().parent().parent().attr("data-did");
            var dishInfo = img.parent().next();
            var dname = dishInfo.find("h3").text();
            var dprice = dishInfo.find("h4").text();
            
            var cartItem = cartOp.findCartItem(did);
            if (cartItem.length > 0) {
                // cart already exists
                cartAjax.increaseCartItem(did, function() {
                    var inputBox = cartItem.find("input");
                    var minusBtn = inputBox.prev();
                    minusBtn.removeClass("mouse-disabled");
                    inputBox.val(parseInt(inputBox.val()) + 1);
                    cartOp.updateCounts();
                });
            } else {
                // add new item to cart
                cartAjax.increaseCartItem(did, function() {
                    cartOp.addToCart(did, dname, dprice, 1, img.attr("src"));
                    cartOp.updateCounts();
                });
                
            }
            
            fixedBtns.effect("shake", { direction: "up", distance: 10 });
            isAnimating = false;
            
        });
        
    });
    
//    $("#cart-pay").click(function() {
//        cartAjax.clearCart(function() {
//            cartOp.clear();
//            cartOp.updateCounts();
//        });
//    })
    
    
    function DishOperator(row) {
        var obj = {};
        
        obj.addDish = function(did, dname, dtype, dprice, dimg) {
            var htmlStr = "<div class=\"col-sm-12 col-md-6\" data-did=\"" + did + "\">"+
                          "    <div class=\"thumbnail\">"+
                          "        <div class=\"dish-img\">"+
                          "            <img src=\"" + contextPath + "/res/" + dimg + "\">"+
                          "            <button class=\"btn btn-warning add-to-cart\"><span class=\"glyphicon glyphicon-plus\"></span>Add to Cart</button>"+
                          "        </div>"+
                          "        <div class=\"dish-info\">"+
                          "            <div class=\"caption\">"+
                          "                <h3>" + dname + "</h3>"+
                          "                <p>" + dtypeList[parseInt(dtype)] + "</p>"+
                          "                <h4>" + dprice + "</h4>"+
                          "            </div>"+
                          "        </div>"+
                          "        <div class=\"clear\"></div>"+
                          "    </div>"+
                          "</div>";

            row.append($.parseHTML(htmlStr));
        }
        obj.clear = function() {
            row.empty();
        }
        return obj;
    }
    
    function CartAjaxManager() {
        var obj = {};
//        obj.addToCart = function(did, onSuccess, onFail, onFinally) {
//            $.ajax({
//                url: "../addcartitem?did=" + did,
//                type: "POST",
//                dataType: "json"
//            }).done(function(response) {
//                if (response.code === 1) {
//                    onSuccess();
//                }
//            });
//        }
        obj.increaseCartItem = function(did, onSuccess, onFail, onFinally) {
            $.ajax({
                url: contextPath + "/api/cart/increase/" + did,
                type: "POST",
                dataType: "json"
            }).done(function(response) {
                if (response.code === 1) {
                    onSuccess();
                } else if (response.msg == "User not logined!") {
                    loginRequiredModal.modal("show");
                }
            });
        }
        obj.reduceCartItem = function(did, onSuccess, onFail, onFinally) {
            $.ajax({
                url: contextPath + "/api/cart/decrease/" + did,
                type: "POST",
                dataType: "json"
            }).done(function(response) {
                if (response.code === 1) {
                    onSuccess();
                } else if (response.msg == "User not logined!") {
                    loginRequiredModal.modal("show");
                }
            });
        }
        obj.deleteCartItem = function(did, onSuccess, onFail, onFinally) {
            $.ajax({
                url: contextPath + "/api/cart/delete/" + did,
                type: "POST",
                dataType: "json"
            }).done(function(response) {
                if (response.code === 1) {
                    onSuccess();
                } else if (response.msg == "User not logined!") {
                    loginRequiredModal.modal("show");
                }
            });
        }
        obj.clearCart = function(onSuccess, onFail, onFinally) {
            $.ajax({
                url: contextPath + "/api/cart/clear",
                type: "POST",
                dataType: "json"
            }).done(function(response) {
                if (response.code === 1) {
                    onSuccess();
                } else if (response.msg == "User not logined!") {
                    loginRequiredModal.modal("show");
                }
            });
        }
//        obj.loadCart = function(did, onSuccess, onFail, onFinally) {
//            $.ajax({
//                url: contextPath + "/api/cart/all",
//                type: "POST",
//                dataType: "json"
//            }).done(function(response) {
//                if (response) {
//                    response.resultList.forEach(function(item) {
//                        cartOp.addToCart(item.dId, item.dName, item.price, item.count, contextPath + "/res/" + item.dimgpath);
//                    });
//                    cartOp.updateCounts();
//                }
//                
//            });
//        }
        
        return obj;
    }
    
    function CartOperator(cart) {
        var obj = {};
        obj.findCartItem = function(did) {
            return cart.find("li[data-did=\"" + did + "\"]");
        }
        obj.addToCart = function(did, dname, dprice, count, dimg) {
            var htmlStr = "<li class=\"list-group-item\" data-did=\"" + did + "\">"+
                          "    <div class=\"cart-item-img\">"+
                          "        <img src=\"" + dimg + "\">"+
                          "    </div>"+
                          "    <div class=\"cart-item-info\">"+
                          "        <div class=\"caption\">"+
                          "            <h4>" + dname + "</h4>"+
                          "            <p>Single: <strong class=\"single-price\">" + dprice + "</strong></p>"+
                          "            <p>Total: <strong class=\"total-price\">" + (parseFloat(dprice) * count) + "</strong></p>"+
                          "        </div>"+
                          "    </div>"+
                          "    <div class=\"cart-item-ops\">"+
                          "        <button class=\"btn btn-danger delete-cart-item\"><span class=\"glyphicon glyphicon-trash\"></span></button>"+
                          "       "+
                          "        <div class=\"input-group\">"+
                          "            <a href=\"javascript:void(0);\" class=\"cart-minus input-group-addon\"><span class=\"glyphicon glyphicon-minus\"></span></a>"+
                          "            <input type=\"text\" class=\"form-control\" value=\"" + count + "\">"+
                          "            <a href=\"javascript:void(0);\" class=\"cart-plus input-group-addon\"><span class=\"glyphicon glyphicon-plus\"></span></a>"+
                          "        </div>"+
                          "    </div>"+
                          "    <div class=\"clear\"></div>"+
                          "</li>";
            cart.append($.parseHTML(htmlStr));
        }
        obj.updateCounts = function() {
            var allPrice = 0;
            cart.children().each(function() {
                var counter = $(this).find(".cart-item-ops>.input-group>input");
                var single = $(this).find(".cart-item-info>.caption>*:nth-child(2)>strong");
                var total = $(this).find(".cart-item-info>.caption>*:nth-child(3)>strong");
                
                var totalVal = parseInt(counter.val()) * parseFloat(single.text());
                allPrice += totalVal;
                total.text(totalVal);
            });
            
            if (allPrice <= 0) {
                payTheBillBtn.addClass("disabled");
            } else {
                payTheBillBtn.removeClass("disabled");
            }
            
            cart.next().find("strong").text(allPrice);
        }
        obj.clear = function() {
            cart.empty();
        }
        return obj;
    }

});