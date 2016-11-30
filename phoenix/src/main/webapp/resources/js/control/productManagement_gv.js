//Variables usadas para actualizar el producto. 
var producto;
var indexTable;

//Función que complementa a productTable.js
function productDblClick(p, i){
	//Colocar el producto actual y el index de la tabla actual en las variables globales. 
	producto = p;
	indexTable = i;
	
	$('#errorMsg').html('');
	
	//Coloca en modo edición.
	$('#btnModificar').prop('disabled', false);
	$('#btnCancelar').prop('disabled', false);
	$('#checkImg').prop('checked', true).trigger('click');		//Al colocar en 'true' y disparar 'click' se obtiene uncheck. 
	$('#file_url').val('');
	
	//Colocar los datos actuales. 
	$('#txtProducto').val(producto.nombre);
	$('#txtDesc').val(producto.desc);
	$('#img_destino').attr('src', producto.urlImg);	
	$('#numPrecio').val(producto.precio);
	$('#numDescuento').val(producto.descuento);
}

//No tiene realmente una utilidad, hay que agregarlo para que funcione DataTable.
function productoAgregado(i){
	indexTable = i;
}


$('#btnModificar').click(function(){
	$('#errorMsg').html('');
	
	//Recupera los datos.
	var nombre = $('#txtProducto').val();
	var desc = $('#txtDesc').val();	
	var precio = $('#numPrecio').val();
	var descuento = $('#numDescuento').val();
	
	//Validación.
	if(nombre == '' || desc == '') $('#errorMsg').html('Ingrese todos los campos!');
	else if(precio == 0|| precio == null) $('#errorMsg').html('Ingrese un precio mayor que 0!');
	else if(descuento == null) $('#errorMsg').html('Ingrese el descuento!');
	
	else{
		
		//					######### Modificar producto gerente de ventas ##########
		
		//Si el producto será válido cuando se le ingrese el precio.
		var habilitar = producto.departamento != "n/a" && producto.categoria != "n/a";
		
		$('#errorMsg').html('Modificando...');
		$.ajax({			
			url: "modificarVenProducto.html",
			type: "POST",
			data:{
				idProducto: producto.id,
				nombre: nombre,
				desc: desc,
				precio: precio,
				descuento: descuento,
				disponible: habilitar
			},
			success: function(resp){
				var r = JSON.parse(resp);
				if(r.exito){
					//Actualizar la tabla con los datos modificados.
					producto.nombre = nombre;
					producto.desc = desc;
					producto.precio = precio;
					producto.descuento = descuento;	
					producto.disponible = habilitar;
					$('#productos').DataTable().row(indexTable).data(producto).draw();
					
					//Quita el modo edición.
					$('#btnModificar').prop('disabled', true);
					$('#btnCancelar').prop('disabled', true);
					
					//Limpia el formulario. 
					$('#txtProducto').val('');
					$('#txtDesc').val('');
					$('#img_destino').attr('src', '');	
					$('#numPrecio').val('');
					$('#numDescuento').val('');
					//$('#file_url').val('');			//Esto se hace al final del guardado de la imagen. (En guardarImagen.js)
										
					//Guardar la imagen (para la modificación, esto es opcional)
					guardarImagen("producto", producto.id, indexTable);				//En guardarImagen.js
					
					$('#errorMsg').html('Producto actualizado!');
				}
				else
					$('#errorMsg').html('Error al modificar el producto!');
			},
		});
	}
});

$('#btnCancelar').click(function(){
	//Quita el modo edición.
	$('#btnModificar').prop('disabled', true);
	$('#btnCancelar').prop('disabled', true);
	$('#checkImg').prop('checked', true).trigger('click');		//Al colocar en 'true' y disparar 'click' se obtiene uncheck. 
	
	//Limpia el formulario. 
	$('#txtProducto').val('');
	$('#txtDesc').val('');
	$('#img_destino').attr('src', '');	
	$('#numPrecio').val('');
	$('#numDescuento').val('');
	$('#file_url').val('');
});