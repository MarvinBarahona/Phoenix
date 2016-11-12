package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import servicio.EmpleadoServicio;
import servicio.UsuarioServicio;
import usuarios.Empleado;
import usuarios.TipoUsuario;
import usuarios.Usuario;
import util.EmailAPI;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
@EnableWebMvc
public class LoginController {
	
	//Para recuperar parámetros.
	@Autowired private HttpServletRequest request;
		
	@PostMapping(value="/login", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String login(){
		
		JsonObject resp = new JsonObject();
		HttpSession session = request.getSession();
		
		//Recupera los parámetros enviados. 
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Recupera el usuario
		Usuario user = UsuarioServicio.validar(email, password);
		
		//Arma la respuesta.
		if(user == null){
			resp.addProperty("msg", "fracaso");
		}
		else{
			resp.addProperty("msg", "exito");
			session.setAttribute("correo", user.getCorreo());
			
			//Si es exito, devuelve el tipo de usuario.
			resp.addProperty("tipoUsuario", user.getTipoUsuario().toString());
			
			if(user.getTipoUsuario().equals(TipoUsuario.empleado)){
				//Si es empleado, devuelve el tipo de empleado.
				Empleado emp = EmpleadoServicio.buscarPorId(user.getCodigo());
				resp.addProperty("tipoEmpleado", emp.getTipoEmpleado().toString());
				session.setAttribute("tipo", emp.getTipoEmpleado().toString());
			}
			else{
				session.setAttribute("tipo", "cliente");
			}
		}
		
		return new Gson().toJson(resp);
	}
	
	//Logout: quita el atributo de la sesión. 
	@PostMapping(value="/logout")
	public void logout(){
		HttpSession session = request.getSession();
		session.removeAttribute("correo");
	}
	
	//Para mandar el correo de "contaseña olvidada". 
	@PostMapping(value="/recuperarContra", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String recuperarContra(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", false);
		
		String correo = request.getParameter("correo");
		Usuario user = UsuarioServicio.buscarPorCorreo(correo);
		if(user != null){
			EmailAPI.enviarCorreoRecuperacion(user);
			resp.addProperty("exito", true);
		}
		
		return new Gson().toJson(resp);
	}
}
