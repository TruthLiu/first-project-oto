$(function() {
    var ws = WebSocketUtil(MSide.WS_URL, function(e) {
//        alert("OrderList Page Received: " + e.data);
        $("#order-accepted-notification")
            .text("OrderList Page Received: " + e.data)
            .css({"display":"block"});
    });
    
    ws.send(JSON.stringify({
        cId: loginCustomerId
    }));
});