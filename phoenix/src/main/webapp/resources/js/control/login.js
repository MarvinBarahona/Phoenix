function login(){
	alert("hasta aqui todo bien");
	$.ajax({
		type: "POST",
	    url:"login.html",
	    data: {email: $('#email').val()},
	    
	    success: function(r){
	        console.log(r);
	    },
	});
}
