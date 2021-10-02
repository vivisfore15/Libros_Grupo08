$(document).ready(function(){
	
$.ajax({
   type:"post",
   url:"Prestamos", //Servlet	
   dataType:"json",
   success: function(resultado){
	console.log(resultado);
	var lista=document.getElementById("listaLibros")
	console.log(lista)
	lista.innerHTML=''
	for(let lib of resultado){
		lista.innerHTML+=`<option value="${lib.Isbn}">${lib.Titulo}</option>`
	}
}
})	
})