package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import sesion.Sesion;
import usuarios.Usuario;

public class UsuarioServicio {
	
	//Recuperar un usuario a partir de su id.
	public static Usuario buscarPorId(int id){
		final Session session = Sesion.getSession();
		//Método para recuperar un objeto a partir de su identificador.
		Usuario u = (Usuario)session.get(Usuario.class, id);
		return u;
	}
	
	//Recuperar un usuario a partir de su correo. 
	public static Usuario buscarPorCorreo(String correo)
	{
		final Session session = Sesion.getSession();
		Usuario usuario;
		
		//Recuperación utilizando el objeto "Criteria" para escribir la consulta. 
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.like("correo", correo));
		
		//Resultado de la consulta.
		usuario = (Usuario)criteria.uniqueResult();
		return usuario;
	}
	
	//Actualiza la contraseña de un usuario.
	public static int actualizarContra(Usuario u, String contra){
		int r = 0;
		final Session session = Sesion.getSession();
		Transaction transaction = session.getTransaction();
		
		try{			
			u.setContra(contra);
			session.update(u);
			
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			r = 1;
		}
		return r;
	}
	
	//Verifica si los datos de correo y contraseña coinciden con un usuario registrado. 
	//Recupera el usuario validado si hay coincidencia o null.
	public static Usuario validar(String correo, String contra){
		
		//Recupera un usuario con el correo dado
		Usuario user = buscarPorCorreo(correo);
		
		//Si el usuario no existe, al tratar de ocupar getContra() producirá un NullPointerException.
		
		try{
			int i = contra.compareTo(user.getContra());
			
			//Si el usuario no tiene la contraseña especificada, retorna null.
			if(i!=0){
				user = null;
			}
		}
		catch(NullPointerException e){
			user = null;
		}		
		
		return user;
	}
	
	//Recupera todos los usuarios de la tabla. 
	@SuppressWarnings("unchecked")
	public static List<Usuario> obtenerUsuarios(){
		Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		return criteria.list();
	}
}
