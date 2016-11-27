$('.previa').css('cursor', 'pointer');
			
$('.previa').click(function(){
	$.redirect(
		"product.html",
		{product: $(this).prop('id')},
		"GET"
	);
});