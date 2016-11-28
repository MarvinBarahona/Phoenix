package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Sesion {

	static SessionFactory sessions;
	static Session session;

	public static Session getSession(){
		getSessions();
		session = sessions.getCurrentSession();		
		return session;
	}
	
	public static void getSessions(){
		if(sessions == null || sessions.isClosed()){
			Configuration cfg = new Configuration()

			//Clases mapeadas
			.addAnnotatedClass(usuarios.Ubicacion.class)
			.addAnnotatedClass(usuarios.Cliente.class)
			.addAnnotatedClass(usuarios.Empleado.class)
			.addAnnotatedClass(usuarios.Empresa.class)
			.addAnnotatedClass(usuarios.Usuario.class)
			.addAnnotatedClass(productos.Categoria.class)
			.addAnnotatedClass(productos.Departamento.class)
			.addAnnotatedClass(productos.DetalleCategoria.class)
			.addAnnotatedClass(productos.DetalleProducto.class)
			.addAnnotatedClass(productos.Producto.class)
			.addAnnotatedClass(productos.ValorDetalleCategoria.class)
			.addAnnotatedClass(pedidos.Pedido.class)
			.addAnnotatedClass(pedidos.LineaPedido.class)

			//Configuración
			.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
			.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
			.setProperty("hibernate.connection.url", "jdbc:postgresql://tantor.db.elephantsql.com:5432/czgkwwqy")
			.setProperty("hibernate.connection.username", "czgkwwqy")
			.setProperty("hibernate.connection.password", "8KY3vDh1amm_scrxrroeq7MIAxgz7Bse")
			.setProperty("hibernate.connection.pool_size","1")
			.setProperty("hibernate.current_session_context_class","thread")
			.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");

			sessions = cfg.buildSessionFactory();
		}
	}
}
