package productos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import servicio.DetalleProductoServicio;
import servicio.ProductoServicio;

@Entity
@Table(name="producto")
public class Producto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
	int codigo;
	
	@Column(name = "codigo_categoria")
	int codigoCategoria;
	
	@Column(name = "codigo_departamento")
	int codigoDepartamento;
	
	@Column(name = "codigo_empresa")
	int codigoEmpresa;
	
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
	
	@Column(name = "img")
	String img;
	
	@Column(name = "disponible")
	boolean disponible;
	
	@Transient
	List<DetalleProducto> detalles;
	
	//Constructores ************************************
	public Producto() {
		
	}
	
	public Producto(String nombre, int existencias, Categoria categoria, int codigoEmpresa) {
		this.nombre = nombre;
		this.existencias = existencias; 
		this.codigoEmpresa = codigoEmpresa;
		this.codigoCategoria = categoria.getCodigo();
		this.codigoDepartamento = categoria.getCodigoDepartamento();
	}

	//Getters y Setters ***********************************
	
	//Atributo: codigo
	public int getCodigo() {
		return codigo;
	}

	//Atributo: codigoEmpresa
	public int getCodigoEmpresa() {
		return codigoEmpresa;
	}
	
	//Atributo:codigoDepartamento
	public int getCodigoDepartamento(){
		return this.codigoDepartamento;
	}
	
	public void setCodigoDepartamento(int codigoDepartamento){
		this.codigoDepartamento = codigoDepartamento;
	}
	
	//Atributo: codigoCategoria
	public int getCodigoCategoria() {
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

	//Atributo: precio
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	//Atributo: existencias.
	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	//Atributo: descuento
	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	//Atributo: img
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	//Atributo: disponible
	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	//Atributo: detalles
	public List<DetalleProducto> getDetalles(){
		if(detalles == null){
			detalles = DetalleProductoServicio.obtenerDetallesProducto(codigo);
		}
		return detalles;
	}

	//Otros métodos
	public int actualizarInventario(String nombre, Categoria categoria, boolean disponible){
		return ProductoServicio.actualizarInventario(this, nombre, categoria, disponible);
	}
	
	public int actualizarExistencias(boolean aumentar, int cantidad){
		return ProductoServicio.actualizarExistencias(this, aumentar, cantidad);
	}
	
	public int actualizarVentas(String descripcion, double precio, int descuento){
		return ProductoServicio.actualizarVentas(this, descripcion, precio, descuento);
	}
	
	public int actualizarImagen(String img){
		return ProductoServicio.actualizarImagen(this, img);
	}
	
	public int actualizarDetalle(int codigoDetalle, String valor){
		getDetalles();
		int r = 1;
		for(DetalleProducto d : detalles){
			if(d.getCodigoDetalle() == codigoDetalle){
				r = DetalleProductoServicio.actualizar(d.getCodigo(), valor);
				break;
			}
		}
		return r;
	}
}
