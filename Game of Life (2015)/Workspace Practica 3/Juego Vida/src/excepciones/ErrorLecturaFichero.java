package excepciones;

/**
 * Esta clase permite monitorizar la lectura 
 * de cada l�nea de un fichero e informar de un error 
 * al encontrar una l�nea no esperada
 */
public class ErrorLecturaFichero {
	private static int contLinea;
	private static String nombreFichero;
	
	public static void setNombreFichero(String nombreFichero){
		ErrorLecturaFichero.nombreFichero = nombreFichero;
	}
	
	public static String errorLectura(){
		return "L�nea con datos err�neos en el fichero " + nombreFichero + " - L�nea " + getContLinea();
	}
		
	public static int incrementaContLinea(){
		return ++contLinea;
	}

	public static int getContLinea() {
		return contLinea;
	}

	public static int setContLinea(int contLinea) {
		ErrorLecturaFichero.contLinea = contLinea;
		return contLinea;
	}

	public static void ini() {
		ErrorLecturaFichero.contLinea = 0;
	}
}
