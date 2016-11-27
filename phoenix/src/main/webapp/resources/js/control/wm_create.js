//Variables utilizadas en la modificación. 
//Se almacena el index de un row de la tabla, el row que se está modificando. 
var indexDeptoMod; 
var indexCategMod;
var indexDetalleMod;

//								Llenar la tablas. 
$(document).ready(function(){	
	
	//Pone un mensaje de "cargando" en cada tabla. 
	$('#tblDepto').html('Cargando datos de departamentos...');
	$('#tblCateg').html('Cargando datos de categorías...');
	$('#tblDetalle').html('Cargando datos de detalles...');
	
	$.ajax({
		//Recuperar un json con la información necesaria para llenar todo. (Todo es todo) 
		url: "obtenerCategorizacion.html",
		type: "POST", 
		data: {			
		}, 
		success: function(resp){
			//Convierte la respuesta en un objeto. 
			var categorizacion = JSON.parse(resp);
			
			//					## Tabla de departamentos ##
			$('#tblDepto').html('');
			$('#tblDepto').DataTable({
				data: categorizacion.departamentos, 
				columns:
					[{
						name: "nombre",
						title: "Departamento", 
						data: "nombre"
					},
					{
						name: "desc",
						title: "Descripción",
						data: "desc"
					}]
			});
			
			//Llena los select con los departamentos.
			$('#cmbCategDepto, #cmbDetalleDepto').append($('<option>', { 
		        value: 0,
		        text : 'Seleccionar...' 
		    }));
			$.each(categorizacion.departamentos, function(i, departamento){
				$('#cmbCategDepto, #cmbDetalleDepto').append($('<option>', { 
			        value: departamento.id,
			        text : departamento.nombre 
			    }));
			});
			
			//Función para llenar el formulario con los datos de un departamento. 
			$('#tblDepto tbody').on( 'dblclick', 'tr', function () {
				var depto = $('#tblDepto').DataTable().row( this ).data();
				//Llenar formulario. 
				$('#txtDeptoNombre').val(depto.nombre);
				$('#txtDeptoDesc').val(depto.desc);
				
				//Coloca en modo edición. 
				$('#btnDeptoGuardar').prop('value', 'Modificar');
				$('#btnDeptoEliminar').prop('disabled', false);
				$('#txtDeptoNombre').prop('disabled', true);
				
				//Asigna el index del departamento en edición.
				indexDeptoMod = $('#tblDepto').DataTable().row(this).index();
			} );
			
			//Coloca el cursor como default para toda la tabla. 
			$('#tblDepto tbody').css( 'cursor', 'default' );
			
			
			//Tabla de categorias.*****************************************************************************
			$('#tblCateg').html('');
			$('#tblCateg').DataTable({
				data: categorizacion.categorias, 
				columns:
					[{
						name: "nombre", 
						title: "Categoria",
						data: "nombre"
					},
					{
						name: "departamento",
						title: "Departamento", 
						data: "departamento"
					},
					{
						name: "desc",
						title: "Descripción",
						data: "desc"
					}]
			});
			
			//Llena el select de categoria dependiendo del departamento.
			$('#cmbCategDepto, #cmbDetalleDepto').on('change', (function(){
				$('#cmbDetalleCateg').html('');
				
				var idDepto = $(this).val();
				var categorias = $('#tblCateg').DataTable().data();
				
				$('#cmbDetalleCateg').append($('<option>', { 
			        value: 0,
			        text : 'Seleccionar...'
			    }));

				//Pone un <option> por cada categoria con el id del departamento seleccionado. 
				var categorias_depto = $.grep(categorias, function(categoria){
					return categoria.idDepartamento == idDepto;
				}); 			
				
				$.each(categorias_depto, function(i, categoria){
					$('#cmbDetalleCateg').append($('<option>', { 
				        value: categoria.id,
				        text : categoria.nombre 
				    }));
				});
			}));
			
			
			//Función al dar doble click a una fila de la tabla de categorias.  
			$('#tblCateg tbody').on( 'dblclick', 'tr', function () {
			    var categ = $('#tblCateg').DataTable().row( this ).data();
		 
				$('#txtCategNombre').val(categ.nombre);
				$('#txtCategDesc').val(categ.desc);
				$('#cmbCategDepto').val(categ.idDepartamento);
				
				//Coloca en modo edición. 
				$('#btnCategGuardar').prop('value', 'Modificar');
				$('#btnCategEliminar').prop('disabled', false);
				$('#txtCategNombre').prop('disabled', true);
				$('#cmbCategDepto').prop('disabled', true);
				
				//Asigna el index de la categoría en edición.
				indexCategMod = $('#tblCateg').DataTable().row(this).index();
			} );
			
			//Cambiar el cursor sobre la tabla a el cursor por defecto. 
			$('#tblCateg tbody').css( 'cursor', 'default' );
			
			
			//Tabla de detalles**************************************************************************
			$('#tblDetalle').html('');
			$('#tblDetalle').DataTable({
				data: categorizacion.detalles, 
				columns:
					[{
						name: "nombre",
						title: "Detalle",
						data: "nombre"
					},
					{
						name: "departamento",
						title: "Departamento", 
						data: "departamento"
					},
					{
						name: "categoria",
						title: "Categoria", 
						data: "categoria"
					},
					{
						name: "desc",
						title: "Descripción",
						data: "desc"
					}]
			});
			
			//Al darle doble click a una fila de la tabla de detalles. 
			$('#tblDetalle tbody').on( 'dblclick', 'tr', function () {
				var detalle = $('#tblDetalle').DataTable().row( this ).data();

				$('#txtDetalleNombre').val(detalle.nombre);
				$('#txtDetalleDesc').val(detalle.desc);
				$('#cmbDetalleDepto').val(detalle.idDepartamento).trigger('change');
				$('#cmbDetalleCateg').val(detalle.idCategoria);
				
				$('#valoresDetalle').val('');
				var detallesStr = '';
				$.each(detalle.valores, function(i, valor){
					detallesStr += valor + '\n';
				});
				$('#valoresDetalle').val(detallesStr);
				
				//Coloca en modo edición. 
				$('#btnDetalleGuardar').prop('value', 'Modificar');
				$('#btnDetalleEliminar').prop('disabled', false);
				$('#cmbDetalleDepto, #cmbDetalleCateg').prop('disabled', true);
				$('#txtDetalleNombre').prop('disabled', true);
				
				//Asigna el index del detalle en edición.
				indexDetalleMod = $('#tblDetalle').DataTable().row(this).index();
			} );
			
			//Cambiar el cursor sobre la tabla a el cursor por defecto. 
			$('#tblDetalle tbody').css( 'cursor', 'default' );
		}
	});
});

