function login(){
	$.ajax({
		type: "POST",
	    url:"login.html",
	    data:{
	    	email: $('#loginEmail').val(), 
	    	password: $('#password').val()
	    },
	    
	    success: function(r){
	        console.log(r);
	    },
	});
}
