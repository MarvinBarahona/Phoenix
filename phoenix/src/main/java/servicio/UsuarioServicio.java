package servicio;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import sesion.Sesion;
import usuarios.Usuario;

public class UsuarioServicio {
	
	//Nota: este metodo retorna un objeto usuario, pero en realidad recupera un objeto "Empleado" o "Cliente".
	//Usar este metodo para recuperar los clientes y los empleados por su correo, 
	//y realizar el respectivo Cast (Empleado) o (Cliente) cuando sea necesario.
	@SuppressWarnings("rawtypes")
	public static Usuario buscarPorCorreo(String correo)
	{
		final Session session = Sesion.getSession();
		Usuario usuario;
		
		//Recuperación utilizando el objeto "Criteria" para escribir la consulta. 
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.like("correo", correo));
		
		//Resultado de la consulta.
		List usuarios = criteria.list();		
		
		//Si no se recupero nada, retornar "null"
		if(usuarios.isEmpty()){
			usuario = null;
		}
		//Si se recupero, enviar como objeto usuario. 
		else{
			usuario = (Usuario)usuarios.get(0);
		}
		return usuario;
	}
}
