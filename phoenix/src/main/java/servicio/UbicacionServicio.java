package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import util.Sesion;
import usuarios.Ubicacion;

public class UbicacionServicio {
	
	//Buscar una ubicación por su id
	public static Ubicacion buscarPorId(int id){
		final Session session = Sesion.getSession();
		//Método para retornar un objeto a partir de su identificador.
		Ubicacion u = (Ubicacion)session.get(Ubicacion.class, id);
		//Retorna una ubicación o null.
		return u;
	}
	
	//Retorna la cantidad de ubicaciones guardadas. 
	public static long conteoUbicaciones(){
		final Session session = Sesion.getSession();
		long result;
		
		result = (long) session.createQuery("select count(*) from Ubicacion").uniqueResult();
		
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
