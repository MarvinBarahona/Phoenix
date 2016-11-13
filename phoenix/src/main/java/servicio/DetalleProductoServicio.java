package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.DetalleProducto;
import util.Sesion;

public class DetalleProductoServicio {
	
	//Crear un nuevo detalle
	public static DetalleProducto crear(int codigoProducto, int codigoDetalle, String valor) throws Exception{
		DetalleProducto d = null;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
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
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		DetalleProducto d = (DetalleProducto)session.get(DetalleProducto.class, codigo);
		
		transaction.commit();
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
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(DetalleProducto.class);
		List<DetalleProducto> result = criteria.list();
		
		transaction.commit();
		return result;
	}
	
	//Obtiene los detalles de un producto especifico.
	@SuppressWarnings("unchecked")
	public static List<DetalleProducto> obtenerDetallesProducto(int codigoProducto){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(DetalleProducto.class);
		criteria.add(Restrictions.eq("codigoProducto", codigoProducto));
		List<DetalleProducto> result = criteria.list();
		
		transaction.commit();
		return result;
	}
	
}
