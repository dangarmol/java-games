package comandos;
import controlador.Controlador;
import mundos.Mundo;
import mundos.MundoComplejo;
import mundos.MundoSimple;

public class Jugar implements Comando {

	public static final String JUEGO_SIMPLE = "SIMPLE";
	public static final String JUEGO_COMPLEJO = "COMPLEJO";
	
	private Mundo mundo;
	int fil, col, nCelSimples, nCelComplejas;
	
	public Jugar(){		
	}
	
	public Jugar(Mundo mundo) {
		this.mundo = mundo;
	}
	
    

	/**
     * Ejecuta el dicho método, el cual permite jugar una partida en un mundo simple o complejo
     * @param controlador recibe el mundo para poder llamar al método
     */
	@Override
	public void ejecuta(Controlador controlador) {
		controlador.juega(this.mundo);
	}

	/**
     *Comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	@Override
	public Comando parsea(String[] cadenaComando){
	/// comprobamos que no da null pointer ex, que la primera palabra coincida y que el usuario escriba comando mas dos coordenadas
			if (cadenaComando[0] != null && cadenaComando[0].equals("JUGAR") && cadenaComando.length > 2) {
				//tipoJuego = cadenaComando[1];
				if(cadenaComando[1].equals(JUEGO_SIMPLE) && cadenaComando.length == 5){
					//boolean comprobarRangoCelulas(int fil, int col);
					fil = Integer.parseInt(cadenaComando[2]); //conversion de string a integer
					col = Integer.parseInt(cadenaComando[3]);
					nCelSimples = Integer.parseInt(cadenaComando[4]); //conversion de string a integer
					
					if(nCelSimples <= fil*col){
						return new Jugar(new MundoSimple(fil, col, nCelSimples));
					}
				}
				else if(cadenaComando[1].equals(JUEGO_COMPLEJO) && cadenaComando.length == 6){
					//boolean comprobarRangoCelulas(int fil, int col);
					fil = Integer.parseInt(cadenaComando[2]); //conversion de string a integer
					col = Integer.parseInt(cadenaComando[3]);
					nCelSimples = Integer.parseInt(cadenaComando[4]); //conversion de string a integer	
					nCelComplejas = Integer.parseInt(cadenaComando[5]); //conversion de string a integer
					
					if((nCelComplejas + nCelSimples) <= fil*col){
						return new Jugar(new MundoComplejo(fil, col, nCelSimples, nCelComplejas));
					}
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
		return "JUGAR: comando el cual inicializa un juego nuevo ya sea un mundo simple o complejo "
				+ "con sus dimensiones y us correspondientes celulas" + System.getProperty("line.separator");
	}
}

