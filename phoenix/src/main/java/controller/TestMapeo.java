package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import servicio.CategoriaServicio;
import servicio.ClienteServicio;
import servicio.DepartamentoServicio;
import servicio.DetalleProductoServicio;
import servicio.DetalleCategoriaServicio;
import servicio.EmpleadoServicio;
import servicio.EmpresaServicio;
import servicio.LineaPedidoServicio;
import servicio.PedidoServicio;
import servicio.ProductoServicio;
import servicio.UbicacionServicio;
import servicio.UsuarioServicio;
import servicio.ValorDetalleCategoriaServicio;

import com.google.appengine.repackaged.com.google.gson.Gson;

@Controller
@EnableWebMvc
public class TestMapeo {
	
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value="/test")
	public ModelAndView test(){
		ModelAndView model = new ModelAndView("test");
		return model;
	}
	
	@RequestMapping(value="testJSP")
	public ModelAndView testJSP(){
		String jsp = request.getParameter("sitio");
		ModelAndView model = new ModelAndView(jsp);
		return model;
	}
	
	@RequestMapping( value="/testAjax2", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String testAjax2(){
		Object resp = null;
		
		String selec = request.getParameter("selection");
		
					
		switch(selec){
		case "0":
			resp = UbicacionServicio.obtenerUbicaciones();
			break;
		case "1":
			resp = UsuarioServicio.obtenerUsuarios();
			break;
		case "2":
			resp = ClienteServicio.obtenerClientes();
			break;
		case "3":
			resp = EmpleadoServicio.obtenerEmpleados();
			break;
		case "4":
			resp = EmpresaServicio.obtenerEmpresas();
			break;
		case "5":
			resp = DepartamentoServicio.obtenerDepartamentos();
			break;
		case "6":
			resp = CategoriaServicio.obtenerCategorias();
			break;
		case "7":
			resp = DetalleCategoriaServicio.obtenerDetallesCategoria();
			break;
		case "8":
			resp = ProductoServicio.obtenerProductos();
			break;
		case "9":
			resp = DetalleProductoServicio.obtenerDetallesProducto();
			break;
		case "10":
			resp = ValorDetalleCategoriaServicio.obtenerDetalles();
			break;
		case "11":
			resp = PedidoServicio.obtenerPedidos();
			break;
		case "12":
			resp = LineaPedidoServicio.obtenerLineasPedido();
			break;
		default:
			resp = "No implementado!";
			break;				
		}
		
		if(resp == null){
			resp = "Nada encontrado";
		}
		
		return new Gson().toJson(resp);
	}
}
