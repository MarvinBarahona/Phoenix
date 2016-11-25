//Para actualizar la tabla del producto.
var indexTable;
var idProducto;

//Variable para decidir si sumar o restar.
var sumar = null;

//Función que complementa a productTable.js
function productDblClick(producto, i){
	indexTable = i;
	idProducto = producto.id;
	
	$('#errorMsg').html('');
	
	//Coloca datos actuales.
	$('#txtProducto').val(producto.nombre);
	$('#txtExistencias').val(producto.cantidad);
	
	//Activa el modo edición.
	$('#btnModificar').prop('disabled', false);
	$('#btnCancelar').prop('disabled', false);
}

//No tiene realmente una utilidad, hay que agregarlo para que funcione DataTable.
function productoAgregado(i){
	indexTable = i;
}


//Para mantener asignado una opción. 
$('#btnSumar').click(function(){
	$('#btnSumar').addClass('active');
	$('#btnRestar').removeClass('active');
	
	sumar = true;
});
 
$('#btnRestar').click(function(){
	$('#btnRestar').addClass('active');
	$('#btnSumar').removeClass('active');
	
	sumar = false;
});


$('#btnModificar').click(function(){
	$('#errorMsg').html('');
	
	//Recuperar los datos. 
	var cant = $('#numStock').val();
	
	//Validación.
	if(cant == null || cant == 0) $('#errorMsg').html('Ingresa una cantidad!');
	else if(sumar == null) $('#errorMsg').html('Seleccione una opción!');
	
	else{
		
		//					######## Actualizar producto (stock) #########
		
		$.ajax({
			url: "modificarStockProducto.html",
			type: "POST",
			data: {
				idProducto: idProducto,
				cantidad: cant,
				sumar: sumar
			},
			success: function(resp){
				r = JSON.parse(resp);
				if(r.exito){
					//Actualizar la tabla con los datos modificados.				
					$('#productos').DataTable().cell(indexTable, 'cantidad:name').data(r.cantidad).draw();
					
					//Quita el modo edición.
					$('#btnModificar').prop('disabled', true);
					$('#btnCancelar').prop('disabled', true);
					
					//Limpia el formulario.
					$('#txtProducto').val('');
					$('#txtExistencias').val('');
					$('#numStock').val('');
					$('#btnSumar').removeClass('active');
					$('#btnRestar').removeClass('active');
					
					$('#errorMsg').html('Producto actualizado!');
				}
				else
					$('#errorMsg').html('Error al actualizar el producto!');
			}
		});
	}
});


//Al cancelar, poner en blanco el formulario.
$('#btnCancelar').click(function(){
	//Limpia el formulario.
	$('#txtProducto').val('');
	$('#txtExistencias').val('');
	$('#numStock').val('');
	$('#btnSumar').removeClass('active');
	$('#btnRestar').removeClass('active');
	
	//Quita el modo edición.
	$('#btnModificar').prop('disabled', true);
	$('#btnCancelar').prop('disabled', true);
	
	sumar = null;
});