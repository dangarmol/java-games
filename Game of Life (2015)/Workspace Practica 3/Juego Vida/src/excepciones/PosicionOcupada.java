package excepciones;

public class PosicionOcupada extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PosicionOcupada (String msg){
		super(msg);
	}
}
