 /**获取浏览器请求根目录*/
        function getRoot(){
        	var param=window.location.href;
            var point= param.split(":",3);
            var file=point[2].split("/");
            var root=file[1];
            return root;
        }