//Cancelar las operaciones. ************************************************************************************

//		###### Departamento ########
$('#btnDeptoCancelar').click(function(){
	//Quita el modo de edición.
	$('#btnDeptoGuardar').prop('value', 'Crear');
	$('#btnDeptoEliminar').prop('disabled', true);
	$('#txtDeptoNombre').prop('disabled', false);
	
	//Pone vacio el formulario
	$('#txtDeptoNombre').val('');
	$('#txtDeptoDesc').val('');
	
	$('#deptoErrorMsg').html('');
});

//		####### Categoria ########
$('#btnCategCancelar').click(function(){
	//Quita el modo edición.
	$('#btnCategGuardar').prop('value', 'Crear');
	$('#btnCategEliminar').prop('disabled', true);
	$('#txtCategNombre').prop('disabled', false);
	$('#cmbCategDepto').prop('disabled', false);
	
	//Pone vacio el formulario
	$('#txtCategNombre').val('');
	$('#txtCategDesc').val('');
	$('#cmbCategDepto').val('0');
	
	$('#categErrorMsg').html('');
});

//		######## Detalle ##########
$('#btnDetalleCancelar').click(function(){
	//Quita el modo edición. 
	$('#btnDetalleGuardar').prop('value', 'Crear');
	$('#btnDetalleEliminar').prop('disabled', true);
	$('#cmbDetalleDepto, #cmbDetalleCateg').prop('disabled', false);
	$('#txtDetalleNombre').prop('disabled', false);	
	
	//Pone vacio el formulario
	$('#txtDetalleNombre').val('');
	$('#txtDetalleDesc').val('');
	$('#cmbDetalleDepto').val('0');
	$('#cmbDetalleCateg').html('');	
	$('#cmbDetalleCateg').val('');
	$('#valoresDetalle').val('');
	
	$('#detalleErrorMsg').html('');
});


