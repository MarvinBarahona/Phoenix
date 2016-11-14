package productos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="valordetallecategoria")
public class ValorDetalleCategoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_valordetallecategoria")
	int codigo;
	
	@Column(name="codigo_detallecategoria")
	int codigoDetalleCategoria;
	
	@Column(name="valor")
	String valor;

	//Constructores.*****************************************************
	public ValorDetalleCategoria(){
		
	}
	
	public ValorDetalleCategoria(int codigoDetalleCategoria, String valor){
		this.codigoDetalleCategoria = codigoDetalleCategoria;
		this.valor = valor;
	}

	//Atributo: codigo.
	public int getCodigo() {
		return codigo;
	}

	//Atributo: codigoDetalleCategoria.
	public int getCodigoDetalleCategoria() {
		return codigoDetalleCategoria;
	}

	//Atributo: valor. 
	public String getValor() {
		return valor;
	}	
}
