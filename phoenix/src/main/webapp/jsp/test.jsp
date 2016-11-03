<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página de prueba</title>
<c:set var="js" >resources/js/vistas/</c:set>
<c:set var="jsControl">resources/js/control/</c:set>
<script src="${js}jquery.min.js"></script>
<script src="${jsControl}jquery.redirect.js"></script>
</head>
<body>
	<label for="input1">ID: </label>
	<input type="text" id="input1">
	<br/>
	<br/>
	<input type="button" id="btn1" onclick="accion()" value="Botón">
	<br/>
	<br/>
	<label for="selecion">Opción: </label>
	<select id="selection">
		<option value="0">Ubicación (id)</option>
		<option value="1">Usuario (id)</option>
		<option value="2">Usuario con cast con Usuario (id)</option>
		<option value="3">Empleado desde usuario</option>
		<option value="4">Cliente desde usuario</option>
		<option value="5">Empleado</option>
		<option value="6">Cliente</option>
		<option value="7">Empresa (id)</option>
	</select>
	<br/>
	<br/>
	<br/>
	<textarea rows="7" cols="150" id="output">${output}</textarea>
		
		
	<script>
		function accion(){
			$("#output").val("");
			$.ajax({
				type: "POST",
			    url:"testAjax.html",
			    data:{
			    	selection: $('#selection').val(), 
			    	id: $('#input1').val()
			    },
			    
			    success: function(resp){
			    	//var r = JSON.parse(resp);
			    	$('#output').val(resp);
			    },
			});
		}
	</script>	
</body>

</html>