//CRUD del departamento. **************************************************************************************************
$('#btnDeptoGuardar').click(function(){
	$('#deptoErrorMsg').html("");
	
	//Validación. 
	var nombre = $('#txtDeptoNombre').val();
	nombre = $.trim(nombre);
	
	var desc = $('#txtDeptoDesc').val();
	desc = $.trim(desc);
	
	if(nombre == "" || desc == ""){
		$('#deptoErrorMsg').html("Ingrese todos los campos!");
	}	
	
	else{
		
		//						#######  Crear departamento #######
		
		if($('#btnDeptoGuardar').val() == 'Crear'){
			$.ajax({
				url: "crearDepartamento.html",
				type: "POST", 
				data:{
					nombre: nombre,
					desc: desc
				},
				success: function(resp){
					var r = JSON.parse(resp);
					if(r.exito){
						var nuevoDepto = r.departamento;
						
						$('#deptoErrorMsg').html("Departamento creado!");
						
						//Actualiza la tabla. 
						$('#tblDepto').DataTable().row.add(nuevoDepto).draw();
						
						//Agrega el nuevo departamento a las opciones de categoria y detalle. 
						$('#cmbCategDepto, #cmbDetalleDepto').append($('<option>', { 
					        value: nuevoDepto.id,
					        text : nuevoDepto.nombre 
					    }));
						
						//Pone vacio el formulario
						$('#txtDeptoNombre').val('');
						$('#txtDeptoDesc').val('');
					}
					else{
						$('#deptoErrorMsg').html("Error al crear departamento!");
					}
				}				
			});
		}
		
		//						####### Modificar departamento #######
		
		else if($('#btnDeptoGuardar').val() == 'Modificar'){
			var id = $('#tblDepto').DataTable().row(indexDeptoMod).data().id;
			
			$.ajax({
				url: "modificarDepartamento.html",
				type: "POST", 
				data:{
					id: id,
					desc: desc					
				},
				success: function(resp){
					var r = JSON.parse(resp);
					if(r.exito){
						$('#deptoErrorMsg').html("Departamento modificado!");
						
						//Actualiza la tabla. 
						$('#tblDepto').DataTable().cell(indexDeptoMod, 'desc:name').data(desc).draw();
												
						//Pone vacio el formulario
						$('#txtDeptoNombre').val('');
						$('#txtDeptoDesc').val('');
						
						//Quita el modo de edición.
						$('#btnDeptoGuardar').prop('value', 'Crear');
						$('#btnDeptoEliminar').prop('disabled', true);
						$('#txtDeptoNombre').prop('disabled', false);
					}
					else{
						$('#deptoErrorMsg').html("Error al modificar departamento!");
					}
				}				
			});
		}
	}
});

//						######## Eliminar departamento #########

