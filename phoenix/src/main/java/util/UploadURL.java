package util;

import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadURL {
	public static String getUploadURL(){
		return BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/subirImagen.html");
	}
	
	public static String getImageURL(String blobkey){
		return "http://localhost:8080/imagenServicio?blob-key=" + blobkey + '"';
	}
}
