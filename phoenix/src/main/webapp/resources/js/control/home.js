//Modifica las imagenes con la clase "imgProducto" para permitir redirigir hacia la vista del producto. 
$('.imgProducto').css('cursor', 'pointer');
			
$('.imgProducto').click(function(){
	$.redirect(
		"product.html",
		{product: $(this).prop('id')},
		"GET"
	);
});