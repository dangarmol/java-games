package comandos;

import juegoVida.Mundo;

public abstract class Comando {
	
	/**
     * Método abstracto que se encarga de ejecutar un metdo u otro en funcion del comando al que le corresponda
     * @param mundo recibe el mundo para poder llamar al método
     */
	public abstract void ejecuta(Mundo mundo);
	
	/**
     *Método abstracto que comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	public abstract Comando parsea(String[] cadenaComando);
	
	/**
     * Devuelve la información del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	public abstract String textoAyuda();
}