package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.Detalle;
import sesion.Sesion;

public class DetalleServicio {
	//Crea un nuevo detalle dentro de la categoria dada.
		public static Detalle crear(String nombre, String descripcion, int codigoCategoria) throws Exception{
			Detalle det = null;
			final Session session = Sesion.getSession();
			Transaction transaction = session.getTransaction();
			
			try{
				det = new Detalle(nombre, descripcion, codigoCategoria);
				session.save(det);
				transaction.commit();
			}catch(HibernateException e){
				transaction.rollback();
				throw new Exception(e.getMessage());
			}
			
			return det;
		}
		
		//Buscar un detalle por id.
			public static Detalle buscarPorId(int codigo){
				final Session session = Sesion.getSession();
				Detalle det = (Detalle) session.get(Detalle.class, codigo);
				return det;
			}
			
			//Actualiza el detalle dado. 
			//Para usar el método, recuperar un detalle y usar los métodos set para registrar los cambios a guardar. 
			public static int actualizar(Detalle det){
				int r = 0;
				final Session session = Sesion.getSession();
				Transaction transaction = session.getTransaction();
				
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
			public static List<Detalle> obtenerDetalles(){
				final Session session = Sesion.getSession();
				Criteria criteria = session.createCriteria(Detalle.class);
				return criteria.list();
			}
			
			//Obtiene los detalles de una categoria especifica.
			@SuppressWarnings("unchecked")
			public static List<Detalle> obtenerDetalles(int codigoCategoria){
				final Session session = Sesion.getSession();
				Criteria criteria = session.createCriteria(Detalle.class);
				criteria.add(Restrictions.eq("codigoCategoria", codigoCategoria));
				return criteria.list();
			}
}
