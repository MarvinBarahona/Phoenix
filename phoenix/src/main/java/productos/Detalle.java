package productos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detalle")
public class Detalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_detalle")
	int codigo;
	
	@Column(name="codigo_categoria")
	int codigoCategoria;
	
	@Column(name = "nombre_detalle")
	String nombre;
	
	@Column(name="descripcion_detalle")
	String descripcion;
	
	//Constructores. *****************************************
	public Detalle(){
		
	}

	public Detalle(String nombre, String descripcion, int codigoCategoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigoCategoria = codigoCategoria;
	}
	
	//Getters y Setters **************************************
	
	//Atributo: codigo
	public int getCodigo() {
		return codigo;
	}
	
	//Atributo: codigoCategoria
	public int getCodigoCategoria(){
		return codigoCategoria;
	}
	
	public void setCodigoCategoria(int codigoCategoria){
		this.codigoCategoria = codigoCategoria;
	}

	//Atributo: nombre
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//Atributo: descripción
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
