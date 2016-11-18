$('#btnLogin').click(function(){	
	var email = $('#loginEmail').val();
	var password = $('#password').val();
	
	//Validación.
	if(email=="" || password=="")
		redirectLogin("Complete ambos campos!");
	if(!validateEmail(email)){			//En validate.js
		redirectLogin("Ingrese una cuenta de correo válida!");
	}
	else{
		$.ajax({
			type: "POST",
		    url:"login.html",
		    data:{
		    	email: email,
		    	password: password
		    },
		    
		    success: function(resp){
		    	var r = JSON.parse(resp);
		    	redirectUser(r.msg, r.tipoUsuario, r.tipoEmpleado);
		    },
		});
	}
});


function redirectUser(msg, tipoUsuario, tipoEmpleado){
	//Si el correo no está registrado: 
    if(msg =="fracaso"){
    	redirectLogin("Cuenta o contraseña incorrectas!");
    }
    
    //Si se loguea con éxito: 
    else if(msg=="exito"){

    	if(tipoUsuario=="cliente"){
    		redirectClient();
    	}
    	
    	else if(tipoUsuario=="webmaster"){
    		redirectWebMaster();
    	}
    	
    	else if(tipoUsuario=="empleado"){
    		switch(tipoEmpleado){
    		
    		case "gerenteGeneral":
    			redirectGeneral();
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
}

//Redirige a la página de login.
function redirectLogin(mensaje){
	$.redirect(
		"/loginFailed.html",
		{
			email: $('#loginEmail').val(), 
			msg: mensaje
		},
		"POST"
	);
}

function redirectClient(){
	$.redirect(
		"/",
		"POST"
	);
}

function redirectWebMaster(){
	$.redirect(
			"/wm_create.html",
			"POST"
		);
}

//Redirige a la gestión de gerente general.
function redirectGeneral(){
	$.redirect(
		"/dashboard_gg.html",
		"POST"
	);
}

//Redirige a la gestión del gerente de ventas. 
function redirectVentas(){
	$.redirect(
		"/productManagement_gv.html",
		"POST"
	);
}

//Redirige a la gestión del gerente de inventario.
function redirectInventario(){
	$.redirect(
		"/productManagement_gi.html",
		"POST"
	);
}