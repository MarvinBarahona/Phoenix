package servicio;

import org.hibernate.Session;

import prueba.Sesiones;
import usuarios.Ubicacion;

public class UbicacionServicio {
	
	public static void guardar(String pais, String ciudad, String direccion, String zip){
		
		final Session session = Sesiones.getSession(); //Objeto de sesion necesario para todas las transacciones
		
		Ubicacion ubi = new Ubicacion(pais, ciudad, direccion, zip);
		
		session.beginTransaction();
		
		session.save(ubi);
 
		session.getTransaction().commit();
		
		//session.close();
	}
	
	public static long conteoUbicaciones(){
		final Session session = Sesiones.getSession(); //Objeto de sesion necesario para todas las transacciones
		
		session.beginTransaction();
		return (long) session.createQuery("select count(*) from Ubicacion").uniqueResult();
	}
}
