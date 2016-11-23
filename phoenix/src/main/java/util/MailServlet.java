package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

import java.io.UnsupportedEncodingException;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

@SuppressWarnings("serial")
public class MailServlet extends HttpServlet {
	
	private Properties propiedades = new Properties();
    private Session session = Session.getDefaultInstance(propiedades, null);
    private String remitente = "phoenix.nighthawks@gmail.com";
    private String cuerpoMensaje = ""; //Se puede mandar texto plano en el cuerpo del mensaje con setText().
    private String asunto;
    private String cuerpoHTML;
    
    //Estas funciones asignan el contenido de los mensajes   
    private void passwordRecovery(String url, String nombreUsuario){
    	cuerpoHTML = "<h2 style=\"text-align: center; padding-left: 30px;\"><span style=\"color: #3366ff;\"><strong>Solicitud de cambio de contrase&ntilde;a:</strong></span></h2>"
        		  +"<h3 style=\"padding-left: 30px;\">Buen dia "+nombreUsuario+". Para poder reestablecer su&nbsp;contrase&ntilde;a de usuario en Phoenix, por favor acceda al siguiente enlace y siga las instrucciones:</h3>"
        		  +"<h3 style=\"text-align: left; padding-left: 30px;\"><a href=\""+url+"\">"+url+"</a></h3>"
        		  +"<h3 style=\"text-align: left; padding-left: 30px;\">&nbsp;</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Si no puede acceder al enlace, copielo en la barra de direcciones de su navegador.</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Si usted no realizo la solicitud de cambio de&nbsp;contrase&ntilde;a, puede ignorar este mensaje.&nbsp;</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Att. Phoenix Dev Team</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">NightHakws</h3>";   
    	  
    	asunto = "Restaure su clave de acceso.";
    }
   
    private void accountConfirm(String url, String nombreUsuario){
    	cuerpoHTML = "<h2 style=\"text-align: center; padding-left: 30px;\"><span style=\"color: #3366ff;\"><strong>Gracias por preferir los servicios de Phoenix</strong></span></h2>"
        		  +"<h3 style=\"padding-left: 30px;\">Buen dia "+nombreUsuario+". Para finalizar el proceso de creacion de su cuenta, por favor acceda al siguiente enlace y complete el formulario de registro:</h3>"
        		  +"<h3 style=\"text-align: left; padding-left: 30px;\"><a href=\""+url+"\">"+url+"</a></h3>"
        		  +"<h3 style=\"text-align: left; padding-left: 30px;\">&nbsp;</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Si no puede acceder al enlace, copielo en la barra de direcciones de su navegador.</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Esta direccion de correo sera autenticada y se usara para la creacion de su cuenta.</h3>"        		  
        		  +"<h3 style=\"padding-left: 30px;\">Si usted no realizo la solicitud, puede ignorar este mensaje.&nbsp;</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Att. Phoenix Dev Team</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">NightHakws</h3>";   
 	  
    	asunto = "Confirme su email para finalizar registro.";
    }
    
    private void accountConfirmEmp(String url, String nombreUsuario){
    	cuerpoHTML = "<h2 style=\"text-align: center; padding-left: 30px;\"><span style=\"color: #3366ff;\"><strong>Gracias por preferir los servicios de Phoenix</strong></span></h2>"
        		  +"<h3 style=\"padding-left: 30px;\">Buen dia "+nombreUsuario+". Su correo se ha ingresado en una de nuestras empresas afiliadas, acceda al siguiente enlace para confirmar su registro:</h3>"
        		  +"<h3 style=\"text-align: left; padding-left: 30px;\"><a href=\""+url+"\">"+url+"</a></h3>"
        		  +"<h3 style=\"text-align: left; padding-left: 30px;\">&nbsp;</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Si no puede acceder al enlace, copielo en la barra de direcciones de su navegador.</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Esta direccion de correo sera autenticada y se usara para la creacion de su cuenta.</h3>"        		  
        		  +"<h3 style=\"padding-left: 30px;\">Si usted desconoce esta informacion, ignore este mensaje.&nbsp;</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Att. Phoenix Dev Team</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">NightHakws</h3>";   
 	  
    	asunto = "Ha sido agregado a un grupo de trabajo.";
    }
    
