package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import sesion.Sesion;
import usuarios.Cliente;
import usuarios.TipoUsuario;
import usuarios.Ubicacion;
import usuarios.Usuario;

public class ClienteServicio {
	
	public static Cliente guardar(String correo, String contra, String nom, String apell, String tarjeta,
			String paypal, String pais, String ciudad, String direccion, String zip){
		
		Usuario us = UsuarioServicio.crear(correo, contra, nom, apell, TipoUsuario.cliente);
		Ubicacion ub = UbicacionServicio.crear(pais, ciudad, direccion, zip);
		
		Cliente c = null;
		Session session = Sesion.getSession();
		
		try{						
			c = new Cliente(us.getCodigo(), tarjeta, paypal, ub.getCodigo());
			
			session.save(c);
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		
		
		return c; 
	}
	
	public static Cliente buscarPorId(int number)
	{
		final Session session = Sesion.getSession();
		Cliente cliente = (Cliente)session.get(Cliente.class, number);
		session.clear();
		return cliente;
	}
	
	public static Cliente buscarPorCorreo(String correo){
		Usuario u = UsuarioServicio.buscarPorCorreo(correo);
		Cliente c = buscarPorId(u.getCodigo());
		return c;
	}
	
	public static int actualizar(Cliente c){
		int r=0;
		UsuarioServicio.actualizar(c.getUsuario());
		UbicacionServicio.actualizar(c.getUbicacion());
		
		final Session session = Sesion.getSession();
		
		try{
			session.update(c);
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			session.getTransaction().rollback();
			r=1;
		}
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Cliente> obtenerClientes(){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Cliente.class);
		return criteria.list();
	}
}