$('#btnDeptoEliminar').click(function(){
	$('#deptoErrorMsg').html("");
	
	var depto = $('#tblDepto').DataTable().row(indexDeptoMod).data();
	var id =  depto.id;
	
	$.ajax({
		url: "eliminarDepartamento.html",
		type: "POST", 
		data:{
			id: id
		},
		success: function(resp){
			r = JSON.parse(resp);
			if(r.exito){
				$('#deptoErrorMsg').html("Departamento eliminado!");
				
				//Quitar el detalle de la tabla. 
				$('#tblDepto').DataTable().row(indexDeptoMod).remove().draw();
				
				//Eliminar de la tabla de categorias las categorias eliminadas. 
				$('#tblCateg').DataTable().rows(function(index, categoria){
					return categoria.idDepartamento == id;
				}).remove().draw();
				
				//Eliminar de la tabla de detalles los detalles eliminados.
				$('#tblDetalle').DataTable().rows(function(index, detalle){
					return detalle.idDepartamento == id;
				}).remove().draw();
				
				//Quita el modo de edición.
				$('#btnDeptoGuardar').prop('value', 'Crear');
				$('#btnDeptoEliminar').prop('disabled', true);
				$('#txtDeptoNombre').prop('disabled', false);
				
				//Pone vacio el formulario
				$('#txtDeptoNombre').val('');
				$('#txtDeptoDesc').val('');
				
				//Vacia el select de categoria de detalle, para evitar inconsistencias.
				$('#cmbDetalleCateg').html('');
				$('#cmbDetalleCateg').val('');
				
				//Elimina los option de los select de departamento.
				$('#cmbDetalleDepto, #cmbCategDepto').val('0');
				$('#cmbCategDepto option[value = "'+ id +'"]').remove();	
				$('#cmbDetalleDepto option[value = "'+ id +'"]').remove();
			}
			else{
				$('#deptoErrorMsg').html("Error al eliminar categoría!");
			}
		}
	});
});


//CRUD de categoria *****************************************************************************************************
$('#btnCategGuardar').click(function(){
	$('#categErrorMsg').html("");
	
	//Validación. 
	var nombre = $('#txtCategNombre').val();
	nombre = $.trim(nombre);
	
	var desc = $('#txtCategDesc').val();
	desc = $.trim(desc);
	
	var depto = $('#cmbCategDepto').val();
	
	if(nombre == "" || desc == "" || depto == '0'){
		$('#categErrorMsg').html("Ingrese todos los campos!");
	}	
	
	else{
		
		//						#######  Crear categoría #######
		
		if($('#btnCategGuardar').val() == 'Crear'){
			$.ajax({
				url: "crearCategoria.html",
				type: "POST", 
				data:{
					nombre: nombre,
					desc: desc,
					depto: depto
				},
				success: function(resp){
					var r = JSON.parse(resp);
					if(r.exito){
						var nuevaCateg = r.categoria;
						
						$('#categErrorMsg').html("Categoría creada!");
						
						//Pone el nuevo dato en la tabla.
						$('#tblCateg').DataTable().row.add(nuevaCateg).draw();
						
						//Pone vacio el formulario
						$('#txtCategNombre').val('');
						$('#txtCategDesc').val('');
						$('#cmbCategDepto').val('0');
						
						//Para recargar el nuevo detalle en el departamento. 
						$('#cmbDetalleDepto').val('0');
						$('#cmbDetalleCateg').html('');
						$('#cmbDetalleCateg').val('');
					}
					else{
						$('#categErrorMsg').html("Error al crear categoría!");
					}
				}				
			});
		}
		
		//					####### Modificar categoría #######
		
		else if($('#btnCategGuardar').val() == 'Modificar'){
			var id = $('#tblCateg').DataTable().row(indexCategMod).data().id;
			
			$.ajax({
				url: "modificarCategoria.html",
				type: "POST", 
				data:{
					id: id,
					desc: desc					
				},
				success: function(resp){
					var r = JSON.parse(resp);
					if(r.exito){
						$('#categErrorMsg').html("Categoría modificada!");
						
						//Modifica la tabla. 
						$('#tblCateg').DataTable().cell(indexCategMod, 'desc:name').data(desc).draw();
												
						//Pone vacio el formulario
						$('#txtCategNombre').val('');
						$('#txtCategDesc').val('');
						$('#cmbCategDepto').val('0');
						
						//Quita el modo de edición. 
						$('#btnCategGuardar').prop('value', 'Crear');
						$('#btnCategEliminar').prop('disabled', true);
						$('#txtCategNombre').prop('disabled', false);
						$('#cmbCategDepto').prop('disabled', false);
					}
					else{
						$('#categErrorMsg').html("Error al modificar categoría!");
					}
				}				
			});
		}
	}
});

