package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import productos.Categoria;
import productos.Departamento;
import productos.Producto;
import servicio.CategoriaServicio;
import servicio.DepartamentoServicio;
import servicio.EmpleadoServicio;
import servicio.ProductoServicio;
import usuarios.Empleado;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
public class ProductoCRUDController {
	
	@Autowired private HttpServletRequest request;
	
	@PostMapping(value = "/guardarProducto", produces="application/json")
	public @ResponseBody String guardarProducto(){
		JsonObject resp = new JsonObject();
		
		return new Gson().toJson(resp);
	}
	
	
    //Obtencion de los productos de una empresa específica. 
	@PostMapping(value="/obtenerProductos", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String obtenerProductos(){
		JsonArray productosJ = new JsonArray();
		HttpSession session = request.getSession();
		
		//Recupera la empresa para la que trabaja el empleado. 		
		String correo = (String)session.getAttribute("correo");
		Empleado e = EmpleadoServicio.buscarPorCorreo(correo);
		int idEmpresa = e.getCodigoEmpresa();		
		List<Producto> productos = ProductoServicio.obtenerProductos(idEmpresa, true);	
		
		//Crea la respuesta (en formato para mostrar los datos y alamcenar otros como referencia. 
		for(Producto producto : productos){
			Categoria categoria = CategoriaServicio.buscarPorId(producto.getCodigoCategoria());
			Departamento departamento = DepartamentoServicio.buscarPorId(producto.getCodigoDepartamento());
			
			JsonObject productoJ = new JsonObject();
			
			//Datos a mostrar. 
			productoJ.addProperty("nombre", producto.getNombre());
			productoJ.addProperty("departamento", departamento.getNombre());
			productoJ.addProperty("categoria", categoria.getNombre());
			productoJ.addProperty("cantidad", producto.getExistencias());
			productoJ.addProperty("precio", "$"+producto.getPrecio());
			productoJ.addProperty("descuento", producto.getDescuento() + "%");
			productoJ.addProperty("disponible", producto.isDisponible());
			
			//Datos a almacenar como referencia. 
			productoJ.addProperty("id", producto.getCodigo());
			productoJ.addProperty("urlImg", producto.getImg(request.getServerName()));
			
			productosJ.add(productoJ);
		}
		
		return new Gson().toJson(productosJ);		
	}
}
