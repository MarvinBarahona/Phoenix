package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controlador2 {
	@RequestMapping("/pruebadireccion")
	public ModelAndView helloWorld2(){
		return new ModelAndView("bienvenido");
	}
}
