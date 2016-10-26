package productos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="codigo_departamento")
	Set<Categoria> categorias = new HashSet<Categoria>();
	
	//Constructores. *****************************************
	public Departamento(){
		
	}

	public Departamento(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	//Getters y Setters **************************************
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
