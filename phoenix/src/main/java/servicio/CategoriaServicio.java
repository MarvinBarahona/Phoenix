package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.Categoria;
import util.Sesion;

public class CategoriaServicio {
	
	//Crea una nueva categoria dentro del departamento dado.
	public static Categoria crear(String nombre, String descripcion, int codigoDepartamento) throws Exception{
		Categoria cat = null;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			cat = new Categoria(nombre, descripcion, codigoDepartamento);
			session.save(cat);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			throw new Exception(e.getMessage());
		}
		
		return cat;
	}
	
	//Buscar una categoria por id.
	public static Categoria buscarPorId(int codigo){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Categoria cat = (Categoria) session.get(Categoria.class, codigo);
		transaction.commit();
		
		return cat;
	}
	
	//Actualiza la categoria dada. 
	//Para usar el método, recuperar una categoria y usar los métodos set para registrar los cambios a guardar. 
	public static int actualizar(Categoria cat){
		int r = 0;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			session.update(cat);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			r = 1;
		}
		
		return r;
	}
	
	
	//Obtiene todos las categorias
	@SuppressWarnings("unchecked")
	public static List<Categoria> obtenerCategorias(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Categoria.class);
		List<Categoria> result = criteria.list();
		
		transaction.commit();
		return result;
	}
	
	//Obtiene las categorias de un departamento especifico.
	@SuppressWarnings("unchecked")
	public static List<Categoria> obtenerCategorias(int codigoDepartamento){
		Session session = Sesion.getSession();		
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("codigoDepartamento", codigoDepartamento));
		List<Categoria> result = criteria.list();
		
		transaction.commit();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static int eliminar(Categoria categoria){
		int r = 0; 
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			//Recupera la categoria "n/a" del departamento al que pertenece la categoria a eliminar. 
			Criteria criteria = session.createCriteria(Categoria.class);
			criteria.add(Restrictions.eq("codigoDepartamento", categoria.getCodigoDepartamento()));
			criteria.add(Restrictions.eq("nombre", "n/a"));
			List<Categoria> categorias = criteria.list();
			Categoria categoriaNA = categorias.get(0);
			
			//Cambia los productos de la categoria a eliminar a la categoria "n/a" de ese departamento. 
			Query queryUpdate = session.createQuery("update from Producto set codigoCategoria = :codigoCategoriaNA"
					+ " where codigoCategoria = :codigoCategoria");
			queryUpdate.setParameter("codigoCategoriaNA", categoriaNA.getCodigo());
			queryUpdate.setParameter("codigoCategoria", categoria.getCodigo());
			queryUpdate.executeUpdate();
			
			//Por el diseño de la base de datos, cuando se elimina una Categoria, se eliminan 
			//los detalles asociados (con sus respectivos ValorDetalleCategoria y DetalleProducto) 
			session.delete(categoria);
			
			transaction.commit();
		}catch(HibernateException | IndexOutOfBoundsException e){
			transaction.rollback();
			r = 1;
		}
		
		return r;
	}
}
