package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.appengine.repackaged.com.google.gson.Gson;

import servicio.UbicacionServicio;
import servicio.UsuarioServicio;
import usuarios.Ubicacion;
import usuarios.Usuario;

@Controller
@EnableWebMvc
public class LoginController {
	
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value = "/id/{number}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public @ResponseBody Usuario findUsuario(@PathVariable int number){
	    Usuario result = UsuarioServicio.findById(number);
	    return result;
	}
	
	@RequestMapping( value="/login", method = RequestMethod.POST, headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String login(){
		
		String prueba = request.getParameter("email");
		
		int i = Integer.parseInt(prueba);
		
		return new Gson().toJson(UbicacionServicio.buscarUbicacion(i));
	}
}
