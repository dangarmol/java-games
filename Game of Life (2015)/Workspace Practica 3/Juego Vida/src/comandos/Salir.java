package comandos;

import controlador.Controlador;

public class Salir  implements Comando {

	 /**
     * Ejecuta el dicho m�todo, el cual permite finalizar la simulacion y salir de la aplicaci�n
     * @param controlador recibe el mundo para poder llamar al m�todo
     */
	@Override
	public void ejecuta(Controlador controlador) {
		controlador.simulacionTerminada();
	}

	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	public Comando parsea(String[] cadenaComando) {
		if(cadenaComando[0] != null && cadenaComando[0].equals("SALIR") && (cadenaComando.length == 1)){		
			return new Salir();
		}
		return null;
	}

	/**
     * Devuelve la informaci�n del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	@Override
	public String textoAyuda() {		
		return "SALIR: Finaliza la ejecucion del programa y sale." + System.getProperty("line.separator");
	}

}
