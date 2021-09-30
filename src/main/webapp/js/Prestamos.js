$(document).ready(function(){
	
$.ajax({
   type:"post",
   url:"Prestamos", //Servlet	
   dataType:"json",
   success: function(resultado){
	console.log(resultado);
}
})	
})