package juegoVida;

import java.util.Scanner;

public class Controlador{

    private Mundo mundo;
    private Scanner in;

    public Controlador(Mundo mundo, Scanner in) {
        this.mundo = mundo;
        this.in = in;
    }

    /**
     * Esta función muestra el tablero de juego, y te pide un comando para proceder a su ejecución. Dependiendo del comando escrito por el usuario, ejecuta una
     * opción u otra.
     * PASO: Todo el tablero evoluciona, haciendo que todas las células mueran, nazcan, se muevan etc...
     * INICIAR: Crea un nuevo mundo con células aleatorias
     * CREARCELULA x y: Crea una nueva célula en la posición que el usuario ha introducido, comprobando previamente que esté vacío y dentro de las posiciones del
     * array
     * ELIMINARCELULA x y: Borra una célula por petición del usuario, comprobando previamente que haya una célula y que las posiciones estén en el array
     * AYUDA: Muestra los posibles comandos
     * VACIAR: Vacía el tablero entero y lo deja sin células
     * SALIR: Finaliza la ejecución del programa
     */
    public void realizaSimulacion() {
        boolean salir = false;
        System.out.println(toString());
        do {
            System.out.println("Comando > ");
            String opcion = in.nextLine();

            if (opcion.equals("PASO")) {        // si el usuario escribe PASO, todas las celulas se mueven
                this.mundo.evoluciona();
            }
            else if (opcion.equals("INICIAR")) { // creacion de un nuevo mundo
                this.mundo = new Mundo();                  //inicializamos el mundo, creando celulas en posiciones aleatorias en la superficie
            }
            else if (opcion.startsWith("CREARCELULA")) { // creacion de una celula en la posicion que indique el usuario
                String[] campos = opcion.split(" ");
                int x = Integer.parseInt(campos[1]);
                int y = Integer.parseInt(campos[2]);
                if (x < Mundo.FILAS && y < Mundo.COLUMNAS) {
		            if (this.mundo.esVacia(x, y)) { //si dicha posicion esta vacía, creamos la celula
		                this.mundo.crearCel(x, y);
		            } else {
		                System.out.println("La casilla estaba ocupada ya");  //en caso contrario mandamos mensaje de error
		            }
                }
                else {
                	System.out.println("Las coordenadas introducidas se salen del tablero.");
                }
            }
            else if (opcion.startsWith("ELIMINARCELULA")) {
                String[] campos = opcion.split(" ");
                int x = Integer.parseInt(campos[1]);
                int y = Integer.parseInt(campos[2]);
                if (x < Mundo.FILAS && y < Mundo.COLUMNAS) {
	                if (!this.mundo.esVacia(x, y)) {    //eliminacion de una celula en la posicion f c
	                    this.mundo.matarCel(x, y);
	                } else {
	                    System.out.println("La casilla esta vacia");
	                }
                }
                else {
                	System.out.println("Las coordenadas introducidas se salen del tablero.");
                }
            }
            else if (opcion.equals("AYUDA")) {
                System.out.println(instrucciones());
            }
            else if (opcion.equals("VACIAR")) {
                this.mundo.vaciarSuperficie();
            }
            else if (opcion.equals("SALIR")) {
                System.out.println("Saliendo...");
                salir = true;
            }
            else { // opc incorrecta
                System.out.println("Opcion no reconocida");
            }
            System.out.println(System.getProperty("line.separator"));
            if(!opcion.equals("SALIR")){
            	System.out.println(toString());
            }
        } while (!salir);
    }
    
    // Guardamos el valor de la prop line.separator, que será constante durante la ejecución
    private final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    /**
     * Genera un string con todas las instrucciones
     * @return Devuelve el string en sí
     */
    private String instrucciones() {
        return (LINE_SEPARATOR + "Las instrucciones son: " + LINE_SEPARATOR
                + "PASO: Evoluciona todas las celulas." + LINE_SEPARATOR
                + "INICIAR: Inicia el mundo con un numero de celulas predeterminado." + LINE_SEPARATOR
                + "CREARCELULA x y: Crea una nueva celula en las coordenadas (x, y)" + LINE_SEPARATOR
                + "ELIMINARCELULA x y: Mata la celula de la posicion (x, y)" + LINE_SEPARATOR
                + "AYUDA: Muestra este menu de ayuda" + LINE_SEPARATOR
                + "VACIAR: Mata a todas las celulas y deja la superficie libre." + LINE_SEPARATOR
                + "SALIR: Finaliza la ejecucion del programa y sale.");
    }
    
    /**
     * Devuelve el toString de mundo del tablero
     */
    @Override
    public String toString() {
        return this.mundo.toString();
    }
}