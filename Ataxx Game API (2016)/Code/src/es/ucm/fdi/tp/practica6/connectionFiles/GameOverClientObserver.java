package es.ucm.fdi.tp.practica6.connectionFiles;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class GameOverClientObserver implements GameObserver {

	private GameClient gameClient;
	
	public GameOverClientObserver (GameClient gc) {
		this.gameClient = gc;
	}
	
	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn) { }

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		gameClient.onGameOver();
	}

	@Override
	public void onMoveStart(Board board, Piece turn) { }

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) { }

	@Override
	public void onChangeTurn(Board board, Piece turn) { }

	@Override
	public void onError(String msg) { }

}
