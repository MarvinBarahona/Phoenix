$('#btnGuardar').click(function(){
	$('#errorMsg').html('');
	
	var tel = $('#txtTelefono').val();
	var costoEnvio = $('#txtCostoEnvio').val();
	var pais = $('#txtPais').val();
	var ciudad = $('#txtCiudad').val();
	var dir = $('#txtDir').val();
	var zip = $('#txtZip').val();
	var nombre = $('#txtNombre').val();
	
	//Validación.
	if(nombre == '' || tel == '' || pais == '' || ciudad == '' || dir == '' || zip == '' || costoEnvio == ''){
		$('#errorMsg').html('Ingrese todos los campos!');
	}
	else{
		$('#errorMsg').html('Modificando...');
		
		$.ajax({
			url: "modificarEmpresa.html",
			type: "POST",
			data:{
				nombre: nombre,
				telefono: tel,
				costoEnvio: costoEnvio,
				pais: pais,
				ciudad: ciudad,
				direccion: dir,
				zip: zip
			},
			success: function(resp){
				r = JSON.parse(resp);
				
				if(r.exito){
					//Guardar la imagen (Se hace hasta este momento para evitar choque de llamados asincronos).
					guardarImagen("empresa", 0, 0);		//En guardarImagen.js
					
					$('#errorMsg').html('Modificaciones realizadas!');
					
					//Actualiza el header con los datos modificados. 
					$('#headerNombreEmpresa').html(nombre);
					$('#logo').attr('src', $('#img_destino').attr('src'));
				}
				else{
					$('#errorMsg').html('Error al modificar los datos!');
				}
				
				
			}		
		});
	}
});