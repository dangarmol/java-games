package juegoVida;

import java.util.ArrayList;

public class GuiaEjecucion {
	
	public GuiaEjecucion(){
		
	}
	public static ArrayList<String> arrayMensajes = new ArrayList<String>();		
	
	/**
     * Método que a través de un bucle for, muestra la lista de errores
     */
	public static void textoAyuda(){
		for(int i = 0; i < arrayMensajes.size(); i++){
			System.out.println(arrayMensajes.get(i) + System.getProperty("line.separator"));
		}
		arrayMensajes = new ArrayList<String>();
	}
	
	public static ArrayList<String> arrayEvoluciona = new ArrayList<String>();		
	
	/**
     * Método que a través de un bucle for, muestra la lista de movimientos que realizan las células
     */	
	public static void pasosDados(){
		for(int i = 0; i < arrayEvoluciona.size(); i++){
			System.out.println(arrayEvoluciona.get(i) + System.getProperty("line.separator"));
		}
		arrayEvoluciona = new ArrayList<String>();
	}
		
}