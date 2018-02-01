package juegoVida;

public class Superficie {

    
    private Celula[][] celulas;
    private static final int PASOS_REPRODUCCION = 5;
    private static final int MAX_PASOS_SIN_MOVER = 2;

    public Superficie(int nf, int nc) {

        this.celulas = new Celula[Mundo.FILAS][Mundo.COLUMNAS];

        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                this.celulas[i][j] = null;
            }
        }
    }
    
    /**
     * Mata una célula dadas sus coordenadas por parámetro
     * @param posx Coordenada x de la célula
     * @param posy Coordenada y de la célula
     */
    public void killCell(int posx, int posy) {
        this.celulas[posx][posy] = null;
    }

    /**
     * Devuelve por booleano si la célula existe o la posición está vacía
     * @param i Coordenada x de la célula
     * @param j Coordenada y de la célula
     * @return true si no existe la célula, false si existe
     */
    public boolean esVacia(int i, int j) {
        return this.celulas[i][j] == null;
    }
    
    /**
     * Introduce una célula nueva en la matriz de células
     * @param fil Coordenada x de la célula
     * @param col Coordenada y de la célula
     * @param celula Célula a introducir
     */
    public void nuevaCelula(int fil, int col, Celula celula) {
        this.celulas[fil][col] = celula;
    }
    
    /**
     * Incrementa los pasos sin moverse de una célula con la función de la clase Celula
     * @param i Coordenada x de la célula
     * @param j Coordenada y de la célula
     */
    public void incNoMov(int i, int j) {
        this.celulas[i][j].incrementaNoMovimiento();
    }

    /**
     * Incrementa los pasos de reproducción de una célula con la función de la clase Célula
     * @param i Coordenada x de la célula
     * @param j Coordenada y de la célula
     */
    public void incPasos(int i, int j) {
        this.celulas[i][j].incrementaPasos();
    }

    /**
     * Comprueba que la célula no haya superado los pasos máximos sin moverse y lo devuelve
     * @param i Coordenada x de la célula
     * @param j Coordenada y de la célula
     * @return true si la célula ha superado los máximos pasos sin mover, false si no
     */
    public boolean comprobarNoMov(int i, int j) { 
        if (this.celulas[i][j].getNoMovimiento() > MAX_PASOS_SIN_MOVER) {
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Se encarga de buscar casillas de alrededor de una celula a las cuales podría moverse, cumpliendo siempre las reglas de restricción dadas
     * Una vez que ya hemos seleccionado las posibles casillas, elegimos una siempre que sea posible.
     * @param fil Coordenada x de donde se encuentra la celula
     * @param col Coordenada y de donde se encuentra la celula
     * @param puedeMover Matriz de booleanos que dice qué células pueden mover en este turno, se genera antes de iniciar el turno para evitar que muevan varias veces
     * @return Devuelve una casilla de forma aleatoria, o un valor nulo en caso de no haber ninguna
     */
    public Casilla buscarMovimiento(int fil, int col, boolean puedeMover[][]) {
        int contador = 0;
        int[] incremFila = {-1, -1, -1, 0, 1, 1, 1, 0};         // creamos los arrays incrementafil y col que contendran los numeros a añadir a la posicion actual de la celula
        int[] incremColumna = {-1, 0, 1, 1, 0, 1, 0, -1, -1};   //para poder acceder a las posiciones de alrededor
        Casilla[] posicionesLibres = new Casilla[8];  //Creacion de un array de casillas de tamaño 8, las posiciones posibles que puede rodear a una celula

        for (int w = 0; w < 8; w++) {                   //bucle que se ejecuta las 8 veces, para ir comprobando las posiciones de alrededor

            int nf = fil + incremFila[w];
            int nc = col + incremColumna[w];
            
            //comprobamos que que las posiciones de alrededor no esten fuera del rango de la matriz, que esten vacias y no haya habido una celula en esa posicion antes
            if ((this.correctos(nf, nc)) && (!puedeMover[nf][nc]) && (this.esVacia(nf, nc))){    
                posicionesLibres[contador]  = new Casilla(nf, nc);  // en caso de cumplirse esas tres condiciones procedemos a ir rellenando nuestro array
                contador++;
            }
        }
        
        if (contador == 0) {  //en caso de que nuestro array de casillas este vacia devolvemos null
            return null;
        } else {        // en caso contrario elegimos aleatoriamente una casilla a devolver
            int elegido = (int) (Math.random() * contador);
            return posicionesLibres[elegido];
        }
    }

    /**
     * Dados unas posiciones nf y nc de una celula, se encarga de ver si dicha coordenada se encuentra acotada dentro de la superficie
     * @param nf Coordenada x
     * @param nc Coordenada y
     * @return Devuelve un booleano true en caso de que dicha posicion este acotada, false si esta fuera de rango
     */
    public boolean correctos(int nf, int nc) {
        boolean ok = true;
        if (nf < 0 || nf >= Mundo.FILAS || nc < 0 || nc >= Mundo.COLUMNAS) {
            ok = false;
        }
        return ok;
    }

    /**
     * Cuando se llega a esta función, ya está comprobado que hay sitio para moverse y se le pasa por parámetro, esta función hace que la célula se reproduzca
     * @param i Nueva coordenada x de la célula madre una vez se ha reproducido
     * @param j Nueva coordenada y de la célula madre una vez se ha reproducido
     * @param auxi Coordenada x de la célula madre, que pasa a ser la coordenada de la célula hija
     * @param auxj Coordenada y de la célula madre, que pasa a ser la coordenada de la célula hija
     */
    public void seReproduce(int i, int j, int auxi, int auxj) {
        this.killCell(auxi, auxj);
        this.nuevaCelula(i, j, new Celula());
        this.nuevaCelula(auxi, auxj, new Celula());
    }

    /**
     * Comprueba si la célula ha dado suficientes pasos para reproducirse
     * @param i Coordenada x de la célula
     * @param j Coordenada y de la célula
     * @return true si se reproducirá, false si no
     */
    public boolean comprobarPasos(int i, int j) {
        boolean ret = false;
        if (this.celulas[i][j].getPasos() >= PASOS_REPRODUCCION) {
            ret = true;
        }
        return ret;
    }

    /**
     * "Mueve" una célula a una posición que ya se ha comprobado que está vacía, creando una nueva célula, pasando los pasos que dio la otra y matando la anterior
     * @param fil Coordenada x de la nueva célula
     * @param col Coordenada y de la nueva célula
     * @param auxi Coordenada x de la antigua célula
     * @param auxj Coordenada y de la antigua célula
     */
    public void seMueve(int fil, int col, int auxi, int auxj) {
        this.nuevaCelula(fil, col, new Celula());
        this.celulas[fil][col].setNoMovimiento(this.celulas[auxi][auxj].getNoMovimiento()); //trasbase de datos de movimiento y pasos dados de la celula
        this.celulas[fil][col].setPasos(this.celulas[auxi][auxj].getPasos());               //a la misma celula que cambiamos de posicion al moverse
        this.incPasos(fil, col);                                                            //ya que al matarla no sabriamos cuantos pasos habia dado
        this.killCell(auxi, auxj);
    }

    /**
     * Crea y devuelve el string del tablero en sí, listo para mostrarlo por pantalla
     */
    @Override
    public String toString() {
        String salida = "";
        salida += "X  ";
        for (int x = 0; x < Mundo.COLUMNAS; x++){
        	salida += x + "   ";
        }
        salida += System.getProperty("line.separator");
        for (int i = 0; i < celulas.length; i++) { // i < FILAS
            salida += i;
            for (int j = 0; j < celulas[i].length; j++) { // j < 
                if (esVacia(i, j)) {
                    salida += "  - ";
                } else {
                    salida += " " + celulas[i][j].toString();
                }
            }
            salida += System.getProperty("line.separator");
        }
        return salida;
    }
}