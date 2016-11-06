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
	
	<label for="sitio">Ir a: </label>
	<input type="text" id="sitio">
	<br/>
	<br/>
	<input type="button" id="boton" onclick="consultaJSP()" value="Consultar JSP">
	
	<br/>
	<br/>
	<br/>
	
	<label for="selecion">Opción: </label>
	<select id="selection">
		<optgroup label="Paquete usuarios">
			<option value="0">Ubicación (id)</option>
			<option value="1">Usuario (id)</option>
			<option value="2">Usuario(correo)</option>
			<option value="3">Cliente(id)</option>
			<option value="4">Cliente(correo)</option>
			<option value="5">Empleado(id)</option>
			<option value="6">Empleado(correo)</option>
			<option value="7">Usuario del cliente(id)</option>
			<option value="8">Usuario del empleado(id)</option>
			<option value="9">Empresa(id)</option>
			<option value="10">Empresa del empleado(id)</option>
			<option value="11">Gerente General de empresa(id)</option>
			<option value="12">Gerente Ventas de empresa(id)</option>
			<option value="13">Gerente Inventario de empresa(id)</option>
			<option value="14">Ubicacion de empresa(id)</option>
		</optgroup>
		<optgroup label="Paquete productos">
			<option value="15">Departamento(id)</option>
			<option value="16">Categoria(id)</option>
			<option value="17">Detalle(id)</option>
			<option value="18">Categorias de departamento(id)</option>
			<option value="19">Detalles de categoria(id)</option>
			<option value="20">Producto(id)</option>
			<option value="21">Productos de empresa(id)</option>
			<option value="22">Producto disponibles de empresa(id)</option>
			<option value="23">Productos de empresa(id) en dep 1 cat 2</option>
			<option value="24">Productos de dep 1 en empresa(id)</option>
			<option value="25">Detalle(id)</option>
			<option value="26">Detalles de producto(id)</option>
		</optgroup>
	</select>
	<br/>
	<br/>
	<label for="input1">ID: </label>
	<input type="text" id="input1">
	<br/>
	<br/>
	<input type="button" id="btn1" onclick="consulta()" value="Consultar">
	
	<br/>
	<br/>
	<br/>
	<textarea rows="7" cols="150" id="output"></textarea>
	
	
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
			<option value="7">Detalle</option>
			<option value="8">Producto</option>
			<option value="9">DetalleProducto</option>
		</optgroup>
		
	</select>
	<br/>
	<br/>
	<input type="button" id="btn2" onclick="consultarTabla()" value="Traer tabla">
	<br/>
	<br/>
	<textarea rows="7" cols="150" id="output2"></textarea>
	
	<!-- <br/>
	<br/>
	<br/>
	<label for="selection3">Accion:</label>
	<select id="selection3">
		<option value="0">Actualizar detalle</option>
		<option value="1">Detalle producto 2</option>
	</select>
	<br/>
	<br/>
	<input type="button" id="btn3" onclick="crear()" value="Crear">
	<br/>
	<br/>
	<textarea rows="7" cols="150" id="output3"></textarea> -->
	
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
	
		function consulta(){
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
		
		function crear(){
			$('#output3').val("");
			$.ajax({
				type: "POST",
				url: "testAjax3.html", 
				data:{
					selection: $('#selection3').val()
				},
				
				success: function(resp){
					$('#output3').val(resp);
				}
			});
		}
	</script>	
</body>

</html>