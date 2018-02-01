package excepciones;

public class PalabraIncorrecta extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PalabraIncorrecta (String msg){
		super(msg);
	}
}
