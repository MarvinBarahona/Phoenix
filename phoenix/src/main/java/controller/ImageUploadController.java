package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import util.UploadURL;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

//Luego de subir la imagen como blob, app engine redirige a la dirección especificada cuando se crea el UploadURL.

@Controller
public class ImageUploadController {
	@Autowired private HttpServletResponse reponse;
	@Autowired private HttpServletRequest request;
	
	private BlobstoreService blobStoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	//Como prueba, luego de guardar la imagen se recupera su blobkey y se manda como respuesta. 
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/subirImagen",headers="Accept=*/*",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody String pruebaImagen() throws IOException{
		//Recupera los blobs agregados en el último upload.
		Map<String,List<BlobKey>> blobs = blobStoreService.getUploads(request);
		
		//Recupera el blobkey de la última imagen agregada. Nota: "imagen_url" debe coincidir con el nombre del 
		//valor a guardar (en nuestro caso, con el nombre del parameter de ajax mandado para guardar. 
		List<BlobKey> blobKeys = blobs.get("imagen_url");
		String resp = blobKeys.get(0).getKeyString();
		
		return new Gson().toJson(resp);
	}
	
	
	//Obtiene un nuevo url para subir una imagen. 
	@RequestMapping(value="/obtenerUploadURL",headers="Accept=*/*",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody String obtenerUploadURL(){
		String url = UploadURL.getUploadURL();
		
		JsonObject resp = new JsonObject();
		resp.addProperty("url", url);
		
		return new Gson().toJson(resp);
	}
}
