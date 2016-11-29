$(document).ready(function(){
	
	//Llena las opciones del cliente. 
	if($('#txtCuenta').html() == 'Cuenta'){
		
		//Si no se ha logueado.
		var li1 = $('<li>').appendTo($('#clientOptions'));
		var a1 = $('<a id="btnLogin">').attr('href', '#').appendTo(li1);
		a1.html('Entrar');
		
		var li2 = $('<li>').appendTo($('#clientOptions'));
		var a2 = $('<a>').attr('href', 'signIn.html').appendTo(li2);
		a2.html('Registrarse');
	}
	else{
		
		//Si ya inicio sesión.
		var li = $('<li>').appendTo($('#clientOptions'));
		var a = $('<a id="btnLogout">').attr('href', '#').appendTo(li);
		a.html('Salir');
	}
	
	//Iniciar sesión.
	$('#btnLogin').click(function(){
		$.redirect(
				"/loginFailed.html",
				{
					nextUrl: $(location).attr('pathname')		//Redirige de vuelta al sitio actual luego de loguearse. 
				},
				"POST"
			);
	});
	
	//Para cerrar sesión, colocar un objeto con un botón con el id "btnLogout"
	$('#btnLogout').click(function(){	
		$.ajax({
			url: "logout.html",
			type: "POST",
			success: function(){
				location.reload();
			}
		});	
	});
});

