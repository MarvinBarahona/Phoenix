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

@Controller
public class RedirectController {
	@Autowired private HttpServletRequest request;
	@Autowired private HttpServletResponse response;
	
	
	//Login********************************************************************************************
	@RequestMapping(value="/loginFailed")
	public ModelAndView loginFailed(){
		ModelAndView model = new ModelAndView("login");
		
		//Manda mensaje del error y mantiene el correo. 
		model.addObject("msg", request.getParameter("msg"));
		model.addObject("correo", request.getParameter("email"));
		
		return model;
	}
	
	//Para agregar un nuevo cliente *********************************************************************
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
	
	
	//Restaurar contraseña *******************************************************************************
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
	
	
	//Para el gerente general*****************************************************************************
	@RequestMapping("/dashboard_gg")
	public ModelAndView dashboard_gg(){
		ModelAndView model;
		
		model = validar("general");
		if(model == null){
			model = new ModelAndView("dashboard_gg");
			setHeaderData(model);
		}
		
		return model;
	}	
	
	@RequestMapping("/company")
	public ModelAndView company(){
		ModelAndView model;
		
		model = validar("general");
		if(model == null){
			model = new ModelAndView("company");
			setHeaderData(model);
		}
		
		return model;
	}
	
	@RequestMapping("/employees")
	public ModelAndView employees(){
		ModelAndView model;
		
		model = validar("general");
		if(model == null){
			model = new ModelAndView("employees");
			setHeaderData(model);
		}
		
		return model;
	}	
	
	
	//Para el gerente de inventario ***********************************************************************
	@RequestMapping("/productManagement_gi")
	public ModelAndView viewProduct_gi() {
		ModelAndView model;
		
		model = validar("inventario");
		
		if(model == null){			
			model = new ModelAndView("productManagement_gi");
			setHeaderData(model);
		}		
		
		return model;
	}
	
	@RequestMapping("/stocks")
	public ModelAndView stocks(){
		ModelAndView model;
		
		model = validar("inventario");
		
		if(model == null){
			model = new ModelAndView("stocks");
			setHeaderData(model);
		}		
		
		return model;
	}	
	
	
	//Para el gerente de ventas *****************************************************************************
	@RequestMapping("/productManagement_gv")
	public ModelAndView viewProduct_gv(){
		ModelAndView model;
	
		model = validar("ventas");
		
		if(model == null){
			model = new ModelAndView("productManagement_gv");
			setHeaderData(model);
		}		
		
		return model;
	}	
	
	@RequestMapping("/dashboard_gv")
	public ModelAndView dashboard_gv(){
		ModelAndView model;
		
		model = validar("ventas");
		
		if(model == null){
			model = new ModelAndView("dashboard_gv");
			setHeaderData(model);
		}		
		
		return model;
	}
	
	//Para el web master*********************************************************************
	@RequestMapping("/wm_create")
	public ModelAndView wm_create(){
		ModelAndView model = new ModelAndView("wm_create");
		return model;
	}
	
	@RequestMapping("/wm_adduser")
	public ModelAndView wm_adduser(){
		ModelAndView model = new ModelAndView("wm_adduser");
		return model;
	}
	
	
	
	//Función: validar. Añade seguridad al sitio (control de acceso) 0->fallo; 1->permtido
	public ModelAndView validar(String tipoEmpleado){
		ModelAndView model = null;
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("correo");
		String tipo = (String)session.getAttribute("tipo");
		
		if(email == null){
			model = new ModelAndView("login");
			model.addObject("msg", "Debe iniciar sesión para ingresar a este sitio!");
		}
		else if(tipo != tipoEmpleado){
			model = new ModelAndView("accessDenied");

			String index = (String)session.getAttribute("index");
			model.addObject("nextPage", index);
		}
		
		return model;
	}
	
	public void setHeaderData(ModelAndView model){
		//Recupera el empleado
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("correo");
		Empleado emp = EmpleadoServicio.buscarPorCorreo(email);
		
		if(emp != null){
			//Asigna el nombre al campo correspondiente en la página.
			model.addObject("nombreEmpleado", emp.getNombre() + " " + emp.getApellido());
			model.addObject("tipoEmpleado", emp.getTipoEmpleado().toString());
			Empresa e = emp.getEmpresa();
			model.addObject("imagenEmpresa", e.getImg(request.getServerName()));
			model.addObject("nombreEmpresa", e.getNombre());
		}
	}
}

