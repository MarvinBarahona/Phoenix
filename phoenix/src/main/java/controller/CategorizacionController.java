package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import productos.Categoria;
import productos.Departamento;
import productos.DetalleCategoria;
import productos.ValorDetalleCategoria;
import servicio.CategoriaServicio;
import servicio.DepartamentoServicio;
import servicio.DetalleCategoriaServicio;
import servicio.ValorDetalleCategoriaServicio;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

@Controller
public class CategorizacionController {
	@Autowired private HttpServletRequest request;
 
	//Obtiene la categorización, segmentada por cada nivel. 
	@PostMapping(value="/obtenerCategorizacion",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerCategorizacion(){
		JsonObject resp = new JsonObject();
		
		JsonArray departamentosJ = new JsonArray();		
		List<Departamento> departamentos =  DepartamentoServicio.obtenerDepartamentos();
		for(Departamento departamento : departamentos){
			if(!departamento.getNombre().matches("n/a")){
				departamentosJ.add(obtenerDepartamento(departamento));
			}			
		}		
		resp.add("departamentos", departamentosJ);
		
		
		JsonArray categoriasJ = new JsonArray();		
		List<Categoria> categorias =  CategoriaServicio.obtenerCategorias();
		for(Categoria categoria : categorias){			
			if(!categoria.getNombre().matches("n/a")){
				categoriasJ.add(obtenerCategoria(categoria));
			}			
		}
		resp.add("categorias", categoriasJ);
		
		JsonArray detallesJ = new JsonArray();		
		List<DetalleCategoria> detalles =  DetalleCategoriaServicio.obtenerDetallesCategoria();
		for(DetalleCategoria detalle : detalles){			
			detallesJ.add(obtenerDetalle(detalle));
		}
		resp.add("detalles", detallesJ);
		
		return new Gson().toJson(resp);
	}
	
	//Obtiene la categorización jerarquizada. 
	@PostMapping(value="/obtenerCategorizacionJerarquizada",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String obtenerCategorizacionJerarquizada(){		
		
		// --Departamentos--
		JsonArray departamentosJ = new JsonArray();
		for(Departamento departamento : DepartamentoServicio.obtenerDepartamentos()) if(!departamento.getNombre().matches("n/a")){
			
			//--Departamento--
			JsonObject departamentoJ = new JsonObject();
			
			departamentoJ.addProperty("id", departamento.getCodigo());
			departamentoJ.addProperty("nombre", departamento.getNombre());
			departamentoJ.addProperty("desc", departamento.getDescripcion());
			
			// --Categorías--
			JsonArray categoriasJ = new JsonArray();
			for(Categoria categoria : CategoriaServicio.obtenerCategorias(departamento.getCodigo())) if(!categoria.getNombre().matches("n/a")){
				
				//--Categoría--
				JsonObject categoriaJ = new JsonObject();
				
				categoriaJ.addProperty("id", categoria.getCodigo());
				categoriaJ.addProperty("nombre", categoria.getNombre());
				categoriaJ.addProperty("desc", categoria.getDescripcion());
				
				//--Detalles--
				JsonArray detallesJ = new JsonArray();
				for(DetalleCategoria detalle : DetalleCategoriaServicio.obtenerDetallesCategoria(categoria.getCodigo())){
					
					//--Detalle--
					JsonObject detalleJ = new JsonObject();
					
					detalleJ.addProperty("id", detalle.getCodigo());
					detalleJ.addProperty("nombre", detalle.getNombre());
					detalleJ.addProperty("desc", detalle.getDescripcion());
					
					//--Valores--
					JsonArray valoresJ = new JsonArray();
					for(ValorDetalleCategoria valor : ValorDetalleCategoriaServicio.obtenerValores(detalle.getCodigo())){
						
						//--Agregar valor a valores--
						valoresJ.add(valor.getValor());
					}
					
					//--Agregar valores a detalle--
					detalleJ.add("valores", valoresJ);
					
					//--Agregar detalle a detalles--
					detallesJ.add(detalleJ);
				}
				
				//--Agregar detalles a categoría--
				categoriaJ.add("detalles", detallesJ);
				
				//--Agregar categoría a categorías--
				categoriasJ.add(categoriaJ);
			}
			
			//--Agregar categorías a departamento--
			departamentoJ.add("categorias", categoriasJ);
			
			//--Agregar departamento a departamentos--
			departamentosJ.add(departamentoJ);
		}
		
		return new Gson().toJson(departamentosJ);
	}
	
//CRUD de Departamento *****************************************************************************************
	
	//							######## Crear Departamento #########
	@PostMapping(value="/crearDepartamento",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String crearDepartamento(){
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);
		
		String nombre = request.getParameter("nombre");
		String desc = request.getParameter("desc");
		
		try{
			Departamento d = DepartamentoServicio.crear(nombre, desc);
			r.add("departamento", obtenerDepartamento(d));		//Retorna el departamento creado. 
		}
		catch(Exception e){
			r.addProperty("exito", false);
		}
		
		return new Gson().toJson(r);		
	}
	
	//							######### Modificar producto #########
	@PostMapping(value="/modificarDepartamento",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String modificarDepartamento(){
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);

		String codigo = request.getParameter("id");
		int id = Integer.parseInt(codigo);
		String desc = request.getParameter("desc");		
		
		Departamento d = DepartamentoServicio.buscarPorId(id);
		d.setDescripcion(desc);
		
		int result = DepartamentoServicio.actualizar(d);
		
		if(result==1){
			r.addProperty("exito", false);
		}
		
		return new Gson().toJson(r);
	} 
	
	//							######### Eliminar producto #########
	@PostMapping(value="/eliminarDepartamento",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String eliminarDepartamento(){
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);
		
		String codigo = request.getParameter("id");
		int id = Integer.parseInt(codigo);
		
		Departamento d = DepartamentoServicio.buscarPorId(id);
		
		if(d != null){
			int result = DepartamentoServicio.eliminar(d);
			if(result==1) r.addProperty("exito", false);
		}
		else{
			r.addProperty("exito", false);
		}
		
		return new Gson().toJson(r);
	}
	
	
//CRUD de categoria. *********************************************************************************************
	
	//							######### Crear categoría #########
	@PostMapping(value="/crearCategoria",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String crearCategoria(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		String nombre = request.getParameter("nombre");
		String desc = request.getParameter("desc");
		String idDepartamento = request.getParameter("depto");
		int codigoDepartamento = Integer.parseInt(idDepartamento);
		
		try{
			Categoria c = CategoriaServicio.crear(nombre, desc, codigoDepartamento);
			resp.add("categoria", obtenerCategoria(c));		//Retorna la categoría creada. 
		}
		catch(Exception e){
			resp.addProperty("exito", false);
		}
		
		return new Gson().toJson(resp);
	}
	
	//							######### Modificar categoría ########
	@PostMapping(value="/modificarCategoria",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String modificarCategoria(){
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);

		String codigo = request.getParameter("id");
		int id = Integer.parseInt(codigo);
		String desc = request.getParameter("desc");		
		
		Categoria c = CategoriaServicio.buscarPorId(id);
		c.setDescripcion(desc);
		
		int result = CategoriaServicio.actualizar(c);
		
		if(result==1){
			r.addProperty("exito", false);
		}
		
		return new Gson().toJson(r);
	} 
	
	
	//							######### Eliminar categoría ##########
	@PostMapping(value="/eliminarCategoria",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String eliminarCategoria(){
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);
		
		String codigo = request.getParameter("id");
		int id = Integer.parseInt(codigo);
		
		Categoria c = CategoriaServicio.buscarPorId(id);
		
		if(c != null){
			int result = CategoriaServicio.eliminar(c);
			if(result==1) r.addProperty("exito", false);
		}
		else{
			r.addProperty("exito", false);
		}
		
		return new Gson().toJson(r);
	}
	
	
//CRUD de detalle categoría****************************************************************************************
	
	//							######### Crear detalle #########
	@PostMapping(value="/crearDetalle",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String crearDetalle(){
		JsonObject resp = new JsonObject();
		resp.addProperty("exito", true);
		
		String nombre = request.getParameter("nombre");
		String desc = request.getParameter("desc");
		String[] valores = request.getParameterValues("valores[]");
		String idCategoria = request.getParameter("categ");
		int codigoCategoria = Integer.parseInt(idCategoria);
		
		try{
			DetalleCategoria det = DetalleCategoriaServicio.crear(nombre, desc, codigoCategoria, valores);
			resp.add("detalle", obtenerDetalle(det));		//Devuelve el detalle creado. 
		}
		catch(Exception e){
			resp.addProperty("exito", false);
		}
		
		return new Gson().toJson(resp);
	}
	
	//							########### Modificar detalle ##########
	@PostMapping(value="/modificarDetalle",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String modificarDetalle(){
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);
		
		String codigo = request.getParameter("id");
		int id = Integer.parseInt(codigo);
		String desc = request.getParameter("desc");	
		//Recibe arreglos de String con los nuevos valores del detalles y los valores que se deben eliminar. 
		String[] nuevosValores = request.getParameterValues("valoresnuevos[]");
		String[] valoresEliminar = request.getParameterValues("valoreseliminar[]");
		
		int result;
		
		//Actualiza la descripción del detalle. 
		DetalleCategoria det = DetalleCategoriaServicio.buscarPorId(id);
		det.setDescripcion(desc);
		result = DetalleCategoriaServicio.actualizar(det);		
		if(result==1) r.addProperty("exito", false);
		
		//Agrega los nuevos valores.
		if(nuevosValores != null){
			result = DetalleCategoriaServicio.agregarValores(det, nuevosValores);		
			if(result==1) r.addProperty("exito", false);
		}		
		
		//Elimina los valores.
		if(valoresEliminar != null){
			result = DetalleCategoriaServicio.eliminarValores(det, valoresEliminar);
			if(result==1) r.addProperty("exito", false);
		}		
		
		return new Gson().toJson(r);
	} 
	
	//							########## Eliminar detalle ##########
	@PostMapping(value="/eliminarDetalle",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String eliminarDetalle(){		
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);
		
		String codigo = request.getParameter("id");
		int id = Integer.parseInt(codigo);
		
		DetalleCategoria det = DetalleCategoriaServicio.buscarPorId(id);
		
		if(det != null){
			int result = DetalleCategoriaServicio.eliminar(det);
			if(result==1) r.addProperty("exito", false);
		}
		else{
			r.addProperty("exito", false);
		}		
		
		return new Gson().toJson(r);
	}

	
//Obtener un objeto JSON a partir de un objeto JAVA (para la categorización segmentada)
	public JsonObject obtenerDepartamento(Departamento departamento){
		JsonObject departamentoJ = new JsonObject();
		
		//Datos para el control.
		departamentoJ.addProperty("id", departamento.getCodigo());
		
		//Estos son visibles por el usuario.
		departamentoJ.addProperty("nombre", departamento.getNombre());
		departamentoJ.addProperty("desc", departamento.getDescripcion());
		
		return departamentoJ;
	}
	
	
	public JsonObject obtenerCategoria(Categoria categoria){
		JsonObject categoriaJ = new JsonObject();
		Departamento departamento = DepartamentoServicio.buscarPorId(categoria.getCodigoDepartamento());
		
		//Datos para el control.
		categoriaJ.addProperty("id", categoria.getCodigo());
		categoriaJ.addProperty("idDepartamento", departamento.getCodigo());
		
		//Estos datos son visibles para el usuario.
		categoriaJ.addProperty("nombre", categoria.getNombre());
		categoriaJ.addProperty("desc", categoria.getDescripcion());
		categoriaJ.addProperty("departamento", departamento.getNombre());
		
		return categoriaJ;
	}
	
	public JsonObject obtenerDetalle(DetalleCategoria detalle){
		JsonObject detalleJ = new JsonObject();
		Categoria categoria = CategoriaServicio.buscarPorId(detalle.getCodigoCategoria());
		Departamento departamento = DepartamentoServicio.buscarPorId(categoria.getCodigoDepartamento());
		
		//Datos para el control.
		detalleJ.addProperty("id", detalle.getCodigo());
		detalleJ.addProperty("idCategoria", categoria.getCodigo());
		detalleJ.addProperty("idDepartamento", departamento.getCodigo());
		
		//Estos datos son visibles para el usuario.
		detalleJ.addProperty("nombre", detalle.getNombre());
		detalleJ.addProperty("desc", detalle.getDescripcion());
		detalleJ.addProperty("categoria", categoria.getNombre());
		detalleJ.addProperty("departamento", departamento.getNombre());
		
		//Agrega los valores de detalle.
		JsonArray valoresJ = new JsonArray();		
		List<ValorDetalleCategoria> valores = ValorDetalleCategoriaServicio.obtenerValores(detalle.getCodigo());
		for(ValorDetalleCategoria valor : valores){
			if(!valor.getValor().matches("n/a")){
				valoresJ.add(valor.getValor());
			}			
		}
		detalleJ.add("valores", valoresJ);
		
		return detalleJ;
	}
}
