$('#btnAceptar').click(function(){
	var contra1 = $('#contra1').val();
	var contra2 = $('#contra2').val();
	
	$('#errorMsg').html('');
	
	if(contra1 == "" || contra2 == "")
		$('#errorMsg').html('Ingrese ambos campos!');
	else if(contra1 != contra2)
		$('#errorMsg').html('No hay coincidencias! Ingrese nuevamente los datos.');
	else if(!validateLengthPassword(contra1))				//Función de validate.js			
		$('#errorMsg').html('El password debe tener al menor 8 caracteres!');
	else if(!validateFormatPassword(contra1))				//Función de validate.js
		$('#errorMsg').html('El password solo puede contener letras y al menos un digito!');
	else{
		var user = $('#correo').html();
		$.ajax({
			url: "cambiarContra.html",
			type: "POST",
			data:{
				correo: user,
				contra: contra1
			},
			success: function(resp){
				r = JSON.parse(resp);
				if(r.msg == "error")
					$('#errorMsg').html('Error en el reestablecimiento!');
				else{
					alert("Password recuperado exitosamente!");
					redirectUser(r.msg, r.tipoUsuario, r.tipoEmpleado);		//Función de login.js
				}
			}
		});
	}
});