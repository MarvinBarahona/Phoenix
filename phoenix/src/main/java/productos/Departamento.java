package productos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="departamento")
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "codigo_departamento")
	int codigo;
	
	@Column (name = "nombre_departamento")
	String nombre;
	
	@Column(name = "descripcion_departamento")
	String descripcion;
		
	
	//Constructores. *****************************************
	public Departamento(){
		
	}

	public Departamento(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	//Getters y Setters **************************************
	
	//Atributo: codigo
	public int getCodigo() {
		return codigo;
	}
	
	//Atriburo: nombre
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
