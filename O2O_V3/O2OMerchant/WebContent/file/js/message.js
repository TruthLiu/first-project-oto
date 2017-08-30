$(function(){
	var ap = new AjaxOperator();
	
	$("#btnState").on("click",function(){
		ap.sendGet("getState", parseState, null);
	});
	
	function parseState(response){
		if(response.code=="100"){
			swal("Server A Down");
			return;
		}else if(response.code="500"){
			swal("You Ban");
			return;
		}
		else{
			swal("Request Success");
			var state=response.result.state;
			var feedback=response.result.content;
			var stateTr=$("#stateTr");
			stateTr.empty();
			var td1 = $("<td>").text("State:");
			var td2 = $("<td>");
			if(state==0){
				td2.text("Pending");
				td2.attr("style","color:#FFFF00");
			}else if(state==1){
				td2.text("Pass");
				td2.attr("style","color:#7CFC00");
				parent.left.location.href=contextPath+"merchant/sec/menuList";
			}else if(state==2){
				td2.text("Reject("+feedback+")");
				td2.attr("style","color:#FF4500");
			}
			stateTr.append(td1);
			stateTr.append(td2);
		}
		
	}
	
	$("#btnApply").on("click",function(){
		$("input[name='mAccount']").val("");
    	$("input[name='mName']").val("");
    	$("input[name='address']").val("");
    	$("input[name='imgCard']").val("");
		$("input[name='imgHead']").val("");
		ap.sendGet("getApplyMsg", parseApply, null);
	});
	
	function parseApply(response){
		if(response.code=="200"){
			var m = response.result;
			$("input[name='mAccount']").val(m.mAccount);
	    	$("input[name='mName']").val(m.mName);
	    	$("input[name='address']").val(m.address);
		}
	}
	
	$("#updateBtn").on("click",function(){
		var mName = $("input[name='mName']").val();
		var address = $("input[name='address']").val();
		var imgCard = $("input[name='imgCard']").val();
		var imgHead = $("input[name='imgHead']").val();
		if(mName==""||address==""){
			swal("message is null");
			return false;
		}
		
		var data={"mName":mName,"address":address};
		var fileId=["imgCard","imgHead"];
		ap.sendFiles("applyStore",data,fileId,function(response){
			if (response.code == "200") {
				swal("application has been send");
				parseMessage(response.result);
				
            }else if(response.code == "100"){
            	swal("Server A Down");
            }
			else{
            	swal(response.msg);
            }
		},null);
		return false;
	});
	
	function parseMessage(data){
		var fileUrl=contextPath+"res/";
		var headDiv=$("#headDiv");
		var shopNameTr=$("#shopNameTr");
		var addressTr=$("#addressTr");
		headDiv.children().remove();
		shopNameTr.children('td').remove();
		addressTr.children('td').remove();
		var headUrl=data.imgHead;
		var shopName=data.mName;
		var address=data.address;
		fileUrl = fileUrl+headUrl;
		var img = $("<img>").addClass("img-circle imgHead");
		img.attr("src",fileUrl+"?"+new Date().getTime());
		var td1 = $("<td>").text("Shop Name:");
		var td2 = $("<td>").text(shopName);
		var td3 = $("<td>").text("Address:");
		var td4 = $("<td>").text(address);
		headDiv.append(img);
		shopNameTr.append(td1);
		shopNameTr.append(td2);
		addressTr.append(td3);
		addressTr.append(td4);
	}
	
});