package controller;


//Objeto respuesta. Se usa para mandar más de una respuesta en formate JSON al controlador en js. 
public class Respuesta {
	String msg;
	String tipoUsuario;
	String tipoEmpleado;
	
	public Respuesta() {
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}	
}
