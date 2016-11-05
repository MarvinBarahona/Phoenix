package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import sesion.Sesion;
import usuarios.Ubicacion;

public class UbicacionServicio {
	
	//Guarda una nueva ubicación en la BD
	public static Ubicacion crear(String pais, String ciudad, String direccion, String zip){
		final Session session = Sesion.getSession();
		
		Ubicacion ubi = null;
		
		try{
			ubi = new Ubicacion(pais, ciudad, direccion, zip);		
			session.save(ubi);
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
			
		//Retorna la ubicación registrada o null.
		return ubi;
	}
	
	//Buscar una ubicación por su id
	public static Ubicacion buscarPorId(int id){
		final Session session = Sesion.getSession();
		//Método para retornar un objeto a partir de su identificador.
		Ubicacion u = (Ubicacion)session.get(Ubicacion.class, id);
		session.clear();
		//Retorna una ubicación o null.
		return u;
	}
	
	//Actualizar una ubicación.
	//Para usar este método, recuperar una ubicación y usar los métodos set para implementar los cambios a registrar en la BD.
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
		
		//Retorna 0->exito, 1->error
		return r;
	}
	
	//Retorna la cantidad de ubicaciones guardadas. 
	public static long conteoUbicaciones(){
		final Session session = Sesion.getSession();
		long result;
		
		result = (long) session.createQuery("select count(*) from Ubicacion").uniqueResult();
		session.clear();
		
		return result;
	}
	
	//Retorna todas las ubicaciones de la tabla. 
	@SuppressWarnings("unchecked")
	public static List<Ubicacion> obtenerUbicaciones(){
		Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Ubicacion.class);
		return criteria.list();
	}
}
