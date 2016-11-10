package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.Categoria;
import productos.Producto;
import util.Sesion;

public class ProductoServicio {
	
	//Crea un producto con los datos que ingresa el gerente de inventario cuando crea un producto. 
	public static Producto crear(String nombre, int existencias, Categoria categoria, int codigoEmpresa) throws Exception{
		Producto p = null;
		final Session session = Sesion.getSession();
		Transaction transaction = session.getTransaction();
		
		try{
			p = new Producto(nombre, existencias, categoria, codigoEmpresa);
			session.save(p);
			
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			throw new Exception(e.getMessage());
		}
		
		return p;
	}
	
	//Actualiza un producto dado.
	public static int actualizar(Producto p){
		int r=0;
		final Session session = Sesion.getSession();
		Transaction transaction = session.getTransaction();
		
		try{
			session.update(p);
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			r=1;
		}
		return r;
	}
	
	//Actualizar como lo hace el gerente de inventario. 
	public static int actualizarInventario(Producto p, String nombre, Categoria categoria, boolean disponible){		
		p.setNombre(nombre);
		p.setCodigoCategoria(categoria.getCodigo());
		p.setCodigoDepartamento(categoria.getCodigoDepartamento());
		p.setDisponible(disponible);
		
		return actualizar(p);
	}
	
	//Actualizar las existencias. aumentar -> true: suma; en otro caso resta. 
	public static int actualizarExistencias(Producto p, boolean aumentar, int cantidad){
		int actual = p.getExistencias();
		if(aumentar){
			p.setExistencias(actual + cantidad);
		}
		else{
			cantidad = actual-cantidad;
			//Evita negativos.
			cantidad = cantidad<0 ? 0 : cantidad;
			p.setExistencias(cantidad);
		}
		
		return actualizar(p);
	}
	
	//Actualiza como lo hace el gerente de ventas. FALTA IMG
	public static int actualizarVentas(Producto p, String descripcion, double precio, int descuento){
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setDescuento(descuento);
		p.setDisponible(true);
		
		return actualizar(p);
	}
	
	//Busca un producto por id.
	public static Producto buscarPorId(int codigo){
		final Session session = Sesion.getSession();
		Producto p = (Producto)session.get(Producto.class, codigo);
		return p;
	}
		
	//Obtiene todos los productos de la tabla. 
	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductos(){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Producto.class);
		return criteria.list();
	}
	
	//Obtiene los productos de una empresa especifica.
	//Retorna todos los productos si gestionar == true;
	//Retorna solo los productos disponibles si gestionar == false;
	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductos(int codigoEmpresa, boolean gestionar){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Producto.class);
		criteria.add(Restrictions.eq("codigoEmpresa", codigoEmpresa));
		
		if(!gestionar){
			criteria.add(Restrictions.eq("disponible", true));
		}
		
		return criteria.list();
	}
	
	//Retorna los productos disponibles de una empresa dentro de un departamento dado. (Todo a través de sus id)
	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductos(int codigoEmpresa, int departamento){
		final Session session = Sesion.getSession();
		Criteria criteria = session.createCriteria(Producto.class);
		criteria.add(Restrictions.eq("codigoEmpresa", codigoEmpresa));
		criteria.add(Restrictions.eq("codigoDepartamento", departamento));
		criteria.add(Restrictions.eq("disponible", true));
		
		return criteria.list();
	}
	
	//Retorna los productos disponibles de una empresa dentro de un departamento y categoria dado. (Todo a través de sus id)
		@SuppressWarnings("unchecked")
		public static List<Producto> obtenerProductos(int codigoEmpresa, int departamento, int categoria){
			final Session session = Sesion.getSession();
			Criteria criteria = session.createCriteria(Producto.class);
			criteria.add(Restrictions.eq("codigoEmpresa", codigoEmpresa));
			criteria.add(Restrictions.eq("codigoDepartamento", departamento));
			criteria.add(Restrictions.eq("codigoCategoria", categoria));
			criteria.add(Restrictions.eq("disponible", true));
			
			return criteria.list();
		}
}