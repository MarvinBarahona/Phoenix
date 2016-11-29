var productos;		//Variable usada para almacenar los productos. 

$(document).ready(function(){
	$.ajax({
		url: "obtenerProdYCate.html",
		type: "POST",
		data:{
			
		},
		success: function(resp){
			r = JSON.parse(resp);
			
			productos = r.productos;			
			generarProductos(productos);		//Generar todos los productos.
			
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
			
			//Al darle click a una categoria.
			$('.btnCateg').click(function(){
				var nombreCateg = $(this).html();
				
				//Generar las imagenes con solo los productos que coincidan en esa categoria. 
				generarProductos($.grep(productos, function(producto){
					return producto.categoria == nombreCateg;
				}));				
			});
			
			//Al darle click a un departamento. 
			$('.btnDepto').click(function(){
				var nombreDepto = $(this).html();
				
				//Generar las imagenes con solo los productos de ese departamento. 
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
	
	//Si el array mandado es vacio. 
	if(prods.length == 0) $('#itemContainer').html('No hay productos que mostrar...');
	
	//Por cada producto dentro del array genera su imagen. 
	$.each(prods, function(i, producto){
		var div = $('<div class="col-lg-3 col-md-3 col-xs-6 thumb">');
		div.appendTo($('#itemContainer'));
		
		var a = $('<a class="thumbnail imgProducto" id=' + producto.id + '>');
		a.appendTo(div);
		
		var img = $('<img class="img-responsive previaC" alt="imagen del producto">');
		img.attr('src', producto.urlImg).appendTo(a);
		
		var div2 = $('<div class="breve">');
		div2.appendTo(a);
		
		var conDesc = producto.descuento != 0;	//¿El producto tiene descuento?
		
		//Agregar el descuento si existe. 
		if(conDesc)	$('<span class="desc">').html("-" + producto.descuento + "%").appendTo(div2);
		
		$('<p class="nomProducto">').html(producto.nombre).appendTo(div2);	//Agregar el nombre. 
		
		//Si no tiene descuento, agregar el precio. Si tiene descuento, calcular el precio a vender  y el precio real. 
		if(!conDesc) $('<span class="precioActual">').html('$' + producto.precio).appendTo(div2);
		else{
			$('<span class="precioActual">').html('$' + producto.precio * (1 - producto.descuento/100)).appendTo(div2);
			$('<del class="precioReal">').html('$' + producto.precio).appendTo(div2);
		}
	});
	
	//Permite a las imagenes redirigir a la vista individual de los productos. 
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

