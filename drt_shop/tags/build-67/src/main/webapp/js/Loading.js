
function StateChangeFun(){
	if(document.readyState=="complete"){
		document.forms[0].onsubmite=FormSubmitFun;
		document.body.onresize=ReSetSizeFun;
		loadingDiv.style.display="none";
	}else{
		ReSetSizeFun();
		loadingDiv.style.display="";
	}
}

function FormSubmitFun(){
	ReSetSizeFun();
	loadingDiv.style.display="";
}

function ReSetSizeFun(){
	loadingDiv.style.top=document.documentElement.scrollTop+"px";
	loadingDiv.style.left=document.documentElement.scrollLeft+"px";
}