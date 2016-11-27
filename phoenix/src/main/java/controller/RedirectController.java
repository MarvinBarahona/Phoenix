package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import productos.Producto;
import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.EmpresaServicio;
import servicio.ProductoServicio;
import usuarios.Cliente;
import usuarios.Empleado;
import usuarios.Empresa;
import usuarios.TipoEmpleado;
import util.Encoder;

@Controller
public class RedirectController {
	@Autowired private HttpServletRequest request;
	
	
//					########## Login *********************************************************
	
	@RequestMapping(value="/loginFailed")
	public ModelAndView loginFailed(){
		ModelAndView model = new ModelAndView("login");
		
		//Recuperar la siguiente página a la que dirige al usuario después de loguearse.
		//Esto se usa para redirigir a los clientes al sitio donde estaban antes de loguearse.
		String nextUrl = request.getParameter("nextUrl");
		if(nextUrl != null && nextUrl != "") model.addObject("nextUrl", nextUrl);
		
		//Manda mensaje del error y mantiene el correo. 
		model.addObject("msg", request.getParameter("msg"));
		model.addObject("correo", request.getParameter("email"));
		
		return model;
	}
	
	
//					########### Para agregar un nuevo cliente ********************************
	
	
	//Solicitar una cuenta. 
	@RequestMapping(value="/signIn")
	public ModelAndView signIn(){
		return new ModelAndView("signIn");
	}
	
	
	//Confirmar la cuenta. 
	@RequestMapping(value="/confirmAccount")
	public ModelAndView confirmAccount(){
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		
		ModelAndView model = new ModelAndView("confirmAccount");
		//Recupera los datos codificados y los pone en el sitio. 
		model.addObject("correo", Encoder.decodificar(mail));
		model.addObject("nombre", Encoder.decodificar(name));
		model.addObject("apellido", Encoder.decodificar(lastname));
		
		return model;		
	}
	
	
//					########## Para restaurar contraseña ******************************************
	
	//Solicitar recuperar la contra. 
	@RequestMapping(value="/recoverPassword")
	public ModelAndView recoverPassword(){
		return new ModelAndView("recoverPassword");
	}
	
	
	//Página para recuperar la contra. 
	@RequestMapping(value="/restorePassword", method=RequestMethod.GET)	
	public ModelAndView restorePassword(){
		String user = request.getParameter("user");
		//Decodifica el correo y lo pone en el sitio.
		String correo = Encoder.decodificar(user);		
		ModelAndView model = new ModelAndView("restorePassword");
		model.addObject("correo", correo);
		return model;
	}
	
	
//					########## Para el gerente general ***************************************
	
