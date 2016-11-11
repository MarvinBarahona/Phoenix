package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import servicio.EmpleadoServicio;
import usuarios.Empleado;
import usuarios.Empresa;
import util.UploadURL;
@Controller
public class RedirectController {
	@Autowired private HttpServletRequest request;
	@Autowired private HttpServletResponse response;
	
	@RequestMapping(value="/loginFailed")
	public ModelAndView loginFailed(){
		ModelAndView model = new ModelAndView("login");
		
		//Manda mensaje del error y mantiene el correo. 
		model.addObject("msg", request.getParameter("msg"));
		model.addObject("correo", request.getParameter("email"));
		
		return model;
	}
	
	@RequestMapping(value="singIn")
	public ModelAndView singIn(){
		return new ModelAndView("singIn");
	}
	
	@RequestMapping(value="restorePassword")
	public ModelAndView restorePassword(){
		return new ModelAndView("restorePassword");
	}
	
	@RequestMapping(value="recoverPassword")
	public ModelAndView recoverPassword(){
		return new ModelAndView("recoverPassword");
	}
	
	@RequestMapping("/product_gi")
	public ModelAndView viewProduct_gi() {
		HttpSession session = request.getSession();
		
		//Recupera el empleado
		String email = session.getAttribute("correo").toString();
		Empleado emp = EmpleadoServicio.buscarPorCorreo(email);
		
		
		ModelAndView model = new ModelAndView("productManagement_gi");
		//Asigna el nombre al campo correspondiente en la página.
		if(emp != null){
			model.addObject("nombre", emp.getNombre() + " " + emp.getApellido());
		}		
		
		return model;
	}
	
	@RequestMapping("/product_gv")
	public ModelAndView viewProduct_gv(){
		ModelAndView model;
		HttpSession session = request.getSession();
		
		//Recupera el empleado
		String email = (String)session.getAttribute("correo");
		
		if(email == null){
			model = loginFailed();
			model.addObject("msg", "Debe iniciar sesión para ingresar a este sitio!");
		}
		else{
			Empleado emp = EmpleadoServicio.buscarPorCorreo(email);			
			model = new ModelAndView("productManagement_gv");	
			
			if(emp != null){
				//Asigna el nombre al campo correspondiente en la página.
				model.addObject("nombre", emp.getNombre() + " " + emp.getApellido());
				Empresa e = emp.getEmpresa();
				model.addObject("imagenEmpresa", e.getImg(request.getServerName()));
				model.addObject("nombreEmpresa", e.getNombre());
			}
			else{
				model.addObject("imagenEmpresa", UploadURL.getImageURL("", request.getServerName()));
			}
		}		
		
		return model;
	}	
}
