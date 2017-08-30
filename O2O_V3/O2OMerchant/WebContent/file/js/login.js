
$(function(){
	var ap = new AjaxOperator();
    $.backstretch("file/img/backgrounds/KFC.jpg");
    
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	e.preventDefault();
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			$(this).addClass('input-error');
    			return false;
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    	var mAccount = $("input[name='mAccount']").val();
		var pwd = $("input[name='pwd']").val();
		
    	ap.sendGet("merchant/login?mAccount="+mAccount+"&pwd="+pwd, function(response){
    		if (response.code == "200") {
    			alert("login success");
				window.location.href="merchant/sec/homePage";
				
            }else if(response.code == "100"){
            	swal("Server A Down");
            }
    		else{
            	swal(response.msg);
            }
    	}, null)
    });
    
    
    
    
});
