package productos;

public class DetalleProducto {
	String valorDetalle;
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
