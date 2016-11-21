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
		
		resp.add("departamentos", obtenerDepartamentos());
		resp.add("categorias", obtenerCategorias());
		resp.add("detalles", obtenerDetalles());
		
		return new Gson().toJson(resp);
	}
	
//CRUD de Departamento *****************************************************************************************
	
	//##Crear##
	@PostMapping(value="/crearDepartamento",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String crearDepartamento(){
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);
		
		String nombre = request.getParameter("nombre");
		String desc = request.getParameter("desc");
		
		try{
			Departamento d = DepartamentoServicio.crear(nombre, desc);
			r.add("departamento", obtenerDepartamento(d));
		}
		catch(Exception e){
			r.addProperty("exito", false);
		}
		
		return new Gson().toJson(r);		
	}
	
	//##Modificar##
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
	
	//##Eliminar##
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
	//##Crear##
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
			resp.add("categoria", obtenerCategoria(c));
		}
		catch(Exception e){
			resp.addProperty("exito", false);
		}
		
		return new Gson().toJson(resp);
	}
	
	//##Modificar##
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
	
	
	//##Eliminar##
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
	
	//##Crear##
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
			resp.add("detalle", obtenerDetalle(det));
		}
		catch(Exception e){
			resp.addProperty("exito", false);
		}
		
		return new Gson().toJson(resp);
	}
	
	//##Modificar##
	@PostMapping(value="/modificarDetalle",headers="Accept=*/*",produces="application/json")
	public @ResponseBody String modificarDetalle(){
		JsonObject r = new JsonObject();
		r.addProperty("exito", true);
		
		String codigo = request.getParameter("id");
		int id = Integer.parseInt(codigo);
		String desc = request.getParameter("desc");		
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
	
	//##Eliminar##
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

	
//Métodos de apoyo. ***********************************************************************************************
	//Departamentos
	public JsonArray obtenerDepartamentos(){
		JsonArray departamentosJ = new JsonArray();
		
		List<Departamento> departamentos =  DepartamentoServicio.obtenerDepartamentos();
		for(Departamento departamento : departamentos){
			if(!departamento.getNombre().matches("n/a")){
				departamentosJ.add(obtenerDepartamento(departamento));
			}			
		}
		
		return departamentosJ;
	}
	
	public JsonObject obtenerDepartamento(Departamento departamento){
		JsonObject departamentoJ = new JsonObject();
		
		departamentoJ.addProperty("id", departamento.getCodigo());
		
		//A mostrar en la tabla
		departamentoJ.addProperty("nombre", departamento.getNombre());
		departamentoJ.addProperty("desc", departamento.getDescripcion());
		
		return departamentoJ;
	}
	
	//Categorias. 
	public JsonArray obtenerCategorias(){
		JsonArray categoriasJ = new JsonArray();
		
		List<Categoria> categorias =  CategoriaServicio.obtenerCategorias();
		for(Categoria categoria : categorias){			
			if(!categoria.getNombre().matches("n/a")){
				categoriasJ.add(obtenerCategoria(categoria));
			}			
		}		
		return categoriasJ;
	}
	
	public JsonObject obtenerCategoria(Categoria categoria){
		JsonObject categoriaJ = new JsonObject();
		Departamento departamento = DepartamentoServicio.buscarPorId(categoria.getCodigoDepartamento());
		
		categoriaJ.addProperty("id", categoria.getCodigo());
		categoriaJ.addProperty("idDepartamento", departamento.getCodigo());
		
		//A mostrar en la tabla
		categoriaJ.addProperty("nombre", categoria.getNombre());
		categoriaJ.addProperty("desc", categoria.getDescripcion());
		categoriaJ.addProperty("departamento", departamento.getNombre());
		
		return categoriaJ;
	}
	
	//Detalles. 
	public JsonArray obtenerDetalles(){
		JsonArray detallesJ = new JsonArray();
		
		List<DetalleCategoria> detalles =  DetalleCategoriaServicio.obtenerDetallesCategoria();
		for(DetalleCategoria detalle : detalles){
			
			
			detallesJ.add(obtenerDetalle(detalle));
		}
		
		return detallesJ;
	}
	
	public JsonObject obtenerDetalle(DetalleCategoria detalle){
		JsonObject detalleJ = new JsonObject();
		Categoria categoria = CategoriaServicio.buscarPorId(detalle.getCodigoCategoria());
		Departamento departamento = DepartamentoServicio.buscarPorId(categoria.getCodigoDepartamento());
		
		detalleJ.addProperty("id", detalle.getCodigo());
		detalleJ.addProperty("idCategoria", categoria.getCodigo());
		detalleJ.addProperty("idDepartamento", departamento.getCodigo());
		
		//A mostrar en la tabla
		detalleJ.addProperty("nombre", detalle.getNombre());
		detalleJ.addProperty("desc", detalle.getDescripcion());
		detalleJ.addProperty("categoria", categoria.getNombre());
		detalleJ.addProperty("departamento", departamento.getNombre());
		
		JsonArray valoresJ = new JsonArray();
		
		List<ValorDetalleCategoria> valores = ValorDetalleCategoriaServicio.obtenerDetalles(detalle.getCodigo());
		for(ValorDetalleCategoria valor : valores){
			if(!valor.getValor().matches("n/a")){
				valoresJ.add(valor.getValor());
			}			
		}
		detalleJ.add("valores", valoresJ);
		
		return detalleJ;
	}
}
