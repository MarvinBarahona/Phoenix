package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import sesion.Sesion;
import usuarios.TipoUsuario;
import usuarios.Usuario;

public class UsuarioServicio {
	
	public static Usuario crear(String correo, String contra, String nom, String apell, TipoUsuario tipoUsuario){
		final Session session = Sesion.getSession();
		
		Usuario u = null;
		
		try{
			u = new Usuario(correo, contra, nom, apell, tipoUsuario);
			session.save(u);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		
		return u;
	}
	
	//Nota: este metodo retorna un objeto usuario, pero en realidad recupera un objeto "Empleado" o "Cliente".
	//Usar este metodo para recuperar los clientes y los empleados por su correo, 
	//y realizar el respectivo Cast (Empleado) o (Cliente) cuando sea necesario.
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
	
	public static Usuario buscarPorId(int id){
		final Session session = Sesion.getSession();
		Usuario u = (Usuario)session.get(Usuario.class, id);
		session.clear();
		return u;
	}
	
	public static int actualizar(Usuario u){
		int r = 0;
		final Session session = Sesion.getSession();
		
		try{
			session.update(u);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
			r = 1;
		}
		
		return r;
	}
	
	public static int actualizarContra(Usuario u, String contra){
		int r = 0;
		final Session session = Sesion.getSession();
		
		try{
			u.setContra(contra);
			session.update(u);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
			r = 1;
		}
		return r;
	}
	
	public static Usuario validar(String correo, String contra){
		
		Usuario user = buscarPorCorreo(correo);
		
		try{
			int i = contra.compareTo(user.getContra());
			if(i!=0){
				user = null;
			}
		}
		catch(NullPointerException e){
			user = null;
		}		
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Usuario> obtenerUsuarios(){
		Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		return criteria.list();
	}
}
