package usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import servicio.ClienteServicio;
import servicio.UbicacionServicio;
import servicio.UsuarioServicio;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table (name="cliente")
public class Cliente implements Serializable{
	
	@Id
	@Column(name="codigo_usuario")
	int codigo;
	
	@Column (name="codigo_ubicacion")
    int codigoUbicacion;
	
	@Column (name = "tarjeta")
    String tarjeta;

	@Column (name = "correo_paypal")
    String correoPayPal;    
    
    @Transient
    Usuario usuario;
    
    @Transient
    Ubicacion ubicacion;
    
    
    //Constructores. *******************************************************************************************
    public Cliente(){

    }

    public Cliente(int codigoUsuario, String tarjeta, String paypal, int ubicacion) {
    	this.codigo = codigoUsuario;
        this.tarjeta = tarjeta;
        this.correoPayPal = paypal;
        this.codigoUbicacion = ubicacion;
    }

    //Getters y Setters. ***************************************************************************************

    //Atributo: codigo
    public int getCodigoUsuario() {
		return codigo;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigo = codigoUsuario;
	}
	
	//Atributo: codigoUbicacion
	public int getCodigoUbicacion() {
		return codigoUbicacion;
	}

	public void setCodigoUbicacion(int codigoUbicacion) {
		this.codigoUbicacion = codigoUbicacion;
	}
    
    //Atributo: tarjeta
    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    //Atributo: correoPayPal
    public String getCorreoPayPal() {
        return correoPayPal;
    }

    public void setCorreoPayPal(String correoPayPal) {
        this.correoPayPal = correoPayPal;
    }
	
	//Otros atributos *********************************************************************
	
	//Atributo: usuario y el resto de atributos de la clase Usuario.
	public Usuario getUsuario(){
		if(this.usuario == null){
			usuario = UsuarioServicio.buscarPorId(codigo);
		}
		return usuario;
	}

	//Correo
	public String getCorreo(){
		return getUsuario().getCorreo();
	}
	
	//Contra
	public String getContra(){
		return getUsuario().getContra();
	}
	
	public void setContra(String contra){
		getUsuario().setContra(contra);
	}
	
	//Nombre
	public String getNombre(){
		return getUsuario().getNombre();
	}
	
	public void setNombre(String nombre){
		getUsuario().setNombre(nombre);
	}
	
	//Apellido
	public String getApellido(){
		return getUsuario().getApellido();
	}
	
	public void setApellido(String apellido){
		getUsuario().setApellido(apellido);
	}
	
	
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
	
	//Métodos *******************************************************
	
	public int actualizar(){
		return ClienteServicio.actualizar(this);
	}
	
	public int actualizarContra(String contra){
		return UsuarioServicio.actualizarContra(getUsuario(), contra);
	}
}
