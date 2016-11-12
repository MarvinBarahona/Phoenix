package util;

import usuarios.Usuario;

public class EmailAPI {
	public static void enviarCorreoRecuperacion(Usuario usuario){
		String url = "https://phoenix-148904.appspot.com/restorePassword.html?user=";
		String correo = usuario.getCorreo();
		url += Encoder.codificarCorreo(correo);
		
		//Insertar resto del método aqui! Ahi está la url a enviar y el correo!
	}
}
