package prueba;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Sesiones {
	
	public static Session getSession(){
		Configuration cfg = new Configuration()
			//Clases mapeadas
			.addAnnotatedClass(usuarios.Ubicacion.class)
			//Configuración
			.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
			.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
			.setProperty("hibernate.connection.url", "jdbc:postgresql://tantor.db.elephantsql.com:5432/czgkwwqy")
			.setProperty("hibernate.connection.username", "czgkwwqy")
			.setProperty("hibernate.connection.password", "8KY3vDh1amm_scrxrroeq7MIAxgz7Bse")
			.setProperty("hibernate.connection.pool_size","1")
			.setProperty("hibernate.current_session_context_class","org.hibernate.context.internal.ThreadLocalSessionContext");
			//Configuración del pool.
	        /*.setProperty("hibernate.c3p0.min_size", "1")
	        .setProperty("hibernate.c3p0.max_size", "32")
	        .setProperty("hibernate.c3p0.initialPoolSize", "1")
	        .setProperty("hibernate.c3p0.timeout", "5000")
	        .setProperty("hibernate.c3p0.acquire_increment", "1")
	        .setProperty("hibernate.c3p0.max_statements", "50")
	        .setProperty("hibernate.c3p0.idle_test_period", "300")
	        .setProperty("hibernate.c3p0.numHelperThreads", "4");*/
		
		SessionFactory sessions = cfg.buildSessionFactory();
		
		return sessions.getCurrentSession();		
	}
}

