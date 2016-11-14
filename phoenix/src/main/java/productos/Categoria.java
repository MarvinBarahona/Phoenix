package productos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import servicio.DetalleCategoriaServicio;

@Entity 
@Table(name="categoria")
public class Categoria {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_categoria")
	int codigo;
	
	@Column(name="nombre_categoria")
	String nombre;
	
	@Column(name="descripcion_categoria")
	String descripcion;
	
	@Column(name="codigo_departamento")
	int codigoDepartamento;
	
	//Constructores. *****************************************
	public Categoria(){
		
	}

	public Categoria(String nombre, String descripcion, int codigoDepartamento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigoDepartamento = codigoDepartamento;
	}
	
	//Getters y Setters **************************************
	
	//Atributo: codigo
	public int getCodigo() {
		return codigo;
	}
	
	//Atributo: codigoDepartamento
	public int getCodigoDepartamento(){
		return this.codigoDepartamento;
	}
	
	public void setCodigoDepartamento(int codigoDepartamento){
		this.codigoDepartamento = codigoDepartamento;
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
	
	//Atributo: detalles
	public List<DetalleCategoria> getDetalles(){
		return DetalleCategoriaServicio.obtenerDetallesCategoria(codigo);
		
	}
}
