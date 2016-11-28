package pedidos; 

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

@Entity
@Table(name="pedido")
public class Pedido{
	
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
	
	public Pedido(){}

	public Pedido(int codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
		this.lineasPedido = new ArrayList<LineaPedido>();
	}

	//Getters y setters. 
	public int getCodigo() {
		return codigo;
	}

	public double getTotal() {
		return total;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getCodigoEmpresa() {
		return codigoEmpresa;
	}
	
	public int getCodigoCliente() {
		return codigoCliente;
	}

	//Get y set de linea pedido
	public List<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}

	public void agregarLineaPedido(int codigoProducto, double precio, int cantidad) {
		this.lineasPedido.add(new LineaPedido(codigoProducto, precio, cantidad));
	}
	
	public void modificarLineaPedido(int codigoProducto, int cantidad){
		for(LineaPedido linea : lineasPedido){
			if(linea.getCodigoProducto() == codigoProducto){
				linea.setCantidad(cantidad);
				break;
			}
		}
	}
}