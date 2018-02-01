package juegoVida;

import Celulas.CelulaCompleja;
import Celulas.CelulaSimple;
import comandos.ParserComandos;

public class Mundo {

	private static final int N_COLUMNAS = 3;
    private static final int N_FILAS = 3;
	private static final int N_CEL_SIMPLES = 3; //n√∫mero de c√©lulas iniciales
	private static final int N_CEL_COMPLEJAS = 2; //n√∫mero de c√©lulas iniciales

	private boolean simulacionTerminada;
	private String ayuda;
	private Superficie superficie;
	
	/**
     * CONSTRUCTORA
     */
	public Mundo() {
		simulacionTerminada = false;
		ayuda = ParserComandos.ayudaComandos();
		superficie = new Superficie(N_FILAS, N_COLUMNAS);

        inicia();
	}	
	
	/**
     * MÈtodo que ejecuta el comando INICIAR,
     * creando una nueva supericie vacia, y creando X celulasSimples y X celulasComplejas en posiciones aleatorias
     */
	public void inicia() {
		nuevaSuperficie();		//crea nueva superficie vacia
        int fil;
        int col;
        int celulasColocadas = 0;
        while (celulasColocadas < N_CEL_SIMPLES) { //coloca celulas simples enposiciones aleatorias
            fil = (int) (Math.random() * N_FILAS);
            col = (int) (Math.random() * N_COLUMNAS);

            if (this.superficie.esVacia(fil, col)) {
                this.superficie.nuevaCelula(fil, col, new CelulaSimple());
                celulasColocadas++;
            }
        }
        celulasColocadas = 0;
        while (celulasColocadas < N_CEL_COMPLEJAS) { //coloca celulas complejas enposiciones aleatorias
            fil = (int) (Math.random() * N_FILAS);
            col = (int) (Math.random() * N_COLUMNAS);
            if (this.superficie.esVacia(fil, col)) {
                this.superficie.nuevaCelula(fil, col, new CelulaCompleja());
                celulasColocadas++;
            }
        }
	}
	
	public void evoluciona () {
		boolean haMovido [][] = new boolean [N_FILAS][N_COLUMNAS];
		Casilla casilla;
		
		for (int i = 0; i < N_FILAS; i++) {
			for (int j = 0; j < N_COLUMNAS; j++) {
				haMovido[i][j] = false;
			}
		}
		for (int i = 0; i < N_FILAS; i++) {
			for (int j = 0; j < N_COLUMNAS; j++) {
				if ((!superficie.esVacia(i, j)) && (!haMovido[i][j])) {
					casilla = superficie.ejecutaMovimiento(i, j);
					if (casilla != null) {
						haMovido[casilla.getFila()][casilla.getColumna()] = true;
					}
				}
			}
		}
	}
	
	/**
     * Elimina una cÈlula, tanto si es compleja o simple, en la posicion x,y
     * @param x Coordenada x de la cÈlula
     * @param y Coordenada y de la cÈlula
     */
	public void eliminarCelula(int x, int y){
		if (x < N_FILAS && y < N_COLUMNAS) {
		    if (!superficie.esVacia(x, y)) { //eliminacion de una celula en la posicion x,y en caso de haber introducido una coordena correcta dentro del tablero
		    	superficie.killCell(x, y);
		    	System.out.println("Eliminada");
		    } else {						//casuÌstica de que hayamos escogido unas coordenadas dentro del tablero erroreas
            	GuiaEjecucion.arrayMensajes.add("La casilla esta vacia");
		    }
		}
		else {//casuÌstica de que hayamos escogido unas coordenadas FUERA del tablero
			GuiaEjecucion.arrayMensajes.add("Las coordenadas introducidas se salen del tablero.");
		}
	}
	
	/**
     * CreaciÛn una cÈlula simple, en la posicion x,y
     * @param x Coordenada x de la cÈlula
     * @param y Coordenada y de la cÈlula
     */
	public void crearCelulaSimple(int x, int y) {
        if (x < N_FILAS && y < N_COLUMNAS) {
            if (superficie.esVacia(x, y)) { //CREACION de una celula SIMPLE en la posicion x,y en caso de haber introducido una coordena correcta dentro del tablero
            	this.superficie.nuevaCelula(x, y, new CelulaSimple());
            } else {	//casuÌstica de que hayamos escogido unas coordenadas dentro del tablero erroreas
            	GuiaEjecucion.arrayMensajes.add("La casilla estaba ocupada ya"); //en caso contrario mandamos mensaje de error
            }
        }
        else {	//casuÌstica de que hayamos escogido unas coordenadas FUERA del tablero
        	GuiaEjecucion.arrayMensajes.add("Las coordenadas introducidas se salen del tablero.");
        }
	}
	
	/**
     * CreaciÛn una cÈlula simple, en la posicion x,y
     * @param x Coordenada x de la cÈlula
     * @param y Coordenada y de la cÈlula
     */
	public void crearCelulaCompleja(int x, int y) {
        if (x < N_FILAS && y < N_COLUMNAS) {
            if (superficie.esVacia(x, y)) { //CREACION de una celula COMPLEJA en la posicion x,y en caso de haber introducido una coordena correcta dentro del tablero
            	this.superficie.nuevaCelula(x, y, new CelulaCompleja());
            } else {	//casuÌstica de que hayamos escogido unas coordenadas dentro del tablero erroreas
            	GuiaEjecucion.arrayMensajes.add("La casilla estaba ocupada ya");
            }
        }
        else {	//casuÌstica de que hayamos escogido unas coordenadas FUERA del tablero
        	GuiaEjecucion.arrayMensajes.add("Las coordenadas introducidas se salen del tablero.");
        }
	}
	
	/**
	 * CreaciÛn d euna nueva superficie
     */
	public void nuevaSuperficie() {
		superficie = new Superficie(N_FILAS, N_COLUMNAS);
	}
	
	/**
     * Devuelve el valor de simulacionTerminada
     */
	public boolean esSimulacionTerminada(){
		return simulacionTerminada;
	}
	
	/**
     * Asigna el valor de simulacionTerminada
     */
	public void terminarSimulacion() {
		simulacionTerminada = true;
	}
	
	/**
     * Muestra la ayuda
     */
	public void muestraAyuda() {
		System.out.println(ayuda);
	}	
	
	/**
     * Devuelve el String ya creado en Superficie
     */
    @Override    
    public String toString(){
        return this.superficie.toString();
    }
}