package excepciones;

public class IndicesFueraDeRango extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndicesFueraDeRango (String msg){
		super(msg);
	}
}
