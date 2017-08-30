var MSide = (function() {
    var obj = {};
    
    // config M-Side host here
    var MSIDE_HOST = "10.222.29.186";
//    var MSIDE_HOST = "10.222.29.146";
    // config M-Side port here
    var MSIDE_PORT = "8080";
    // config M-Side URI
    var URI = "/websocket/order/customer";
    
    obj.WS_URL = "ws://" + MSIDE_HOST + ":" + MSIDE_PORT + "/O2OMerchant" + URI;
    return obj;
})();


//var WebSocketFactory = (function() {
//    var ws;
//    var obj = {};
//    obj.getInstance = function() {
//        if (!ws) {
//            ws = new WebSocket(MSide.WS_URL);
//        }
//        return ws;
//    }
//    return obj;
//})();


function WebSocketUtil(url, onmessage) {
//    var ws = WebSocketFactory.getInstance();
    var ws = new WebSocket(url);
    ws.onmessage = onmessage;
    
    var obj = {};
    obj.send = function(message, callback) {
        waitForConnection(function() {
            ws.send(message);
            if (typeof callback !== 'undefined') {
                callback();
            }
        }, 1000);
    }
    obj.close = function() {
        ws.close();
    }
    
    function waitForConnection(callback, interval) {
        if (ws.readyState === 1) {
            callback();
        } else {
            setTimeout(function() {
                waitForConnection(callback, interval);
            }, interval);
        }
    }
    
    return obj;
}