function login(){
	$.ajax({
		type: "POST",
	    url:"login.html",
	    data:{
	    	email: $('#loginEmail').val(), 
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
	        			redirectLogin("Es un gerenteGeneral pero no está implmentado todavia xD!"); //Cambiar!!
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
		{
			email: $('#loginEmail').val()
		},
		"POST"
	);
}

//Redirige a la gestión del gerente de inventario.
function redirectInventario(){
	$.redirect(
		"product_gi.html",
		{
			email: $('#loginEmail').val()
		},
		"POST"
	);
}