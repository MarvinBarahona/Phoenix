package servicio;

import org.hibernate.Session;
import sesion.Sesion;
import usuarios.Empleado;

public class empleadoServicio {
	public static Empleado findById(int number)
	{
		final Session session = Sesion.getSession();
		session.beginTransaction();
		Empleado usuario = (Empleado)session.get(Empleado.class, number);
		session.close();
		return usuario;
	}
}
