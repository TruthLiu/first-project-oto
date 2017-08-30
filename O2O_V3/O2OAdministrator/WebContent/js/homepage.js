$(function(){
	var ap = new AjaxOperator();
	var to = new TableOperator();
	//默认显示审核列表
	ap.sendGet("../sec/pendingList",parseInProcess,parseError);
    
    //查询驳回列表
    $("#reject").on("click",function(){
    	ap.sendGet("../sec/reject",parseReject,parseError);
    });
    function parseReject(data){
    	if(data.success){
    		 to.createTable($("#tab"),data.objList,"reject");
    	     to.bindCheckDetail("../sec/detail",bindViewModal,parseError);
    	}
    }
    
    //查询通过列表
    $("#passed").on("click",function(){
    	ap.sendGet("../sec/pass",parsePass,parseError);
    	
    });
    function parseBan(data){
        if(data.success==true){
            var selector="button[mid="+data.obj+"]";
            var text=$(selector).eq(0).text();
            if(text=="Ban"){
            	$(selector).eq(0).removeClass("black").addClass("white").text("Not Ban").attr("change","white");
            }else{
            	$(selector).eq(0).removeClass("white").addClass("black").text("Ban").attr("change","black");
            }
        }else{
            alert("Ban fail");
        }
    }
    function parsePass(data){
    	 if(data.success){
    		to.createTable($("#tab"),data.objList,"passed");
	        to.bindCheckDetail("../sec/detail",bindViewModal,parseError);
	        to.bindBanEvent("../sec/ban",parseBan,parseError);
    	 }
    }
    
    //查询审核列表
    $("#inprocess").on("click",function(){
    	ap.sendGet("../sec/pendingList",parseInProcess,parseError);
    });
    function parseInProcess(data){
       if(data.success){
    	   to.createTable($("#tab"),data.objList,"process");
       	   to.bindApprovalEvent("../sec/toProcess",bindApproveModal,parseError);
       }
    }
    
    //渲染数据并显示审核模态框
    function bindApproveModal(data){
    	if(data.success){
    		data=data.obj;
    		$("#processModal tr").eq(0).children().last().children().first().children().first().attr("src",to.IMG_URL+data.idcard);
            $("#processModal tr").eq(1).children().last().children().first().children().first().attr("src",to.IMG_URL+data.heading);
            $("#processModal tr").eq(2).children().last().children().first().children().first().val(data.name);
            $("#processModal tr").eq(3).children().last().children().first().children().first().val(data.address);
            $("#processId").val(data.id);
    	}else {
    		alert("no data find");
    	}
    	
    }
    
    //渲染数据并显示模态框
    function bindViewModal(data){
//    	alert(data.name);
    	if(data.success){
    		data=data.obj;
    		$("#viewModal tr").eq(0).children().last().children().first().children().first().attr("src",to.IMG_URL+data.idcard);
            $("#viewModal tr").eq(1).children().last().children().first().children().first().attr("src",to.IMG_URL+data.heading);
            $("#viewModal tr").eq(2).children().last().children().first().children().first().val(data.name);
            $("#viewModal tr").eq(3).children().last().children().first().children().first().val(data.address);
            
            var status = data.status;
            if(status==0){
            	 $("#viewModal tr").eq(4).children().last().children().first().children().first().val("Inprocess");
            }
            if(status==1){
            	$("#viewModal tr").eq(4).children().last().children().first().children().first().val("Passed");
            }
            if(status==2){
            	$("#viewModal tr").eq(4).children().last().children().first().children().first().val("Rejected");
            }
            $("#viewModal tr").eq(5).children().last().children().first().children().first().val(data.ban);
    	}else {
    		alert("No data find")
    	}
    	
    }
    
    //提交审核
    $("#submitProcess").on("click",function(e){
    	var rs = $("#usertype option:selected").val();
    	var bcontent=$("#reasonText").val();
    	var id = $("#processId").val();
        var obj = {result:rs,bContent:bcontent,id:id};
    	ap.sendPost("../sec/process",obj,parseSubmitProcess,parseError);
    });
    function parseSubmitProcess(data){
    	var flag = data.success;
    	if(flag){
    		alert(data.obj);
    		$("#processModal").modal('hide');
    		ap.sendGet("../sec/pendingList",parseInProcess,parseError);
    	}else {
    		alert(data.obj);
    	}
    }
    
  //监听器
//  listener.listen("../sec/listen",parseListen,parseError)
  var listener = setInterval(listen,1000);
  function listen(data){
  	ap.sendGet("../sec/listen",parseListen,parseListenError);
  }
  function parseListen(data){
  	if(data.success){
  		console.log(data.obj);
  		$("#pendCount").text("("+data.obj+")");
  	}else {
  		$("#pendCount").text("");
  	}
  }
  function parseListenError(){
  	console.log("listener is down")
  	clearInterval(listener);
  }
    
    
    //ajax请求失败
    function parseError(error){
        alert("server is down^-^");
    }
    
    //驳回意见显示和隐藏
    $("#rejectReason").hide();
    var flag = false;
    $("#usertype").bind("change",function(e){
        if(flag){
            $("#rejectReason").hide();
        }else {
            $("#rejectReason").show();
        }
        flag=!flag;
    });
});