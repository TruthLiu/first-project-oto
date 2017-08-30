$(function() {
	var ajaxOP = new AjaxOperator();
	var dishes = [];
	$("#searchDish").on(
			"click",
			function(e) {
				e.preventDefault();
				var type = $("#dtype").val();
				var dishName = $("#dname").val();
				var mid = $("#searchfrom input[name='id']").val();
				if ("" == dishName) {
					var dish = {
						"dType" : type,
						"mId" : mid
					};
					ajaxOP.sendGet("findDishesByType?dType=" + type + "&mId="
							+ mid, pareState);
				} else {
					var dish = {
						"dType" : type,
						"dName" : dishName,
						"mId" : mid
					};
					var id = "mId";
					ajaxOP.sendGet("findDishesByBoth?dType=" + type + "&mId="
							+ mid + "&dName=" + dishName, pareState);
				}
			});
	
	//增加菜品
	$("#addDish").on("click", function(e) {
		e.preventDefault();
		var did = $("#toAdd input[name='id']").val();
		var dishName = $("#toAdd input[name='dishName']").val();
		var dishType = $("#toAdd select[name='dishType']").val();
		var price = $("#toAdd input[name='price']").val();
		var head = $("#toAdd input[name='headImg']").val();
		var dish = {
			id : did,
			dName : dishName,
			dType : dishType,
			dPrice : price
		};
		var fileId = [ "headImg" ];

		ajaxOP.sendFiles("addDish", dish, fileId, function(data) {
			if (data.code == '200') {
				swal("Add Success");
				dishes.push(data.result);
				pareState(dishes);
			} else if (data.code == "500") {
				swal("You Ban");
			} else if (data.code == "400") {
				swal(data.msg);
			}
			$("#addDish").modal('hide');
		}, null);
	});
	function pareState(data) {
		dishes = data;
		var fileUrl = contextPath + "res/";
		// alert(JSON.stringify(data));
		var dishBody = $("#dishbody");
		dishBody.empty();
		var types = [ "全部", "小吃", "酒水", "主食", "套餐" ];
		dishes.forEach(function(item) {
			var tr = $("<tr>");
			var td1 = $("<td>");
			var img = $("<img>");
			img.attr("src", fileUrl + item.dImage+"?aaa="+(new Date().getTime())).attr("width", 200).attr(
					"height", 200);
			td1.append(img);
			var td2 = $("<td>").text(item.dName);
			var type = item.dType;
			var td3 = $("<td>").text(types[type]);
			var td4 = $("<td>").text(item.dPrice);
			tr.append(td1).append(td2).append(td3).append(td4);
			tr.append($("<td>").append(
					$("<button>").addClass("btn btn-info update").attr("id",
							item.id).attr("data-toggle", "modal").attr(
							"data-target", "#toUpdate").text("update").data(
							item)).append("&nbsp;&nbsp;&nbsp;&nbsp;")
					.append(
							$("<button>").addClass("btn btn-danger delete")
									.attr("id", item.id).attr("data-toggle",
											"modal").attr("data-target",
											"#delete").text("delete")
									.data(item))
			);
			dishBody.append(tr);
		});
		_bindDeleteEvent();
		_bindUpdateEvent();
	}
	
	//点击删除按钮进入模态框确定框
	function _bindDeleteEvent() {
		$("button.delete").on("click",function(e){
			$("#delete input[name='deleteDishId']").val($(this).data().id);
		});
	}
	//确定删除
	$("#makedelete").on("click",function(e) {
			var dishId = $("#delete input[name='deleteDishId']").val();
//			alert(dishId);
			ajaxOP.sendDelete("deleteDish?id="+dishId, function(data){
				if (data.code == '200') {
					dishes = dishes.filter(function(item) {
						return item.id != data.result.id;
					});
					swal("delete Success");
					pareState(dishes);
					
				} else if (data.code == "500") {
					swal("You Ban");
				} else if (data.code == "400") {
					swal(data.msg);
				}
			});
			
			$("#delete").modal('hide');
		});
	
	function _bindUpdateEvent() {
		$("button.update").on("click", function(e) {
			var data = $(this).data();
			$("#toUpdate input[name='dishName']").val(data.dName);
			$("#toUpdate select[name='dishType']").val(data.dType);
			$("#toUpdate input[name='price']").val(data.dPrice);
			$("#toUpdate input[name='image']").val(data.dImage);
			$("#toUpdate input[name='did']").val(data.id);
		});
	}

	$("#updateDish").on("click", function(e) {
		var mid = $("#toUpdate input[name='id']").val();
		var dishName = $("#toUpdate input[name='dishName']").val();
		var dishType = $("#toUpdate select[name='dishType']").val();
		var price = $("#toUpdate input[name='price']").val();
		var head = $("#toUpdate input[name='headImg']").val();
		var image = $("#toUpdate input[name='image']").val();
		var did = $("#toUpdate input[name='did']").val();
		var dish = null;
		if ("" == head) {
			dish = {
				id : did,
				mid : mid,
				dName : dishName,
				dType : dishType,
				dPrice : price,
				dImage : image
			};
		} else {
			dish = {
				id : did,
				mid : mid,
				dName : dishName,
				dType : dishType,
				dPrice : price,
				dImage : head
			};
		}
//		alert(JSON.stringify(dish));
		var fileId = [ "headImgUpdate" ];
		ajaxOP.sendFiles("updateDish", dish, fileId, function(data) {
			$("#updateDish").modal('hide');
			if (data.code == '200') {
				var cindex = -1;
				dishes.forEach(function(item, index) {
					if (item.id == data.result.id) {
						cindex = index;
					}
				});
				dishes.splice(cindex, 1, data.result);
				pareState(dishes);
				swal("update Success");
			} else if (data.code == "500") {
				swal("You Ban");
			} else if (data.code == "400") {
				swal(data.msg);
			}
		}, null);
	});
});