package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.Sesion;
import usuarios.Empleado;
import usuarios.TipoEmpleado;
import usuarios.Usuario;

public class EmpleadoServicio {
	
	//Recupera un empleado por su identificador.
	public static Empleado buscarPorId(int id){
		final Session session = Sesion.getSession();
		//Método para retorna un objeto a partir de su identificador.
		Empleado emp = (Empleado)session.get(Empleado.class,id);
		return emp;
	}
	
	//Recupera un empleado a partir de su correo. 
	public static Empleado buscarPorCorreo(String correo){
		Empleado e = null;
		//Recupera el usuario con el correo dado
		Usuario u = UsuarioServicio.buscarPorCorreo(correo);
		if(u!=null){
			//Si el usuario existe, buscar un empleado con su id.
			e = buscarPorId(u.getCodigo());
		}
		
		//Devuelve el empleado con ese correo o null.
		return e;
	}
	
	//Recupera un empleado de una empresa y con un rol especificos.
	public static Empleado buscarPorEmpresa(int codigoEmpresa, TipoEmpleado tipoEmpleado){
		final Session session = Sesion.getSession();
		Empleado emp;
		
		//Crear un objeto criteria para hacer la consulta.
		Criteria criteria = session.createCriteria(Empleado.class);
		//Asignar las restricciones de la consulta. (Notar que se colocan los atributos de la clase, no los nombres de las tablas.) 
		criteria.add(Restrictions.eq("codigoEmpresa", codigoEmpresa));
		criteria.add(Restrictions.eq("tipoEmpleado", tipoEmpleado));
		
		//Recupera el primer resultado como objeto y se hace un cast a Empleado
		emp = (Empleado)criteria.uniqueResult();
		
		//Retorna el empleado recuperado o null.
		return emp;		
	}
	
	//Actualiza un empleado y la ubicación vinculada.
	//Para usar el método, recuperar un empleado y ocupar los métodos set para realizar los cambios a registrar. 
	public static int actualizar(Empleado emp){
		int r = 0;
		final Session session = Sesion.getSession();
		Transaction transaction = session.getTransaction();
		
		try{
			session.update(emp.getUsuario());
			
			session.update(emp);
			transaction.commit();
			
		}catch(Exception e){
			transaction.rollback();
			r=1;
		}
		
		//Retorna 0->exito, 1->error
		return r;
	}
	
	//Recupera todos los empleados de la tabla. 
	@SuppressWarnings("unchecked")
	public static List<Empleado> obtenerEmpleados(){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Empleado.class);
		return criteria.list();
	}
}
