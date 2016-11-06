package usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import servicio.EmpleadoServicio;
import servicio.EmpresaServicio;
import servicio.ProductoServicio;
import servicio.UbicacionServicio;
import productos.Producto;

@SuppressWarnings("serial")
@Entity
@Table(name="empresa")
public class Empresa implements Serializable{
	
	@Id
	@Column(name="codigo_empresa")
	int codigo;
	
	@Column (name="codigo_ubicacion")
    int codigoUbicacion;
	
	@Column(name="nombre_empresa")
	String nombre;
	
	@Column(name="telefono_empresa")
	String telefono;
	
	@Column(name="img")
	String img;
	
	@Transient
	Ubicacion ubicacion;
	
	@Transient
	Empleado gerenteGeneral;
	
	@Transient
	Empleado gerenteVentas;
	
	@Transient
	Empleado gerenteInventario;
	
	@Transient
	List<Producto> productos;
	
	//Constructores***********************************************************
	
	public Empresa(){
		
	}
	
	public Empresa(int codigo, String nombre, int codigoUbicacion){
		this.codigo = codigo;
		this.nombre = nombre;
		this.codigoUbicacion = codigoUbicacion;
	}	
	
	//Getter y setters. *****************************************************************
	
	//Atributo: codigo
	public int getCodigo() {
		return codigo;
	}

	//Atriburo: codigoUbicacion
	public int getCodigoUbicacion() {
		return codigoUbicacion;
	}
	
	//Atributo: nombre
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//Atributo: telefono
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	//Atributo: img
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	//Otros atributos. ******************************************************************
	//Atributo: ubicación y el resto de atributos de la clase Ubicacion.
	public Ubicacion getUbicacion(){
		if(ubicacion == null){
			ubicacion = UbicacionServicio.buscarPorId(codigoUbicacion);
		}
		return ubicacion;
	}
	
	//Pais
	public String getPais(){
		return getUbicacion().getPais();
	}
	
	public void setPais(String pais){
		getUbicacion().setPais(pais);
	}
	
	//Ciudad
	public String getCiudad(){
		return getUbicacion().getCiudad();
	}
	
	public void setCiudad(String ciudad){
		getUbicacion().setCiudad(ciudad);
	}
	
	//Direccion
	public String getDireccion(){
		return getUbicacion().getDireccion();
	}
	
	public void setDireccion(String dir){
		getUbicacion().setDireccion(dir);
	}
	
	//Ciudad
	public String getZip(){
		return getUbicacion().getZip();
	}
	
	public void setZip(String zip){
		getUbicacion().setZip(zip);
	}
	
	//NOTA: LOS EMPLEADOS NO SE PUEDEN ACTUALIZAR A PARTIR DE LA EMPRESA. DEBEN ACTUALIZARSE INDIVIDUALMENTE.
	
	//Atributo: gerenteGeneral
	public Empleado getGerenteGeneral(){
		if(gerenteGeneral == null){
			gerenteGeneral = EmpleadoServicio.buscarPorEmpresa(getCodigo(), TipoEmpleado.gerenteGeneral);
		}
		return gerenteGeneral;
	}
	
	//Atributo: gerenteVentas
	public Empleado getGerenteVentas(){
		if(gerenteVentas == null){
			gerenteVentas = EmpleadoServicio.buscarPorEmpresa(getCodigo(), TipoEmpleado.gerenteVentas);
		}
		return gerenteVentas;
	}
	
	//Atributo: gerenteInventario
	public Empleado getGerenteInventario(){
		if(gerenteInventario == null){
			gerenteInventario = EmpleadoServicio.buscarPorEmpresa(getCodigo(), TipoEmpleado.gerenteInventario);
		}
		return gerenteInventario;
	}
	
	//Atributo: productos
	//Solo se usará una de las dos posibilidades de gestionar a la vez, si se usan ambas solo se cumple la primera (porque almacena resultado)
	public List<Producto> getProductos(boolean gestionar){
		if(productos == null){
			productos = ProductoServicio.obtenerProductos(codigo, gestionar);
		}
		
		return productos;
	}
	
	//**Falta la lista de productos**
	
	//Otros métodos. ****************************************************************************
	
	//Actualiza este empleado; registra los cambios hechos con los metodos set. 
	public int actualizar(){
		return EmpresaServicio.actualizar(this);
	}	
	
	public List<Producto> getProductos(int codigoDepartamento, int codigoCategoria){
		List<Producto> r = new ArrayList<Producto>(); 
		getProductos(false);
		for(Producto p : productos){
			if(p.getCodigoCategoria() == codigoCategoria && p.getCodigoDepartamento() == codigoDepartamento){
				r.add(p);
			}
		}		
		return r;
	}
	
	public List<Producto> getProductos(int codigoDepartamento){
		List<Producto> r = new ArrayList<Producto>(); 
		getProductos(false);
		for(Producto p : productos){
			if(p.getCodigoDepartamento() == codigoDepartamento){
				r.add(p);
			}
		}		
		return r;
	}
	
}
