package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import servicio.UsuarioServicio;
import usuarios.Usuario;

import com.google.appengine.repackaged.com.google.gson.Gson;

@Controller
@EnableWebMvc
public class LoginController {
	
	@Autowired private HttpServletRequest request;
		
	@RequestMapping( value="/login", method = RequestMethod.POST, headers="Accept=*/*", produces="application/json")	
	public @ResponseBody String login(){
		String resp;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Usuario user = UsuarioServicio.buscarPorCorreo(email);
		
		if(user == null){
			resp = new String("noExiste");
		}
		else{
			int i = password.compareTo(user.getContra());
			if(i==0){
				resp = new String("exito");
			}
			else{
				resp = new String("fracaso");
			}
		}
		
		return new Gson().toJson(resp);
	}
}
