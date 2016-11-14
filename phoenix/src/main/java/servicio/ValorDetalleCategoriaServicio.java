package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.ValorDetalleCategoria;
import util.Sesion;

public class ValorDetalleCategoriaServicio {
	//Crea un nuevo detalle dentro de la categoria dada.
	public static ValorDetalleCategoria crear(int codigoDetalleCategoria, String valor) throws Exception{
		ValorDetalleCategoria valorDetalle = null;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			valorDetalle = new ValorDetalleCategoria(codigoDetalleCategoria, valor);
			session.save(valorDetalle);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			throw new Exception(e.getMessage());
		}
		
		return valorDetalle;
	}
	
	//Buscar un valor por id.
	public static ValorDetalleCategoria buscarPorId(int codigo){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		ValorDetalleCategoria valorDetalle = (ValorDetalleCategoria) session.get(ValorDetalleCategoria.class, codigo);
		
		transaction.commit();
		return valorDetalle;
	}		
	
	//Obtiene todos los valores de detalles
	@SuppressWarnings("unchecked")
	public static List<ValorDetalleCategoria> obtenerDetalles(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ValorDetalleCategoria.class);
		List<ValorDetalleCategoria> result = criteria.list();
		
		transaction.commit();
		return result;
	}
	
	//Obtiene los valores de un detalle especifica.
	@SuppressWarnings("unchecked")
	public static List<ValorDetalleCategoria> obtenerDetalles(int codigoDetalleCategoria){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ValorDetalleCategoria.class);
		criteria.add(Restrictions.eq("codigoDetalleCategoria", codigoDetalleCategoria));
		List<ValorDetalleCategoria> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}
