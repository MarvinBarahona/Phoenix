package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import servicio.UbicacionServicio;

@Controller
public class PruebaController {
	
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		
		UbicacionServicio.guardar("El Salvador", "San Salvador", "Mejicanos", "1234");
		
		return new ModelAndView("welcome", "message", UbicacionServicio.conteoUbicaciones());
	}
}
