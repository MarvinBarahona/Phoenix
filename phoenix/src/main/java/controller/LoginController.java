package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.appengine.repackaged.com.google.gson.Gson;

import servicio.EmpresaServicio;
import servicio.empleadoServicio;
import servicio.usuarioServicio;
import usuarios.Empleado;
import usuarios.Empresa;
import usuarios.Usuario;
import controller.EmpresaCRUDController;

@Controller
@EnableWebMvc

public class LoginController {
	
	@RequestMapping(value = "/id/{number}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	public @ResponseBody Usuario findUsuario(@PathVariable int number){
	    Usuario result = usuarioServicio.findById(number);
	    return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, headers="Accept=*/*", value="/login", produces="application/json")	
	public @ResponseBody String login(){
		Empresa user = new Empresa();
		user = EmpresaServicio.consultar(1);
		return new Gson().toJson(user);
	}
}
