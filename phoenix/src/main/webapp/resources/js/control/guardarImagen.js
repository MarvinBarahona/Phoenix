//Guarda una imagen seleccionada dentro del jsp. 
//Para usar, colocar un input type='file' con el id='imagen_url'.

//Agregar parametros para decidir dónde guardar el atributo img. 
function guardarImagen(){
	var blobkey;
	$.ajax({
		type: "POST",
		url: "obtenerUploadURL.html",
		success: function(resp){
			r = JSON.parse(resp)
			
			var inputFileImage = $('#imagen_url')[0];
			var file = inputFileImage.files[0];
			
			var data = new FormData();
			data.append("imagen_url", file);
			
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