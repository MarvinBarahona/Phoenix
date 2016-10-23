package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PruebaController {
	
	@RequestMapping("/product_gi")
	public ModelAndView viewProduct_gi() {
		
		return new ModelAndView("productManagement_gi");
	}
	
	@RequestMapping("/product_gv")
	public ModelAndView viewProduct_gv(){
		
		return new ModelAndView("productManagement_gv");
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/login", produces="application/json")	
	public int login(){
		
		return 12;
	}
}
