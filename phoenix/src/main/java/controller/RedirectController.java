package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedirectController {
	
	@RequestMapping("/product_gi")
	public ModelAndView viewProduct_gi() {
		return new ModelAndView("productManagement_gi");
	}
	
	@RequestMapping("/product_gv")
	public ModelAndView viewProduct_gv(){
		return new ModelAndView("productManagement_gv");
	}
}
