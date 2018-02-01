package juegoVida;

public class Casilla {
    
    private int fila;
    private int columna;
    
    public Casilla(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }
    
    /**
     * Devuelve la fila de la casilla
     * @return fila
     */
    public int getFila(){
        return fila;
    }
    
    /**
     * Devuelve la columna de la casilla
     * @return columna
     */
    public int getColumna(){
        return columna;
    }
}