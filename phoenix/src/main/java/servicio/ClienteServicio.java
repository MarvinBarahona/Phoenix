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
	
	//Guarda un cliente con su ubicación
	public static Cliente guardar(String correo, String contra, String nom, String apell, String tarjeta,
			String paypal, String pais, String ciudad, String direccion, String zip){
		
		//Genera el Usuario para este cliente.
		Usuario us = UsuarioServicio.crear(correo, contra, nom, apell, TipoUsuario.cliente);
		//Crea la Ubicación del cliente. 
		Ubicacion ub = UbicacionServicio.crear(pais, ciudad, direccion, zip);
		
		Cliente c = null;
		Session session = Sesion.getSession();
		
		try{
			//Crea el objeto cliente, con los códigos del usuario y la ubicación vinculados.
			c = new Cliente(us.getCodigo(), tarjeta, paypal, ub.getCodigo());
			
			//Salvar
			session.save(c);
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		
		//Retorna el cliente creado o null si hubo error. 
		return c; 
	}
	
	//Recuperar un cliente a partir de su identificador.
	public static Cliente buscarPorId(int number)
	{
		final Session session = Sesion.getSession();
		//Consulta de clases por su id.
		Cliente cliente = (Cliente)session.get(Cliente.class, number);
		session.clear();
		
		return cliente;
	}
	
	//Retorna un cliente a partir de su correo. 
	public static Cliente buscarPorCorreo(String correo){
		Cliente c = null;
		//Recupera al usuario con ese correo.
		Usuario u = UsuarioServicio.buscarPorCorreo(correo);
		//Si la busqueda retorno un usuario. 
		if(u != null){
			c = buscarPorId(u.getCodigo());
		}
		
		//Retorna un cliente con ese correo o null.
		return c;
	}
	
	//Actualiza un cliente. Guarda los cambios entre el cliente recibido y el objeto almacenado. 
	//Para utilizarlo, recuperar un cliente y ocupar los métodos set para modificar los datos a almacenar. 
	public static int actualizar(Cliente c){
		int r=0;
		
		//Actualiza los objetos vinculados. 
		UsuarioServicio.actualizar(c.getUsuario());
		UbicacionServicio.actualizar(c.getUbicacion());
		
		final Session session = Sesion.getSession();
		
		try{
			//Actualiza el objeto. 
			session.update(c);
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			session.getTransaction().rollback();
			r=1;
		}
		
		//Retorna 0->exitoso, 1->error
		return r;
	}
	
	//Recupera todos los clientes registrados. 
	@SuppressWarnings("unchecked")
	public static List<Cliente> obtenerClientes(){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Cliente.class);
		return criteria.list();
	}
}
