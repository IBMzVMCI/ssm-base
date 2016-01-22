;$(function(){ 
	$.ajax({//请求职位信息
			url:"/login",
			type:"post",
			dataType:"json",
			success:function(d){
				 if(d.code==1){
				 	 
				 }else{
				 	window.localtion.url="/"
				 }
			}
	});
});