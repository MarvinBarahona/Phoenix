package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import servicio.EmpresaServicio;
import usuarios.Empleado;
import usuarios.Empresa;
import usuarios.Ubicacion;

@Controller
public class EmpresaCRUDController {
	@RequestMapping("/guardar_empresaProv")
	public ModelAndView guardar_empresaProv(){
		EmpresaServicio.guardarProvisional();
		return new ModelAndView("guardarEmpresa","message", EmpresaServicio.conteoEmpresas());
	}
	
	@RequestMapping("/guardar_empresa")
	public ModelAndView guardar_empresa(String nombre, String telefono, Ubicacion ubi, Empleado[] empleado){
		Empresa empresa = new Empresa(nombre,telefono,ubi,empleado);
		EmpresaServicio.guardar(empresa);
		return new ModelAndView("guardarEmpresa");
	}
	
	@RequestMapping("/consultar_empresa")
	public ModelAndView consultar_empresa(int codigo){
		Empresa empresa = EmpresaServicio.consultar(codigo);
		return new ModelAndView("");
	}
	
	/*@RequestMapping("/actualizar_empresa")
	public ModelAndView actualizar_empresa(int codigo, String nombre, String telefono, Ubicacion ubi, Empleado[] empleado){
		Empresa empresa = new Empresa(codigo, nombre, telefono, ubi, empleado);
		int i = empresaServicio.actualizar(empresa);
		return new ModelAndView("");
	}*/
	
	@RequestMapping("/contar_empresas")
	public ModelAndView consultar(){
		return new ModelAndView("guardarEmpresa","message",EmpresaServicio.conteoEmpresas());
	}
}
