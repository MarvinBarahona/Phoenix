package usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * Created by maosv on 15/10/2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(name="codigo_usuario")  
public class Empleado extends Usuario implements Serializable {

    @Enumerated (EnumType.STRING)
    @Column(name = "tipo_empleado")
    TipoEmpleado tipoEmpleado;
    
    //Constructores. *********
    public Empleado(){

    }

    public Empleado(String correo, String contra, String nom, String apellido, TipoEmpleado tipo) {
        //Crea un usuario con tipoUsuario = empleado.
        super(correo, contra, nom, apellido, TipoUsuario.empleado);

        this.tipoEmpleado = tipo;
    }

    //Getters y Setters. *********

    //Atributo: tipoEmpleado
    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipo) {
        this.tipoEmpleado = tipo;
    }
    

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


    // Atributo: tipo
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
