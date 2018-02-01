package juegoVida;

import java.util.Scanner;

public class Main {
	/**
	 * Función principal del programa, a partir de ella se ejecuta todo lo demás
	 * @param args
	 */
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        Mundo mundo = new Mundo();
        Controlador c = new Controlador(mundo, in);
        
        c.realizaSimulacion();
    }
}