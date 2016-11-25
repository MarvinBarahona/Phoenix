package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import productos.Categoria;
import productos.Departamento;
import productos.DetalleProducto;
import productos.Producto;
import servicio.CategoriaServicio;
import servicio.DepartamentoServicio;
import servicio.DetalleProductoServicio;
import servicio.ProductoServicio;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
public class ProductoCRUDController {
	
	@Autowired private HttpServletRequest request;
	
	
// 							########## Crear #########
	
	@PostMapping(value = "/crearProducto", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String crearProducto(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		//Recuperar los valores.
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		int codigoDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
		int codigoCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		int codigoEmpresa = (int)request.getSession().getAttribute("idEmpresa");
		
		//Obtener un arreglo de int con los detalles. 
		String[] idDetallesStr = request.getParameterValues("idDetalles[]");
		int[] idDetalles = null;		
		if(idDetallesStr != null){
			idDetalles = new int[idDetallesStr.length];
			for(int i = 0; i<idDetallesStr.length; i++){
				idDetalles[i] = Integer.parseInt(idDetallesStr[i]);
			}
		}		
		//Obtener los valores de los detalles. 
		String[] valoresDetalles = request.getParameterValues("valoresDetalles[]");
		
		try{
			Producto producto = ProductoServicio.crear(nombre, cantidad, codigoCategoria, codigoDepartamento, codigoEmpresa,
					idDetalles, valoresDetalles);
			
			resp.add("producto", obtenerProducto(producto));
		}
		catch(Exception e){
			resp.addProperty("exito", false);
		}
		
		return new Gson().toJson(resp);
	}
	
	
// 						########## Modificar de inventario #########
	
	@PostMapping(value = "/modificarInvProducto", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String modificarInvProducto(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		//Recuperar los valores.
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));
		String nombre = request.getParameter("nombre");
		int codigoCategoria = Integer.valueOf(request.getParameter("idCategoria"));
		int codigoDepartamento = Integer.valueOf(request.getParameter("idDepartamento"));
		
		//Obtener un arreglo de int con los detalles. 
		String[] idDetallesStr = request.getParameterValues("idDetalles[]");
		int[] idDetalles = null;
		if(idDetallesStr != null){
			idDetalles = new int[idDetallesStr.length];
			for(int i = 0; i<idDetallesStr.length; i++){
				idDetalles[i] = Integer.parseInt(idDetallesStr[i]);
			}
		}
		//Obtener los valores de los detalles.
		String[] valoresDetalles = request.getParameterValues("valoresDetalles[]");
		
		//Recuperar el producto a actualizar. 
		Producto p = ProductoServicio.buscarPorId(codigoProducto);
		
		//Determina si se ha modificado la categoria y el departamento.
		codigoCategoria = codigoCategoria == p.getCodigoCategoria() ? -1 : codigoCategoria;
		codigoDepartamento = codigoDepartamento == p.getCodigoDepartamento() ? -1 : codigoDepartamento;
		
		//Actualizar. 
		int r = ProductoServicio.actualizarInventario(p, nombre, idDetalles, valoresDetalles, codigoCategoria, codigoDepartamento);
		if(r == 1) resp.addProperty("exito", false);
		
		return new Gson().toJson(resp);
	}
	
	
// 									########### Modificar de ventas ###########	
	
	@PostMapping(value = "/modificarVenProducto", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String modificarVenProducto(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		//Recuperar los valores.
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));
		String nombre = request.getParameter("nombre");
		String desc = request.getParameter("desc");
		int descuento = Integer.valueOf(request.getParameter("descuento"));
		float precio = Float.valueOf(request.getParameter("precio"));
		boolean disponible = Boolean.valueOf(request.getParameter("disponible"));
		
		//Recuperar el producto a actualizar. 
		Producto p = ProductoServicio.buscarPorId(codigoProducto);
		
		//Actualizar. 
		int r = ProductoServicio.actualizarVentas(p, nombre, desc, precio, descuento, disponible);
		if(r == 1) resp.addProperty("exito", false);
		
