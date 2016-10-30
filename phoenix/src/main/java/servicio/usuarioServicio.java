package servicio;

import org.hibernate.Session;

import sesion.Sesion;
import usuarios.Usuario;

public class usuarioServicio {
	
	public static Usuario findById(int number)
	{
		final Session session = Sesion.getSession();
		Usuario usuario = (Usuario)session.get(Usuario.class, number);
		return usuario;
	}
}
