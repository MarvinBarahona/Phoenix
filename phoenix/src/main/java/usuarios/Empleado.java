package usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * Created by maosv on 15/10/2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "empleado")
public class Empleado extends Usuario implements Serializable {

    @Id
    @Column(name = "codigo_usuario")
    Integer codigo_usuario;

  //  @Column(name = "tipo_empleado")
    TipoEmpleado tipoEmpleado;

    //Constructores. *********
    public Empleado(){

    }

    public Empleado(String correo, String contra, String nom, String apell, TipoEmpleado tipo) {
        //Crea un usuario con tipoUsuario = empleado.
        super(correo, contra, nom, apell, TipoUsuario.empleado);

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
}