		return new Gson().toJson(resp);
	}
	
	
//								########### Modificar stock ###########	
	
	@PostMapping(value = "/modificarStockProducto", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String modificarStockProducto(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		//Recuperar los valores.
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));
		int cantidad = Integer.valueOf(request.getParameter("cantidad"));
		boolean esSuma = Boolean.valueOf(request.getParameter("sumar"));
		
		//Recuperar el producto a actualizar. 
		Producto p = ProductoServicio.buscarPorId(codigoProducto);
		
		//Actualizar. 
		int r = ProductoServicio.actualizarExistencias(p, esSuma, cantidad);
		if(r == -1) resp.addProperty("exito", false);
		else resp.addProperty("cantidad", r);
		
		return new Gson().toJson(resp);
	}

	
//							########### Habilitar producto ###########	

	@PostMapping(value = "/habilitarProducto", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String habilitarProducto(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		//Recuperar los valores.
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));
		boolean disponible = Boolean.valueOf(request.getParameter("habilitar"));
		
		//Recuperar el producto a habilitar o deshabilitar
		Producto p = ProductoServicio.buscarPorId(codigoProducto);
		
		//Actualizar. 
		int r = ProductoServicio.habilitar(p, disponible);
		if(r == 1) resp.addProperty("exito", false);
		
		return new Gson().toJson(resp);
	}
	
	
//									 ########## Consultar ##########
	
    //Obtencion de los productos de una empresa específica. 
	@PostMapping(value="/obtenerProductos", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String obtenerProductos(){
		JsonArray productosJ = new JsonArray();
		
		//Recupera la empresa para la que trabaja el empleado. 		
		int codigoEmpresa = (int)request.getSession().getAttribute("idEmpresa");		
		List<Producto> productos = ProductoServicio.obtenerProductos(codigoEmpresa, true);	
		
		//Crea la respuesta (en formato para mostrar los datos y almacenar otros como referencia. 
		for(Producto producto : productos){			
			productosJ.add(obtenerProducto(producto));
		}
		
		return new Gson().toJson(productosJ);		
	}
	
//Tranforma un objeto producto JAVA en un objeto JSON.
	private JsonObject obtenerProducto(Producto producto){
		JsonObject productoJ = new JsonObject();
		Categoria categoria = CategoriaServicio.buscarPorId(producto.getCodigoCategoria());
		Departamento departamento = DepartamentoServicio.buscarPorId(producto.getCodigoDepartamento());			
		
		//Datos a mostrar. 
		productoJ.addProperty("nombre", producto.getNombre());
		productoJ.addProperty("departamento", departamento.getNombre());
		productoJ.addProperty("categoria", categoria.getNombre());
		productoJ.addProperty("cantidad", producto.getExistencias());
		productoJ.addProperty("precio", producto.getPrecio());
		productoJ.addProperty("descuento", producto.getDescuento());
		productoJ.addProperty("disponible", producto.isDisponible());
		productoJ.addProperty("desc", producto.getDescripcion());
		
		//Datos a almacenar como referencia. 
		productoJ.addProperty("id", producto.getCodigo());
		productoJ.addProperty("urlImg", producto.getImg(request.getServerName()));
		productoJ.addProperty("idCategoria", producto.getCodigoCategoria());
		productoJ.addProperty("idDepartamento", producto.getCodigoDepartamento());
		
		//Agregar un arreglo de detalles (en pares id, valor)
		JsonArray detallesJ = new JsonArray();
		for(DetalleProducto detalle : DetalleProductoServicio.obtenerDetallesProducto(producto.getCodigo())){
			JsonObject detalleJ = new JsonObject();
			
			detalleJ.addProperty("id", detalle.getCodigoDetalle());
			detalleJ.addProperty("valor", detalle.getValor());
			
			detallesJ.add(detalleJ);
		}		
		productoJ.add("detalles", detallesJ);
		
		return productoJ;
	}
}
