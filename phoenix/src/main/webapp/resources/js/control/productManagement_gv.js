$('#btnGuardar').click(function(){
	var blobkey;
	var img = true;		//Ocupar con un checkbox para decidir cuando guardar la imagen o no.
	if(img){
		//Agregar parametros para decidir dónde guardar el atributo img. 
		guardarImagen();		//Dentro de guardarImagen.js
	}
	//guardarDatos();      		
});