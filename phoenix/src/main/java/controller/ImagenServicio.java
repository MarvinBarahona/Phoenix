package controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.BlobstoreService;


//Crea un servlet para recuperar las imagenes guardadas como blobs. 
//Llamar a las imagenes con la dirección host/imagenServicio?blob-key={blobkey a buscar}
//El servlet se debe agregar a web.xml
@SuppressWarnings("serial")
@Controller
public class ImagenServicio extends HttpServlet{
	private BlobstoreService blobStoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String blob_key = req.getParameter("blob-key");
		BlobKey blobKey = new BlobKey(blob_key);
		blobStoreService.serve(blobKey, res);
	}
}
