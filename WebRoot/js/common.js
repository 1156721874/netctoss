function show2Dcode(){
	window.open("../2DCode.html", "二维码","width=500,height=500,top=120,left=400,location=no");
}



function RunOnBeforeUnload(path) {  
	alert("before"+path);  
    window.event.returnValue = '关闭浏览器将退出系统.';  
 }  

function RunOnUnload() {  
	//执行你的代码  
    alert("good");
}  

function checkrolename(){
    var rolename = window.document.getElementsByName("role.name");
    var flag = true;
     if(rolename[0].value==""){
    	$('input[name=role.name]').siblings("div").css("display","block");
    	flag = false;
     } 
     return flag;
    }

function checkcount(){
	var $roles = $('input[name=role.privilegesIds]:checked');
	var flag = false;
	if($roles.length>0){
		flag = true;
	}
	return flag;
}
