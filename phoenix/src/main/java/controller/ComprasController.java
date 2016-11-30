package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pedidos.LineaPedido;
import pedidos.Pedido;
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
	
	//Obtiene un producto por su id y lo coloca en pantalla.  
	@PostMapping(value="/obtenerProducto",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerProducto(){
		JsonObject productoJ = new JsonObject();
		
		//Recuperar el producto.
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));		
		Producto producto = ProductoServicio.buscarPorId(codigoProducto);
		
		//Convertirlo a objeto JSON. 
		productoJ.addProperty("urlImg", producto.getImg(request.getServerName()));
		productoJ.addProperty("precio", producto.getPrecio());
		productoJ.addProperty("descuento", producto.getDescuento());
		
		//Agregar al producto sus detalles. 
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
	
	
	//Obtiene los productos y categorias para agregarse al catálogo. 
	@PostMapping(value="/obtenerProdYCate",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerProdYCate(){
		JsonObject resp = new JsonObject();
		
		//Productos.	
		
		//Recuperar los productos de la empresa actual. 
		int codigoEmpresa = Integer.valueOf((String)request.getSession().getAttribute("idEmpresa"));
		List<Producto> productos = ProductoServicio.obtenerProductos(codigoEmpresa, false);
		
		JsonArray productosJ = new JsonArray();
		for(Producto producto : productos){			
			JsonObject productoJ = new JsonObject();
			
			//Recuperar el departamento y categoría del producto. 
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
			
			//Agregar el departamento solo si no es "n/a"
			if(!departamento.getNombre().matches("n/a")){				
				JsonObject departamentoJ = new JsonObject();			
				departamentoJ.addProperty("nombre", departamento.getNombre());
				
				JsonArray categoriasJ = new JsonArray();
				for(Categoria categoria : CategoriaServicio.obtenerCategorias(departamento.getCodigo())){
					
					//Agregar la categoria solo si no es "n/a"
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
	
	
	//Agregar un producto al carrito.
	@PostMapping(value="/agregarProductoCarrito",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String agregarProductoCarrito(){
		
		HttpSession session = request.getSession();		
		JsonObject resp = new JsonObject();			//Objeto queda vacio al final. 
		
		//Recupera la información necesaria. 
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));		
		Producto producto = ProductoServicio.buscarPorId(codigoProducto);		
		int codigoEmpresa = Integer.valueOf((String)request.getSession().getAttribute("idEmpresa"));		
		Pedido carrito = (Pedido)session.getAttribute("carrito");
		
		//Crear un carrito si no hay, y agregar el producto solo si no se ha agregado. 
		if(carrito == null){
			carrito = new Pedido(codigoEmpresa);
		}
		
		boolean agregado = false;
		for(LineaPedido linea : carrito.getLineasPedido()){
			if(linea.getCodigoProducto() == codigoProducto){
				agregado = true;
				break;
			}
		}		
		if(!agregado) carrito.agregarLineaPedido(producto);
		
		//Sobreescribir el objeto "carrito" en la sesión. 
		session.setAttribute("carrito", carrito);
		
		return new Gson().toJson(resp);
	}
	
	//Recuperar el carrito de compras. 
	@PostMapping(value="/obtenerCarrito",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerCarrito(){
		
		HttpSession session = request.getSession();		
		JsonObject carritoJ = new JsonObject();		
		Pedido carrito = (Pedido)session.getAttribute("carrito");
		
		carritoJ.addProperty("isEmpty", true);		//El carrito está vacio si no existe o si no tiene lineas. 
		if(carrito != null && carrito.getCantidadProductos() != 0){
			carritoJ.addProperty("isEmpty", false);
			 
			//Recupera el carrito como una lista de sus lineas. 
			JsonArray lineasPedidoJ = new JsonArray();
			for(LineaPedido linea : carrito.getLineasPedido()){
				JsonObject lineaJ = new JsonObject();
				Producto producto = ProductoServicio.buscarPorId(linea.getCodigoProducto());
				 
				lineaJ.addProperty("idProducto", linea.getCodigoProducto());
				lineaJ.addProperty("producto", producto.getNombre());
				lineaJ.addProperty("precio", linea.getPrecioVendido());
				lineaJ.addProperty("cantidad", linea.getCantidad());
				lineaJ.addProperty("existencias", producto.getExistencias());
				lineaJ.addProperty("subtotal", linea.getSubtotal());
				 
				lineasPedidoJ.add(lineaJ);
			}
			 
			carritoJ.add("lineas", lineasPedidoJ);
		}
		
		return new Gson().toJson(carritoJ);
	}
	
	//Eliminar un producto del carrito. 
	@PostMapping(value="/eliminarLineaCarrito",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String eliminarLineaCarrito(){
		
		//Recuperar los datos necesarios. 
		HttpSession session = request.getSession();		
		JsonObject resp = new JsonObject();		
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));
		Pedido carrito = (Pedido)session.getAttribute("carrito");
		
		//Elimina la línea de pedido.
		carrito.eliminarLineaPedido(codigoProducto);
		
		//Recuperar la información actual del carrito para actualizar el header. 
		resp.addProperty("total", carrito.getTotal());
		resp.addProperty("cantidad", carrito.getCantidadProductos());
		
		//Sobreescribir el objeto "carrito" en la sesión. 
		session.setAttribute("carrito", carrito);
		
		return new Gson().toJson(resp);
	}
	
	//Modificar una línea de pedido. 
	@PostMapping(value="/modificarLineaCarrito",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String modificarLineaCarrito(){
		
		//Recuperar la información. 
		HttpSession session = request.getSession();		
		JsonObject resp = new JsonObject();		
		int codigoProducto = Integer.valueOf(request.getParameter("idProducto"));
		int cantidad = Integer.valueOf(request.getParameter("cantidad"));
		Pedido carrito = (Pedido)session.getAttribute("carrito");
		
		//Modificar el carrito. 
		double subtotal = carrito.modificarLineaPedido(codigoProducto, cantidad);
		
		//Recupera el nuevo total para colocarlo en el header. 
		resp.addProperty("total", carrito.getTotal());
		resp.addProperty("subtotal", subtotal);
		
		//Sobreescribir el objeto "carrito" en la sesión. 
		session.setAttribute("carrito", carrito);
		
		return new Gson().toJson(resp);
	}
	
	//Limpiar el carrito. 
	@PostMapping(value="/limpiarCarrito", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String limpiarCarrito(){
		JsonObject resp = new JsonObject();	
		
		HttpSession session = request.getSession();
		session.removeAttribute("carrito");
		
		return new Gson().toJson(resp);
	}
}