//							######## Eliminar categoría #########

$('#btnCategEliminar').click(function(){
	$('#categErrorMsg').html("");
	
	var categoria = $('#tblCateg').DataTable().row(indexCategMod).data();
	var id =  categoria.id;
	
	$.ajax({
		url: "eliminarCategoria.html",
		type: "POST", 
		data:{
			id: id
		},
		success: function(resp){
			r = JSON.parse(resp);
			if(r.exito){
				$('#categErrorMsg').html("Categoria eliminada!");
				
				//Quitar el detalle de la tabla. 
				$('#tblCateg').DataTable().row(indexCategMod).remove().draw();
				
				//Eliminar de la tabla de detalles los detalles eliminados al eliminar la categoría. 
				$('#tblDetalle').DataTable().rows(function(index, detalle){
					return detalle.idCategoria == id;
				}).remove().draw();
				
				//Quita el modo edición.
				$('#btnCategGuardar').prop('value', 'Crear');
				$('#btnCategEliminar').prop('disabled', true);
				$('#txtCategNombre').prop('disabled', false);
				$('#cmbCategDepto').prop('disabled', false);
				
				//Pone vacio el formulario
				$('#txtCategNombre').val('');
				$('#txtCategDesc').val('');
				$('#cmbCategDepto').val('0');
				
				//Vacia el select de categoria de detalle, para evitar inconsistencias. 
				$('#cmbDetalleDepto').val('0')
				$('#cmbDetalleCateg').html('');
				$('#cmbDetalleCateg').val('');
			}
			else{
				$('#categErrorMsg').html("Error al eliminar categoría!");
			}
		}
	});
});

