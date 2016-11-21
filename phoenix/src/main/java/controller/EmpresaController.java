package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import servicio.EmpresaServicio;
import util.EmailSender;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
public class EmpresaController {
	@Autowired private HttpServletRequest request;
	
	//Para crear la empresa.
	@PostMapping(value="/crearEmpresa",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String crearEmpresa(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String empresa = request.getParameter("nombreEmpresa");
		
		try{
			EmpresaServicio.crear(empresa, correo, nombre, apellido);
			EmailSender.enviarCorreoConfirmacionEmpresa(empresa, nombre, apellido, correo);
		}
		catch(Exception e){
			resp.addProperty("exito", false);
		}
		
		return new Gson().toJson(resp);
	}
}
