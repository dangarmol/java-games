package comandos;

import controlador.Controlador;

public class Ayuda implements Comando {

	/**
     * Muestra las instrucciones del juego
     * @param controlador recibe el mundo para poder llamar al m�todo
     */
	@Override
	public void ejecuta(Controlador controlador) {
		System.out.println(ParserComandos.ayudaComandos());
	}

	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	public Comando parsea(String[] cadenaComando){	
		if(cadenaComando[0] != null && cadenaComando[0].equals("AYUDA")  && (cadenaComando.length == 1)){		
			return new Ayuda();
		}
		return null;
	}
	
	/**
     * Devuelve la informaci�n del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	@Override
	public String textoAyuda() {		
		return "AYUDA: Muestra este menu de ayuda" + System.getProperty("line.separator");
	}
}
