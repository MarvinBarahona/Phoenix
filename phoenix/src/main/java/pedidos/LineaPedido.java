package pedidos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="lineapedido")
public class LineaPedido{

	@Id
	@Column(name="codigo_linea")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int codigo;
	
	@Column(name="preciovendido")
	double precioVendido;
	
	@Column(name="cantidad")
	int cantidad;
	
	@Column(name="subtotal")
	double subtotal;
	
	@Column(name="codigo_producto")
	int codigoProducto;
		
	
	@Column(name="codigo_pedido")
	int codigoPedido;
	
	//Constructores
	public LineaPedido() {}
	
	public LineaPedido(int codigoProducto, double precioVendido, int cantidad) {
		this.codigoProducto = codigoProducto;
		this.precioVendido = precioVendido;
		this.cantidad = cantidad;
		this.subtotal = precioVendido * cantidad; 		
	}

	//Get para el codigo de linea producto (se autogenera)	
	public int getCodigo() {
		return codigo;
	}
	
	//Set y get para precio, cantidad y subtotal
	public double getPrecioVendido() {
		return precioVendido;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		this.subtotal = cantidad * precioVendido;
	}
	
	public double getSubtotal() {
		return subtotal;
	}	
	
	//Set y get para producto y pedido
	public int getCodigoProducto() {
		return codigoProducto;
	}
	
	public void setCodigoPedido(int codigoPedido){
		this.codigoPedido = codigoPedido;
	}
	
	public int getCodigoPedido() {
		return codigoPedido;
	}
}