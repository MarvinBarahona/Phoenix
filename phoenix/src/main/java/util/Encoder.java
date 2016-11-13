package util;

public class Encoder {
	//Desplaza uno en ASCII cada caracter.
	public static String codificar(String correo){
		String r = "";
		
		for(char c : correo.toCharArray()){
			int num = (int) c;
			num += 1;
			r += (char)num;
		}
		
		return r;
	}
	
	public static String decodificar(String r){
		String correo = "";
		if(r != null){
			for(char c : r.toCharArray()){
				int num = (int) c;
				num -= 1;
				correo += (char)num;
			}
		}
		
		return correo;
	}
}
