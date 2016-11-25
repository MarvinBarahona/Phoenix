//Para el control de los select. 
var departamentos;		//Objeto base de toda la categorización
var departamento;		//Departamento actualmente seleccionado en el div. 

//Para el control de los productos. 
var producto;		//Producto actual (en modificación o recien creado)
var indexTable;		//Index del row del DataTable actualmente modificandose. 

//							######## Llenar campos ##########

//Obtiene un objeto con la categorización.
//El evento 'init.dt' es un evento de DataTable, que se dispara cuando la tabla se llena con datos. 
$(document).on( 'init.dt', function ( e, settings ) {
    $.ajax({
    	url: "obtenerCategorizacionJerarquizada.html",
    	type: "POST",
    	data: {},
    	success: function(resp){
    		departamentos = JSON.parse(resp);
    		
    		$('#cmbDepartamento').html('');		//Quitar el mensaje de "Cargando..."
    		
    		//Coloca las opciones en el select del departamento. 
    		$('#cmbDepartamento').append($('<option>', { 
		        value: 0,
		        text : "Seleccione..."
		    }));    		
    		$.each(departamentos, function(index, departamento){
    			$('#cmbDepartamento').append($('<option>', { 
			        value: departamento.id,
			        text : departamento.nombre 
			    }));
    		});
    		
    		//Habilita los botones, estos no se mantienen habilitados todo el tiempo. 
    		$('#btnGuardar').prop('disabled', false);
    		$('#btnCancelar').prop('disabled', false);
    	}
    });
});


//Llena el select de categorias con el departamento seleccionado. 
$('#cmbDepartamento').change(function(){
	
	//La función jQuery.grep usada de esta forma obtiene solo un objeto de un array que compra con la condición dada.
	departamento = $.grep(departamentos, function(d){
		return d.id == $('#cmbDepartamento').val();
	})[0];
	
	
	$('#cmbCategoria').html('');		//Vacia el contenido actual del select.
	
	//Coloca las opciones en el select.
	$('#cmbCategoria').append($('<option>', { 
        value: 0,
        text : "Seleccione..."
    }));
	$.each(departamento.categorias, function(index, categoria){
		$('#cmbCategoria').append($('<option>', { 
	        value: categoria.id,
	        text : categoria.nombre 
	    }));
	});
	
	$('#divDetalles').html('');		//Limpia el div de detalles. 
});


//Llena los detalles con la información de la categoria. 
$('#cmbCategoria').change(function(){
	
	//Obtiene la categoria con el id elegido.
	var categoria = $.grep(departamento.categorias, function(c){
		return c.id == $('#cmbCategoria').val();
	})[0];
	
	$('#divDetalles').html('');		//Limpia el contenido actual del div de detalles. 
	
	//Llenar el div con los detalles de esa categoria. 
	$.each(categoria.detalles, function(index, detalle){
		//Label con el nombre del detalle. 
		$('#divDetalles').append($('<label class="lblDetalle col-sm-10">' + detalle.nombre + '</label>'));
		
		//Select con las opciones. El id de los select es el codigo del detalle,
		//el id de los options es el valor del detalle. (Con esto se recupera después
		//los pares idDetalle, valorDetalle)
		var sel = $('<select class="cmbDetalle" id='+ detalle.id +'></select>');
		$.each(detalle.valores, function(index, valor){
			$('<option>', { 
				value: valor,
		        text : valor 
		    }).appendTo(sel);
		});		
		$('#divDetalles').append(sel);
		
		$('#divDetalles').append('<br><br>');
	});
});

