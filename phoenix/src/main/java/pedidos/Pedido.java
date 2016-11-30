package pedidos; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import productos.Producto;
import util.DoubleFormat;

@SuppressWarnings("serial")
@Entity
@Table(name="pedido")
public class Pedido implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_pedido")
	int codigo;
	
	@Column(name="total_pedido")
	double total;
	
	@Column(name="fecha_pedido")
	Date fecha;
   
	@Column(name="codigo_empresa")
	int codigoEmpresa;
   
	@Column(name="codigo_cliente")
	int codigoCliente;
   
   	@Transient
	List<LineaPedido> lineasPedido;
	
   	//Constructores. 
	public Pedido(){
		
	}

	public Pedido(int codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
		this.lineasPedido = new ArrayList<LineaPedido>();
		total = 0;
	}

	//Getters y setters. 
	
	//Atributo: código
	public int getCodigo() {
		return codigo;
	}

	//Atributo: total
	public double getTotal() {
		return total;
	}

	//Atributo: fecha. 
	public Date getFecha() {
		return fecha;
	}

	//Atributo: código empresa. 
	public int getCodigoEmpresa() {
		return codigoEmpresa;
	}
	
	//Atributo: código cliente. 
	public int getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(int cod){
		this.codigoCliente = cod;
	}

	//Atributo: lineas de pedido. 
	
	public List<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}
	
	//Al agregar un producto al carrito, solo se agrega uno. Luego se puede modificar. 
	public void agregarLineaPedido(Producto producto) {
		double desc = (double) producto.getDescuento() / 100;	//División no entera. 
		double precio = producto.getPrecio() * (1 - desc);	
		
		LineaPedido linea = new LineaPedido(producto.getCodigo(), precio, 1);		
		this.lineasPedido.add(linea);
		
		total += linea.getSubtotal();
	}
	
	//Busca la línea que se desea modificar y la modifica. 
	public double modificarLineaPedido(int codigoProducto, int cantidad){
		double subtotal = 0;
		for(LineaPedido linea : lineasPedido){
			
			if(linea.getCodigoProducto() == codigoProducto){
				total = DoubleFormat.round(total - linea.getSubtotal(), 2);		//Disminuya el total
				linea.setCantidad(cantidad);		//Modifica la línea.
				
				subtotal = linea.getSubtotal();	//Devolver el nuevo subtotal.
				total = DoubleFormat.round(total + linea.getSubtotal(), 2);		//Suma el nuevo subtotal. 
				break;
			}
			
		}
		
		return subtotal;
	}
	
	//Recuperar y eliminar una línea de pedido. 
	public void eliminarLineaPedido(int codigoProducto){
		for(LineaPedido linea : lineasPedido){
			
			if(linea.getCodigoProducto() == codigoProducto){
				total = DoubleFormat.round(total - linea.getSubtotal(), 2);		//Disminuye el total
				lineasPedido.remove(linea);			//Elimina la línea. 
				break;
			}
			
		}
	}
	
	//Recupera la cantidad de producto agregados, igual que la cantidad de líneas agregadas. 
	public int getCantidadProductos(){
		return lineasPedido.size();
	}
}