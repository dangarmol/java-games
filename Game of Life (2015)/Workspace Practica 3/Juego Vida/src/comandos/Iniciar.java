package comandos;

import controlador.Controlador;

public class Iniciar implements Comando {
	
	 /**
     * Ejecuta el dicho m�todo, el cual permite inicar un nuevo mundo,
     * eliminando las celulas presentes y genereando las de por defecto en posiciones aleatorias
     * @param controlador recibe el mundo para poder llamar al m�todo
     */
	@Override
	public void ejecuta(Controlador controlador) {
		controlador.inicia();
	}
	
	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	//comprobamos que no da null pointer ex, que la primera palabra coincida y que solo escriba el comando
	public Comando parsea(String[] cadenaComando){
		if(cadenaComando[0] != null && cadenaComando[0].equals("INICIAR") && (cadenaComando.length == 1)){		
			return new Iniciar(); //devolvemos el camando iniciar
		}
		return null;
	}
	
	/**
     * Devuelve la informaci�n del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	@Override
	public String textoAyuda() {
	return "INICIAR: Inicia una nueva simulacion con una poblacio�?n aleatoria" + System.getProperty("line.separator");
	}
}
