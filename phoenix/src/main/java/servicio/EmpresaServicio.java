package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import sesion.Sesion;
import usuarios.Empresa;
import usuarios.TipoEmpleado;
import usuarios.Ubicacion;

public class EmpresaServicio {

	public static Empresa crear(String nombre, String correoG, String nombreG, String apellidoG){
		Ubicacion ub = UbicacionServicio.crear("pendiente", "pendiente", "pendiente", "000");
				
		Empresa e = null;				
		final Session session = Sesion.getSession();
		
		try{
			e =  new Empresa(nombre, ub.getCodigo());
			session.save(e);
			session.getTransaction().commit();
			
			EmpleadoServicio.crear(correoG, "12345", nombreG, apellidoG, TipoEmpleado.gerenteGeneral, e.getCodigo());
			EmpleadoServicio.crear("pendiente", "12345", "pendiente", "pendiente", TipoEmpleado.gerenteVentas, e.getCodigo());
			EmpleadoServicio.crear("pendiente", "12345", "pendiente", "pendiente", TipoEmpleado.gerenteInventario, e.getCodigo());
			
		}catch(HibernateException exc){
			session.getTransaction().rollback();
		}
		
		return e;
	}
	
	public static Empresa buscarPorId(int id){
		final Session session = Sesion.getSession();		
		Empresa e = session.get(Empresa.class, id);
		session.clear();		
		return e;
	}
	
	public static int actualizar(Empresa e){
		int r = 0;
		UbicacionServicio.actualizar(e.getUbicacion());
		
		final Session session = Sesion.getSession();
		
		try{
			session.update(e);
			session.getTransaction().commit();
		}
		catch(HibernateException exc){
			session.getTransaction().rollback();
			r = 1;
		}
		
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Empresa> obtenerEmpresas(){
		Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Empresa.class);
		return criteria.list();
	}
}
