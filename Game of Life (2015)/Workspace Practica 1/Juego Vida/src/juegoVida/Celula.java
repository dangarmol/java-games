package juegoVida;

public class Celula {

    private int noMovimiento;
    private int pasos; //contabiliza el numero de pasos (tanto si se ha movido como si no)

    public Celula() {  //Constructor
        this.noMovimiento = 0;
        this.pasos = 0;
    }

    /**
     * Devuelve los pasos sin moverse de una célula
     * @return Pasos sin moverse
     */
    public int getNoMovimiento() {
        return this.noMovimiento;
    }
    
    /**
     * Devuelve los pasos dados por una célula para reproducirse
     * @return Pasos dados para reproducción
     */
    public int getPasos() {
        return this.pasos;
    }
    
    /**
     * Introduce por parametro los pasos sin moverse a la célula
     * @param Pasos sin moverse
     */
    public void setNoMovimiento(int noMovimiento) {
        this.noMovimiento = noMovimiento;
    }
    
    /**
     * Introduce por parámetro los pasos de reproducción a la célula
     * @param Pasos de reproducción
     */
    public void setPasos(int pasos) {
        this.pasos = pasos;
    }
    
    /**
     * Incrementa en una unidad los pasos sin mover de la célula
     */
    public void incrementaNoMovimiento() {
        this.noMovimiento++;
    }
    
    /**
     * Incrementa en una unidad los pasos de reproducción de la célula
     */
    public void incrementaPasos() {
        this.pasos++;
    }
    
    /**
     * Devuelve el String de cada Célula
     */
    @Override
    public String toString() {
        return pasos + "-" + noMovimiento;
    }
}