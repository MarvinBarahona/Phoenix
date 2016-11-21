$('#btnRegistrar').click(function(){
	$('#errorMsg').html('');

	var nombreEmpresa = $('#nombreEmpresa').html();

	//Validación, parte 1.
	if(nombreEmpresa == "")
		$('#errorMsg').html('Error! Registro de empresa incorrecto!');
	else{
		var contra1 = $('#contra1').val();
		var contra2 = $('#contra2').val();
		var pais = $('#pais').val();
		var ciudad = $('#ciudad').val();
		var direccion = $('#direccion').val();
		var zip = $('#zip').val();

		//Validación, parte 2.
		if(contra1 == "" || contra2 == "" || pais == "" || ciudad == "" || direccion == "" || zip == "")
			$('#errorMsg').html('Ingrese todos los campos!');
		else if(contra1 != contra2)
			$('#errorMsg').html('No hay coincidencias en la contraseña! Ingrese nuevamente.');
		else if(!validateLengthPassword(contra1))				//Función de validate.js
			$('#errorMsg').html('La contraseña debe tener al menor 8 caracteres!');
		else if(!validateFormatPassword(contra1))				//Función de validate.js
			$('#errorMsg').html('La contraseña solo puede tener letras y al menos un digito!');

		else{
			$.ajax({
				url: "confirmarEmpresa.html",
				type: "POST",
				data:{
					nombreEmpresa: nombreEmpresa,
					contra: contra1,
					pais: pais,
					ciudad: ciudad,
					direccion: direccion,
					zip: zip
				},
				success: function(resp){
					r = JSON.parse(resp);
					if(r.exito){
						alert("Empresa creada!");
						redirectUser("exito", "empleado", "gerenteGeneral");			//En login.js
					}
					else
						$('#errorMsg').html('Error al crear la cuenta!');
				}
			});
		}
	}

});
