package util;

import usuarios.Usuario;

public class EmailSender{
	public static void enviarCorreoRecuperacion(Usuario usuario){
		String url = "https://phoenix-148904.appspot.com/restorePassword.html?user=";
		String correo = usuario.getCorreo();
		url += Encoder.codificar(correo);
		String nombre = usuario.getNombre() + usuario.getApellido();
		
		MailServlet mensaje = new MailServlet();
		mensaje.enviarMsjRecupPass(url, correo, nombre);
	}
	
	public static void enviarCorreoConfirmacion(String correo, String nombre, String apellido){
		String url = "https://phoenix-148904.appspot.com/confirmAccount.html?mail=";
		url += Encoder.codificar(correo);
		url += "&name=" + Encoder.codificar(nombre);
		url += "&lastname=" + Encoder.codificar(apellido);
		
		String name = nombre + " " + apellido;
		
		MailServlet mensaje = new MailServlet();
		mensaje.enviarMsjConfirmCuenta(url, correo, name);
	}
}
