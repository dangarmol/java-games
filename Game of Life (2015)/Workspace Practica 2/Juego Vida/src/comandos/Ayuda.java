package comandos;

import juegoVida.Mundo;

public class Ayuda extends Comando {

	/**
     * Muestra las instrucciones del juego
     * @param mundo recibe el mundo para poder llamar al método
     */
	@Override
	public void ejecuta(Mundo mundo) {		
		mundo.muestraAyuda();
	}

	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	public Comando parsea(String[] cadenaComando) {	
		if(cadenaComando[0] != null && cadenaComando[0].equals("AYUDA")  && (cadenaComando.length == 1)){		
			return new Ayuda();
		}
		return null;
	}
	
	/**
     * Devuelve la información del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	@Override
	public String textoAyuda() {		
		return "AYUDA: Muestra este menu de ayuda" + System.getProperty("line.separator");
	}

}