	//Muestra la información de las ventas de la empresa. 
	@RequestMapping("/dashboard_gg")
	public ModelAndView dashboard_gg(){
		ModelAndView model;
		
		model = validar("general", "/dashboard_gg.html");
		if(model == null){
			model = new ModelAndView("dashboard_gg");
			setHeaderData(model);
		}
		
		return model;
	}	
	
	
	//Maneja la información de la empresa. 
	@RequestMapping("/company")
	public ModelAndView company(){
		ModelAndView model;
		
		model = validar("general", "/company.html");
		if(model == null){
			model = new ModelAndView("company");
			setHeaderData(model);
			
			HttpSession session = request.getSession();			
			int codigoEmpresa = (int)session.getAttribute("idEmpresa");
			Empresa e = EmpresaServicio.buscarPorId(codigoEmpresa);
			
			model.addObject("telefono", e.getTelefono());
			model.addObject("pais", e.getPais());
			model.addObject("ciudad", e.getCiudad());
			model.addObject("direccion", e.getDireccion());
			model.addObject("zip", e.getZip());
		}
		
		return model;
	}
	
	
	//Gestiona los empleados de su empresa. 
	@RequestMapping("/employees")
	public ModelAndView employees(){
		ModelAndView model;
		
		model = validar("general", "/employees.html");
		if(model == null){
			model = new ModelAndView("employees");
			setHeaderData(model);
			
			//Recupera la empresa. 
			HttpSession session = request.getSession();			
			int codigoEmpresa = (int)session.getAttribute("idEmpresa");
			Empresa e = EmpresaServicio.buscarPorId(codigoEmpresa);
			
			//Recupera cada empleado y coloca sus datos. 
			List<Empleado> empleados = EmpresaServicio.obtenerEmpleados(e);
			for(Empleado empleado : empleados){
				switch(empleado.getTipoEmpleado()){
				case gerenteGeneral:
					model.addObject("nombreGG", empleado.getNombre());
					model.addObject("apellidoGG", empleado.getApellido());
					model.addObject("correoGG", empleado.getCorreo());
					break;
				case gerenteInventario:
					if(!empleado.getNombre().matches("n/a")){
						model.addObject("nombreGI", empleado.getNombre());
						model.addObject("apellidoGI", empleado.getApellido());
						model.addObject("correoGI", empleado.getCorreo());
					}					
					break;
				case gerenteVentas:
					if(!empleado.getNombre().matches("n/a")){
						model.addObject("nombreGV", empleado.getNombre());
						model.addObject("apellidoGV", empleado.getApellido());
						model.addObject("correoGV", empleado.getCorreo());
					}					
					break;
				default:
					break;				
				}
			}
		}
		
		return model;
	}	
	
	
	//Para confirmar una empresa.
	@RequestMapping("/confirmAccountEmp")
	public ModelAndView confirmAccountEmp(){
		String emp = request.getParameter("emp");
		
		ModelAndView model = new ModelAndView("confirmAccountEmp");
		//Recupera los datos codificados y los pone en el sitio. 
		model.addObject("nombreEmpresa", Encoder.decodificar(emp));
		
		return model;	
	}
	
	
//					################ Para el gerente de inventario ***************************
	
	
	//Maneja el producto (creación y modificación) del nombre, stock inicial, y categorización. 
	@RequestMapping("/productManagement_gi")
	public ModelAndView viewProduct_gi() {
		ModelAndView model;
		
		model = validar("inventario", "/productManagement_gi.html");
		
		if(model == null){			
			model = new ModelAndView("productManagement_gi");
			setHeaderData(model);
		}		
		
		return model;
	}
	
	
	//Aumentar o disminuir las existencias de un producto. 
	@RequestMapping("/stocks")
	public ModelAndView stocks(){
		ModelAndView model;
		
		model = validar("inventario", "/stocks.html");
		
		if(model == null){
			model = new ModelAndView("stocks");
			setHeaderData(model);
		}		
		
		return model;
	}	
	
	
//					################# Para el gerente de ventas ******************************
	
	
	//Maneja el producto (precio, descripción, descuento, imagen)
	@RequestMapping("/productManagement_gv")
	public ModelAndView viewProduct_gv(){
		ModelAndView model;
	
		model = validar("ventas", "/productManagement_gv.html");
		
		if(model == null){
			model = new ModelAndView("productManagement_gv");
			setHeaderData(model);
		}		
		
		return model;
	}	
	
	
	//Muestra las ventas de la empresa. 
	@RequestMapping("/dashboard_gv")
	public ModelAndView dashboard_gv(){
		ModelAndView model;
		
		model = validar("ventas", "/dashboard_gv.html");
		
		if(model == null){
			model = new ModelAndView("dashboard_gv");
			setHeaderData(model);
		}		
		
		return model;
	}
	
	
//					#################### Para el web master **********************************
	
	
	//Para gestionar la categorización de los productos.   
	@RequestMapping("/wm_create")
	public ModelAndView wm_create(){
		ModelAndView model;
		
		model = validar("webmaster", "/wm_create.html");
		
		if(model == null){
			model = new ModelAndView("wm_create");
			
			//Asigna los valores en el header. 
			model.addObject("nombreEmpleado", "WebMaster");
			model.addObject("tipoEmpleado", "webmaster");
			model.addObject("imagenEmpresa", "#");
			model.addObject("nombreEmpresa", "");
		}
		
		return model;
	}
	
	
	//Para crear una nueva empresa.  
	@RequestMapping("/wm_adduser")
	public ModelAndView wm_adduser(){
		ModelAndView model;
		
		model = validar("webmaster", "/wm_adduser.html");
		
		if(model == null){
			model = new ModelAndView("wm_adduser");
			
			//Asigna los valores en el header. 
			model.addObject("nombreEmpleado", "WebMaster");
			model.addObject("tipoEmpleado", "webmaster");
			model.addObject("imagenEmpresa", "#");
			model.addObject("nombreEmpresa", "");
		}
		
		return model;
	}
		
	
//					############## Para el cliente *******************************************
	
