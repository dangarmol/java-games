package comandos;

import java.io.IOException;

import controlador.Controlador;
import excepciones.ErrorLecturaTipoCelula;
import excepciones.PalabraIncorrecta;
import excepciones.FormatoNumericoIncorrecto;

public class Cargar implements Comando {

	private String nombreFichero;
	
	/**
     * Carga una partida de un fichero
     * @param controlador recibe el mundo para poder llamar al m�todo
	 * @throws ErrorLecturaTipoCelula 
     */
	@Override
	public void ejecuta(Controlador controlador) throws IOException, PalabraIncorrecta, FormatoNumericoIncorrecto, ErrorLecturaTipoCelula {
		controlador.cargar(this.nombreFichero + ".txt");
	}

	@Override
	public Comando parsea(String[] cadenaComando){
		if (cadenaComando[0] != null && cadenaComando[0].equals("CARGAR")  && (cadenaComando.length == 2)) {
			
			nombreFichero = cadenaComando[1]; // conversion de string a integer	
			
			return this;
		}		return null;
	}

	@Override
	public String textoAyuda() {
		return "CARGAR: carga el archivo con el nombre a elegir" + System.getProperty("line.separator");

	}

}
