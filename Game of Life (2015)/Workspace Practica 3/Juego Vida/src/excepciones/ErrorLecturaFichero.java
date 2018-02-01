package excepciones;

/**
 * Esta clase permite monitorizar la lectura 
 * de cada línea de un fichero e informar de un error 
 * al encontrar una línea no esperada
 */
public class ErrorLecturaFichero {
	private static int contLinea;
	private static String nombreFichero;
	
	public static void setNombreFichero(String nombreFichero){
		ErrorLecturaFichero.nombreFichero = nombreFichero;
	}
	
	public static String errorLectura(){
		return "Línea con datos erróneos en el fichero " + nombreFichero + " - Línea " + getContLinea();
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
