package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.Categoria;
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
			
			Categoria cat = new Categoria("n/a", "Categoria indefinida", dep.getCodigo());
			session.save(cat);
			
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
	
	@SuppressWarnings("unchecked")
	public static int eliminar(Departamento departamento){
		int r = 0; 
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			//Recupera el departamento "n/a". 
			Criteria criteriaD = session.createCriteria(Departamento.class);
			criteriaD.add(Restrictions.eq("nombre", "n/a"));
			List<Departamento> departamentos = criteriaD.list();
			Departamento departamentoNA = departamentos.get(0);
			
			//Recupera la categoria "n/a" del departamento "n/a" 
			Criteria criteriaC = session.createCriteria(Categoria.class);
			criteriaC.add(Restrictions.eq("codigoDepartamento", departamentoNA.getCodigo()));
			criteriaC.add(Restrictions.eq("nombre", "n/a"));
			List<Categoria> categorias = criteriaC.list();
			Categoria categoriaNA = categorias.get(0);
			
			//Cambia los productos del departamento a eliminar al departamento "n/a". 
			Query queryUpdate = session.createQuery("update from Producto "
					+ "set codigoDepartamento = :codigoDepartamentoNA, "
					+ "codigoCategoria = :codigoCategoriaNA "
					+ "where codigoDepartamento = :codigoDepartamento");
			queryUpdate.setParameter("codigoDepartamentoNA", departamentoNA.getCodigo());
			queryUpdate.setParameter("codigoCategoriaNA", categoriaNA.getCodigo());
			queryUpdate.setParameter("codigoDepartamento", departamento.getCodigo());
			queryUpdate.executeUpdate();
			
			//Por el diseño de la base de datos, cuando se elimina un Departamento,
			//se eliminan las categorias asociadas, con los detalles asociados 
			//(con sus respectivos ValorDetalleCategoria y DetalleProducto) 
			session.delete(departamento);
			
			transaction.commit();
		}catch(HibernateException | IndexOutOfBoundsException e){
			transaction.rollback();
			r = 1;
		}
		
		return r;
	}
}
