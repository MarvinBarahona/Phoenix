package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.UsuarioServicio;
import usuarios.Cliente;
import usuarios.Empleado;
import usuarios.TipoUsuario;
import usuarios.Usuario;
import util.EmailSender;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
@EnableWebMvc
public class LoginController {
	
	//Para recuperar parámetros.
	@Autowired private HttpServletRequest request;
		
	@PostMapping(value="/login", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String login(){
		
		//Recupera los parámetros enviados. 
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Recupera el usuario
		Usuario user = UsuarioServicio.validar(email, password);
		
		return new Gson().toJson(obtenerRespuesta(user));
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
			EmailSender.enviarCorreoRecuperacion(user);
			resp.addProperty("exito", true);
		}
		
		return new Gson().toJson(resp);
	}
	
	//Para reestablecer la contraseña.
	@PostMapping(value="/cambiarContra", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String cambiarContra(){
		JsonObject resp;
		
		String email = request.getParameter("correo");
		String password = request.getParameter("contra");
		
		Usuario u = UsuarioServicio.buscarPorCorreo(email);
		int r = UsuarioServicio.actualizarContra(u, password);
		
		if(r == 1){
			resp = new JsonObject();
			resp.addProperty("msg", "error");
		}
		else{
			resp = obtenerRespuesta(u);
		}
		
		return new Gson().toJson(resp);
	}
	
	//Para mandar correo de "confirmar cuenta"
	@PostMapping(value="/enviarConfirmacion", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String enviarConfirmacion(){
		JsonObject resp = new JsonObject();
		resp.addProperty("msg", "fallo");
		
		String correo = request.getParameter("correo");
		
		if(UsuarioServicio.buscarPorCorreo(correo) != null){
			resp.addProperty("msg", "duplicado");
		}
		else{
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			
			if(correo != null && nombre != null && apellido != null){
				EmailSender.enviarCorreoConfirmacion(correo, nombre, apellido);
				resp.addProperty("msg", "enviado");
			}
		}		
		return new Gson().toJson(resp);
	}
	
	//Para confirmar la cuenta.
	@PostMapping(value="/confirmarCuenta", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String confirmarCuenta(){
		JsonObject resp = new JsonObject();
		HttpSession session = request.getSession();
		
		String correo = request.getParameter("correo");
		String contra = request.getParameter("contra");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String pais = request.getParameter("pais");
		String ciudad = request.getParameter("ciudad");
		String direccion = request.getParameter("direccion");
		String zip = request.getParameter("zip");
		
		
		try {
			Cliente c = ClienteServicio.crear(correo, contra, nombre, apellido, pais, ciudad, direccion, zip);
			resp.addProperty("exito", true);
			session.setAttribute("correo", c.getCorreo());
			session.setAttribute("tipo", "cliente");
			
		} catch (Exception e) {
			resp.addProperty("exito", false);
		}
		
		return new Gson().toJson(resp);
	}
	
	private JsonObject obtenerRespuesta(Usuario user){
		JsonObject resp = new JsonObject();
		HttpSession session = request.getSession();
		
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
		
		return resp;
	}
}
