$('#btnRegistrar').click(function(){
	$('#errorMsg').html('');
	
	var correo = $('#correo').html();
	var nombre = $('#nombre').html();
	var apellido = $('#apellido').html();
	
	if(correo == null || nombre == null || apellido == null)
		$('#errorMsg').html('Error! Registro de cuenta incorrecto!');
	else if(!validateEmail(correo))		//En validate.js
		$('#errorMsg').html('Error! Formato de correo no permitido');
	else if(!validateName(nombre) || !validateName(apellido))  		//En validate.js
		$('#errorMsg').html('Error! El nombre y apellido deben iniciar en mayuscula!');
	else{
		var contra1 = $('#contra1').val();
		var contra2 = $('#contra2').val();
		var pais = $('#pais').val();
		var ciudad = $('#ciudad').val();
		var direccion = $('#direccion').val();
		var zip = $('#zip').val();
		
		if(contra1 == "" || contra2 == "" || pais == "" || ciudad == "" || direccion == "" || zip == "")
			$('#errorMsg').html('Ingrese todos los campos!');
		else if(contra1 != contra2)
			$('#errorMsg').html('No hay coincidencia! Ingrese nuevamente los password.');
		else if(!validateLengthPassword(contra1))				//Función de validate.js			
			$('#errorMsg').html('El password debe tener al menor 8 caracteres!');
		else if(!validateFormatPassword(contra1))				//Función de validate.js
			$('#errorMsg').html('El password solo puede contener letras y al menos un digito!');
		else{
			$.ajax({
				url: "confirmarCuenta.html",
				type: "POST",
				data:{
					correo: correo,
					nombre: nombre,		//En validate.js
					apellido: apellido,	//En validate.js
					contra: contra1,
					pais: pais, 
					ciudad: ciudad,
					direccion: direccion,
					zip: zip
				},
				success: function(resp){
					r = JSON.parse(resp);
					if(r.exito){
						alert("Cuentra creada!");
						redirectUser("exito", "cliente", "NA");			//En login.js
					}
					else
						$('#errorMsg').html('Error al crear la cuenta!');
				}
			});
		}		
	}
		
});