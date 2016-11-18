package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.Sesion;
import usuarios.Empleado;
import usuarios.Empresa;
import usuarios.TipoEmpleado;
import usuarios.TipoUsuario;
import usuarios.Ubicacion;
import usuarios.Usuario;

public class EmpresaServicio {

	//Crea una empresa, con los usuarios y la ubicación pendientes.
	public static Empresa crear(String nombre, String correoG, String nombreG, String apellidoG) throws Exception{
				
		Empresa e = null;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			
			//Crea un ubicación por defecto. 
			Ubicacion ub = new Ubicacion("pendiente", "pendiente", "pendiente", "000");
			session.save(ub);
			
			//Crear una nueva empresa con la ubicación creada. (El id de la empresa dependerá del id de la ubicación)
			e =  new Empresa(ub.getCodigo(), nombre, ub.getCodigo());
			session.save(e);
			
			
			//Crear un empleado gerenteGeneral para que ingrese al sistema y configure la empresa.
			Usuario user1 = new Usuario(correoG, "12345", nombreG, apellidoG, TipoUsuario.empleado);
			session.save(user1);
			
			Empleado empl1 = new Empleado(user1.getCodigo(), e.getCodigo(), TipoEmpleado.gerenteGeneral);			
			session.save(empl1);		
			
			//Crear otros dos empleados con datos por defecto, para modificarlos después. 
			Usuario user2 = new Usuario("pendiente1" + e.getCodigo(), "12345", "pendiente", "pendiente", TipoUsuario.empleado);
			session.save(user2);			
			Empleado empl2 = new Empleado(user2.getCodigo(), e.getCodigo(), TipoEmpleado.gerenteVentas);			
			session.save(empl2);
			
			Usuario user3 = new Usuario("pendiente2" + e.getCodigo(), "12345", "pendiente", "pendiente", TipoUsuario.empleado);
			session.save(user3);			
			Empleado empl3 = new Empleado(user3.getCodigo(), e.getCodigo(), TipoEmpleado.gerenteInventario);			
			session.save(empl3);
					
			transaction.commit();
		}catch(HibernateException exc){			
			transaction.rollback();
			throw new Exception(exc.getLocalizedMessage());
		}
		
		return e;
	}
	
	//Recupera una empresa con el id especifico.
	public static Empresa buscarPorId(int id){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		//Método para recuperar un objeto con su identificador. 
		Empresa e = (Empresa)session.get(Empresa.class, id);
		
		transaction.commit();
		return e;
	}
	
	//Actualiza una empresa.
	//Para usar este metodo, recuperar una empresa y usar los métodos set para registrar los cambios a persistir.
	public static int actualizar(Empresa e){
		int r = 0;		
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			session.update(e.getUbicacion());
			session.update(e);
			transaction.commit();
		}
		catch(Exception exc){
			transaction.rollback();
			r = 1;
		}
		
		//Retorna 0->exito, 1->error
		return r;
	}
	
	//Actualiza la imagen de una empresa dada. 
	public static int actualizarImagen(Empresa e, String img){
		e.setImg(img);
		return actualizar(e);
	}
	
	//Recuperar todas las empresas de la tabla. 
	@SuppressWarnings("unchecked")
	public static List<Empresa> obtenerEmpresas(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Empresa.class);
		List<Empresa> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}
