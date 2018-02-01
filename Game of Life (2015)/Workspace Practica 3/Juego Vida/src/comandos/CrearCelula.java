package comandos;

import controlador.Controlador;
import excepciones.CelulaIncorrecta;
import excepciones.IndicesFueraDeRango;
import excepciones.PosicionOcupada;

public class CrearCelula implements Comando {

	private int x;
    private int y;
    
    /**
     * Ejecuta el dicho método, el cual permite crear una celula compleja en las posiciones x,y
     * @param controlador recibe el mundo para poder llamar al método
     * @throws PosicionOcupada 
     * @throws IndicesFueraDeRango 
     * @throws CelulaIncorrecta 
     */
	@Override
	public void ejecuta(Controlador controlador) throws IndicesFueraDeRango, PosicionOcupada, CelulaIncorrecta {
		controlador.crearCelula(x, y);
	}

	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * y que la longitud sea de 3: el comando y las dos coordenadas, en caso de querer invocar dicho comando
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	public Comando parsea(String[] cadenaComando){
		/// comprobamos que no da null pointer ex, que la primera palabra coincida y que el usuario escriba comando mas dos coordenadas
		if (cadenaComando[0] != null && cadenaComando[0].equals("CREARCELULA") && (cadenaComando.length == 3)) {
			if (cadenaComando[1] != null && cadenaComando[2] != null) { //comprobamos que no esten vacion los dos parametros
				x = Integer.parseInt(cadenaComando[1]); //conversion de string a integer
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
		return "CREARCELULA x y: Crea una nueva celula en las coordenadas (x, y) " + System.getProperty("line.separator")
				+ "En el caso de que sea un Mundo Simple se creará una célula simple, " + System.getProperty("line.separator")
				+ "El el caso de un Mundo Complejo se pedirá al usuario que espeficique el tipo de célula a crear."
				+ System.getProperty("line.separator");
	}
}
