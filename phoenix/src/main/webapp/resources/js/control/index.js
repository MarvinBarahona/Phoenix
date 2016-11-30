//Recuperar las imagenes de las empresas. 
$(document).ready(function(){
	$.ajax({
		url: "obtenerImgEmpresas.html",
		type: "POST",
		data:{
			
		},
		success: function(resp){
			var empresas = JSON.parse(resp);
			
			$.each(empresas, function(i, empresa){
				var div = $('<div class="col-md-3">');
				div.appendTo("#divImg");
				
				var img = $('<img class="img-responsive base-orange imgEmpresa" alt="Imagen empresa" id=' + empresa.id + '>');
				img.attr('src', empresa.urlImg);
				img.appendTo(div);
				
			});
			
			//Permite redirigir al home de cada empresa al darle click a la imagen. 
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