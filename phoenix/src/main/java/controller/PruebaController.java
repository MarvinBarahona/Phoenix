package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import servicio.empresaServicio;
import usuarios.Empleado;
import usuarios.Empresa;
import usuarios.Ubicacion;
import productos.Producto;

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
	
	@RequestMapping("/guardar_empresaProv")
	public ModelAndView guardar_empresaProv(){
		empresaServicio.guardarProvisional();
		return new ModelAndView("guardarEmpresa","message", empresaServicio.conteoEmpresas());
	}
	
	@RequestMapping("/guardar_empresa")
	public ModelAndView guardar_empresa(String nombre, String telefono, Ubicacion ubi, Empleado[] empleado){
		Empresa empresa = new Empresa(nombre,telefono,ubi,empleado);
		empresaServicio.guardar(empresa);
		return new ModelAndView("guardarEmpresa");
	}
	
	@RequestMapping("/consultar")
	public ModelAndView consultar_empresa(int codigo){
		Empresa empresa = empresaServicio.consultar(codigo);
		return new ModelAndView("");
	}
	
	/*@RequestMapping("/actualizar_empresa")
	public ModelAndView actualizar_empresa(int codigo, String nombre, String telefono, Ubicacion ubi, Empleado[] empleado){
		Empresa empresa = new Empresa(codigo, nombre, telefono, ubi, empleado);
		int i = empresaServicio.actualizar(empresa);
		return new ModelAndView("");
	}*/
	
	@RequestMapping("/consultar_empresa")
	public ModelAndView consultar(){
		return new ModelAndView("guardarEmpresa","message",empresaServicio.conteoEmpresas());
	}
	
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
	
	/*@RequestMapping("/lista_departamentos")
	public String listaDepartamentos(){
		String valores;
		return valores;
	}*/
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/login", produces="application/json")	
	public int login(){
		return 12;
	}
}
