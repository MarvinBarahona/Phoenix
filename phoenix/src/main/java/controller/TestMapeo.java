package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.EmpresaServicio;
import servicio.UbicacionServicio;
import servicio.UsuarioServicio;
import usuarios.Cliente;
import usuarios.Empleado;
import usuarios.Empresa;
import usuarios.Ubicacion;
import usuarios.Usuario;

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
	
	@RequestMapping( value="/testAjax", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String testAjax(){
		Object resp = null;
		
		String selec = request.getParameter("selection");
		String idString = request.getParameter("id");
		int id;
		
		try{
					
			switch(selec){
			case "0":
				id = Integer.parseInt(idString);
				Ubicacion ubi = UbicacionServicio.buscarPorId(id);
				resp = ubi;
				break;
			case "1":
				id = Integer.parseInt(idString);
				Usuario u1 = UsuarioServicio.buscarPorId(id);
				resp = u1;
				break;
			case "2":
				id = Integer.parseInt(idString);
				Usuario u2 = (Usuario)UsuarioServicio.buscarPorId(id);
				resp = u2;
				break;
			case "3":
				Empleado emp1 = (Empleado)UsuarioServicio.buscarPorId(1);
				resp = emp1;
				break;
			case "4":
				Cliente c1 = (Cliente)UsuarioServicio.buscarPorId(4);
				resp = c1;
				break;
			case "5":
				Empleado emp2 = EmpleadoServicio.buscarPorId(1);
				resp = emp2;
				break;
			case "6":
				Cliente c2 = ClienteServicio.buscarPorId(4);
				resp = c2;
				break;
			case "7": 
				id = Integer.parseInt(idString);
				Empresa e = EmpresaServicio.buscarPorId(id);
				resp = e.getProductos();
				break;
			default:
				resp = "No implementado!";
				break;				
			}
		}catch(NumberFormatException e){
			resp = "Ingrese un número!";
		}
		
		if(resp == null){
			resp = "Nada encontrado";
		}
		
		return new Gson().toJson(resp);
	}
}
