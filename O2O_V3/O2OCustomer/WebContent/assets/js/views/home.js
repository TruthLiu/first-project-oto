$(function() {
    var searchDtype = $("#search-dtype");
    var shopOp = ShopsOperator($("#main-content>.row"));
    
    // test
//    shopOp.addShop("1", "Test2", "Houston", "http://fanyi.baidu.com/static/translation/img/header/logo_cbfea26.png");
    
    // fix advert region on scrool
    $(window).scroll(function() {
        var currentScrollTop = $(this).scrollTop();
        
    });
    
    $(".dish-type-selector").click(function() {
        var prt = $(this).parent().parent().parent();
        prt.attr("data-index", $(this).attr("data-index"));
        prt.children(":first-child").html($(this).text() + " <span class=\"caret\"></span>");
    });
    
    $("#search-form").submit(function(e) {
        e.preventDefault();
        
        var dtype = searchDtype.attr("data-index");
        var keyword = $(this).find("input").val();
        
        // AJAX: search shops
        $.ajax({
            url: contextPath + "/api/search/restaurant",
            type: "POST",
            data: JSON.stringify({
                dtype: dtype,
                keyword: keyword
            }),
            headers: {"Content-Type": "application/json"},
            dataType: "json"
        }).done(function(response) {
            if (response && response.code === 1) {
                shopOp.clear();
                response.resultList.forEach(function (item) {
                    shopOp.addShop(item.id, item.mName, item.address, item.imgHead);
                });
            }
        });
    });
    
    function ShopsOperator(row) {
        var obj = {};
        obj.addShop = function(mid, mname, maddr, mimg) {
            var htmlStr = "<div class=\"col-xs-12 col-sm-6 col-md-4\">"+
                          "    <a href=\"restaurant/" + mid + "\">"+
                          "        <div class=\"thumbnail\">"+
                          "            <img src=\"" + contextPath + "/res/" + mimg + "\">"+
                          "            <div class=\"caption\">"+
                          "                <h3>" + mname + "</h3>"+
                          "                <p>Address: " + maddr + "</p>"+
                          "            </div>"+
                          "        </div>"+
                          "    </a>"+
                          "</div>";
            row.append($.parseHTML(htmlStr));
        }
        obj.clear = function() {
            row.empty();
        }
        return obj;
    }
});