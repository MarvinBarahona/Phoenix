package usuarios;



import javax.persistence.*;

import java.io.Serializable;

/**
 * Created by maosv on 15/10/2016.
 */
@SuppressWarnings("serial")
@Entity
@Table (name="cliente")
public class Cliente extends Usuario implements Serializable{


    String tarjeta;

    String correoPayPal;



    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Ubicacion ubicacion;



    //Constructores
    public Cliente(){

    }

    public Cliente(String correo, String contra, String nom, String apell, String tarjeta, String paypal, Ubicacion ubicacion) {
        super(correo, contra, nom, apell, TipoUsuario.cliente);

        this.tarjeta = tarjeta;
        this.correoPayPal = paypal;
        this.ubicacion = ubicacion;
    }

    //Getters y Setters.

    //Atributo: tarjeta
    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    //Atributo: correoPayPal
    public String getCorreoPayPal() {
        return correoPayPal;
    }

    public void setCorreoPayPal(String correoPayPal) {
        this.correoPayPal = correoPayPal;
    }

    //Atributo: ubicacion
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }


}
