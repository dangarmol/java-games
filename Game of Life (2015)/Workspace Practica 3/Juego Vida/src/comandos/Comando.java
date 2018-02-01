package comandos;

import java.io.FileNotFoundException;
import java.io.IOException;

import controlador.Controlador;
import excepciones.ErrorLecturaTipoCelula;
import excepciones.IndicesFueraDeRango;
import excepciones.PalabraIncorrecta;
import excepciones.PosicionOcupada;
import excepciones.PosicionVacia;
import excepciones.CelulaIncorrecta;
import excepciones.FormatoNumericoIncorrecto;

public interface Comando {
	
	/**
     * Método abstracto que se encarga de ejecutar un metdo u otro en funcion del comando al que le corresponda
     * @param controlador recibe el mundo para poder llamar al método
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws IndicesFueraDeRango 
	 * @throws FormatoNumericoIncorrecto 
	 * @throws PalabraIncorrecta 
	 * @throws excepciones.FormatoNumericoIncorrecto 
	 * @throws PosicionOcupada 
	 * @throws CelulaIncorrecta 
	 * @throws ErrorLecturaTipoCelula 
     */
	public void ejecuta(Controlador controlador) throws FileNotFoundException, IOException, PosicionVacia, IndicesFueraDeRango, PalabraIncorrecta, FormatoNumericoIncorrecto, PosicionOcupada, CelulaIncorrecta, ErrorLecturaTipoCelula;
	
	/**
     *Método abstracto que comprueba si el parametro recibido coincide con el comando propio
     * @param cadenaComando es el comando que el usuario ha escrito por pantalla, el cual vamos a comparar
     * @return null en caso de no ser el correcto, en caso contrario devuelve el comando propio
     */
	public Comando parsea(String[] cadenaComando);
	
	/**
     * Devuelve la información del comando, su funcionalidad
     * @return cadena String de ayuda
     */
	public String textoAyuda();
}