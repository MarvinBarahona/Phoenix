package servicio;

import org.hibernate.Session;

import sesion.Sesion;
import usuarios.Empresa;
import usuarios.Ubicacion;
import usuarios.Empleado;
import usuarios.TipoEmpleado;

public class EmpresaServicio{
	public static void guardarProvisional(){
		final Session session = Sesion.getSession(); //Objeto de sesion necesario para todas las transacciones
		Empleado[] empleados = new Empleado[3];
		//TipoEmpleado tipo1 = TipoEmpleado.gerenteGeneral;
		//TipoEmpleado tipo2 = TipoEmpleado.gerenteInventario;
		//TipoEmpleado tipo3 = TipoEmpleado.gerenteVentas;
		Ubicacion ubi = new Ubicacion("Pais prueba","Ciudad prueba", "Direccion prueba", "12345");
		//empleados[0] = new Empleado("correo1@gmail.com","contra1","nombre1","apellido1",tipo1);
		//empleados[1] = new Empleado("correo2@gmail.com","contra2","nombre2","apellido2",tipo2);
		//empleados[2] = new Empleado("correo3@gmail.com","contra3","nombre3","apellido3",tipo3);
		Empresa empresa = new Empresa("Empresa prueba", "7898583", ubi, empleados);
		session.beginTransaction();
		session.save(empresa);
		session.getTransaction().commit();
	}
	
	public static long conteoEmpresas(){
		final Session session = Sesion.getSession();
		long result;
		session.beginTransaction();
		result = (long) session.createQuery("select count(*) from Empresa").uniqueResult();
		//session.close();
		return result;
	}
	
	public static int guardar(Empresa empresa)
	{
		final Session session = Sesion.getSession();
		session.beginTransaction();
		session.save(empresa);
		session.getTransaction().commit();
		return 0;
	}
	
	public static Empresa consultar(int codigo){
		final Session session = Sesion.getSession();
		session.beginTransaction();
		Empresa empresa = (Empresa) session.get(Empresa.class, codigo);
		session.close();
		return empresa;
	}
	
	public static int actualizar(Empresa empresa){
		final Session session = Sesion.getSession();
		session.beginTransaction();
		session.merge(empresa);
		session.flush();
		session.getTransaction().commit();
		return 0;
	}
	
	public static int borrar(Empresa empresa){
		final Session session = Sesion.getSession();
		session.beginTransaction();
		session.delete(empresa);
		session.getTransaction().commit();
		return 0;
	}
}
