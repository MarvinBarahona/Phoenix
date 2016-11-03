package servicio;

import org.hibernate.Session;

import sesion.Sesion;
import usuarios.Cliente;

public class ClienteServicio {
	public static Cliente buscarPorId(int number)
	{
		final Session session = Sesion.getSession();
		Cliente cliente = (Cliente)session.get(Cliente.class, number);
		session.clear();
		return cliente;
	}
}
