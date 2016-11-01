package productos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	//@OneToMany(cascade= CascadeType.ALL)
	//@JoinColumn(name="codigo_categoria")
	//Set<Detalle> detalles = new HashSet<Detalle>();
	
	
	
	//Constructores. *****************************************
	public Categoria(){
		
	}

	public Categoria(String nombre, String descripcion) {
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
