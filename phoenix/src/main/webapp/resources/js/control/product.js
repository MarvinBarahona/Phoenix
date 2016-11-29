var producto;		//Variable usada para almacenar el producto visualizado. 

$(document).ready(function(){
	if($('#producto').html() != null && $('#producto').html() != ''){
		$.ajax({
			url: "obtenerProducto.html",
			type: "POST",
			data:{
				idProducto: $('#producto').html()
			},
			success: function(resp){
				producto = JSON.parse(resp);
				
				//Agregar la imagen
				$('#txtNombre').html(producto.nombre);
				
				//Agregar la imagen
				$('#imgProducto').attr('src', producto.urlImg);
				
				//Agregar el precio (con y sin descuento si aplica)
				var precio = producto.precio;
				if(producto.descuento != 0){
					var descuento = producto.descuento;					
					$('#txtPrecio').html('$' + precio).css('margin-right', '1em');
					precio = precio * (1 - descuento/100);
				}				
				$('#txtPrecioD').html('$' + precio);
				
				//Agregar los detalles. 
				$.each(producto.detalles, function(i, detalle){
					var p = $('<p>');
					p.html(detalle.nombre + ":   " + detalle.valor);
					$('#divDetalles').append(p);
				});
				
				if(producto.detalles.length == 0) $('#divDetalles').html('No hay detalles que mostrar.');
				
				//Agregar la descripción.
				$('#txtDesc').html(producto.desc);
			}
		});
	}	
});

//Si se cancela, retorna al catálogo. 
$('#btnCancelar').click(function(){
	$.redirect(
		"/searchResults.html",
		"POST"
	);
});

//Si se agrega, modificar el carrito y luego retornar al catálogo. 
$('#btnAgregar').click(function(){
	var idProducto = $('#producto').html();
	
	$.ajax({
		url: "agregarProductoCarrito.html", 
		type: "POST", 
		data: {
			idProducto : idProducto
		},
		success: function(resp){
			$.redirect(
				"/searchResults.html",
				"POST"
			);
		}
	});
});