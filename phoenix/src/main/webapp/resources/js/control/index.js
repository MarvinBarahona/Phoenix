$(document).ready(function(){
	$.ajax({
		url: "obtenerImgEmpresas.html",
		type: "POST",
		data:{
			
		},
		success: function(resp){
			var empresas = JSON.parse(resp);
			
			$.each(empresas, function(i, empresa){
				var img = $('<img class="img-responsive base-orange imgEmpresa" alt="Imagen empresa" id=' + empresa.id + '>');
				img.attr('src', empresa.urlImg);
				
				img.appendTo("#divImg");
			});
			
			$('.imgEmpresa').css('cursor', 'pointer');
			
			$('.imgEmpresa').click(function(){
				$.redirect(
					"home.html",
					{idEmpresa: $(this).prop('id')},
					"POST"
				);
			});
		}			
	});
});