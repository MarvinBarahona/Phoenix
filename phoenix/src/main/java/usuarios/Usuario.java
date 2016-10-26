package usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "usuario")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    int codigo;

    @Column(name = "correo_usuario")
    String correo;

    @Column(name = "contrasena_usuario")
    String contra;

    @Column(name = "nombre_usuario")
    String nombre;

    @Column(name = "apellido_usuario")
    String apellido;
    
    @Enumerated (EnumType.ORDINAL)
    @Column(name = "tipo_usuario")
    TipoUsuario tipoUsuario;

    //Constructores ***********************
    public Usuario(){

    }



    public Usuario(String correo, String contra, String nom, String apell, TipoUsuario tipoUsuario) {
        this.correo = correo;
        this.contra = contra;
        this.nombre = nom;
        this.apellido = apell;
        this.tipoUsuario = tipoUsuario;
    }

    //Getters y Setters ********************

    // Atributo: codigo
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }
    
    
    // Atributo: correo
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    // Atributo: contraseña
    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    // Atributo: nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    // Atributo: apellido
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Atributo: tipo
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}