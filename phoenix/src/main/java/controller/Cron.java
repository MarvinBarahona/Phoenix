//Tarea programada, una consulta que se ejecuta cada 4 minutos (ver cron.xml)
//Devuelve status 200 cuando no encuentra error. 

package controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import servicio.UsuarioServicio;

@Controller
public class Cron {
	
	@Autowired HttpServletResponse response;
	
	@RequestMapping
	public ModelAndView cron(){
		ModelAndView model = new ModelAndView("login");

		UsuarioServicio.buscarPorId(1);
		response.setStatus(200);
		
		return model;
	}
}
