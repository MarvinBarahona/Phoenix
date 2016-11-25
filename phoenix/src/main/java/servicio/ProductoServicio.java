package servicio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import productos.DetalleProducto;
import productos.Producto;
import util.Sesion;

public class ProductoServicio {
	
	//Crea un producto con los datos que ingresa el gerente de inventario cuando crea un producto. 
	public static Producto crear(String nombre, int existencias, int codigoCategoria, int codigoDepartamento, 
			int codigoEmpresa, int[] codigosDetalles, String[] valoresDetalles) throws Exception{
		Producto p = null;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			p = new Producto(nombre, existencias, codigoCategoria, codigoDepartamento, codigoEmpresa);
			session.save(p);
			
			//Guarda los detalles del producto. 
			if(codigosDetalles != null){
				for(int i = 0; i<codigosDetalles.length; i++){
					session.save(new DetalleProducto(valoresDetalles[i], codigosDetalles[i], p.getCodigo()));
				}
			}			
			
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
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
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
	public static int actualizarInventario(Producto p, String nombre, int[] codigosDetalles, String[] valoresDetalles,
			int codigoCategoria, int codigoDepartamento){
		
		int r=0;
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			//Si no se ha cambiado de categoria y hay detalles, los actualiza. 
			if(codigoCategoria == -1 && codigosDetalles != null){
				for(int i = 0; i<codigosDetalles.length; i++){
					Query queryUpdate = session.createQuery("update DetalleProducto set valor = :valor "
							+ "where codigoDetalle = :codigoDetalle and codigoProducto = :codigoProducto");
					queryUpdate.setParameter("valor", valoresDetalles[i]);
					queryUpdate.setParameter("codigoDetalle", codigosDetalles[i]);
					queryUpdate.setParameter("codigoProducto", p.getCodigo());
					
					queryUpdate.executeUpdate();
				}
			}
			
			//Si se cambia de categoria, elimina los detalles del producto actuales y crea los nuevos detalles. 
			else if(codigoCategoria != -1){
				Query queryDelete = session.createQuery("delete from DetalleProducto where codigoProducto = :codigoProducto");
				queryDelete.setParameter("codigoProducto", p.getCodigo());				
				queryDelete.executeUpdate();
				
				if(codigosDetalles != null){
					for(int i = 0; i<codigosDetalles.length; i++){
						session.save(new DetalleProducto(valoresDetalles[i], codigosDetalles[i], p.getCodigo()));
					}
				}
				
				//Asigna el nuevo codigo de categoria y el nuevo codigo del departamento (si se ha modificado)
				p.setCodigoCategoria(codigoCategoria);				
				if(codigoDepartamento != -1) p.setCodigoDepartamento(codigoDepartamento);
			}
			
			//Setea el nuevo nombre y actualiza. 
			p.setNombre(nombre);			
			session.update(p);
			
			transaction.commit();
		}catch(HibernateException e){
			transaction.rollback();
			r=1;
		}
		return r;
	}
	
	//Actualizar las existencias. aumentar -> true: suma; en otro caso resta. 
	public static int actualizarExistencias(Producto p, boolean aumentar, int cantidad){
		int actual = p.getExistencias();
		int nuevo;
		if(aumentar){
			nuevo = actual + cantidad;
		}
		else{
			nuevo = actual-cantidad;
			//Evita negativos.
			nuevo = nuevo < 0 ? 0 : nuevo;			
		}
		
		p.setExistencias(nuevo);
		int r = actualizar(p);
		
		r = r == 1? -1 : nuevo;
		
		return r;
	}
	
	//Actualiza como lo hace el gerente de ventas.
	public static int actualizarVentas(Producto p, String nombre, String descripcion, double precio, int descuento, boolean disponible){
		p.setNombre(nombre);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setDescuento(descuento);
		p.setDisponible(disponible);
		
		return actualizar(p);
	}
	
	//Actualizar la imagen del producto.
	public static int actualizarImagen(Producto p, String img) {
		p.setImg(img);		
		return actualizar(p);
	}
	
	//Habilitar/deshabilitar el producto.
	public static int habilitar(Producto p, boolean disponible){
		p.setDisponible(disponible);
		return actualizar(p);
	}
	
	//Busca un producto por id.
	public static Producto buscarPorId(int codigo){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Producto p = (Producto)session.get(Producto.class, codigo);
		
		transaction.commit();
		return p;
	}
		
	//Obtiene todos los productos de la tabla. 
	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductos(){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Producto.class);
		List<Producto> result = criteria.list();
		
		transaction.commit();
		return result;
	}
	
	//Obtiene los productos de una empresa especifica.
	//Retorna todos los productos si gestionar == true;
	//Retorna solo los productos disponibles si gestionar == false;
	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductos(int codigoEmpresa, boolean gestionar){
		Session session = Sesion.getSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Producto.class);
		criteria.add(Restrictions.eq("codigoEmpresa", codigoEmpresa));
		
		if(!gestionar){
			criteria.add(Restrictions.eq("disponible", true));
		}
		List<Producto> result = criteria.list();
		
		transaction.commit();
		return result;
	}
}