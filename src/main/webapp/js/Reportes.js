$(document).ready(function(){

function listaLibros(){

$.ajax({
	type:"post",
	url:"Reportes",
	dataType:"json",
	data:{opcion:"Libros"},
	success: function(result){
		console.log(result)
		var tabla=document.querySelector(".tabla")
		tabla.innerHTML=''  
		// Hacemos encabezado de la tabla
		tabla.innerHTML+=`<tr> 
		<th>ISBN</th>
		<th>TITULO</th>
		<th>EDITORIAL</th>
		<th>AUTOR</th>
		<th>PAGINAS</th>
		</tr>`
		for(let fila of result){
			tabla.innerHTML+=`<tr>
			<td>${fila.Isbn}</td>
			<td>${fila.Titulo}</td>
			<td>${fila.Editorial}</td>
			<td>${fila.Autor}</td>
			<td>${fila.No_paginas}</td>
			</tr>`
		}
	}
})	
}

function listaPrestamos(){

$.ajax({
	type:"post",
	url:"Reportes",
	dataType:"json",
	data:{opcion:"Prestamos"},
	success: function(result){
		console.log(result)
		var tabla=document.querySelector(".tabla")
		tabla.innerHTML=''  
		// Hacemos encabezado de la tabla
		tabla.innerHTML+=`<tr class="bordes"> 
		<th>ID</th>
		<th>ESTUDIANTE</th>
		<th>LIBRO</th>
		<th>FECHA</th>
		<th>CANT</th>
		<th>TOTAL</th>
		</tr>`
		for(let fila of result){
			tabla.innerHTML+=`<tr>
			<td>${fila.id_Prestamo}</td>
			<td>${fila.estudiante}</td>
			<td>${fila.libro}</td>
			<td>${fila.fecha_Prestamo}</td>
			<td>${fila.cant}</td>
			<td>${fila.total}</td>
			</tr>`
		}
	}
})	
}

$('.libros').on('click',function(){
	listaLibros()
})	

$('.prestamos').on('click',function(){
	listaPrestamos()
})
	
})