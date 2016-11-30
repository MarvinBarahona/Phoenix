package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pedidos.LineaPedido;
import pedidos.Pedido;
import productos.Departamento;
import productos.Producto;
import servicio.DepartamentoServicio;
import servicio.LineaPedidoServicio;
import servicio.PedidoServicio;
import servicio.ProductoServicio;
import servicio.UsuarioServicio;
import usuarios.Usuario;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
public class VentasController {
	@Autowired private HttpServletRequest request;
	
	//Obtiene los datos de las ventas para graficar.  
	@PostMapping(value="/obtenerDatosVentas",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerDatosVentas(){
		JsonObject resp = new JsonObject();
		
		//Recuperar los productos y los pedidos de la empresa actual; y los departamentos. 
		int codigoEmpresa = (int) request.getSession().getAttribute("idEmpresa");
		List<Producto> productos = ProductoServicio.obtenerProductos(codigoEmpresa, true);
		List<Pedido> pedidos = PedidoServicio.obtenerPedidos(codigoEmpresa);
		List<Departamento> departamentos = DepartamentoServicio.obtenerDepartamentos();
		
		//Crear listas para almacenar los resultados. 
		ArrayList<Integer> idProductos = new ArrayList<Integer>();
		ArrayList<Integer> vendidos = new ArrayList<Integer>();
		
		ArrayList<Integer> idDepartamentos = new ArrayList<Integer>();
		ArrayList<Integer> vendidosD = new ArrayList<Integer>();
		
		//Inicializar las listas. 
		for(int i = 0; i<productos.size(); i++){
			idProductos.add(productos.get(i).getCodigo());
			vendidos.add(0);
		}
		
		for(int i = 0; i<departamentos.size(); i++){
			idDepartamentos.add(departamentos.get(i).getCodigo());
			vendidosD.add(0);
		}
		
		//Por cada pedido, crear un objeto JSON de pedido y construir las listas de productos vendidos individualmente. 
		JsonArray pedidosJ = new JsonArray();
		for(Pedido pedido : pedidos){
			//Lineas del pedido
			List<LineaPedido> lineas = LineaPedidoServicio.obtenerLineasPedido(pedido.getCodigo()); 
			
			//Llenar la lista de productos vendidos individualmente. 
			for(LineaPedido linea : lineas){
				int index = idProductos.indexOf(linea.getCodigoProducto());
				int cantActual = vendidos.get(index);
				vendidos.set(index, cantActual + linea.getCantidad());
			}
			
			//Crear el objeto JSON de pedido y agregarlo al arreglo de pedidos. 
			JsonObject pedidoJ = new JsonObject();
			Usuario usuario = UsuarioServicio.buscarPorId(pedido.getCodigoCliente());
			
			pedidoJ.addProperty("cliente", usuario.getNombre() + " " + usuario.getApellido());
			pedidoJ.addProperty("cantidad", lineas.size());
			pedidoJ.addProperty("total", pedido.getTotal());
			
			pedidosJ.add(pedidoJ);
		}
		resp.add("pedidos", pedidosJ);
		
		
		//Por cada producto, se crea un objeto JSON de producto y y se llena las listas de productos vendidos por departamento.
		JsonArray productosJ = new JsonArray();
		for(Producto producto : productos){
			JsonObject productoJ = new JsonObject();
			int index;
			int cantProducto;
			int cantActual;
			
			//Crear objeto JSON.
			productoJ.addProperty("nombre", producto.getNombre());
			index = idProductos.indexOf(producto.getCodigo());
			cantProducto = vendidos.get(index);
			productoJ.addProperty("cantVendido", cantProducto);
			
			productosJ.add(productoJ);
			
			//Llenar lista de productos vendidos por departamento.
			index = idDepartamentos.indexOf(producto.getCodigoDepartamento());
			cantActual = vendidosD.get(index);
			vendidosD.set(index, cantActual + cantProducto);
		}
		resp.add("productos", productosJ);
		
		
		//Por cada departamento crear un objeto JSON.
		JsonArray departamentosJ = new JsonArray();
		for(Departamento departamento : departamentos){
			JsonObject departamentoJ = new JsonObject();
			
			departamentoJ.addProperty("nombre", departamento.getNombre());
			int index = idDepartamentos.indexOf(departamento.getCodigo());
			departamentoJ.addProperty("cantVendido", vendidosD.get(index));

			departamentosJ.add(departamentoJ);
		}
		resp.add("departamentos", departamentosJ);
		
		return new Gson().toJson(resp);
	}
}
