package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.DetalleProducto;
import sesion.Sesion;

public class DetalleProductoServicio {
	
	//Crear un nuevo detalle
	public static DetalleProducto crear(int codigoProducto, int codigoDetalle, String valor) throws Exception{
		DetalleProducto d = null;
		final Session session = Sesion.getSession();
		Transaction transaction = session.getTransaction();
		
		try{
			d = new DetalleProducto(valor, codigoDetalle, codigoProducto);
			session.save(d);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			throw new Exception(e.getMessage());
		}
		
		return d;
	}
	
	//Buscar un detalle de producto por id.
	public static DetalleProducto buscarPorId(int codigo){
		final Session session = Sesion.getSession();
		DetalleProducto d = session.get(DetalleProducto.class, codigo);
		return d;
	}
	
	//Actualizar un detalle producto dado.
	public static int actualizar(int codigo, String valor){
		int r = 0;
		
		DetalleProducto d = buscarPorId(codigo);
		
		if(d == null){
			r = 1;
		}
		else{
			d.setValor(valor);
			Session session = Sesion.getSession();
			Transaction transaction = session.getTransaction();
			
			try{
				session.update(d);
				transaction.commit();
			}catch(HibernateException e){
				transaction.rollback();
				r = 1;
			}
		}
		
		return r;
	}
	
	//Obtiene todos los detalles de productos
	@SuppressWarnings("unchecked")
	public static List<DetalleProducto> obtenerDetallesProducto(){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(DetalleProducto.class);
		return criteria.list();
	}
	
	//Obtiene los detalles de un producto especifico.
	@SuppressWarnings("unchecked")
	public static List<DetalleProducto> obtenerDetallesProducto(int codigoProducto){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(DetalleProducto.class);
		criteria.add(Restrictions.eq("codigoProducto", codigoProducto));
		return criteria.list();
	}
	
}
