$('#btnGuardarGG').click(function(){
	$('#errorMsgGG').html('');
	
	var nombre = $.trim($('#txtNombreGG').val());
	var apellido = $.trim($('#txtApellidoGG').val());
	
	//Validación
	if(!validateName(nombre) || !validateName(apellido))  		//En validate.js
		$('#errorMsgGG').html('El nombre y apellido deben iniciar en mayúscula!');
	else{
		$.ajax({
			url: "modificarEmpleado.html", 
			type: "POST",
			data:{
				tipo: "gerenteGeneral",
				nombre: nombre,
				apellido: apellido,
				correo: $('#txtCorreoGG').val()
			},
			success: function(resp){
				var r = JSON.parse(resp);
				if(r.exito){
					$('#errorMsgGG').html('Datos modificados');
					$('#headerName').html(nombre + ' ' + apellido);
				}
				else{
					$('#errorMsgGG').html('Error al guardar los datos!');
				}
			}
		});
	}
});

$('#btnGuardarGV').click(function(){
	$('#errorMsgGV').html('');
	
	var nombre = $.trim($('#txtNombreGV').val());
	var apellido = $.trim($('#txtApellidoGV').val());
	var correo = $('#txtCorreoGV').val();
	
	//Validación
	if(!validateName(nombre) || !validateName(apellido))  		//En validate.js
		$('#errorMsgGV').html('El nombre y apellido deben iniciar en mayúscula!');
	else if(!validateEmail(correo))		//En validate.js
		$('#errorMsgGV').html('Formato de correo no permitido');
	else{
		$.ajax({
			url: "modificarEmpleado.html", 
			type: "POST",
			data:{
				tipo: "gerenteVentas",
				nombre: nombre,
				apellido: apellido,
				correo: correo
			},
			success: function(resp){
				var r = JSON.parse(resp);
				if(r.exito){					
					if(r.nuevo) $('#errorMsgGV').html('Datos modificados! Se ha enviado un correo al nuevo gerente de ventas.');
					else $('#errorMsgGV').html('Datos modificados!');
				}
				else{
					$('#errorMsgGV').html('Error al guardar los datos! El correo ingresado ya está registrado.');
				}
			}
		});
	}
});

$('#btnGuardarGI').click(function(){
	$('#errorMsgGI').html('');
	
	var nombre = $.trim($('#txtNombreGI').val());
	var apellido = $.trim($('#txtApellidoGI').val());
	var correo = $('#txtCorreoGI').val();
	
	//Validación
	if(!validateName(nombre) || !validateName(apellido))  		//En validate.js
		$('#errorMsgGI').html('El nombre y apellido deben iniciar en mayúscula!');
	else if(!validateEmail(correo))		//En validate.js
		$('#errorMsgGI').html('Formato de correo no permitido');
	else{
		$.ajax({
			url: "modificarEmpleado.html", 
			type: "POST",
			data:{
				tipo: "gerenteInventario",
				nombre: nombre,
				apellido: apellido,
				correo: correo
			},
			success: function(resp){
				var r = JSON.parse(resp);
				if(r.exito){
					if(r.nuevo) $('#errorMsgGI').html('Datos modificados! Se ha enviado un correo al nuevo gerente de inventario.');
					else $('#errorMsgGI').html('Datos modificados!');
				}
				else{
					$('#errorMsgGI').html('Error al guardar los datos! El correo ingresado ya está registrado.');
				}
			}
		});
	}
});