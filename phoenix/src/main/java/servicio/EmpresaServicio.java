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

	//Crea una empresa, con los usuarios y la ubicación pendientes. 
	public static Empresa crear(String nombre, String correoG, String nombreG, String apellidoG){
		//Crear una ubicación por defecto.
		Ubicacion ub = UbicacionServicio.crear("pendiente", "pendiente", "pendiente", "000");
				
		Empresa e = null;				
		final Session session = Sesion.getSession();
		
		try{
			//Crear una nueva empresa con la ubicación creada.
			e =  new Empresa(nombre, ub.getCodigo());
			session.save(e);
			session.getTransaction().commit();
			
			//Crear un empleado gerenteGeneral para que ingrese al sistema y configure la empresa.
			EmpleadoServicio.crear(correoG, "12345", nombreG, apellidoG, TipoEmpleado.gerenteGeneral, e.getCodigo());
			//Crear otros dos empleados con datos por defecto, para modificarlos después. 
			EmpleadoServicio.crear("pendiente", "12345", "pendiente", "pendiente", TipoEmpleado.gerenteVentas, e.getCodigo());
			EmpleadoServicio.crear("pendiente", "12345", "pendiente", "pendiente", TipoEmpleado.gerenteInventario, e.getCodigo());
			
		}catch(HibernateException exc){
			session.getTransaction().rollback();
		}
		
		return e;
	}
	
	//Recupera una empresa con el id especifico.
	public static Empresa buscarPorId(int id){
		final Session session = Sesion.getSession();
		//Método para recuperar un objeto con su identificador. 
		Empresa e = session.get(Empresa.class, id);
		session.clear();		
		return e;
	}
	
	//Actualiza una empresa.
	//Para usar este metodo, recuperar una empresa y usar los métodos set para registrar los cambios a persistir.
	public static int actualizar(Empresa e){
		int r = 0;
		//Actualiza a la ubicación vinculada. 
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
		
		//Retorna 0->exito, 1->error
		return r;
	}
	
	//Recuperar todas las empresas de la tabla. 
	@SuppressWarnings("unchecked")
	public static List<Empresa> obtenerEmpresas(){
		Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Empresa.class);
		return criteria.list();
	}
}
