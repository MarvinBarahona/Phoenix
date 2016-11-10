package util;

import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadURL {
	public static String getUploadURL(){
		return BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/subirImagen.html");
	}
	
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
