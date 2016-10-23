package productos;

import java.util.ArrayList;

public class Producto {
	int codigo;
	String nombre;
	String descripcion;
	double precio;
	int existencias;
	int descuento;
	Categoria categoria;
	ArrayList<Detalle> detalles;
	
	//Constructores ************************************
	public Producto() {
		
	}
	
	public Producto(String nombre, String descripcion, double precio, int existencias, int descuento, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.existencias = existencias;
		this.descuento = descuento;
		this.categoria = categoria;
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
