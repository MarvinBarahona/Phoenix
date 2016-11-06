package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import productos.Categoria;
import productos.Departamento;
import servicio.CategoriaServicio;
import servicio.ClienteServicio;
import servicio.DepartamentoServicio;
import servicio.DetalleServicio;
import servicio.EmpleadoServicio;
import servicio.EmpresaServicio;
import servicio.ProductoServicio;
import servicio.UbicacionServicio;
import servicio.UsuarioServicio;
import usuarios.Cliente;
import usuarios.Empleado;
import usuarios.Empresa;

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
	
	@RequestMapping( value="/testAjax", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String testAjax(){
		Object resp = null;
		
		String selec = request.getParameter("selection");
		String idString = request.getParameter("id");
		int id;
		
		Cliente c;
		Empleado empl;
		//Usuario u;
		Empresa empr;
		Departamento dep;
		Categoria cat;
		
		try{
					
			switch(selec){
			case "0":
				id = Integer.parseInt(idString);
				resp = UbicacionServicio.buscarPorId(id);
				break;
			case "1":
				id = Integer.parseInt(idString);
				resp = UsuarioServicio.buscarPorId(id);
				break;
			case "2":
				resp = UsuarioServicio.buscarPorCorreo(idString);
				break;
			case "3":
				id = Integer.parseInt(idString);
				resp = ClienteServicio.buscarPorId(id);
				break;
			case "4":
				resp = ClienteServicio.buscarPorCorreo(idString);
				break;
			case "5":
				id = Integer.parseInt(idString);
				resp = EmpleadoServicio.buscarPorId(id);
				break;
			case "6":
				resp = EmpleadoServicio.buscarPorCorreo(idString);
				break;
			case "7":
				id = Integer.parseInt(idString);
				c = ClienteServicio.buscarPorId(id);
				resp = c.getUsuario();
				break;
			case "8":
				id = Integer.parseInt(idString);
				empl = EmpleadoServicio.buscarPorId(id);
				resp = empl.getUsuario();
				break;
			case "9":
				id = Integer.parseInt(idString);
				resp = EmpresaServicio.buscarPorId(id);
				break;
			case "10":
				id = Integer.parseInt(idString);
				empl = EmpleadoServicio.buscarPorId(id);
				resp = empl.getEmpresa();
				break;
			case "11":
				id = Integer.parseInt(idString);
				empr = EmpresaServicio.buscarPorId(id);
				resp = empr.getGerenteGeneral();
				break;
			case "12":
				id = Integer.parseInt(idString);
				empr = EmpresaServicio.buscarPorId(id);
				resp = empr.getGerenteVentas();
				break;
			case "13":
				id = Integer.parseInt(idString);
				empr = EmpresaServicio.buscarPorId(id);
				resp = empr.getGerenteInventario();
				break;
			case "14":
				id = Integer.parseInt(idString);
				empr = EmpresaServicio.buscarPorId(id);
				resp = empr.getUbicacion();
				break;
			case "15":
				id = Integer.parseInt(idString);
				resp = DepartamentoServicio.buscarPorId(id);
				break;
			case "16":
				id = Integer.parseInt(idString);
				resp = CategoriaServicio.buscarPorId(id);
				break;
			case "17":
				id = Integer.parseInt(idString);
				resp = DetalleServicio.buscarPorId(id);
				break;
			case "18":
				id = Integer.parseInt(idString);
				dep = DepartamentoServicio.buscarPorId(id);
				resp = dep.getCategorias();
				break;
			case "19":
				id = Integer.parseInt(idString);
				cat = CategoriaServicio.buscarPorId(id);
				resp = cat.getDetalles();
				break;
			case "20":
				id = Integer.parseInt(idString);
				resp = ProductoServicio.buscarPorId(id);
				break;
			case "21":
				id = Integer.parseInt(idString);
				empr = EmpresaServicio.buscarPorId(id);
				resp = empr.getProductos(true);
				break;
			case "22":
				id = Integer.parseInt(idString);
				empr = EmpresaServicio.buscarPorId(id);
				resp = empr.getProductos(false);
				break;
			case "23":
				id = Integer.parseInt(idString);
				empr = EmpresaServicio.buscarPorId(id);
				resp = empr.getProductos(1, 1);
				break;
			case "24":
				id = Integer.parseInt(idString);
				empr = EmpresaServicio.buscarPorId(id);
				resp = empr.getProductos(1);
				break;
			default:
				resp = "No implementado!";
				break;				
			}
		}catch(NumberFormatException e){
			resp = "Ingrese un número!";
		}catch(NullPointerException e){
			resp = "Nada encontrado";
		}
		
		
		if(resp == null){
			resp = "Nada encontrado";
		}
		
		return new Gson().toJson(resp);
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
			resp = DetalleServicio.obtenerDetalles();
			break;
		case "8":
			resp = ProductoServicio.obtenerProductos();
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
