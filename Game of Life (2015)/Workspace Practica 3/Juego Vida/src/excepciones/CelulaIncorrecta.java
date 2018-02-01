package excepciones;

public class CelulaIncorrecta extends Throwable {

	private static final long serialVersionUID = 3035861382697532856L;

	public CelulaIncorrecta (String msg){
		super(msg);
	}
}
