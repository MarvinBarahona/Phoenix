package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import servicio.EmpleadoServicio;
import servicio.UsuarioServicio;
import usuarios.Empleado;
import usuarios.TipoUsuario;
import usuarios.Usuario;

import com.google.appengine.repackaged.com.google.gson.Gson;

@Controller
@EnableWebMvc
public class LoginController {
	
	//Para recuperar parámetros.
	@Autowired private HttpServletRequest request;
		
	@PostMapping( value="/login", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String login(){
		//Ver clase "Respuesta.java"
		Respuesta resp = new Respuesta();
		
		//Recupera los parámetros enviados. 
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Recupera el usuario
		Usuario user = UsuarioServicio.validar(email, password);
		
		//Arma la respuesta.
		if(user == null){
			resp.setMsg("fracaso");
		}
		else{
			resp.setMsg("exito");
			//Si es exito, devuelve el tipo de usuario.
			resp.setTipoUsuario(user.getTipoUsuario().toString());
			
			//Si es empleado, devuelve el tipo de empleado.
			if(user.getTipoUsuario().equals(TipoUsuario.empleado)){
				Empleado emp = EmpleadoServicio.buscarPorId(user.getCodigo());
				resp.setTipoEmpleado(emp.getTipoEmpleado().toString());
			}
		}
		
		return new Gson().toJson(resp);
	}
	
	@RequestMapping(value="/loginFailed")
	public ModelAndView loginFailed(){
		ModelAndView model = new ModelAndView("login");
		
		//Manda mensaje del error y mantiene el correo. 
		model.addObject("msg", request.getParameter("msg"));
		model.addObject("correo", request.getParameter("email"));
		
		return model;
	}	
}
