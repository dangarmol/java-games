package excepciones;

public class PosicionVacia extends Throwable {

	private static final long serialVersionUID = 3035861382697532856L;

	public PosicionVacia (String msg){
		super(msg);
	}
}
