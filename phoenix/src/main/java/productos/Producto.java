package productos;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
	int codigo;
	
	@Column(name = "nombre_producto")
	String nombre;
	
	@Column(name = "descripcion_producto")
	String descripcion;
	
	@Column(name = "precio")
	double precio;
	
	@Column(name = "existencia")
	int existencias;
	
	@Column(name = "descuento")
	int descuento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	Categoria categoria;
	
	@Column(name = "img")
	String img;
	
	//@OneToMany(cascade= CascadeType.ALL)
    //@JoinColumn(name="codigo_producto")
	//Set<Detalle> detalles  = new HashSet<Detalle>();
	
	//Constructores ************************************
	public Producto() {
		
	}
	
	public Producto(String nombre, String descripcion, double precio, int existencias, int descuento, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.existencias = existencias;
		this.descuento = descuento;
		//this.categoria = categoria;
	}

	//Getters y Setters ***********************************
	
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
