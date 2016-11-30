//Cargar la información del carrito. 
$(document).ready(function(){
	$.ajax({
		url: "obtenerCarrito.html", 
		type: "POST",
		data:{
			
		},
		success: function(resp){			
			var carrito = JSON.parse(resp);
			if(carrito.isEmpty){
				var h = $('<h3 class="text-center">');
				h.html('Carro de compras vacío').appendTo($('#contenido'));
			}
			else{
				//Crear una tabla con las lineas de pedido. 
				var table = $('<table class="table table-hover">');
				table.appendTo($('#contenido'));
				
				// ** Encabezado **
				var thead = $('<thead>');
				thead.appendTo(table);
				
				var trhead = $('<tr>');
				trhead.appendTo(thead);
				
				trhead.append($('<th>').html('Producto'));
				trhead.append($('<th>').html('Precio unitario'));
				trhead.append($('<th>').html('Cantidad'));
				trhead.append($('<th>').html('Subtotal'));
				
				// ** Datos **
				var tbody = $('<tbody>');
				tbody.appendTo(table);
				
				$.each(carrito.lineas, function(i, linea){					
					var tr = $('<tr id=' + linea.idProducto + '>');
					tr.appendTo(tbody);
					
					tr.append($('<td>').html(linea.producto));
					tr.append($('<td>').html('$' + linea.precio));
					
					var input = $('<input class="existencias">');	//Crer un input:number para ingresar la cantidad deseada. 
					input.attr('type', 'number');
					input.attr('value', linea.cantidad);
					input.attr('min', 1);
					input.attr('max', linea.existencias);					
					var btn = $('<button type="button" name="button" class="eliminar">');	//Crear un botón para eliminar
					btn.append($('<span class="fa fa-trash" data-toggle="tooltip" title="Eliminar">'));					
					tr.append($('<td>').append(input, btn));	//Agregar el numbre y el botón a la tabla. 
					
					tr.append($('<td>').html('$' + linea.subtotal));					
				});
				
				//Funciones para eliminar y modificar las lineas. 
				
				$('.existencias').focusout(function(){
					var cant = $(this).val();
					var row = $(this).closest('tr');
					idProducto = row.prop('id');
					modificarLinea(idProducto, cant);
				});
				
				$('.eliminar').click(function(){
					var row = $(this).closest('tr');
					idProducto = row.prop('id');
					eliminarLinea(idProducto);
				});
			}
		}
	});
});

//Modificar una línea. 
function modificarLinea(idProducto, cant){
	$.ajax({
		url: "modificarLineaCarrito.html",
		type: "POST",
		data:{
			idProducto: idProducto,
			cantidad: cant
		},
		success: function(resp){
			r = JSON.parse(resp);
			$('#headerTotal, #tdTotalPedido').html('$' + r.total);
			
			var costoEnvio = $('#totalEnvio').html();	//Si hay costo de envio, el cliente inició sesión y se calcula el nuevo total.
			if(costoEnvio != '' && costoEnvio != null){
				costoEnvio = parseFloat(costoEnvio);
				$('#txtTotal').html(parseFloat(r.total + costoEnvio).toFixed(2));
			}
			else{
				$('#txtTotal').html(r.total);
			}
			
			
			//Modificar el subtotal. 
			var row = $('#' + idProducto);
			$('td:last-child', row).remove();
			row.append($('<td>').html('$' + r.subtotal));
		}
	});
}

//Eliminar una línea.
function eliminarLinea(idProducto){
	$.ajax({
		url: "eliminarLineaCarrito.html",
		type: "POST",
		data:{
			idProducto: idProducto
		},
		success: function(resp){
			r = JSON.parse(resp);
			$('#headerTotal, #tdTotalPedido').html('$' + r.total);
			$('#headerCant').html("Artículos: " + r.cantidad);
			
			var costoEnvio = $('#totalEnvio').html();	//Si hay costo de envio, el cliente inició sesión y se calcula el nuevo total.
			if(costoEnvio != '' && costoEnvio != null){
				costoEnvio = parseFloat(costoEnvio);
				$('#txtTotal').html(parseFloat(r.total + costoEnvio).toFixed(2));
			}
			else{
				$('#txtTotal').html(r.total);
			}
			
			//Remover el tr de la tabla.
			var row = $('#' + idProducto);
			var tbody = row.parent();
			$(row, tbody).remove();
		}
	});
}

//Limpiar el carrito. 
$('#btnLimpiar').click(function(){
	$.ajax({
		url: "limpiarCarrito.html",
		type: "POST",
		data: {},
		success: function(){
			location.reload();		//Recargar la página luego de quitar el carrito de la sesión. 
		}
	});
});


//Para pruebas, redirige de una vez al sitio dónde se guarda el pedido. 
$('#btnComprar').click(function(){
	$.redirect(
		"/purchaseCompleted.html",
		{},
		"POST"
	);
});

//Para realizar las comprar a través de paypal.
$('#btnPaypal').click(function(){
	var total = $('#txtTotal').html();
	$('#totalPagar').val(total);
	$('#formPago').submit();
});