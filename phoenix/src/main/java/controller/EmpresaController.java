package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import servicio.EmpleadoServicio;
import servicio.EmpresaServicio;
import usuarios.Empleado;
import usuarios.Empresa;
import usuarios.TipoEmpleado;
import util.EmailSender;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
public class EmpresaController {
	@Autowired private HttpServletRequest request;
	
	//					###### Crear empresa ########
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
	
	
	//						###### Modificar empresa ########
	@PostMapping(value="/modificarEmpresa",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String modificarEmpresa(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		double costoEnvio = Double.parseDouble(request.getParameter("costoEnvio"));
		String pais = request.getParameter("pais");
		String ciudad = request.getParameter("ciudad");
		String direccion = request.getParameter("direccion");
		String zip = request.getParameter("zip");
		
		int codigoEmpresa = (int)request.getSession().getAttribute("idEmpresa");
		
		Empresa empresa = EmpresaServicio.buscarPorId(codigoEmpresa);
		empresa.setNombre(nombre);
		empresa.setTelefono(telefono);
		empresa.setCostoEnvio(costoEnvio);
		empresa.setPais(pais);
		empresa.setCiudad(ciudad);
		empresa.setDireccion(direccion);
		empresa.setZip(zip);
		
		int result = EmpresaServicio.actualizar(empresa);
		
		if(result == 1) resp.addProperty("exito", false);
		
		return new Gson().toJson(resp);
	}
	
	
	//						###### Modificar empleado ######## 
	@PostMapping(value="/modificarEmpleado", headers="Accept=*/*", produces="application/json")
	public @ResponseBody String modificarEmpleado(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		resp.addProperty("nuevo", false);
		
		//Recuperar los datos. 
		String tipo = request.getParameter("tipo");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");		
		TipoEmpleado tipoEmpleado = TipoEmpleado.valueOf(tipo);		//Convierte el String de tipoEmpleado en TipoEmpleado. 
		int idEmpresa = (int)request.getSession().getAttribute("idEmpresa");	//Recupera el atributo de la sesión.
		
		//Recupera al gerente que se está modificando y se modifican sus datos. 
		Empleado gerente = EmpleadoServicio.buscarPorEmpresa(idEmpresa, tipoEmpleado);				
		gerente.setNombre(nombre);
		gerente.setApellido(apellido);
		
		//Si es el gerente de o el de inventario y se ha modificado el correo, se les envia un correo de confirmación.
		switch(tipoEmpleado){
		case gerenteGeneral:			
			break;
		case gerenteVentas:
		case gerenteInventario:
			if(!gerente.getCorreo().matches(correo)){
				resp.addProperty("nuevo", true);
				gerente.setCorreo(correo);
				EmailSender.enviarCorreoEstablecerContra(gerente.getUsuario());
			}
			break;
		default:
			resp.addProperty("exito", false);
			break;
		}
		
		//Actualizar. 
		int r = EmpleadoServicio.actualizar(gerente);
		if(r==1) resp.addProperty("exito", false);
		
		return new Gson().toJson(resp);
	}
}
