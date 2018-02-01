package Celulas;

import juegoVida.Casilla;
import juegoVida.Superficie;

public abstract class Celula {
    
     
	protected boolean esComestible;
	
	 public Celula() {  //Constructor
	 }
	
	/**
     * Calcula la casilla de destino.
     * @param fil Coordenada x de donde se encuentra la celula
     * @param col Coordenada y de donde se encuentra la celula
     * @param superficie
     * @return Devuelve una casilla de destino a la celula
     */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	
	/**
	 * Método que devuelve si una celula es comestibel o no, es decir, celulaSimple o compleja
	 * @return true si es comestible(simple), false lo contrario
	 */
	public abstract boolean esComestible();
}