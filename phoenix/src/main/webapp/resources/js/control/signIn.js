$('#btnRegistrar').click(function(){
	$('#errorMsg').html('');
	
	var correo = $('#correo').val();
	var nombre = $('#nombre').val();
	var apellido = $('#apellido').val();
	
	//Validación.
	if(correo == "" || nombre == "" || apellido == "")
		$('#errorMsg').html('Ingrese todos los campos!');
	else if(!validateEmail(correo))				//En validate.js
		$('#errorMsg').html('Formato de correo no permitido');
	else if(!validateName(nombre) || !validateName(apellido))		//En validate.js
		$('#errorMsg').html('Solo un nombre y un apellido, con solo la primera letra mayúscula!');
	
	
	else{
		$('#errorMsg').html('Enviando correo...');
		$.ajax({
			url: "enviarConfirmacion.html",
			type: "POST",
			data:{
				correo: correo,
				nombre: nombre,
				apellido: apellido
			},
			success: function(resp){
				r = JSON.parse(resp);
				if(r.msg == 'fallo')
					$('#errorMsg').html('Fallo en el envío de correo!');
				else if(r.msg == 'duplicado')
					$('#errorMsg').html('El correo ingresado ya está registrado!');
				else
					$('#errorMsg').html('Correo enviado! Por favor revise su bandeja de entrada para ver las instrucciones.');
			}
		});
	}
		
});