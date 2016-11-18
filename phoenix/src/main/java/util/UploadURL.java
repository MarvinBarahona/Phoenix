package util;

import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadURL {
	
	//Se crea un nuevo URL para subir un archivo (usado para imagenes). 
	//Luego de guardar, se redirecciona al url especificado en el método "createUploadUrl".
	public static String getUploadURL(){
		return BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/subirImagen.html");
	}
	
	//Recupera una imagen, especificada por un blobkey y un servername (para no  
	//amarrar el método al sitio desplegado o a localhost)
	public static String getImageURL(String blobkey, String servername){
		String url;
		
		if(blobkey == null || blobkey == ""){
			blobkey = "aPFZxqqadGndlqY17jnItg";
		}
		
		if("localhost".equals(servername)){
			url = "http://localhost:8080/imagenServicio?blob-key=" + blobkey + '"';
		}
		else{
			url = "http://" + servername + "/imagenServicio?blob-key=" + blobkey + '"';
		}
		return url;	
	}
}
