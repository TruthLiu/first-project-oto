$(function(){
	var ap = new AjaxOperator();
	$.backstretch("file/img/backgrounds/KFC.jpg");
	$('.regist-form').on("submit",function(e){
		e.preventDefault();
		var mAccount = $("input[name='mAccount']").val();
		var pwd = $("input[name='pwd']").val();
		var pwd2 = $("input[name='pwd2']").val();
		var mName = $("input[name='mName']").val();
		var address = $("input[name='address']").val();
		var imgCard = $("input[name='imgCard']").val();
		var imgHead = $("input[name='imgHead']").val();
		if(mAccount==""||pwd==""||pwd2==""||mName==""||imgCard==""||imgHead==""){
			swal("message is null");
			return false;
		}
		if(pwd!=pwd2){
			swal("pwd is no same");
			return false;
		}
		var data={"mAccount":mAccount,"pwd":pwd,"mName":mName,"address":address};
		var fileId=["imgCard","imgHead"];
		ap.sendFiles("merchant/regist",data,fileId,function(response){
			if (response.code == "200") {
				
				window.location.href="login.jsp";
				
            }else if(response.code == "100"){
            	swal("Server A Down");
            }
			else{
            	swal(response.msg);
            }
		},null);
		return false;
	});
	
});