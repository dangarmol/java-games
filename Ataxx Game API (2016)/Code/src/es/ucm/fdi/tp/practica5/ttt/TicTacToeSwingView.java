package es.ucm.fdi.tp.practica5.ttt;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.practica5.others.FiniteRectBoardSwingView;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")
public class TicTacToeSwingView extends FiniteRectBoardSwingView {

	private TicTacToeSwingPlayer player;
	private boolean activeBoard;
	
	/**
	 * Constructor
	 * @param g
	 * @param c
	 * @param localPiece
	 * @param randPlayer
	 * @param aiPlayer
	 */
	public TicTacToeSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randPlayer,
			Player aiPlayer) {
		super(g, c, localPiece, randPlayer, aiPlayer);
		player = new TicTacToeSwingPlayer();
		activeBoard = true;
	}

	/**
	 * Decides what to do with the click
	 */
	@Override
	protected void handleMouseClick(int row, int col, int mouseButton) {
		if(activeBoard && mouseButton == 1)
		{
			player.setMoveValue(row, col);
			decideMakeManualMove(player);
		}
	}

	/**
	 * Activates the board for the view
	 */
	@Override
	protected void activateBoard()
	{
		activeBoard = true;
		addMsg("Click on an empty position to play");
	}

	/**
	 * Deactivates the board for the view
	 */
	@Override
	protected void deActivateBoard()
	{
		activeBoard = false;
	}

	/**
	 * Not used here
	 */
	@Override
	protected void resetCounter() {
		//Not used
	}
}
