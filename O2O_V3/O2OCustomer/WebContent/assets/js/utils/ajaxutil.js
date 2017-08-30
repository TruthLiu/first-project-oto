(function() {
    var obj = {};
    obj.ajax = function(type, url, data, headers, context, callback) {
        $.ajax({
            type: type,
            url: url,
            data: data,
            headers: headers,
            context: context
        }).done(function(response) {
            callback(response);
        });
    };
    
    window.j = obj;
})();