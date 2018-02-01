package juegoVida;
import java.util.Scanner;

import controlador.Controlador;
import mundos.Mundo;

public class Main {
	/**
	 * Funci�n principal del programa, a partir de ella se ejecuta todo lo dem�s
	 */
    public static void main(String[] args) {
        
       Scanner in = new Scanner(System.in);
       Mundo mundo = null;
       Controlador c = new Controlador(mundo, in);
        
       c.realizaSimulacion();
       in.close();
    }
}