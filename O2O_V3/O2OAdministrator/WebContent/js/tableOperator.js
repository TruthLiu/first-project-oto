function TableOperator(){
	var ap = new AjaxOperator();
	this.IMG_URL="http://10.222.29.181:8082/O2OFileServer/res";
    this.createTable=function(table,data,type){
        table.empty();
        if(type=="reject"){
            $("li.active").removeClass("active");
            $("#nav3").addClass("active");
            table.append($("<caption>").text("Merchants was Rejected").addClass("tabTitle"));
            var thead=$("<thead>").append($("<tr>")
                                              .append($("<th>").text("Index"))
                                              .append($("<th>").text("Name"))
                                              .append($("<th>").text("Address"))
                                              .append($("<th>").text("Check")));
            table.append(thead);
            $("#a1").append(table);
            
            data.forEach((item,index)=>{
                var td1=$("<td>").text(++index);
                var td2=$("<td>").text(item.name);
                var td3=$("<td>").text(item.address);
                var td5=$("<td>").append($("<button>").text("Detail").addClass("btn btn-primary").addClass("detail")
                		.attr("mid",item.id)
                		.attr("data-toggle","modal")
                		.attr("data-target","#viewModal"));
                var tr=$("<tr>").append(td1).append(td2).append(td3).append(td5);
                table.append(tr);
            });
        }
        
        if(type=="passed"){
            $("li.active").removeClass("active");
            $("#nav1").addClass("active");
            table.append($("<caption>").text("Merchants Passed Approval").addClass("tabTitle"));
            var thead=$("<thead>").append($("<tr>")
                                              .append($("<th>").text("Index"))
                                              .append($("<th>").text("Name"))
                                              .append($("<th>").text("Address"))
                                              .append($("<th>").text("Operate"))
                                              .append($("<th>").text("Check")));
            table.append(thead);
            $("#a1").append(table);
            
            data.forEach((item,index)=>{
                var td1=$("<td>").text(++index);
                var td2=$("<td>").text(item.name);
                var td3=$("<td>").text(item.address);
                var td4=$("<td>");
                var it = item;
                if(item.ban){
                    td4.append($("<button>").text("Not Ban").addClass("btn white").attr("mid",item.id).attr("change","white").addClass("isBan"));
                }else{
                    td4.append($("<button>").text("Ban").addClass("btn black").attr("mid",item.id).attr("change","black").addClass("isBan"));
                }
                var td5=$("<td>").append($("<button>").text("Detail").addClass("btn btn-primary").addClass("detail")
                		.attr("mid",item.id)
                		.attr("data-toggle","modal")
                		.attr("data-target","#viewModal"));
                var tr=$("<tr>").append(td1).append(td2).append(td3).append(td4).append(td5);
                table.append(tr);
            });
        }
        
        if(type=="process"){
            $("li.active").removeClass("active");
            $("#nav2").addClass("active");
            table.append($("<caption>").text("In Process Merchants").addClass("tabTitle"));
            var thead=$("<thead>").append($("<tr>")
                                              .append($("<th>").text("Index"))
                                              .append($("<th>").text("Name"))
                                              .append($("<th>").text("Address"))
                                              .append($("<th>").text("Approval")));
            table.append(thead);
            $("#a1").append(table);
            
            data.forEach((item,index)=>{
                var td1=$("<td>").text(++index);
                var td2=$("<td>").text(item.name);
                var td3=$("<td>").text(item.address);
                var td5=$("<td>").append($("<button>").text("Approval").addClass("btn btn-primary").addClass("approval")
                		.attr("mid",item.id)
                		.attr("data-toggle","modal")
                		.attr("data-target","#processModal"));
                var tr=$("<tr>").append(td1).append(td2).append(td3).append(td5);
                table.append(tr);
            });
        }
        
        this.bindCheckDetail=function(url,fndone,fnfail){
            $("button.detail").on("click",function(){
                var mid=$(this).attr("mid");
                ap.sendGet(url+"?mid="+mid,fndone,fnfail);
            });
        };
        
        this.bindApprovalEvent=function(url,fndone,fnfail){
            $("button.approval").on("click",function(){
                var mid=$(this).attr("mid");
                ap.sendGet(url+"?mid="+mid,fndone,fnfail);
            });
        };
        
        this.bindBanEvent=function(url,fndone,fnfail){
            $("button.isBan").on("click",function(){
                var mid=$(this).attr("mid");
                var change=$(this).attr("change");
                console.log(mid+change);
                ap.sendGet(url+"?mid="+mid+"&change="+change,fndone,fnfail);
            });
        };
    }
}