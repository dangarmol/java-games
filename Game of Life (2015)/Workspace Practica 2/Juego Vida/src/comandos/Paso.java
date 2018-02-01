package comandos;

import juegoVida.Mundo;

public class Paso extends Comando {

	 /**
     * Ejecuta el dicho método, el cual permite evolucionar la superficie, permitiendo que las celulas se muevan
     * @param mundo recibe el mundo para poder llamar al método
     */
	@Override
	public void ejecuta(Mundo mundo) {
		mundo.evoluciona();
	}

	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	public Comando parsea(String[] cadenaComando) {
		if(cadenaComando[0] != null && cadenaComando[0].equals("PASO") && (cadenaComando.length == 1)){		
			return new Paso();
		}
		
		return null;
	}

	/**
     * Devuelve la información del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	@Override
	public String textoAyuda() {
		
		return "PASO: Evoluciona todas las celulas." + System.getProperty("line.separator");
	}

}
