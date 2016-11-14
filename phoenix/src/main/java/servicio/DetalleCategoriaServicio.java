package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.DetalleCategoria;
import util.Sesion;

public class DetalleCategoriaServicio {
	//Crea un nuevo detalle dentro de la categoria dada.
	public static DetalleCategoria crear(String nombre, String descripcion, int codigoCategoria) throws Exception{
		DetalleCategoria det = null;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			det = new DetalleCategoria(nombre, descripcion, codigoCategoria);
			session.save(det);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			throw new Exception(e.getMessage());
		}
		
		return det;
	}
	
	//Buscar un detalle por id.
	public static DetalleCategoria buscarPorId(int codigo){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		DetalleCategoria det = (DetalleCategoria) session.get(DetalleCategoria.class, codigo);
		
		transaction.commit();
		return det;
	}
	
	//Actualiza el detalle dado. 
	//Para usar el método, recuperar un detalle y usar los métodos set para registrar los cambios a guardar. 
	public static int actualizar(DetalleCategoria det){
		int r = 0;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			session.update(det);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			r = 1;
		}
		
		return r;
	}
	
	
	//Obtiene todos los detalles.
	@SuppressWarnings("unchecked")
	public static List<DetalleCategoria> obtenerDetallesCategoria(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(DetalleCategoria.class);
		List<DetalleCategoria> result = criteria.list();
		
		transaction.commit();
		return result;
	}
	
	//Obtiene los detalles de una categoria especifica.
	@SuppressWarnings("unchecked")
	public static List<DetalleCategoria> obtenerDetallesCategoria(int codigoCategoria){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(DetalleCategoria.class);
		criteria.add(Restrictions.eq("codigoCategoria", codigoCategoria));
		List<DetalleCategoria> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}
