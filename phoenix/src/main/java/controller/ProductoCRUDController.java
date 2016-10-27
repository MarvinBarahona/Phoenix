package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class ProductoCRUDController {
	@RequestMapping(value = "/productManagement_gi", method = RequestMethod.GET)
	public ModelAndView getProductManagement_gi(){
		ModelAndView model = new ModelAndView("productManagement_gi");
		return model;
	}
	
	@RequestMapping(value="/guardar_producto" ,method=RequestMethod.POST)
	public ModelAndView guardarProducto(@RequestParam("nombre_producto")String nombre,@RequestParam("descripcion_producto") String descripcion
			, @RequestParam("existencia") double existencia){
		ModelAndView mv = new ModelAndView("guardarEmpresa");
		mv.addObject("msg","Detalles = " + nombre + " desciprcion: " + descripcion);
		return mv;
	}
}