// 									######## Complemento de productTable.js ##########
//Función que complementa a productTable.js
function productDblClick(p, i){
	//Colocar el producto actual y el index de la tabla actual en las variables globales. 
	producto = p;
	indexTable = i;
	
	$('#errorMsg').html('');
	
	//Coloca en modo edición.
	$('#btnGuardar').html('Modificar');
	$('#btnHabilitar').prop('disabled', false);
	$('#numStock').prop('disabled', true);	
	$('#checkImg').prop('checked', true).trigger('click');		//Al colocar en 'true' y disparar 'click' se obtiene uncheck. 
	$('#file_url').val('');
	
	//Colocar los datos actuales. 
	$('#txtProducto').val(producto.nombre);
	$('#numStock').val(producto.cantidad);
	$('#img_destino').attr('src', producto.urlImg);	
	
	$('#cmbDepartamento').val(producto.idDepartamento).trigger('change');	//Dispara 'change' para llenar el select de Categoria.
	$('#cmbCategoria').val(producto.idCategoria).trigger('change');			//Dispara 'change' para llenar el div de detalles. 
	
	//Recordar que los detalles se manejan en pares (idDetalle, valorDetalle), donde el 
	//idDetalle correponde el id del select y el valorDetalle corresponde al id de los option.
	$.each(producto.detalles, function(index, detalle){
		$('#' + detalle.id).val(detalle.valor);
	});
	
	//Cambia el valor del botón de habilitación.
	if(producto.disponible){
		$('#btnHabilitar').html('Deshabilitar');
	}
	else{
		$('#btnHabilitar').html('Habilitar');
	}
}

//Función que complementa a productoTable.js
//Al completar la adición de un row, la coloca como index actual. (Sirve para agregarle la imagen)
function productoAgregado(i){
	indexTable = i;
}

// 									######## CRUD de producto #########
//Al dar click al botón de guardar.
//Con este botón se crea y se modifica. 
$('#btnGuardar').click(function(){
	$('#errorMsg').html('');
	
	//Captura de datos. 
	var nombre = $.trim($('#txtProducto').val());
	var cantidad = $('#numStock').val();
	var departamento = $('#cmbDepartamento').val();
	var categoria = $('#cmbCategoria').val();
	
	//Crea un array con los id de los detalles y sus respectivos valores. (Estos no se validan)
	var idDetalles = [];
	var valoresDetalles = [];
	$(".cmbDetalle").each(function(index, detalle){
		idDetalles.push($(detalle).attr('id'));
		valoresDetalles.push($(detalle).val());
	});

	//Validación general (para crear o modificar)
	if(nombre == '') $('#errorMsg').html('Ingrese el nombre del producto!');
	else if(departamento == '0' || departamento == null) $('#errorMsg').html('Ingrese un departamento!');
	else if(categoria == '0' || categoria == null) $('#errorMsg').html('Ingrese una categoria!');
	else if(cantidad == null) $('#errorMsg').html('Ingrese el inventario inicial!');
	else{
		
		//			########### Crear producto ############
		
		if($('#btnGuardar').html() == 'Crear'){
			
			//Validación secundaria (solo para crear)
			if($('#file_url')[0].files.length == 0) $('#errorMsg').html('Ingrese una imagen!');
			
			else{				
				$.ajax({
					url: "crearProducto.html",
					type: "POST",
					data: {
						nombre: nombre, 
						cantidad: cantidad,
						idDepartamento: departamento,
						idCategoria: categoria,
						idDetalles: idDetalles, 
						valoresDetalles: valoresDetalles
					},
					success: function(resp){
						var r = JSON.parse(resp);
						
						if(r.exito){
							//El servidor contesta mandando el nuevo producto creado. 
							var nuevoProducto = r.producto;
							
							//Agregar el nuevo producto a la tabla. 
							$('#productos').DataTable().row.add(nuevoProducto).draw();
							
							//Vacia el formulario.
							$('#txtProducto').val('');
							$('#numStock').val('0');
							$('#cmbDepartamento').val('0');		//Coloca el select de departamento a "Seleccionar..."
							$('#cmbCategoria').val('');			//Vacia el select de categoría (lo pone a value nulo
							$('#cmbCategoria').html('');		//y borra su contenido)
							$('#divDetalles').html('');
							$('#img_destino').attr('src', '');
							//$('#file_url').val('');			//Esto se hace al final del guardado de la imagen. (En guardarImagen.js)	
							
							//Guardar la imagen.
							guardarImagen("producto", nuevoProducto.id, indexTable);	//en guardarImagen.js										
							
							$('#errorMsg').html('Producto creado!');							
						}
						else{
							$('#errorMsg').html('Error al crear producto!');
						}
					}
				});
			}
		}
		
		//					######## Modificar producto (para el gerente de inventario) #########
		
		else if($('#btnGuardar').html() == 'Modificar'){
			
			$.ajax({
				url: "modificarInvProducto.html",
				type: "POST",
				data: {
					idProducto: producto.id,
					nombre: nombre, 
					idDetalles: idDetalles, 
					valoresDetalles: valoresDetalles,
					idDepartamento: departamento,
					idCategoria: categoria
				},
				success: function(resp){
					var r = JSON.parse(resp);
					if(r.exito){
						
						//Modificar la tabla con los valores modificados.
						producto.nombre = nombre;
						producto.departamento = $("#cmbDepartamento option:selected").text();
						producto.categoria = $("#cmbCategoria option:selected").text();
						
						$.each(producto.detalles, function(index, detalle){
							detalle.valor = valoresDetalles[index];
						});
						
						$('#productos').DataTable().row(indexTable).data(producto).draw();
						
						//Vacia el formulario.
						$('#txtProducto').val('');
						$('#numStock').val('0');
						$('#cmbDepartamento').val('0');		//Coloca el select de departamento a "Seleccionar..."
						$('#cmbCategoria').val('');			//Vacia el select de categoría (lo pone a value nulo
						$('#cmbCategoria').html('');		//y borra su contenido)
						$('#divDetalles').html('');
						$('#img_destino').attr('src', '');	
						//$('#file_url').val('');			//Esto se hace al final del guardado de la imagen. (En guardarImagen.js)
						
						//Quita el modo edición.
						$('#btnGuardar').html('Crear');
						$('#btnHabilitar').prop('disabled', true);
						$('#numStock').prop('disabled', false);
						$('#checkImg').prop('checked', false).trigger('click');	
						
						//Guardar la imagen (para la modificación, esto es opcional)
						guardarImagen("producto", producto.id, indexTable);				//En guardarImagen.js							
						
						$('#errorMsg').html('Producto modificado!');							
					}
					else{
						$('#errorMsg').html('Error al modificar producto!');
					}
				}
			});
		}
	}	
});

