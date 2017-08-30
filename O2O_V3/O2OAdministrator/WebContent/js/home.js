$(function(){
	//merchent js
	$("#search").on("click",function(e){
		$("#iframe").attr("src","merchant");
		
	});
	
	//forward
	$("#comSearch").on("click",function(e){
		$("#iframe").attr("src","complaint/home");
	});
	
	$("#adverSearch").on("click",function(e){
		$("#iframe").attr("src","adver/search");
	});
	
	$("#adverProcess").on("click",function(e){
		$("#iframe").attr("src","adver/process");
	});
	
    //退出
    $("#logout").on("click",function(){
        window.location="../sec/signout";
    });
});