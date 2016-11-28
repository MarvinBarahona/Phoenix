package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import productos.Categoria;
import productos.Departamento;
import productos.DetalleCategoria;
import productos.DetalleProducto;
import productos.Producto;
import servicio.CategoriaServicio;
import servicio.DepartamentoServicio;
import servicio.DetalleCategoriaServicio;
import servicio.DetalleProductoServicio;
import servicio.EmpresaServicio;
import servicio.ProductoServicio;
import usuarios.Empresa;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
public class ComprasController {
	@Autowired private HttpServletRequest request;
	
	//Obtiene las imagenes de las empresas para ponerlas en el index. 
	@PostMapping(value="/obtenerImgEmpresas",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerImgEmpresas(){
		JsonArray empresasJ = new JsonArray();
		
		for(Empresa empresa : EmpresaServicio.obtenerEmpresas()){
			JsonObject empresaJ = new JsonObject();
			
			empresaJ.addProperty("id", empresa.getCodigo());
			empresaJ.addProperty("urlImg", empresa.getImg(request.getServerName()));
			
			empresasJ.add(empresaJ);
		}
		
		return new Gson().toJson(empresasJ);
	}
	
	//Obtiene las imagenes de las empresas para ponerlas en el index. 
	@PostMapping(value="/obtenerProducto",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerProducto(){
		JsonObject productoJ = new JsonObject();
		
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));		
		Producto producto = ProductoServicio.buscarPorId(codigoProducto);
		
		productoJ.addProperty("urlImg", producto.getImg(request.getServerName()));
		productoJ.addProperty("precio", producto.getPrecio());
		productoJ.addProperty("descuento", producto.getDescuento());
		
		JsonArray detallesJ = new JsonArray();
		for(DetalleProducto detalleP : DetalleProductoServicio.obtenerDetallesProducto(producto.getCodigo())){
			JsonObject detalleJ = new JsonObject();
			
			DetalleCategoria detalleC = DetalleCategoriaServicio.buscarPorId(detalleP.getCodigoDetalle());
			detalleJ.addProperty("nombre", detalleC.getNombre());
			detalleJ.addProperty("valor", detalleP.getValor());
			
			detallesJ.add(detalleJ);
		}
		productoJ.add("detalles", detallesJ);
		
		productoJ.addProperty("desc", producto.getDescripcion());
		
		return new Gson().toJson(productoJ);
	}
	
	
	//Obtiene las imagenes de las empresas para ponerlas en el index. 
	@PostMapping(value="/obtenerProdYCate",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerProdYCate(){
		JsonObject resp = new JsonObject();
		
		//Productos.		
		int codigoEmpresa = Integer.valueOf((String)request.getSession().getAttribute("idEmpresa"));
		List<Producto> productos = ProductoServicio.obtenerProductos(codigoEmpresa, false);
		
		JsonArray productosJ = new JsonArray();
		for(Producto producto : productos){			
			JsonObject productoJ = new JsonObject();
			Departamento departamento = DepartamentoServicio.buscarPorId(producto.getCodigoDepartamento());
			Categoria categoria = CategoriaServicio.buscarPorId(producto.getCodigoCategoria());
			
			productoJ.addProperty("id", producto.getCodigo());
			productoJ.addProperty("urlImg", producto.getImg(request.getServerName()));
			productoJ.addProperty("descuento", producto.getDescuento());
			productoJ.addProperty("precio", producto.getPrecio());
			productoJ.addProperty("nombre", producto.getNombre());
			productoJ.addProperty("departamento", departamento.getNombre());
			productoJ.addProperty("categoria", categoria.getNombre());
			
			productosJ.add(productoJ);
		}		
		resp.add("productos", productosJ);
		
		
		//Categorización. 
		JsonArray departamentosJ = new JsonArray();		
		for(Departamento departamento : DepartamentoServicio.obtenerDepartamentos()){
			if(!departamento.getNombre().matches("n/a")){
				JsonObject departamentoJ = new JsonObject();			
				departamentoJ.addProperty("nombre", departamento.getNombre());
				
				JsonArray categoriasJ = new JsonArray();
				for(Categoria categoria : CategoriaServicio.obtenerCategorias(departamento.getCodigo())){
					if(!categoria.getNombre().matches("n/a")){
						JsonObject categoriaJ = new JsonObject();
						
						categoriaJ.addProperty("nombre", categoria.getNombre());
						
						categoriasJ.add(categoriaJ);
					}					
				}
				departamentoJ.add("categorias", categoriasJ);
				
				departamentosJ.add(departamentoJ);
			}			
		}		
		resp.add("departamentos", departamentosJ);
		
		
		return new Gson().toJson(resp);
	}
}
