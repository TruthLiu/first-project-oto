$(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("assets/img/backgrounds/KFC.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
        
        var cpwd = $(this).find('input[name="cpwd"]');
        var cpwdcfm = $(this).find('input[name="cpwdcfm"]');
        if (cpwd.val() != cpwdcfm.val()) {
            e.preventDefault();
            cpwd.addClass('input-error');
            cpwdcfm.addClass('input-error');
        }
        else {
            cpwd.removeClass('input-error');
            cpwdcfm.removeClass('input-error');
        }
    	
    });
    
    
});
