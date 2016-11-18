//Guarda una imagen seleccionada dentro del jsp. 
//Para usar, colocar un input type='file' con el id='file_url', 
//y un ckeckbox con el id="chechImg"

//Para los parámetros, se debe enviar "producto" o "empresa", dependiendo de a qué objeto se haga referencia. 
//En ambos casos es necesario un id para guardar la imagen. 

function guardarImagen(tipo, id){
	var blobkey;
	
	if($('#checkImg').is(':checked')){		
		$.ajax({
			type: "POST",
			url: "obtenerUploadURL.html",
			success: function(resp){
				r = JSON.parse(resp)
				
				var inputFileImage = $('#file_url')[0];
				var file = inputFileImage.files[0];
				
				var data = new FormData();
				data.append("imagen_url", file);
				
				//Datos para guardar el blob-key de la imagen. 
				data.append("tipo", tipo);
				data.append("id", id);
				
				$.ajax({
					type: "POST",
					processData:false,
					contentType: false,
					cache:false,
					data: data,
				    dataType: "json",
					url: r.url,
				    success: function(resp){
				    	console.log(resp);
				    },
				});
			}
		});
	}	
	
}