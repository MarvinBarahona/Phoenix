package usuarios;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import servicio.EmpleadoServicio;
import servicio.EmpresaServicio;
import servicio.UsuarioServicio;

@SuppressWarnings("serial")
@Entity
@Table(name="empleado")
public class Empleado implements Serializable{
	@Id
	@Column(name="codigo_usuario")
	int codigo;
	
	@Column(name="codigo_empresa")
	int codigoEmpresa;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_empleado")
	TipoEmpleado tipoEmpleado;
	
	@Transient
	Usuario usuario;
	
	//Constructores.*********************************************************
	public Empleado(){
		
	}
	
	public Empleado(int codigo, int codigoEmpresa, TipoEmpleado tipoEmpleado){
		this.codigo = codigo;
		this.codigoEmpresa = codigoEmpresa; 
		this.tipoEmpleado = tipoEmpleado;
	}
	
	//Getters y Setters.*******************************************************
	
	//Atributo: codigo
	public int getCodigo(){
		return this.codigo;
	}
	
	//Atributo: codigoEmpresa
	public int getCodigoEmpresa(){
		return this.codigoEmpresa;
	}
	
	//Atributo: tipoEmpleado
	public TipoEmpleado getTipoEmpleado(){
		return this.tipoEmpleado;
	}
	
	
	//Atributo: usuario y los atributos de la clase Usuario
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
	
	public void setCorreo(String correo){
		getUsuario().setCorreo(correo);
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
	
	//Atributo: empresa. No se puede modificar a la empresa a partir de sus empleados, solo recuperarse.
	public Empresa getEmpresa(){
		return EmpresaServicio.buscarPorId(codigoEmpresa);
	}
	
	//Otro métodos. *****************************************************************
	public int actualizar(){
		return EmpleadoServicio.actualizar(this);
	}
	
	public int actualizarContra(String contra){
		return UsuarioServicio.actualizarContra(getUsuario(), contra);
	}
}
