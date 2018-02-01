package comandos;

public class ParserComandos {
	/**
     * Inicialización del array de comando[]
     */
	private static final Comando[] comandos = { 
		new Ayuda(), new Iniciar(), new CrearCelulaSimple(),
		new CrearCelulaCompleja(), new EliminarCelula(),
		new Paso(), new Vaciar(), new Salir()
	};
	
	/**
     * Recorre los metodos textoAyuda() de las clases que heredan de comando y almacena la cadena de strings en la variable ayudaTexto
     * @return devuelve la informcaion e instrucciones del juego
     */
	static public String ayudaComandos() {
		String ayudaTexto = System.getProperty("line.separator") + "Las instrucciones son: " + System.getProperty("line.separator");
		
		for (int i = 0; i < comandos.length; i++){
			ayudaTexto += comandos[i].textoAyuda();
		}
		return ayudaTexto;
	}
	
	/**
     * A través de un comando dado, va preguntado a los comandos si alguno coincide
     * @return null si el comando es incorrecto, o el comando requerido
     */
	static public Comando parseaComandos(String[] cadenas) {
		int i = 0;
		boolean encontrado = false;
		Comando comando = null;
		
		while (i < ParserComandos.comandos.length && !encontrado){
			comando = comandos[i].parsea(cadenas);
			if (comando != null){
				encontrado = true;
			}
			else{
				i++;
			}
		}
		
		return comando;
	}
	
}