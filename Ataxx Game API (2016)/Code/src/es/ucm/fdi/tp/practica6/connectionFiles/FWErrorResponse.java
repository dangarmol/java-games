package es.ucm.fdi.tp.practica6.connectionFiles;

import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;

public class FWErrorResponse implements Response {

	private static final long serialVersionUID = 1L;
	private String msg;
	
	public FWErrorResponse(String msg){
		this.msg = msg;
	}
	@Override
	public void run(GameObserver o) {
		o.onError(msg);
	}

}
