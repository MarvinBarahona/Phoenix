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
	
	//Inicio y cierre de sesión de usuario.********************************************************************
	
	@PostMapping(value="/login", headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String login(){
		JsonObject resp;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		//Para el webmaster se crea una cuenta y contraseñas definidas.
		if(!email.equals("webmaster@phoenix.com")){
			
			//Código cuando el correo ingresado no es el del webmaster.
			Usuario user = UsuarioServicio.validar(email, password);
			resp = obtenerRespuesta(user);
		}
		else{
			
			//Si el correo ingresado corresponde al web master. 
			if(password.equals("wm_phoenix3214")){
				resp = new JsonObject();
				resp.addProperty("msg", "exito");
				resp.addProperty("tipoUsuario", "webmaster");
				
				HttpSession session = request.getSession();
				session.setAttribute("correo", "webmaster@phoenix.com");
				session.setAttribute("tipo", "webmaster");
				session.setAttribute("index", "/wm_create.html");
			}
			else{
				resp = obtenerRespuesta(null);
			}
		}		
		return new Gson().toJson(resp);
	}
	
	//Logout: quita el atributo de la sesión. 
	@PostMapping(value="/logout", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String logout(){
		HttpSession session = request.getSession();
		session.removeAttribute("correo");
		session.removeAttribute("tipo");
		session.removeAttribute("index");
		return new Gson().toJson("logout");
	}
	
	//Contraseña olvidada ************************************************************************************
	
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
	
	//Creación de cliente *****************************************************************************************
	
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
		
		String correo = request.getParameter("correo");
		String contra = request.getParameter("contra");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String pais = request.getParameter("pais");
		String ciudad = request.getParameter("ciudad");
		String direccion = request.getParameter("direccion");
		String zip = request.getParameter("zip");
		
		
		try {
			//Crea el objeto cliente y lo guarda en la base. 
			Cliente c = ClienteServicio.crear(correo, contra, nombre, apellido, pais, ciudad, direccion, zip);
			resp.addProperty("exito", true);
			
			//Guarda los datos en la sesión.
			setSessionsAtributes(c.getUsuario());
			
		} catch (Exception e) {
			resp.addProperty("exito", false);
		}
		
		return new Gson().toJson(resp);
	}

	
	//Otros métodos *****************************************************************************************
	
	//Obtiene una respuesta JSON para el usuario validado. 
	private JsonObject obtenerRespuesta(Usuario user){
		JsonObject resp = new JsonObject();
		
		//Arma la respuesta.
		if(user == null){
			resp.addProperty("msg", "fracaso");
		}
		else{
			setSessionsAtributes(user);
			
			resp.addProperty("msg", "exito");
			
			//Si es exito, devuelve el tipo de usuario.
			resp.addProperty("tipoUsuario", user.getTipoUsuario().toString());
			
			if(user.getTipoUsuario().equals(TipoUsuario.empleado)){
				//Si es empleado, devuelve el tipo de empleado.
				Empleado emp = EmpleadoServicio.buscarPorId(user.getCodigo());
				resp.addProperty("tipoEmpleado", emp.getTipoEmpleado().toString());
			}
		}
		
		return resp;
	}
	
	//Coloca los datos de sesión, dependiendo del usuario validado. 
	private void setSessionsAtributes(Usuario user) {
		HttpSession session = request.getSession();
		String tipo = "cliente";
		String index = "/";
		
		
		//Arma la respuesta.
		if(user != null){
			session.setAttribute("correo", user.getCorreo());
			
			
			if(user.getTipoUsuario().equals(TipoUsuario.empleado)){
				Empleado emp = EmpleadoServicio.buscarPorId(user.getCodigo());
				
				switch(emp.getTipoEmpleado()){
				case gerenteGeneral:
					tipo = "general";
					index = "/dashboard_gg.html";
					break;
				case gerenteInventario:
					tipo = "inventario";
					index = "/productManagement_gi.html";
					break;
				case gerenteVentas:
					tipo = "ventas";
					index = "/productManagement_gv.html";
					break;
				default:
					break;				
				}
			}
			
			session.setAttribute("tipo", tipo);
			session.setAttribute("index", index);
		}		
	}
}
