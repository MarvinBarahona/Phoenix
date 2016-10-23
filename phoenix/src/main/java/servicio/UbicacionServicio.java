package servicio;

import org.hibernate.Session;

import sesion.Sesion;
import usuarios.Ubicacion;

public class UbicacionServicio {
	
	public static void guardar(String pais, String ciudad, String direccion, String zip){
		
		final Session session = Sesion.getSession(); //Objeto de sesion necesario para todas las transacciones
		
		Ubicacion ubi = new Ubicacion(pais, ciudad, direccion, zip);
		
		session.beginTransaction();
		
		session.save(ubi);
 
		session.getTransaction().commit();

		//session.close();
	}
	
	public static long conteoUbicaciones(){
		final Session session = Sesion.getSession(); //Objeto de sesion necesario para todas las transacciones
		long result;
		
		session.beginTransaction();
		
		
		result = (long) session.createQuery("select count(*) from Ubicacion").uniqueResult();
		
		//session.close();
		
		return result;
	}
}
