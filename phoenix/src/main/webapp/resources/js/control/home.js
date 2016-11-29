//Modifica las imagenes con la clase "previa" para permitir redirigir hacia la vista del producto. 
$('.previa').css('cursor', 'pointer');
			
$('.previa').click(function(){
	$.redirect(
		"product.html",
		{product: $(this).prop('id')},
		"GET"
	);
});