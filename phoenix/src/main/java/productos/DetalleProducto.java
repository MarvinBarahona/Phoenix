package productos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detalleProducto")
public class DetalleProducto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_detalleProducto")
	int codigo;
	
	@Column(name="valor")
	String valor;
	
	@Column(name="codigo_detalle")
	int codigoDetalle;
	
	@Column(name="codigo_producto")
	int codigoProducto;
	
	//Constructores**********************************
	public DetalleProducto(){
		
	}

	public DetalleProducto(String valor, int codigoDetalle, int codigoProducto) {
		this.valor = valor;
		this.codigoDetalle = codigoDetalle;
		this.codigoProducto = codigoProducto;
	}	
	
	//Getters y Setters*************************************
	
	//Atributo: codigo
	public int getCodigo(){
		return this.codigo;
	}
	
	//Atributo: valor
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor){
		this.valor = valor;
	}

	//Atributo: codigoDetalle
	public int getCodigoDetalle() {
		return codigoDetalle;
	}

	//Atributo: codigoProducto
	public int getCodigoProducto() {
		return codigoProducto;
	}	
}
