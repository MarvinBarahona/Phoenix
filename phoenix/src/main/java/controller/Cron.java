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
		//Obtiene un model, pero bien podria no devolver nada útil. La respuesta no se usa para nada. 
		ModelAndView model = new ModelAndView("login");

		//Consulta programada para que no se cierre la conexión a la base de datos. La respuesta debe tener un status entre 200 y 290. 
		UsuarioServicio.buscarPorId(1);
		response.setStatus(200);
		
		return model;
	}
}
