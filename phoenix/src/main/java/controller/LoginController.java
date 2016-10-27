package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class LoginController {
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/login", produces="application/json")	
	public int login(){
		return 12;
	}
}
