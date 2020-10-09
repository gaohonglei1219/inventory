 /* 增删改页面跳转 */
var basePath = "http://localhost:8080/inventory/"
	  function adu_fun(path,method,id){
		  if (method == 'del'){
			  window.location.href = basePath+path+"/delById?id="+id
		  }else{
			  window.location.href = basePath+path+"/goInfo?method="+method+"&id="+id
		  }
		  
	  }