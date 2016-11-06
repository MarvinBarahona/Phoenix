package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import productos.Departamento;
import sesion.Sesion;

public class DepartamentoServicio {
	
	//Crea un nuevo departamento. 
	public static Departamento crear(String nombre, String descripcion) throws Exception{
		Departamento dep = null;
		final Session session = Sesion.getSession();
		Transaction transaction = session.getTransaction();
		
		try{
			dep = new Departamento(nombre, descripcion);
			session.save(dep);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			throw new Exception(e.getMessage());
		}
		
		return dep;
	}
	
	//Buscar un departamento por id.
	public static Departamento buscarPorId(int codigo){
		final Session session = Sesion.getSession();
		Departamento dep = (Departamento) session.get(Departamento.class, codigo);
		return dep;
	}
	
	//Actualiza el departamento dado. 
	//Para usar el método, recuperar un departamento y usar los métodos set para registrar los cambios a guardar. 
	public static int actualizar(Departamento dep){
		int r = 0;
		final Session session = Sesion.getSession();
		Transaction transaction = session.getTransaction();
		
		try{
			session.update(dep);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			r = 1;
		}
		
		return r;
	}
	
	
	//Obtiene todos los departamentos.
	@SuppressWarnings("unchecked")
	public static List<Departamento> obtenerDepartamentos(){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Departamento.class);
		return criteria.list();
	}
}
