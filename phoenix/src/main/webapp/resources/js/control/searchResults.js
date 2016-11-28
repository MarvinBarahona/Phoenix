var productos;

$(document).ready(function(){
	$.ajax({
		url: "obtenerProdYCate.html",
		type: "POST",
		data:{
			
		},
		success: function(resp){
			r = JSON.parse(resp);
			
			productos = r.productos;			
			generarProductos(productos);
			
			var departamentos = r.departamentos;
			
			//Crea el aside. 
			$.each(departamentos, function(i, departamento){
				var depto = 'depto' + i;
				
				var a1 = $('<a class="list-group-item strong lvl1 btnDepto" data-toggle="collapse" data-parent="#MainMenu">');
				a1.attr('href', '#' + depto).html(departamento.nombre).appendTo($('#mainMenu'));
				
				var div = $('<div class="collapse" id="' + depto + '">');
				div.appendTo($('#mainMenu'));
				
				$.each(departamento.categorias, function(i, categoria){
					var a2 = $('<a href="#" class="list-group-item strong lvl2 btnCateg" data-toggle="collapse">');
					a2.html(categoria.nombre).appendTo(div);
				});
			});
			
			$('.btnCateg').click(function(){
				var nombreCateg = $(this).html();
				
				generarProductos($.grep(productos, function(producto){
					return producto.categoria == nombreCateg;
				}));				
			});
			
			$('.btnDepto').click(function(){
				var nombreDepto = $(this).html();
				
				generarProductos($.grep(productos, function(producto){
					return producto.departamento == nombreDepto;
				}));				
			});
		}
	});	
	
});

//Modifica la paginación si se cambia el select. 
$("select").change(function(){
  /* get new nº of items per page */
  var newPerPage = parseInt( $(this).val() );

  /* destroy jPages and initiate plugin again */
  $("div.holder").jPages("destroy").jPages({
    containerID   : "itemContainer",
    perPage       : newPerPage
  });
});

//Crea las cajitas de los productos. 
function generarProductos(prods){
	$('#itemContainer').html('');
	
	if(prods.length == 0) $('#itemContainer').html('No hay productos que mostrar...');
	
	$.each(prods, function(i, producto){
		var div = $('<div class="col-lg-3 col-md-3 col-xs-6 thumb">');
		div.appendTo($('#itemContainer'));
		
		var a = $('<a class="thumbnail imgProducto" id=' + producto.id + '>');
		a.appendTo(div);
		
		var img = $('<img class="img-responsive previaC" alt="imagen del producto">');
		img.attr('src', producto.urlImg).appendTo(a);
		
		var div2 = $('<div class="breve">');
		div2.appendTo(a);
		
		var conDesc = producto.descuento != 0;
		
		if(conDesc)	$('<span class="desc">').html("-" + producto.descuento + "%").appendTo(div2);
		$('<p class="nomProducto">').html(producto.nombre).appendTo(div2);
		if(!conDesc) $('<span class="precioActual">').html('$' + producto.precio).appendTo(div2);
		else{
			$('<span class="precioActual">').html('$' + producto.precio * (1 - producto.descuento/100)).appendTo(div2);
			$('<del class="precioReal">').html('$' + producto.precio).appendTo(div2);
		}
	});
	
	$('.imgProducto').css('cursor', 'pointer');
	
	$('.imgProducto').click(function(){
		$.redirect(
			"product.html",
			{product: $(this).prop('id')},
			"GET"
		);
	});
	
	/* Inicio de plugin jPages*/
    $("div.holder").jPages({
      containerID : "itemContainer",
      perPage : 10
    });
}

