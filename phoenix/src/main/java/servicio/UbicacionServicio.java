package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import sesion.Sesion;
import usuarios.Ubicacion;

public class UbicacionServicio {
	
	public static Ubicacion crear(String pais, String ciudad, String direccion, String zip){
		final Session session = Sesion.getSession(); //Objeto de sesion necesario para todas las transacciones
		
		Ubicacion ubi = null;
		
		try{
			ubi = new Ubicacion(pais, ciudad, direccion, zip);		
			session.save(ubi);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
			
		return ubi;
	}
	
	public static Ubicacion buscarPorId(int id){
		final Session session = Sesion.getSession();
		Ubicacion u = (Ubicacion)session.get(Ubicacion.class, id);
		session.clear();
		return u;
	}
	
	public static int actualizar(Ubicacion u){
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
	
	public static long conteoUbicaciones(){
		final Session session = Sesion.getSession(); //Objeto de sesion necesario para todas las transacciones
		long result;
		
		result = (long) session.createQuery("select count(*) from Ubicacion").uniqueResult();
		session.clear();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Ubicacion> obtenerUbicaciones(){
		Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Ubicacion.class);
		return criteria.list();
	}
}
