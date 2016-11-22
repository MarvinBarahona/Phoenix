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
		
		if("localhost".equals(servername)){
			//En localhost no se muestran imagenes.
			url = "http://localhost:8080/imagenServicio?blob-key=u21V1Vd_4ZdJLG488Z2WVg";
		}
		else{
			//Cargar la imagen por defecto.
			if(blobkey == null || blobkey == "") blobkey = "AMIfv97MLwXi0PFenxKgZI6xALTH_fxgNbCdVHY0g1-sPZbP4z9MyvNQ8-fJ4zkx-7OzOsZZgiKLXlXf44FGpcTSELHZ5a8qMchidYR36Zc8dk5oW4_Ysko2CzBbM_GS9ZF4-e7oDkqIc7S1W35xH6t-uGEuUuESW2w8AlvH3RTa1O_AfyHCfoioPR4pw3hu8pGnMyc7teldxAnkLzlAoc3Q3QxEexyQDmE1EmYCfe3IGm2_IeOYVeaNB8TYzKws2q_zriqf-A3bbY-UyxBEEEqQQMO0jPvF6pWZ_9fiXuQQMdu4OlIEbdU9oVY5mDqAWajq-9LSC4ZRN8XnredPbxSQTZOxYIW8Zj9Jq9JQRngsaitIL30MZqPQETps3lC84W-HHsnjXP5WCE_8QkecjjiP6vmnOjncT_-_Kb-8g-SShswdoLkQFS4";
			//Recuperar la imagen en forma de path.
			url = "https://" + servername + "/imagenServicio?blob-key=" + blobkey + '"';
		}
		return url;	
	}
}
