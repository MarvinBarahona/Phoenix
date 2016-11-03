package usuarios;

import javax.persistence.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

import productos.Producto;
import sesion.Sesion;

/**
 * Created by maosv on 15/10/2016.
 */
//Edwin: Voy a agregar el atributo img como string para la url que no lo veo :V

@SuppressWarnings("serial")
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    @Id
    @Column(name = "codigo_empresa")
    int codigo;

    @Column(name = "nombre_empresa")
    String nombre;

    @Column(name = "telefono_empresa")
    String telefono;

    @Column(name="img")
    String img;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_ubicacion")
    Ubicacion ubicacion;

    /*@OrderColumn
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="codigo_empresa") 
    Set<Empleado> empleados = new HashSet<Empleado>();*/
    
    
   // Empleado[] empleados = new Empleado[3];

    /*@OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="codigo_empresa")
    Set<productos.Producto> productos = new HashSet<productos.Producto>();*/
    
    
    //Constructores ***************
    public Empresa(){
    	
    }
    
    public Empresa(String nom, String tel, Ubicacion ubicacion){
        this.nombre = nom;
        this.telefono = tel;
        this.ubicacion = ubicacion;
       // this.empleados = empleados;
       }

    //Getters y Setters *******************
    //Atributo: codigo
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    //Atributo: nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Atributo: telefono
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Atributo: ubicacion
    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    //Atributo: img
    public String getImg() {
        return this.img;
    }

    public void setImg(String imagen) {
        this.img = imagen;
    }

    //Atributo: productos
	@SuppressWarnings({ "unchecked" })
	public List<Producto> getProductos() {
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Producto.class);
		criteria.add(Restrictions.eq("empresa", codigo));
		
		List<Producto> result = criteria.list();
		
		return result;
	}
    
    
}
