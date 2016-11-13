package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import productos.Departamento;
import util.Sesion;

public class DepartamentoServicio {
	
	//Crea un nuevo departamento. 
	public static Departamento crear(String nombre, String descripcion) throws Exception{
		Departamento dep = null;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
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
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Departamento dep = (Departamento) session.get(Departamento.class, codigo);
		
		transaction.commit();
		return dep;
	}
	
	//Actualiza el departamento dado. 
	//Para usar el método, recuperar un departamento y usar los métodos set para registrar los cambios a guardar. 
	public static int actualizar(Departamento dep){
		int r = 0;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
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
		Session session = Sesion.getSession();
		
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Departamento.class);
		List<Departamento> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}
