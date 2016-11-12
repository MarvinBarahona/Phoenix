//Para cerrar sesión, colocar un objeto con un botón con el id "btnLogout"
$('#btnLogout').click(function(){
	$.ajax({
		url: "logout.html",
		type: "POST",
	});
	
	$.redirect(
		"/",
		"POST"
	);
});