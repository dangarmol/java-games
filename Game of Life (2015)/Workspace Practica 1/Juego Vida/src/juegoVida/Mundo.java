package juegoVida;

import java.util.ArrayList;

public class Mundo {

    private final int NUMCELL = 5; //número de células iniciales
    public static final int COLUMNAS = 5;
    public static final int FILAS = 5;
    private Superficie superficie;

    public Mundo() {
        superficie = new Superficie(FILAS, COLUMNAS);

        int celulasColocadas = 0;
        int fil;
        int col;
        while (celulasColocadas < NUMCELL) { //do while?

            fil = (int) (Math.random() * FILAS);
            col = (int) (Math.random() * COLUMNAS);

            if (this.superficie.esVacia(fil, col)) {
                this.superficie.nuevaCelula(fil, col, new Celula());
                celulasColocadas++;
            }
        }
    }

   /**
    * Esta función es la más importante del juego, recorre todas las casillas del tablero y llamando a las funciones adecuadas, "evoluciona" todas las células,
    * comprueba todo lo necesario una a una y la hace reproducirse, morir, nacer, moverse... etc.
    * (Comprobar los comentarios dentro de la función para más información en detalle)
    * También añade a un string los mensajes de todo lo que se va ejecutando para mostrar el bloque completo
    */
    public void evoluciona() {
        int fil, col;
        boolean[][] puedeMover = new boolean[FILAS][COLUMNAS];  //creacion de una matriz booleana auxiliar que determina las posiciones donde se encuentran las celulas
        boolean muerePasos = false;                             //para evtar que una celula se mueva dos veces
        boolean muereRep = false;
        startMatrix(puedeMover);
        
        ArrayList<String> arrayMensajes = new ArrayList<String>();
        
        for (int i = 0; i < FILAS; i++) {                   //bucle for anudado que recorre toda la superficie
            for (int j = 0; j < COLUMNAS; j++) {
                if (puedeMover[i][j]) {                     //solo movermos aquellas celulas que se hayan licalizado antes de que todas las demas se hayan movido
                    Casilla casilla = this.superficie.buscarMovimiento(i, j, puedeMover);  //funcion que devueve una casilla posible a la qu moverse

                    if (casilla == null){ //en caso de que la celula este rodeada o no pueda moverse
                        this.superficie.incPasos(i, j); //incrementamos los pasos dados
                        this.superficie.incNoMov(i, j); //incrementamos los pasos de noMovimineto
                        
                        if(this.superficie.comprobarPasos(i, j)){  //en caso de que la celula haya alcanzado el numero maximo de pasos permitidos de reproduccion, se muere  
                        	muereRep = true;
                        	arrayMensajes.add("Muera la celula de la casilla " + i +"," + j + " por no poder reproducirse" );
                        }
                        if(this.superficie.comprobarNoMov(i, j)){   //en caso de que la celula haya alcanzado el numero maximo de pasos permitidos de nomimiento, se muere  
                        	muerePasos = true;
                        	arrayMensajes.add("Muera la celula de la casilla " + i +"," + j + " por no moverse" );
                        }
                        if (muerePasos || muereRep){//comprueba y mata si es necesario
                            this.superficie.killCell(i, j);
                        }
                        else{
                            arrayMensajes.add("No se mueve la celula  " + i +"," + j );//celula que esta en inactiidad ya que no aha alcanzado los max de noMov o Repro
                        }
                    }
                    else {
                        fil = casilla.getFila();        // fil y col devuelven la la posicion de una casilla vecina a q ue moverse              
                        col = casilla.getColumna();
                        
                        if (this.superficie.comprobarPasos(i, j)) { //comprbamos que la celula haya dado las pasos para poder reproducirse
                            this.superficie.seReproduce(fil, col, i, j); //en ese caso, deja una cria en la nueva posicion, y mantiene en la que estaba, las dos inicializadas a 0 los pasos
                            arrayMensajes.add("Nace una nueva celula en " + fil +"," + col + " cuyo padre ha sido " + i + "," + j);
                        }
                        else {                                                        
                            this.superficie.seMueve(fil, col, i, j); //en caso qde que no haya conseguido los pasos necesarios para reproducirse, simplemente se desplaza la ceua, dejando libre la posicion anterior
                            arrayMensajes.add("Movimiento de " + i + "," + j + " a " + fil + "," + col);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < arrayMensajes.size(); i++){
            System.out.println(arrayMensajes.get(i));
        }
    }
    
    /**
     * Inicia la matriz de booleanos diciendo si una célula puede mover, está vacía, ya ha movido etc...
     * @param puedeMover es la matriz
     */
    public void startMatrix(boolean puedeMover[][]) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                puedeMover[i][j] = !this.superficie.esVacia(i, j);
            }
        }
    }
    
    /**
     * Vacía la superficie del juego entera, matando a todas las células
     */
    public void vaciarSuperficie(){
        for(int i = 0; i < FILAS; i++){
            for (int j = 0; j < COLUMNAS; j++){
                this.superficie.killCell(i, j);
            }                
        }
    }
    
    /**
     * Devuelve el String ya creado en Superficie
     */
    @Override    
    public String toString(){
        return this.superficie.toString();
    }

    /**
     * Devuelve si una casilla de la superficie está vacía
     * @param x Coordenada x de la célula de la superficie
     * @param y Coordenada y de la célula de la superficie
     * @return Devuelve true si está vacía, false si no
     */
	public boolean esVacia(int x, int y) {
		return this.superficie.esVacia(x, y);
	}
	
	/**
	 * Crea una nueva célula sin pasos en las coordenadas dadas por parámetro
	 * @param x Coordenada x de la célula
	 * @param y Coordenada y de la célula
	 */
	public void crearCel(int x, int y) {
		this.superficie.nuevaCelula(x, y, new Celula());
	}

	/**
	 * Mata la célula pasada por parámetro
	 * @param x Coordenada x de la célula
	 * @param y Coordenada y de la célula
	 */
	public void matarCel(int x, int y) {
		this.superficie.killCell(x, y);
	}
}