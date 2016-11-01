package servicio;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import sesion.Sesion;
import usuarios.Usuario;

public class UsuarioServicio {
	
	@SuppressWarnings("rawtypes")
	public static Usuario buscarPorCorreo(String correo)
	{
		final Session session = Sesion.getSession();
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.like("correo", correo));
		
		List usuarios = criteria.list();
		
		Usuario usuario;
		
		if(usuarios.isEmpty()){
			usuario = null;
		}
		else{
			usuario = (Usuario)usuarios.get(0);
		}
		return usuario;
	}
}
