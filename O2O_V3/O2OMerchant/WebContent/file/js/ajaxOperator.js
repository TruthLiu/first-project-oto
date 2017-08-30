function AjaxOperator(){
    this.sendGet=function(url,fndone,fnfail){
        $.ajax({
            type:"GET",
            url:url,
            dataType:"json"
        }).done(function(response){
            fndone(response);
        }).fail(function(error){
            
        });
    }
    
    this.sendPost=function(url,paramObj,fndone,fnfail){
        $.ajax({
            type:"POST",
            url:url,
            data:JSON.stringify(paramObj),
            dataType:"json",
            contentType:"application/json"
        }).done(function(response){
            fndone(response);
        }).fail(function(error){
            
        });
    }
    
    this.sendDelete=function(url,fndone,fnfail){
         $.ajax({
            type:"DELETE",
            url:url,
            dataType:"json"
        })
        .done(function(response){
            fndone(response);
        }).fail(function(error){
            fnfail(error);
        });
    }
    
    this.sendPut=function(url, paramObj, fndone, fnfail){
        $.ajax({
               type:"PUT",
               url:url,
               data:JSON.stringify(paramObj),
               dataType:"json",
               contentType:"application/json"
           }).done(function(data){
               if(fndone){
                   fndone(data);
               }
           }).fail(function(error){
               if(fnfail){
                   fnfail(error);
               }
           });
       }
    
    this.sendFiles=function(url,paramObj,files,fndone,fnfail){
    	$.ajaxFileUpload({
			url: url,
			secureuri: false,
			fileElementId: files,
			data: paramObj,
			dataType: "json",
			success: function(response) {
					fndone(response)
			},
			error: function(response) {
					
			}
			});
    }
}