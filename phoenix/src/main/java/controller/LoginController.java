package controller;

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
	
	@RequestMapping(value = "/id/{number}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public @ResponseBody Usuario findUsuario(@PathVariable int number){
	    Usuario result = UsuarioServicio.findById(number);
	    return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, headers="Accept=*/*", value="/login", produces="application/json")	
	public @ResponseBody String login(){
		String resp;
		Ubicacion u = UbicacionServicio.buscarUbicacion(1);
		
		
		
		resp = u.getCiudad();		
		
		resp += UbicacionServicio.conteoUbicaciones();	
		
		UbicacionServicio.guardar("Prueba", "Prueba", "Prueba", "Prueba");
		
		return new Gson().toJson(resp);
	}
}
