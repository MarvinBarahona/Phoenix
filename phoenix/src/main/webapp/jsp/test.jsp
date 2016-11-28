<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	
	<label for="sitio">Ir a: </label>
	<input type="text" id="sitio">
	<br/>
	<br/>
	<input type="button" id="boton" onclick="consultaJSP()" value="Consultar JSP">

	<br/>
	<br/>
	<br/>
	<label for="selection2">Tabla:</label>
	<select id="selection2">
		<optgroup label="Paquete usuarios">
			<option value="0">Ubicacion</option>
			<option value="1">Usuario</option>
			<option value="2">Cliente</option>
			<option value="3">Empleado</option>
			<option value="4">Empresa</option>
		</optgroup>
		<optgroup label="Paquete productos">
			<option value="5">Departamento</option>
			<option value="6">Categoria</option>
			<option value="7">DetalleCategoria</option>
			<option value="8">Producto</option>
			<option value="9">DetalleProducto</option>
			<option value="10">ValorDetalleCategoria</option>
		</optgroup>
		<optgroup label="Paquete pedidos">
			<option value="11">Pedido</option>
			<option value="12">LineaPedido</option>
		</optgroup>
	</select>
	<br/>
	<br/>
	<input type="button" id="btn2" onclick="consultarTabla()" value="Traer tabla">
	<br/>
	<br/>
	<textarea rows="7" cols="150" id="output2"></textarea>
	
	<script>
		function consultaJSP(){
			$.redirect(
				"testJSP.html",
				{
					sitio: $('#sitio').val()
				}, 
				"POST",
				"_blank"
			);
		}
		
		function consultarTabla(){
			$('#output2').val("");
			$.ajax({
				type: "POST",
				url: "testAjax2.html", 
				data:{
					selection: $('#selection2').val()
				},
				
				success: function(resp){
					$('#output2').val(resp);
				}
			});
		}
	</script>	
</body>

</html>