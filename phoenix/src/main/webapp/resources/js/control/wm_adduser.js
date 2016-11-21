$('#btnGuardar').click(function(){
	$('#errorMsg').html('');
	
	var nombre = $('#txtNombre').val();
	var apellido = $('#txtApellido').val();
	var nombreEmpresa = $('#txtNombreEmpresa').val();
	var correo = $('#txtCorreo').val();
	
	if(correo == "" || nombre == "" || apellido == "" || nombreEmpresa == "")
		$('#errorMsg').html('Ingrese todos los campos!');
	else if(!validateEmail(correo))		//En validate.js
		$('#errorMsg').html('Ingrese un correo válido!');
	else if(!validateName(nombre) || !validateName(apellido))  		//En validate.js
		$('#errorMsg').html('El nombre y apellido deben iniciar en mayúscula!');
	else{
		$.ajax({
			url: "crearEmpresa.html",
			type: "POST",
			data:{
				nombre: nombre,
				apellido: apellido,
				correo: correo, 
				nombreEmpresa: nombreEmpresa
			},
			success: function(resp){
				r = JSON.parse(resp);
				if(r.exito){
					$('#txtNombre').val('');
					$('#txtApellido').val('');
					$('#txtNombreEmpresa').val('');
					$('#txtCorreo').val('');
					$('#errorMsg').html("Empresa creada! Se envió un correo al usuario para que confirme la cuenta.");
				}
				else
					$('#errorMsg').html('Error al crear la empresa!');
			}
		});
	}
});