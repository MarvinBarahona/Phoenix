package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import servicio.EmpleadoServicio;
import usuarios.Empleado;
import usuarios.Empresa;
@Controller
public class RedirectController {
	@Autowired private HttpServletRequest request;
	
	@RequestMapping("/product_gi")
	public ModelAndView viewProduct_gi() {
		//Recupera el empleado (como un usuario)
		String email = request.getParameter("email");
		Empleado emp = EmpleadoServicio.buscarPorCorreo(email);
		
		
		ModelAndView model = new ModelAndView("productManagement_gi");
		//Asigna el nombre al campo correspondiente en la página.
		model.addObject("nombre", emp.getNombre() + " " + emp.getApellido());
		
		return model;
	}
	
	@RequestMapping("/product_gv")
	public ModelAndView viewProduct_gv(){
		//Recupera el empleado (como un usuario)
		String email = request.getParameter("email");
		ModelAndView model = new ModelAndView("productManagement_gv");
		
		
		Empleado emp = EmpleadoServicio.buscarPorCorreo(email);
		
		if(emp != null){
			//Asigna el nombre al campo correspondiente en la página.
			model.addObject("nombre", emp.getNombre() + " " + emp.getApellido());
			Empresa e = emp.getEmpresa();
			model.addObject("imagenEmpresa", e.getImg());
			model.addObject("nombreEmpresa",e.getNombre());
		}
		
		return model;
	}
}
