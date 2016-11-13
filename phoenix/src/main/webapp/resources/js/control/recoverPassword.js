$('#btnAceptar').click(function(){
	$('#errorMsg').html("");
	var correo = $('#correo').val();
	
	if(!validateEmail(correo)){			//En validate.js
		$('#errorMsg').html("Formato de correo incorrecto!");
	}
	else{
		$.ajax({
			url: "recuperarContra.html",
			type: "POST",
			data:{
				correo: correo,
			},
			success: function(resp){
				r = JSON.parse(resp);
				
				if(r.exito==false){
					$('#errorMsg').html("El correo ingresado no está registrado!");
				}
				else{
					$('#errorMsg').html("Correo enviado! Por favor revise su bandeja de entrada para ver las instrucciones.");
				}
			}		
		});
	}
});