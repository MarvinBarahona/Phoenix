package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.DetalleCategoria;
import productos.ValorDetalleCategoria;
import util.Sesion;

public class DetalleCategoriaServicio {
	//Crea un nuevo detalle dentro de la categoria dada.
	public static DetalleCategoria crear(String nombre, String descripcion, int codigoCategoria, String[] valores) throws Exception{
		DetalleCategoria det = null;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			det = new DetalleCategoria(nombre, descripcion, codigoCategoria);
			session.save(det);
			
			session.save(new ValorDetalleCategoria(det.getCodigo(), "n/a"));
			
			for(String valor : valores){
				session.save(new ValorDetalleCategoria(det.getCodigo(), valor));
			}
			
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
	
	//Agrega nuevos valores al detalle dado. 
	public static int agregarValores(DetalleCategoria det, String[] valores){
		int r = 0;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			for(String valor : valores){
				session.save(new ValorDetalleCategoria(det.getCodigo(), valor));
			}
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			r = 1;
		}
		
		return r;
	}
	
	//Elimina los valores dados. 
	public static int eliminarValores(DetalleCategoria det, String[] valores){
		int r = 0; 
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			for(String valor : valores){
				Query queryUpdate = session.createQuery("update DetalleProducto set valor = :valor where codigoDetalle = :codigoDetalle and valor = :valorElim");
				queryUpdate.setParameter("valor", "n/a");
				queryUpdate.setParameter("codigoDetalle", det.getCodigo());
				queryUpdate.setParameter("valorElim", valor);
				
				queryUpdate.executeUpdate();
				
				Query queryDelete = session.createQuery("delete from ValorDetalleCategoria where codigoDetalleCategoria = :codigoDetalle and valor = :valor");
				queryDelete.setParameter("codigoDetalle", det.getCodigo());
				queryDelete.setParameter("valor", valor);
				
				queryDelete.executeUpdate();
			}
			transaction.commit();
		}catch(HibernateException | NullPointerException e){
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
	
	//Elimina un detalle. 
	public static int eliminar(DetalleCategoria det){
		int r = 0; 
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			//Por el diseño de la base de datos, cuando se eliminar un DetalleCategoria, se eliminan 
			//los ValorDetalleCategoria y DetalleProducto asociados. 
			session.delete(det);
			
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			r = 1;
		}
		
		return r;
	}
}
