package usuarios;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Created by maosv on 15/10/2016.
 */
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

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Ubicacion ubicacion;


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Empleado gerenteGeneral;


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Empleado gerenteVentas;


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Empleado gerenteInventario;

    //Constructores ***************
    public Empresa(){

    }

    public Empresa(String nom, String tel, Ubicacion ubicacion){
        this.nombre = nom;
        this.telefono = tel;
        this.ubicacion = ubicacion;

        gerenteGeneral = null;
        gerenteVentas = null;
        gerenteInventario = null;
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
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    //Atributo: gerenteGeneral
    public Empleado getGerenteGeneral() {
        return gerenteGeneral;
    }

    public void setGerenteGeneral(Empleado gerenteGeneral) {
        this.gerenteGeneral = gerenteGeneral;
    }

    //Atributo: gerenteVentas
    public Empleado getGerenteVentas() {
        return gerenteVentas;
    }

    public void setGerenteVentas(Empleado gerenteVentas) {
        this.gerenteVentas = gerenteVentas;
    }

    //Atributo: gerenteInventario
    public Empleado getGerenteInventario() {
        return gerenteInventario;
    }

    public void setGerenteInventario(Empleado gerenteInventario) {
        this.gerenteInventario = gerenteInventario;
    }
}
