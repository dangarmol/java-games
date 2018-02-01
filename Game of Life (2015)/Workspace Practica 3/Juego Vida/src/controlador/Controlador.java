package controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import juegoVida.GuiaEjecucion;
import mundos.Mundo;
import mundos.MundoComplejo;
import mundos.MundoSimple;
import comandos.Comando;
import comandos.ParserComandos;
import excepciones.CelulaIncorrecta;
import excepciones.ErrorLecturaFichero;
import excepciones.ErrorLecturaTipoCelula;
import excepciones.FormatoNumericoIncorrecto;
import excepciones.IndicesFueraDeRango;
import excepciones.PalabraIncorrecta;
import excepciones.PosicionOcupada;
import excepciones.PosicionVacia;

public class Controlador{
	
    private Mundo mundo;
    private Scanner in;
    private boolean simulacionTerminada;   

    public Controlador(Mundo mundo, Scanner in) {
        this.mundo = mundo;
        this.in = in;
    }

    /**
     * Realiza la simulacion, para ello lee por pantalla lo que escribe el usuario y ejecita el programa
     */
    public void realizaSimulacion() {
        do {
        	if (mundo != null) {
        		System.out.println(mundo.toString());
        	}
        	
        	System.out.println("Elija un comando: "); // solicitud del comando
            System.out.println("Comando > ");
            String opcion = in.nextLine();
            opcion = opcion.toUpperCase();

            String[] words = opcion.split(" ");
            
            try {
            	if((mundo != null) || (mundo == null && !comandosNoPermitidos(opcion))){
	            	Comando comando = ParserComandos.parseaComandos(words); //busca el comando comparandolo
	            	
		            if (comando != null) {	
						comando.ejecuta(this); // Si lo encuentra, lo ejecuta
		            }
		            else {
		            	System.out.println("Comando incorrecto");
		            }
            	}
            
            } catch (IOException | PalabraIncorrecta | ErrorLecturaTipoCelula | FormatoNumericoIncorrecto | IndicesFueraDeRango | InputMismatchException | PosicionVacia | PosicionOcupada | CelulaIncorrecta e) {
        		e.printStackTrace();
        	}
            
            
            GuiaEjecucion.textoAyuda();		//muestra codigos de mensaje que no afecten a evoluciona
            GuiaEjecucion.pasosDados();     //muestra codigos de mensajes que tengan que ver con los movimientos de las celulas
        } while (!this.simulacionTerminada);	//en casod e que el usuario esciba el comando SALIR, se termina la ejecuci�n
    }
    
    /**
     * funcion que no permite la eecion de dichos comandos cunado iniciamos la aplicacion
     * con un mundo vacio por defecto
     * @param comandos
     * @return
     */
    private boolean comandosNoPermitidos(String comandos){
    	boolean encontrado = false;
    	String iniciar = "INICIAR".toUpperCase();
    	String paso = "paso".toUpperCase();
    	String vaciar = "vaciar".toUpperCase();
    	String crearCelula = "crearCelula".toUpperCase();
    	String eliminarCelula = "eliminarCelula".toUpperCase();

    	
    	comandos = comandos.toUpperCase();
    	String[] words = comandos.split(" ");
    	if(words[0].equals(iniciar)){
    		encontrado = true;
    	}
    	else if(words[0].equals(paso)){
    		encontrado = true;
    	}
    	else if(words[0].equals(vaciar)){
    		encontrado = true;
    	}
    	else if(words[0].equals(crearCelula)){
    		encontrado = true;
    	}
    	else if(words[0].equals(eliminarCelula)){
    		encontrado = true;
    	}
    	
    	return encontrado;
    }
    
  // COMANDO CREARCELULA DE FORMA GEN�RICA
  	public void crearCelula(int x, int y) throws IndicesFueraDeRango, PosicionOcupada, CelulaIncorrecta {
  		mundo.crearCelula(x, y,this.in);  		
  	}
  	
  	
  // METODO ELIMINAR
  	public void eliminarCelula(int x, int y) throws IndicesFueraDeRango, PosicionVacia {
  		mundo.eliminarCelula(x, y);
  		
  	}
  	
  // COMANDO INICIAR
  	public void inicia() {
  		mundo.inicializaMundo();		
  	}
  	
  // COMANDO JUEGA
  	public void juega(Mundo mundo){
  		this.mundo = mundo;	
  	}
    
  	// COMANDO EVOLUCIONA
    public String evoluciona(){
    	mundo.evoluciona();
    	return "";
    }
    
   // COMANDO SALIR
  	public void simulacionTerminada() {
  		simulacionTerminada = true;  		
  	}
	
  	// COMANDO GUARDAR
	public void guardar(String nomFichero)throws IOException{
		mundo.guardar(nomFichero);
	}
	
	// M�TODO CARGAR
	public void cargar(String nombre) throws PalabraIncorrecta, FormatoNumericoIncorrecto, FileNotFoundException, ErrorLecturaTipoCelula{
		
		ErrorLecturaFichero.setNombreFichero(nombre);
		Scanner sc = null;
		Mundo mundoAux = mundo;
		try {
		FileReader fr = new FileReader(nombre);
		
		sc = new Scanner(fr);
		
		String tipoMundo = sc.nextLine();
		ErrorLecturaFichero.incrementaContLinea();
		 
		if(tipoMundo.equals("simple")){
			mundoAux = new MundoSimple();
		} else if (tipoMundo.equals("complejo")){
			mundoAux = new MundoComplejo();
		} else {
			
			throw new PalabraIncorrecta(ErrorLecturaFichero.errorLectura());
		}
				
		mundoAux.cargar(sc);
		this.mundo = mundoAux;
		}
		finally {
			if (sc!=null) sc.close();
		}
	}

	//COMANDO VACIAR
	public void vaciarMundo() {
		mundo.vaciarMundo();		
	}
}