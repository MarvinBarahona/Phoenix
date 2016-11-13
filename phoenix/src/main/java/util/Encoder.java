package util;

public class Encoder {
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
		
		for(char c : r.toCharArray()){
			int num = (int) c;
			num -= 1;
			correo += (char)num;
		}
		
		return correo;
	}
}
