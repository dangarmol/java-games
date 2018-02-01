package comandos;

import juegoVida.Mundo;

public class EliminarCelula extends Comando {
	
	int x;
    int y;

    /**
     * Ejecuta el dicho método, el cual permite eliminar una celula en las posiciones x,y
     * @param mundo recibe el mundo para poder llamar al método
     */
	@Override
	public void ejecuta(Mundo mundo) {	
		mundo.eliminarCelula(x, y);
	}

	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * y que la longitud sea de 3: el comando y las dos coordenadas, en caso de querer invocar dicho comando
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	public Comando parsea(String[] cadenaComando) {
		/// comprobamos que no da null pointer ex, que la primera palabra coincida y que el usuario escriba comando mas dos coordenadas
		if (cadenaComando[0] != null && cadenaComando[0].equals("ELIMINARCELULA")  && (cadenaComando.length == 3)) {
			if (cadenaComando[1] != null && cadenaComando[2] != null) { //comprobamos que no esten vacion los dos parametros
				x = Integer.parseInt(cadenaComando[1]);//conversion de string a integer
				y = Integer.parseInt(cadenaComando[2]);
				return this;
			}
		}
		return null;
	}

	/**
     * Devuelve la información del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	@Override
	public String textoAyuda() {
		return "ELIMINARCELULA x y: Elimina la celula de la posicion (x, y)" + System.getProperty("line.separator");
	}
}