	//Sitio de inicio de cada empresa. 
	@RequestMapping("/home")
	public ModelAndView home(){
		ModelAndView model;
		
		HttpSession session = request.getSession();
		
		String codigoEmpresaStr = request.getParameter("idEmpresa");		
		if(codigoEmpresaStr == null) codigoEmpresaStr = (String) session.getAttribute("idEmpresa");
		
		if(codigoEmpresaStr == null){
			model = new ModelAndView("accessDenied");
			model.addObject("nextPage", "/");
		}
		else{
			session.setAttribute("idEmpresa", codigoEmpresaStr);			
			model = new ModelAndView("home");			
			setHeaderDataC(model);
			
			int codigoEmpresa = Integer.valueOf((String) session.getAttribute("idEmpresa"));
			List<Producto> productos = ProductoServicio.obtenerProductos(codigoEmpresa, false);
			
			for(int i=0; i<4 && i<productos.size(); i++){
				Producto producto = productos.get(i);
				
				model.addObject("idProducto" + i, producto.getCodigo());
				model.addObject("imgProducto" + i, producto.getImg(request.getServerName()));
			}
		}		
		
		return model;
	}	
	
	
	//Mostrar los productos con las opciones de búsqueda. 
	@RequestMapping("/searchResults")
	public ModelAndView searchResults(){
		ModelAndView model = new ModelAndView("searchResults");
		
		return model; 
	}
	
	
	//Muestra un producto y permite agregarlo al carrito. 
	@RequestMapping("/product")
	public ModelAndView product(){
		ModelAndView model;
		
		HttpSession session = request.getSession();
		
		String codigoEmpresaStr = (String) session.getAttribute("idEmpresa");
		
		if(codigoEmpresaStr == null){
			model = new ModelAndView("accessDenied");
			model.addObject("nextPage", "/");
		}
		else{			
			model = new ModelAndView("product");
			setHeaderDataC(model);
			
			String idProducto = request.getParameter("product");
			if(idProducto != null){
				model.addObject("idProducto", idProducto);
			}			
			
		}	
		
		return model;
	}
	
	
	//Muestra el carrito de compras. 
	@RequestMapping("/shoppingCart")
	public ModelAndView shoppingCart(){
		ModelAndView model = new ModelAndView();
		
		return model;
	}
	
	
	//Permite al cliente cambiar su dirección.
	@RequestMapping("/location")
	public ModelAndView location(){
		ModelAndView model = new ModelAndView();
		
		return model;
	}
//					############## Funciones del controlador *********************************
	
	//Función: validar. Añade seguridad al sitio (control de acceso).
	//Recibe el tipo de usuario (como String) para comparar con el usuario logueado
	//y el URL al que redirigir al usuario una vez que se loguee. 
	public ModelAndView validar(String tipoEmpleado, String afterLoginUrl){
		ModelAndView model = null;
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("correo");
		String tipo = (String)session.getAttribute("tipo");
		
		//Si no hay usuario logueado. 
		if(email == null){
			model = new ModelAndView("login");
			model.addObject("msg", "Debe iniciar sesión para ingresar a este sitio!");
			model.addObject("nextUrl", afterLoginUrl);		//Almacena el URL solicitado por el usuario, para redirigirlo cuando se loguee.
		}
		//Comparar con el usuario logueado. 
		else if(!tipo.matches(tipoEmpleado)){
			model = new ModelAndView("accessDenied");

			String index = (String)session.getAttribute("index");		//URL por defecto para cada empleado. 
			model.addObject("nextPage", index);
		}
		
		return model;
	}
	
	
	//Solo para gerentes. Pone sus datos en el encabezado de los sitios. 
	public void setHeaderData(ModelAndView model){
		//Recupera el empleado
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("correo");
		Empleado emp = EmpleadoServicio.buscarPorCorreo(email);
		
		if(emp != null){
			//Asigna el lo campos correspondiente al header. 
			model.addObject("nombreEmpleado", emp.getNombre() + " " + emp.getApellido());
			model.addObject("tipoEmpleado", emp.getTipoEmpleado().toString());
			Empresa e = EmpresaServicio.buscarPorId(emp.getCodigoEmpresa());
			model.addObject("imagenEmpresa", e.getImg(request.getServerName()));
			model.addObject("nombreEmpresa", e.getNombre());
		}
	}
	
	public void setHeaderDataC(ModelAndView model){
		HttpSession session = request.getSession();
		
		int codigoEmpresa = Integer.valueOf((String) session.getAttribute("idEmpresa"));
		Empresa e = EmpresaServicio.buscarPorId(codigoEmpresa);
		
		model.addObject("imgEmpresa", e.getImg(request.getServerName()));
		model.addObject("nombreEmpresa", e.getNombre());
		model.addObject("telEmpresa", e.getTelefono());
		model.addObject("ubiEmpresa",e.getCiudad() + ", " + e.getPais());
		model.addObject("correoEmpresa", EmpleadoServicio.buscarPorEmpresa(e.getCodigo(), TipoEmpleado.gerenteGeneral).getCorreo());
		
		String tipo = (String)session.getAttribute("tipo");
		
		if(tipo == null || !tipo.matches("cliente")){
			model.addObject("cuenta", "Cuenta");
		}
		else{
			String correo = (String)session.getAttribute("correo");
			Cliente cliente = ClienteServicio.buscarPorCorreo(correo);
			model.addObject("cuenta", cliente.getNombre() + " " + cliente.getApellido());
		}		
	}
}

