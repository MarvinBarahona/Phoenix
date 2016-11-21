package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import productos.Producto;
import servicio.EmpresaServicio;
import servicio.ProductoServicio;
import usuarios.Empresa;
import util.UploadURL;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;



@Controller
public class ImageUploadController {
	@Autowired private HttpServletResponse reponse;
	@Autowired private HttpServletRequest request;
	
	private BlobstoreService blobStoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	
	//Obtiene un nuevo url para subir una imagen. 
	//Este url solo sirve una vez, y se debe acceder al url por medio de un post especificando 
	//el dato a guardar. El método subirImagen soporta el upload de la imagen.
	@PostMapping(value="/obtenerUploadURL",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerUploadURL(){
		String url = UploadURL.getUploadURL();
		
		JsonObject resp = new JsonObject();
		resp.addProperty("url", url);
		
		return new Gson().toJson(resp);
	}
	
	
	//Luego de guardar la imagen se recupera su blobkey y se almacena según el tipo de imagen que se almacena. 
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value="/subirImagen",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String subirImagen() throws IOException{		
		String resp = "exito";
		
		//Recupera el blobkey de la última imagen agregada. Nota: "imagen_url" debe coincidir con el nombre del 
		//valor a guardar (en nuestro caso, con el nombre del parameter de ajax mandado para guardar)
		Map<String,List<BlobKey>> blobs = blobStoreService.getUploads(request);
		List<BlobKey> blobKeys = blobs.get("imagen_url");
		String blob = blobKeys.get(0).getKeyString();
		
		try{
			String tipo = request.getParameter("tipo");
			int id = Integer.valueOf(request.getParameter("id"));
			
			if(tipo.matches("producto")){
				Producto p = ProductoServicio.buscarPorId(id);
				ProductoServicio.actualizarImagen(p, blob);
			}
			else if(tipo.matches("empresa")){
				Empresa e = EmpresaServicio.buscarPorId(id);
				EmpresaServicio.actualizarImagen(e, blob);
			}
			
		}catch(NullPointerException e1){
			resp = "fallo1";
		}catch(NumberFormatException e2){
			resp = "fallo2";
		}
		
		return new Gson().toJson(resp);
	}
}
