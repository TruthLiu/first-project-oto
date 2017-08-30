function Listener(){
	var ap = new AjaxOperator();
    var curWork;
	this.listen=function(url,fndone,fnfail){
		curWork = setInterval(this.work(url, fndone, fnfail),2000);
	}
    
    this.work=function(url,fndone,fnfail){
        ap.sendGet(url,fndone,fnfail);
    }
    
    this.stop=function(){
        clearInterval(curWork);
    }
}