package excepciones;

public class ErrorLecturaTipoCelula extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorLecturaTipoCelula (String msg){
		super(msg);
	}
}