    private void companyUnion(String url, String nombreUsuario){
    	cuerpoHTML = "<h2 style=\"text-align: center; padding-left: 30px;\"><span style=\"color: #3366ff;\"><strong>Ha sido agregado como gerente en nuestro sitio:</strong></span></h2>"
        		  +"<h3 style=\"padding-left: 30px;\">Buen dia "+nombreUsuario+". Para poder establecer su&nbsp;contrase&ntilde;a de usuario en Phoenix, por favor acceda al siguiente enlace y siga las instrucciones:</h3>"
        		  +"<h3 style=\"text-align: left; padding-left: 30px;\"><a href=\""+url+"\">"+url+"</a></h3>"
        		  +"<h3 style=\"text-align: left; padding-left: 30px;\">&nbsp;</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Si no puede acceder al enlace, copielo en la barra de direcciones de su navegador.</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Si usted no realizo la solicitud de cambio de&nbsp;contrase&ntilde;a, puede ignorar este mensaje.&nbsp;</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">Att. Phoenix Dev Team</h3>"
        		  +"<h3 style=\"padding-left: 30px;\">NightHakws</h3>";   
    	  
    	asunto = "Ha sido agregado a un grupo de trabajo.";
    }
    
    private void signInAlert(String nombreUsuario){
  	   	cuerpoHTML = "<h2 style=\"text-align: center; padding-left: 30px;\"><span style=\"color: #3366ff;\"><strong>Nuevo Inicio de Sesion</strong></span></h2>"
         		  +"<h3 style=\"padding-left: 30px;\"> "+nombreUsuario+". Se ha iniciado sesion correctamente con su usuario hace unos momentos.:</h3>"
         		  +"<h3 style=\"padding-left: 30px;\">Si usted realizo la operacion, puede ignorar este mensaje.&nbsp;</h3>"
         		  +"<h3 style=\"padding-left: 30px;\">Att. Phoenix Dev Team</h3>"
         		  +"<h3 style=\"padding-left: 30px;\">NightHakws</h3>";   
  	  
  	   	asunto = "Se ha logueado en Phoenix correctamente.";
    }
    

	//Esta funcion guarda el contenido y manda el mensaje.
    private void setMessageData(String destinatario){
    	try{
    		Message msg = new MimeMessage(session);
    	    msg.setSubject(asunto); //Titulo mensaje 
    	    msg.setText(cuerpoMensaje);
    	    msg.setFrom(new InternetAddress(remitente, "Phoenix Dev Team")); //Se puede agregar un parametro opcional con el nombre de usuario del admin
    	    msg.addRecipient(Message.RecipientType.TO,
    	    		new InternetAddress(destinatario)); //Se puede agregar un parametro opcional con el nombre del destinatario
    	      
    	    Multipart mp = new MimeMultipart();
    	    MimeBodyPart seccionHTML = new MimeBodyPart();
    	    seccionHTML.setContent(cuerpoHTML, "text/html");
    	    mp.addBodyPart(seccionHTML);
    	    msg.setContent(mp);
    	    Transport.send(msg);
    	}catch (AddressException e) {
    		System.out.println(e);
    	} catch (MessagingException e) {
    		System.out.println(e);
    	} catch (UnsupportedEncodingException e) {
    		// ...
    	}   	
    }
    
	//Restaurar pass.
    public void enviarMsjRecupPass(String url, String correoDestinatario, String nombreDestinatario) {
    	passwordRecovery(url, nombreDestinatario); //Esta funcion asigna valores a parametros de la clase (asunto y cuerpo del mensaje)
    	setMessageData(correoDestinatario);   //Esta funcion recoge los datos de los parametros y envia a destinatario.
    }
    
    //Validar cuenta.
    public void enviarMsjConfirmCuenta(String url, String correoDestinatario, String nombreDestinatario){
    	accountConfirm(url, nombreDestinatario);
    	setMessageData(correoDestinatario);
	  
    }
  
    //Agregado como gerente
    public void enviarMsjConfirmacionEmpresa(String url, String correoDestinatario, String nombreDestinatario){
    	accountConfirmEmp(url, nombreDestinatario);
    	setMessageData(correoDestinatario);	  
    }
    
    public void enviarMsjAgregacionEmpresa(String url, String correoDestinatario, String nombreDestinatario){
    	companyUnion(url, nombreDestinatario);
    	setMessageData(correoDestinatario);
    }
    
    //Alerta de inicio de sesion.
    public void enviarMsjAlertInicioSesion(String correoDestinatario, String nombreDestinatario){
    	signInAlert(nombreDestinatario);
    	setMessageData(correoDestinatario);	  
    }
 
}