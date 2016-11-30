package pedidos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import util.DoubleFormat;

@SuppressWarnings("serial")
@Entity
@Table(name="lineapedido")
public class LineaPedido implements Serializable{

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

	//Getters y setters. 
	
	//Atributo: código
	public int getCodigo() {
		return codigo;
	}
	
	//Atributo: precio de venta.
	public double getPrecioVendido() {
		return precioVendido;
	}
	
	//Atributo: cantidad. 
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		this.subtotal = DoubleFormat.round(cantidad * precioVendido, 2);		//Modificar el subtotal. 
	}
	
	//Atributo: subtotal.
	public double getSubtotal() {
		return subtotal;
	}	
	
	//Atributo: código del producto. 
	public int getCodigoProducto() {
		return codigoProducto;
	}
	
	//Atributo: código pedido (Se permite el set porque no se tiene el código del pedido hasta que se almacena en la base)
	public void setCodigoPedido(int codigoPedido){
		this.codigoPedido = codigoPedido;
	}
	
	public int getCodigoPedido() {
		return codigoPedido;
	}
}