//Para habilitar un producto.
$('#btnHabilitar').click(function(){
	
	//Condiciones para habilitar una imagen. 
	if(producto.departamento == "n/a")	$('#errorMsg').html('No se puede habilitar un producto sin departamento!');
	else if(producto.categoria == "n/a") $('#errorMsg').html('No se puede habilitar un producto sin categoria!');
	else if(producto.precio == 0) $('#errorMsg').html('No se puede habilitar un producto sin precio!');
	else{
		var habilitar;
		if($('#btnHabilitar').html() == 'Habilitar') habilitar = true;
		else if($('#btnHabilitar').html() == 'Deshabilitar') habilitar = false;
		
		$.ajax({
			url: "habilitarProducto.html",
			type: "POST",
			data:{
				idProducto: producto.id,
				habilitar: habilitar
			},
			success: function(){
				if(habilitar){
					$('#errorMsg').html('Producto habilitado.');
					$('#btnHabilitar').html('Deshabilitar');
				}
				else{
					$('#errorMsg').html('Producto deshabilitado.');
					$('#btnHabilitar').html('Habilitar');
				}
				
				//Actualiza la tabla. 
				$('#productos').DataTable().cell(indexTable, 'disponible:name').data(habilitar).draw();
			}
		});
	}
});

$('#btnCancelar').click(function(){
	$('#errorMsg').html('');
	
	//Quita el modo edición.
	$('#btnGuardar').html('Crear');
	$('#btnHabilitar').prop('disabled', true);
	$('#numStock').prop('disabled', false);
	$('#checkImg').prop('checked', false).trigger('click');		//Al colocar en 'false' y disparar 'click' se obtiene check.
	
	//Vacia el formulario.
	$('#txtProducto').val('');
	$('#numStock').val('0');
	$('#cmbDepartamento').val('0');		//Coloca el select de departamento a "Seleccionar..."
	$('#cmbCategoria').val('');			//Vacia el select de categoría (lo pone a value nulo
	$('#cmbCategoria').html('');		//y borra su contenido)
	$('#divDetalles').html('');
	$('#img_destino').attr('src', '');	
	$('#file_url').val('');
});

