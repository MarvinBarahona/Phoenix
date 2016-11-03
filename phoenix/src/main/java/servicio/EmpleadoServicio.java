package servicio;

import org.hibernate.Session;
import sesion.Sesion;
import usuarios.Empleado;

public class EmpleadoServicio {
	public static Empleado buscarPorId(int number)
	{
		final Session session = Sesion.getSession();
		Empleado emp = (Empleado)session.get(Empleado.class, number);
		session.clear();
		return emp;
	}
}
