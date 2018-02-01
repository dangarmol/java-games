package comandos;

import java.io.IOException;

import controlador.Controlador;

public class Guardar implements Comando {

	private String nomFichero;
	
	public Guardar(String nomFichero){
		this.nomFichero = nomFichero;
	}	
	
	public Guardar() {
		// TODO Auto-generated constructor stub
	}

	/**
     * Guarda una partida en un fichero
     * @param controlador recibe el mundo para poder llamar al método
     */
	@Override
	public void ejecuta(Controlador controlador) throws IOException{
		controlador.guardar(nomFichero + ".txt");
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0] != null && cadenaComando[0].equals("GUARDAR")  && (cadenaComando.length == 2)) {
			
			nomFichero = cadenaComando[1]; // conversion de string a integer	
			
			return this;
		}
		return null;
	}

	@Override
	public String textoAyuda() {
		return "GUARDAR: guarda la partida en un fichero con nombre a elegir" + System.getProperty("line.separator");
	}

}
