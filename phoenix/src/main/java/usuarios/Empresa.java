package usuarios;

import java.io.Serializable;
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
import util.UploadURL;
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
	public String getImg(String servername) {
		return UploadURL.getImageURL(img, servername);
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
		return EmpleadoServicio.buscarPorEmpresa(getCodigo(), TipoEmpleado.gerenteGeneral);
	}
	
	//Atributo: gerenteVentas
	public Empleado getGerenteVentas(){
		return EmpleadoServicio.buscarPorEmpresa(getCodigo(), TipoEmpleado.gerenteVentas);
	}
	
	//Atributo: gerenteInventario
	public Empleado getGerenteInventario(){
		return EmpleadoServicio.buscarPorEmpresa(getCodigo(), TipoEmpleado.gerenteInventario);
	}
	
	//Atributo: productos
	//Solo se usará una de las dos posibilidades de gestionar a la vez, si se usan ambas solo se cumple la primera (porque almacena resultado)
	public List<Producto> getProductos(boolean gestionar){		
		return  ProductoServicio.obtenerProductos(getCodigo(), gestionar);
	}
	
	//**Falta la lista de productos**
	
	//Otros métodos. ****************************************************************************
	
	//Actualiza este empleado; registra los cambios hechos con los metodos set. 
	public int actualizar(){
		return EmpresaServicio.actualizar(this);
	}	
	
	public List<Producto> getProductos(int codigoDepartamento, int codigoCategoria){
		return ProductoServicio.obtenerProductos(getCodigo(), codigoDepartamento, codigoCategoria);
	}
	
	public List<Producto> getProductos(int codigoDepartamento){
		return ProductoServicio.obtenerProductos(getCodigo(), codigoDepartamento);
	}
	
}
