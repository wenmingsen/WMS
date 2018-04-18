 /**获取请求地址ip*/
        function getRoot(){
        	var param=window.location.href;
            var point= param.split(":",3);
            var file=point[2].split("/");
            var root=file[1];
            return root;
        }
/**文件服务器IP地址*/
        var fileServer="http://168.168.4.106";
     
        
        
function Toast(msg,duration){
    		duration = isNaN(duration)?3000:duration;
    		var m = document.createElement('div');
    		m.innerHTML = msg;
    		m.style.cssText="width:30%;min-width:150px;background:#000;opacity:0.5;height:40px;color:#fff;line-height:40px;text-align:center;border-radius:5px;position:fixed;top:40%;left:35%;z-index:9999999;font-weight:bold;";
    		document.body.appendChild(m);
    		setTimeout(function(){
    			var d = 0.5;
    			m.style.webkitTransition = "-webkit-transform"+d +"s ease-in,opacity"+d +"s ease-in";
    			m.style.opacity = "0";
    			setTimeout(function(){
    				document.body.removeChild(m);
    			},d*1000);
    		},duration);
    	}