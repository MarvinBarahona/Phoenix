package usuarios;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import servicio.EmpresaServicio;
import servicio.UbicacionServicio;
import util.UploadURL;

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
	
	@Column(name="costoenvio")
	double costoEnvio;
	
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
		//Devuelve la imagen en forma de path. 
		return UploadURL.getImageURL(img, servername);
	}
	
	public String getBlob(){
		//Devuelve la imagen en forma de blobkey
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	//Atributo: costoEnvio
	public double getCostoEnvio(){
		return this.costoEnvio;
	}
	
	public void setCostoEnvio(double costoEnvio){
		this.costoEnvio = costoEnvio;
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
	
	//**Falta la lista de productos**
	
	//Otros métodos. ****************************************************************************
	
	//Actualiza este empleado; registra los cambios hechos con los metodos set. 
	public int actualizar(){
		return EmpresaServicio.actualizar(this);
	}		
}
