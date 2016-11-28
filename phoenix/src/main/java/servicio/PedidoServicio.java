package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pedidos.LineaPedido;
import pedidos.Pedido;
import util.Sesion;

public class PedidoServicio {
	
	//Guardar un pedido
	public static Pedido crear(Pedido pedido){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			session.save(pedido);
			
			for(LineaPedido linea : pedido.getLineasPedido()){
				linea.setCodigoPedido(pedido.getCodigo());
				session.save(linea);
			}
			
			transaction.commit();
		}
		catch(HibernateException e){
			transaction.rollback();
			pedido = null;
		}
		
		return pedido;
	}
	
	//Obtiene todos los pedidos.
	@SuppressWarnings("unchecked")
	public static List<Pedido> obtenerPedidos(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Pedido.class);
		List<Pedido> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}
