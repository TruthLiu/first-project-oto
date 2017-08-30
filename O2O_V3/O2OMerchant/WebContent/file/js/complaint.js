$(function(){
	var ap = new AjaxOperator();
	window.ap = ap;
	
	window.component = new ComplaintComponent($("#complaintTab"));
	
	$("#search").on("click", function(e){
		e.preventDefault();
		var from = $("input[name=from]").val();
		var to = $("input[name=to]").val();
		var obj = {"from": from, "to": to};
		
		component.createTab(obj);
	});

});

function ComplaintComponent(table){
	this.model = null;
	this.table = table;
	
	this.createTab = function(obj){
		ap.sendPost("getComplaints", obj, function (response){
			alert(response);
			if(response.code=="500"){
				swal("You Ban");
			}else{
				this.model = response.resultList;
				renderTable(model);
			}
		});
	};
	
//	var fndone = function (response){
//		alert(response);
//		if(response.code=="500"){
//			swal("You Ban");
//		}else{
//			this.model = response.resultList;
////			var data = response.resultList;
//			renderTable(model);
//		}
//	};
	
	function renderTable(data){
		table.empty();
		var thead=$("<thead>").append($("<tr>")
				.append($("<th>").text("#").addClass("style","text-align: center"))
                .append($("<th>").text("Time").addClass("style","text-align: center"))
                .append($("<th>").text("Message").addClass("style","text-align: center")));
		table.append(thead);
		$("tab_div").append(table);
		
		data.forEach(function(item,index){
			var td0=$("<td>").text(index);
            var time=new Date(item.time).getFullYear()+"-"+(new Date(item.time).getMonth()+1)+"-"+new Date(item.time).getDate();
            var td2=$("<td>").text(time);
            var td3=$("<td>").text(item.msg);
            var tr=$("<tr>").append(td0).append(td2).append(td3);
            table.append(tr);
		});
	}
}
