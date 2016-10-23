package usuarios;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="ubicacion")
public class Ubicacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_ubicacion")
	int codigo;
	
	@Column(name = "direccion")
    String direccion;

    @Column(name = "cuidad")
    String ciudad;

    @Column(name = "pais")
    String pais;

    @Column(name = "zip")
    String zip;
    
    
    //Constructores *************************************
    public Ubicacion(){
		
	}
	
	public Ubicacion(String pais, String ciudad, String direccion, String zip){
		this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.zip = zip;
	}
    
	
	//Getters y Setters *************************************
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
    
    
}
