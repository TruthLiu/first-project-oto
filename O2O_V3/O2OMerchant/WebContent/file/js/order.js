$(function() {
	var ap = new AjaxOperator();
	window.ap = ap;

	var waitOrders = new findWaitOrders($("#waitUl"));
	waitOrders.findWairOrders();
	
});

//未接订单
function WaitOrdersComponent(template){
	this.model = null;
	this.template = template;
	this.findWaitOrders=function(){
		ap.sendGet("getOrders?status=0",function(response){
			if(response.code=='500'){
				swal("You Ban");
			}else{
				this.model = response.resultList;
				renderWaitOrders(model);
			}
		},null);
	}
	//"+item.realName+"
	function renderWaitOrders(data){
		template.empty();
		data.forEach(function(item){
			var htmlTr;
			item.itemVO.forEach(function(itemTr){
				htmlTr = 
				"   <tr>"+
				"       <td>"+itemTr.dishName+"</td>"+
				"		<td>"+itemTr.num+"</td>"+
				"		<td>"+itemTr.unitPrice+"/td>"+
				"	</tr>";
			});
			
			
			var htmlStr = "<li class=\"list-group-item\">"+
			"    <div class=\"float-left\">"+
			"        <h2>"+item.realName+"</h2>"+
			"        <p>"+item.totalPrice+"</p>"+
			"        <p>"+item.addr+"</p>"+
			"        <a data-toggle=\"collapse\" data-parent=\"#accordion\" "+
			"				 href=\"#"+item.id+"\">"+
			"			Detail"+
			"		</a>"+
			"    </div>"+
			"    <div class=\"float-right\">"+
			"        <p>"+item.createTime+"</p>"+
			"        <button class=\"yes-btn btn btn-success\">Yes</button>"+
			"        <button class=\"no-btn btn btn-danger\">No</button>"+
			"    </div>"+
			"    "+
			"    <div class=\"clear\"></div>"+
			"    <div id=\""+item.id+"\" class=\"panel-collapse collapse\">"+
			"		<div class=\"panel-body\">"+
			"			<table class=\"table table-striped\">"+
			"				<thead>"+
			"					<td>DishName</td>"+
			"					<td>Num</td>"+
			"					<td>Price</td>"+
			"				</thead>"+
			"				<tbody>"+
									htmlTr
									+
			"				</tbody>"+
			"			</table>"+
			"		</div>"+
			"	</div>"+
			"</li>";
			
			template.append($.parseHTML(htmlStr));
		});	
	}
}

//已接订单
function ReceiveOrdersComponent(template){
	this.model = null;
	this.template = template;
	this.findReceiveOrders=function(){
		ap.sendGet("getOrders?status=1",function(response){
			if(response.code=='500'){
				swal("You Ban");
			}else{
				this.model = response.resultList;
				renderReceiveOrders(model);
			}
		},null);
	}
	//"+item.realName+"
	function renderReceiveOrders(data){
		template.empty();
		data.forEach(function(item){
			var htmlTr;
			item.itemVO.forEach(function(itemTr){
				htmlTr = 
				"   <tr>"+
				"       <td>"+itemTr.dishName+"</td>"+
				"		<td>"+itemTr.num+"</td>"+
				"		<td>"+itemTr.unitPrice+"/td>"+
				"	</tr>";
			});
			
			
			var htmlStr = "<li class=\"list-group-item\">"+
			"    <div class=\"float-left\">"+
			"        <h2>"+item.realName+"</h2>"+
			"        <p>"+item.totalPrice+"</p>"+
			"        <p>"+item.addr+"</p>"+
			"        <a data-toggle=\"collapse\" data-parent=\"#accordion\" "+
			"				 href=\"#"+item.id+"\">"+
			"			Detail"+
			"		</a>"+
			"    </div>"+
			"    <div class=\"float-right\">"+
			"        <p>"+item.createTime+"</p>"+
			"        <button class=\"reply-btn btn btn-success\">Yes</button>"+
			"        <button class=\"reply-btn btn btn-danger\">No</button>"+
			"    </div>"+
			"    "+
			"    <div class=\"clear\"></div>"+
			"    <div id=\""+item.id+"\" class=\"panel-collapse collapse\">"+
			"		<div class=\"panel-body\">"+
			"			<table class=\"table table-striped\">"+
			"				<thead>"+
			"					<td>DishName</td>"+
			"					<td>Num</td>"+
			"					<td>Price</td>"+
			"				</thead>"+
			"				<tbody>"+
									htmlTr
									+
			"				</tbody>"+
			"			</table>"+
			"		</div>"+
			"	</div>"+
			"</li>";
			
			template.append($.parseHTML(htmlStr));
		});	
	}
}