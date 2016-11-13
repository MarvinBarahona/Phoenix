package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import servicio.EmpleadoServicio;
import usuarios.Empleado;
import usuarios.Empresa;
import util.Encoder;
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
	
	@RequestMapping(value="/signIn")
	public ModelAndView signIn(){
		return new ModelAndView("signIn");
	}
	
	@RequestMapping(value="/confirmAccount")
	public ModelAndView confirmAccount(){
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		
		ModelAndView model = new ModelAndView("confirmAccount");
		//Recupera los datos codificados y los pone en el sitio. 
		model.addObject("correo", Encoder.decodificar(mail));
		model.addObject("nombre", Encoder.decodificar(name));
		model.addObject("apellido", Encoder.decodificar(lastname));
		
		return model;		
	}
	
	@RequestMapping(value="/recoverPassword")
	public ModelAndView recoverPassword(){
		return new ModelAndView("recoverPassword");
	}
	
	//Página para recuperar la contra. 
	@RequestMapping(value="/restorePassword", method=RequestMethod.GET)	
	public ModelAndView restorePassword(){
		String user = request.getParameter("user");
		//Decodifica el correo y lo pone en el sitio.
		String correo = Encoder.decodificar(user);		
		ModelAndView model = new ModelAndView("restorePassword");
		model.addObject("correo", correo);
		return model;
	}
	
	@RequestMapping("/product_gi")
	public ModelAndView viewProduct_gi() {
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
			model = new ModelAndView("productManagement_gi");	
			
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
