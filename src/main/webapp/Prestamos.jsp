<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/Prestamos.js"></script>
<title>Insert title here</title>
</head>
<body>
<%!String titulo,isbn; %>
<%
if(request.getParameter("titulo")!=null){
	titulo=request.getParameter("titulo");
	isbn=request.getParameter("isbn");
}

if(request.getParameter("men")!=null){
String mensaje=request.getParameter("men");
out.print("<script type='text/javascript'>alert('"+mensaje+"');</script>");	
}
%>

<form action="Prestamos" method="post">
<div><label>Libro: </label>
<select id="listaLibros" name="libro">
</select>
<input type="submit" name="consultarLib" value="Consultar">
<label><%=titulo%></label>
<input type="text" name="isbn" value="<%=isbn%>"></div>
<div><label>Estudiante: </label><input type="text" name="est"></div>
<div><label>Fecha: </label><input type="date" name="fecha"></div>
<div><label>Cantidad: </label><input type="number" name="cant"></div>
<div><input type="submit" name="registrar" value="Registrar"></div>
</form>

</body>
</html>