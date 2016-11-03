package productos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class IdEmbeddedClass implements Serializable{

	@Column (name="codigo_producto")
	protected Integer codigo_producto;
	
	@Column (name="codigo_detalle")
	 protected Integer codigo_detalle;

	    public IdEmbeddedClass() {}

		public IdEmbeddedClass(Integer codigo_producto, Integer codigo_detalle) {
			this.codigo_producto = codigo_producto;
			this.codigo_detalle = codigo_detalle;
		}

	   
	
}
