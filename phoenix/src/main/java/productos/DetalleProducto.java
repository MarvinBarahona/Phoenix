package productos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "detalleProducto")
public class DetalleProducto {
	
	@Id
	@Column(name="codigo_producto")
	int codigo;
	
	@Column(name="valor")
	String valorDetalle;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	Detalle detalle;
	
	//Constructores**********************************
	public DetalleProducto(){
		
	}

	public DetalleProducto(String valorDetalle, Detalle detalle) {
		this.valorDetalle = valorDetalle;
		this.detalle = detalle;
	}	
	
	//Getters y Setters*************************************
	public String getValorDetalle() {
		return valorDetalle;
	}

	public void setValorDetalle(String valorDetalle) {
		this.valorDetalle = valorDetalle;
	}

	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}
	
}
