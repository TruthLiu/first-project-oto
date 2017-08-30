$(function() {
	var ap = new AjaxOperator();
	window.ap = ap;
    
    
    var allComment = new AllCommentComponent($("#allUl"));
    var newComment = new NewCommentComponent($("#newUl"));
    newComment.findNewComments();
   
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    	  var target = $(e.target).attr("href") // activated tab
    	  if(target=='#all'){
    		  alert(target);
    		  allComment.findAllComments();
    		  
    	  }else{
    		  alert(target);
    		  newComment.findNewComments();
    	  }
    });
    
    //模态框回复按钮
    $("#btnReply").on("click",function(){
    	var coId = $("#coId").val();
    	var content = $("#replyArea").val();
    	alert("coId:"+coId);
    	var data = {"coId":coId,"content":content};
    	ap.sendPost("reply", data, function(response){
    		swal("Reply Success");
    		allComment.findAllComments();
    		newComment.findNewComments();
    	});
    	
    });
    
});
//new comment
function NewCommentComponent(template){
	this.model = null;
	this.template = template;
	this.findNewComments=function(){
			ap.sendGet("getNewComments",function(response){
				if(response.code=="500"){
					swal("You Ban");
				}else{
					this.model = response.resultList;
					renderNewComment(model);
				}
			});
	}
	
	function renderNewComment(data){
		template.empty();
		data.forEach(function(item){
			var li = $("<li>").addClass("list-group-item");
			var div1 = $("<div>").addClass("float-left");
			var div2 = $("<div>").addClass("float-right");
			var divClear = $("<div>").addClass("clear");
			var p = $("<p>").text(item.customer.cname+" : "+item.content);
			var starP = renderStar(item.star);
			var timeP = item.createTime;
			var button = $("<button>").addClass("reply-btn btn btn-success").text("Reply")
										.attr("coId",item.id)
										.attr("data-toggle","modal")
										.attr("data-target","#reply-modal");
				//has reply
			div1.append(p);
			div1.append(starP);
			div2.append(timeP);
			div2.append(button);
			li.append(div1);
			li.append(div2);
			li.append(divClear);
			template.append(li);
		});	
		$(".reply-btn").on("click", function() {
			$("#replyArea").val('');
			replyContent($(this));
	    });
	}
}


//all comment
function AllCommentComponent(template){
	this.model = null;
	this.template = template;
	
	 this.findAllComments=function(){
		ap.sendGet("getAllComments",function(response){
			if(response.code=="500"){
				swal("You Ban");
			}else{
				this.model = response.resultList;
				renderAllComment(model);
			}
			
			
			
		});
	}
	
	
	function renderAllComment(data){
		template.empty();
		
		data.forEach(function(item){
			var li = $("<li>").addClass("list-group-item");
			var div1 = $("<div>").addClass("float-left");
			var div2 = $("<div>").addClass("float-right");
			var divClear = $("<div>").addClass("clear");
			var p = $("<p>").text(item.customer.cname+" : "+item.content);
			var starP = renderStar(item.star);
			var timeP = item.createTime;
			if(item.reply!=null){
				//has reply
				var replyP = $("<p>").text("Reply : "+item.reply.content).attr("style","color:green"); 
				div1.append(p);
				div1.append(starP);
				div1.append(replyP);
				div2.append(timeP);
			}else{
				//no reply
				div1.append(p);
				div1.append(starP);
				var button = $("<button>").addClass("reply-btn btn btn-success").text("Reply")
											.attr("coId",item.id)
											.attr("data-toggle","modal")
											.attr("data-target","#reply-modal");
				div2.append(timeP);
				div2.append(button);
			}
			li.append(div1);
			li.append(div2);
			li.append(divClear);
			template.append(li);
		});	
		$(".reply-btn").on("click", function() {
			$("#replyArea").val('');
			replyContent($(this));
	    });
		
	}
	
}


function replyContent(btn){
	var coId = btn.attr("coId");
	$("#coId").val(coId);
}

//星级函数
function renderStar(num){
	var p= $("<p>");
	for(let i=1;i<=num;i++){
		let i1=$("<i>").addClass("glyphicon glyphicon-star");
		p.append(i1);
	}
	var last = 5-num;
	if(last>0){
		for(let j=1;j<=last;j++){
			let i2 = $("<i>").addClass("glyphicon glyphicon-star-empty");
			p.append(i2);
		}
	}
	return p;
}


