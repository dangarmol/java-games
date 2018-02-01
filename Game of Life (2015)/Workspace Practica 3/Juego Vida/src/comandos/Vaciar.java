package comandos;

import controlador.Controlador;

public class Vaciar implements Comando {

	 /**
     * Ejecuta el dicho método, el cual permite vaciar la superficie, eliminando todas las celulas, dejando un tablero vacio
     * @param controlador recibe el mundo para poder llamar al método
     */
	@Override
	public void ejecuta(Controlador controlador) {
		controlador.vaciarMundo();
	}

	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	public Comando parsea(String[] cadenaComando){
		if(cadenaComando[0] != null && cadenaComando[0].equals("VACIAR") && (cadenaComando.length == 1)){		
			return new Vaciar();
		}
		return null;
	}

	/**
     * Devuelve la información del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	@Override
	public String textoAyuda() {
		return "VACIAR: Mata a todas las celulas y deja la superficie libre." + System.getProperty("line.separator");
	}
}