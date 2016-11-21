//Función que complementa a productTable.js
function productDblClick(producto){
	$('#txtProducto').val(producto.nombre);
	$('#txtExistencias').val(producto.cantidad);
	
	$('#btnGuardar').prop('disabled', false);
	$('#btnCancelar').prop('disabled', false);
}

//Para mantener asignado una opción. 
$('#btnSumar').click(function(){
	$('#btnSumar').addClass('active');
	$('#btnRestar').removeClass('active');
});
 
$('#btnRestar').click(function(){
	$('#btnRestar').addClass('active');
	$('#btnSumar').removeClass('active');
});


//Al cancelar, poner en blanco el formulario.
$('#btnCancelar').click(function(){
	$('#txtProducto').val('');
	$('#txtExistencias').val('');
	
	$('#btnGuardar').prop('disabled', true);
	$('#btnCancelar').prop('disabled', true);
	
	$('#btnSumar').removeClass('active');
	$('#btnRestar').removeClass('active');
});