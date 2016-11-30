package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pedidos.LineaPedido;
import util.Sesion;

public class LineaPedidoServicio {
	//Obtiene todos las líneas de pedido. 
	@SuppressWarnings("unchecked")
	public static List<LineaPedido> obtenerLineasPedido(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(LineaPedido.class);
		List<LineaPedido> result = criteria.list();
		
		transaction.commit();
		return result;
	}
	
	//Obtiene las líneas de pedido de un pedido 
	@SuppressWarnings("unchecked")
	public static List<LineaPedido> obtenerLineasPedido(int codigoPedido){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(LineaPedido.class);
		criteria.add(Restrictions.eqOrIsNull("codigoPedido", codigoPedido));
		List<LineaPedido> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}
