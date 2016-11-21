//Función que complementa a productTable.js
function productDblClick(producto){
	$('#producto').val(producto.nombre);
}

//Al dar click al botón de guardar. 
$('#btnGuardar').click(function(){
	guardarImagen("producto", 3);		//Dentro de guardarImagen.js
		
});