//CRUD de detalle categoría *************************************************************************************************
$('#btnDetalleGuardar').click(function(){
	$('#detalleErrorMsg').html("");
	
	//Validación. 
	var nombre = $('#txtDetalleNombre').val();
	nombre = $.trim(nombre);
	
	var desc = $('#txtDetalleDesc').val();
	desc = $.trim(desc);
	
	var categ = $('#cmbDetalleCateg').val();
	
	//Crea un arreglo de valores ingresados, separados por "\n"
	var valoresStr = $.trim($('#valoresDetalle').val());		
	var valores = $.map(valoresStr.split("\n"), $.trim);
	
	if(nombre == "" || desc == "" || categ == '0' || valoresStr == ""){
		$('#detalleErrorMsg').html("Ingrese todos los campos!");
	}	
	else if(valores.length < 2){
		$('#detalleErrorMsg').html("Ingrese por lo menos 2 opciones para el detalle!");
	}
	else if(duplicado(valores)){
		$('#detalleErrorMsg').html("Eliminar valores repetidos!");
	}
	
	else{
		
		//					#######  Crear detalle #######
		
		if($('#btnDetalleGuardar').val() == 'Crear'){
			$.ajax({
				url: "crearDetalle.html",
				type: "POST", 
				data:{
					nombre: nombre,
					desc: desc,
					categ: categ,
					valores: valores
				},
				success: function(resp){
					var r = JSON.parse(resp);
					if(r.exito){
						var nuevoDetalle = r.detalle;
						
						$('#detalleErrorMsg').html("Detalle creado!");
						
						//Pone el nuevo dato en la tabla.
						$('#tblDetalle').DataTable().row.add(nuevoDetalle).draw();
						
						//Pone vacio el formulario
						$('#txtDetalleNombre').val('');
						$('#txtDetalleDesc').val('');
						$('#valoresDetalle').val('');
						$('#cmbDetalleDepto').val('0');
						$('#cmbDetalleCateg').html('');
						$('#cmbDetalleCateg').val('');
					}
					else{
						$('#detalleErrorMsg').html("Error al crear detalle!");
					}
				}				
			});
		}
		
		//				####### Modificar detalle #######
		
		else if($('#btnDetalleGuardar').val() == 'Modificar'){
			var detalle =  $('#tblDetalle').DataTable().row(indexDetalleMod).data();
			var id = detalle.id;
			var valoresR = detalle.valores;
			
			//Crea un arreglo con los nuevos valores. 
			var nuevosValores = $.grep(valores, function(valor){
				var r = true;
				
				$.each(valoresR, function(i, valorR){
					if(valor == valorR) r = false;
				});
				
				return r;
			});
			
			//Crea un arreglo con los valores eliminados.
			var valoresElim = $.grep(valoresR, function(valorR){
				var r = true;
				
				$.each(valores, function(i, valor){
					if(valorR == valor) r = false;
				});
				
				return r;
			});
			
			$.ajax({
				url: "modificarDetalle.html",
				type: "POST", 
				data:{
					id: id,
					desc: desc,
					valoresnuevos: nuevosValores,
					valoreseliminar: valoresElim 
				},
				success: function(resp){
					var r = JSON.parse(resp);
					if(r.exito){
						$('#detalleErrorMsg').html("Detalle modificado!");
						
						//Modifica la tabla, utilizando el row de la tabla obtenido al inicio de modificar.
						detalle.desc = desc;
						detalle.valores = valores;
						$('#tblDetalle').DataTable().row(indexDetalleMod).data(detalle).draw();
						
						//Pone vacio el formulario
						$('#txtDetalleNombre').val('');
						$('#txtDetalleDesc').val('');
						$('#valoresDetalle').val('');
						$('#cmbDetalleDepto').val('0');
						$('#cmbDetalleCateg').html('');
						$('#cmbDetalleCateg').val('');
						
						//Quita el modo edición. 
						$('#btnDetalleGuardar').prop('value', 'Crear');
						$('#btnDetalleEliminar').prop('disabled', true);
						$('#cmbDetalleDepto, #cmbDetalleCateg').prop('disabled', false);
						$('#txtDetalleNombre').prop('disabled', false);	
					}
					else{
						$('#detalleErrorMsg').html("Error al modificar detalle!");
					}
				}				
			});
		}
	}
});

//					######## Eliminar detalle #########
$('#btnDetalleEliminar').click(function(){
	$('#detalleErrorMsg').html("");
	
	var id =  $('#tblDetalle').DataTable().row(indexDetalleMod).data().id;
	$.ajax({
		url: "eliminarDetalle.html",
		type: "POST", 
		data:{
			id: id
		},
		success: function(resp){
			r = JSON.parse(resp);
			if(r.exito){
				$('#detalleErrorMsg').html("Detalle eliminado!");
				
				//Quitar el detalle de la tabla. 
				$('#tblDetalle').DataTable().row(indexDetalleMod).remove().draw();
				
				//Pone vacio el formulario
				$('#txtDetalleNombre').val('');
				$('#txtDetalleDesc').val('');
				$('#valoresDetalle').val('');
				$('#cmbDetalleDepto').val('0');
				$('#cmbDetalleCateg').html('');
				$('#cmbDetalleCateg').val('');
				
				//Quita el modo edición. 
				$('#btnDetalleGuardar').prop('value', 'Crear');
				$('#btnDetalleEliminar').prop('disabled', true);
				$('#cmbDetalleDepto, #cmbDetalleCateg').prop('disabled', false);
				$('#txtDetalleNombre').prop('disabled', false);	
			}
			else{
				$('#detalleErrorMsg').html("Error al eliminar detalle!");
			}
		}
	});
});


//*************************************************************************************************************
function duplicado(string){
	var str = string.sort();
	for (var i = 0; i < str.length - 1; i++) {
	    if (str[i + 1] == str[i]) {
	        return true;
	    }
	}
	return false;
}