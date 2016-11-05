package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import sesion.Sesion;
import usuarios.Empleado;
import usuarios.TipoEmpleado;
import usuarios.TipoUsuario;
import usuarios.Usuario;

public class EmpleadoServicio {
	public static Empleado crear(String correo, String contra, String nom, String apell, TipoEmpleado tipoEmpleado, int codigoEmpresa){
		Usuario u = UsuarioServicio.crear(correo, contra, nom, apell, TipoUsuario.empleado);
		
		Empleado e = null;
		final Session session = Sesion.getSession();
		
		try{
			e = new Empleado(u.getCodigo(), codigoEmpresa, tipoEmpleado);
			session.save(e);
			session.getTransaction().commit();
		}catch(HibernateException exc){
			session.getTransaction().rollback();
		}
		
		return e;
	}
	
	public static Empleado buscarPorId(int id){
		final Session session = Sesion.getSession();
		Empleado emp = session.get(Empleado.class,id);
		session.clear();
		return emp;
	}
	
	public static Empleado buscarPorCorreo(String correo){
		Usuario u = UsuarioServicio.buscarPorCorreo(correo);
		Empleado e = buscarPorId(u.getCodigo());
		return e;
	}
	
	public static Empleado buscarPorEmpresa(int codigoEmpresa, TipoEmpleado tipoEmpleado){
		final Session session = Sesion.getSession();
		Empleado emp;
		
		Criteria criteria = session.createCriteria(Empleado.class);
		criteria.add(Restrictions.eq("codigoEmpresa", codigoEmpresa));
		criteria.add(Restrictions.eq("tipoEmpleado", tipoEmpleado));
		
		emp = (Empleado)criteria.uniqueResult();
		
		return emp;		
	}
	
	public static int actualizar(Empleado emp){
		int r = 0;
		UsuarioServicio.actualizar(emp.getUsuario());
		
		final Session session = Sesion.getSession();
		
		
		try{
			session.update(emp);
			session.getTransaction().commit();
			
		}catch(HibernateException e){
			session.getTransaction().rollback();
			r=1;
		}
		
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Empleado> obtenerEmpleados(){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Empleado.class);
		return criteria.list();
	}
}
