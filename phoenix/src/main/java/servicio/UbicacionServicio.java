package servicio;

import org.hibernate.Session;
import sesion.Sesion;
import usuarios.Ubicacion;

public class UbicacionServicio {
	
	public static void guardar(String pais, String ciudad, String direccion, String zip){
		final Session session = Sesion.getSession(); //Objeto de sesion necesario para todas las transacciones
		
		Ubicacion ubi = new Ubicacion(pais, ciudad, direccion, zip);		
		session.save(ubi);
		session.getTransaction().commit();
	}
	
	public static long conteoUbicaciones(){
		final Session session = Sesion.getSession(); //Objeto de sesion necesario para todas las transacciones
		long result;
		
		result = (long) session.createQuery("select count(*) from Ubicacion").uniqueResult();
		session.clear();
		
		return result;
	}
	
	public static Ubicacion buscarUbicacion(int id){
		final Session session = Sesion.getSession();
		Ubicacion u = (Ubicacion)session.get(Ubicacion.class, id);
		session.clear();
		return u;
	}
	
}
