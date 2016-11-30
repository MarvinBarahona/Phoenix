//Modifica las imagenes con la clase "imgProducto" para permitir redirigir hacia la vista del producto. 
$('.imgProducto').css('cursor', 'pointer');
			
$('.imgProducto').click(function(){
	var id = $(this).prop('id');
	if(id != null && id != ''){
		$.redirect(
			"product.html",
			{product: id},
			"GET"
		);
	}	
});