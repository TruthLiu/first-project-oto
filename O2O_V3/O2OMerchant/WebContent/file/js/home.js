$(function(){
	
	var ajaxOP = new AjaxOperator();
	
	$("#btnState").on("click",function(){
		ajaxOP.sendGet("getState.action", pareState, error);
	});
	
	function pareState(data){
		
		var status = data.mStatus;
		var feedback =data.feedback;
		var stateDiv = $("#stateDiv");
        stateDiv.empty();
		var p = $("<p>");
		if(status==0){
			p.attr("style","color:#FFFF00");
            p.text("State : Pending");
		}else if(status==1){
			p.attr("style","color:#7CFC00");
            p.text("State : Pass");
            var dishManageLi = $("#dishManageLi");
            dishManageLi.empty();
            var a = $("<a>");
            a.text("Dish Manage");
            a.attr("href","dishes/list");
            dishManageLi.append(a);
            var applyStoreLi = $("#applyStoreLi");
            applyStoreLi.remove();
		}else if(status==2){
			p.attr("style","color:#FF4500");
            p.text("State : Reject");
            var rejectDiv = $("#rejectDiv");
            rejectDiv.empty();
            var rejectP = $("<p>");
            rejectP.text("Reject Reson : "+feedback);
            rejectP.attr("style","color:red;");
            rejectDiv.attr("style","font-size:16px");
            rejectDiv.append(rejectP);
		}
        stateDiv.append(p);
	}
	
	function error(data){
		
	}
	
});