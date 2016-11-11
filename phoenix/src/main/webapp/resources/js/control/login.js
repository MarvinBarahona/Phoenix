$('#btnLogin').click(function(){
	var regex = /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}$/; 
	var email = $('#loginEmail').val();
	if(!regex.test(email)){
		redirectLogin("Ingrese una cuenta de correo válida!");
	}
	else{
		$.ajax({
			type: "POST",
		    url:"login.html",
		    data:{
		    	email: email,
		    	password: $('#password').val()
		    },
		    
		    success: function(resp){
		    	var r = JSON.parse(resp);
		    	
		    	//Si el correo no está registrado: 
		        if(r.msg =="fracaso"){
		        	redirectLogin("Cuenta o contraseña incorrectas!");
		        }
		        
		        //Si se loguea con éxito: 
		        else if(r.msg=="exito"){
		 
		        	if(r.tipoUsuario=="cliente"){
		        		redirectLogin("Es un cliente pero no está implementado todavia xD!") //Cambiar!!
		        	}
		        	
		        	if(r.tipoUsuario=="empleado"){
		        		switch(r.tipoEmpleado){
		        		
		        		case "gerenteGeneral":
		        			redirectLogin("Es un gerenteGeneral pero no está implementado todavia xD!"); //Cambiar!!
		        			break;
		        			
		        		case "gerenteVentas":
		        			redirectVentas();
		        			break;
		        			
		        		case "gerenteInventario":
		        			redirectInventario();
		        			break;
		        		}
		        	}
		        }
		    },
		});
	}
});


//Redirige a la página de login.
function redirectLogin(mensaje){
	$.redirect(
		"loginFailed.html",
		{
			email: $('#loginEmail').val(), 
			msg: mensaje
		},
		"POST"
	);
}

//Redirige a la gestión del gerente de ventas. 
function redirectVentas(){
	$.redirect(
		"product_gv.html",
		"POST"
	);
}

//Redirige a la gestión del gerente de inventario.
function redirectInventario(){
	$.redirect(
		"product_gi.html",
		"POST"
	);
}