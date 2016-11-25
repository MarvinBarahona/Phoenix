package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.ValorDetalleCategoria;
import util.Sesion;

public class ValorDetalleCategoriaServicio {
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
	public static List<ValorDetalleCategoria> obtenerValores(int codigoDetalleCategoria){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ValorDetalleCategoria.class);
		criteria.add(Restrictions.eq("codigoDetalleCategoria", codigoDetalleCategoria));
		List<ValorDetalleCategoria> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}
