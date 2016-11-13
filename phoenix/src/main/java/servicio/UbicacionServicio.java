package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.Sesion;
import usuarios.Ubicacion;

public class UbicacionServicio {
	
	//Buscar una ubicación por su id
	public static Ubicacion buscarPorId(int id){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		//Método para retornar un objeto a partir de su identificador.
		Ubicacion u = (Ubicacion)session.get(Ubicacion.class, id);
		transaction.commit();
		
		//Retorna una ubicación o null.
		return u;
	}
	
	//Retorna la cantidad de ubicaciones guardadas. 
	public static long conteoUbicaciones(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		long result;
		
		result = (long) session.createQuery("select count(*) from Ubicacion").uniqueResult();
		transaction.commit();
		return result;
	}
	
	//Retorna todas las ubicaciones de la tabla. 
	@SuppressWarnings("unchecked")
	public static List<Ubicacion> obtenerUbicaciones(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Ubicacion.class);
		List<Ubicacion> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}
