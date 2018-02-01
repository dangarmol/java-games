package es.ucm.fdi.tp.practica5.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.ataxx.AtaxxMove;


@SuppressWarnings("serial")
public class AtaxxSwingPlayer extends Player {

	private int row;
	private int col;
	private int rowD;
	private int colD;

	/**
	 * Constructor
	 */
	public AtaxxSwingPlayer() {	}

	/**
	 * Returns a game move
	 */
	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return createMove(row, col, rowD, colD, p);
	}

	/**
	 * Sets the value of the move
	 * @param row
	 * @param col
	 * @param rowD
	 * @param colD
	 */
	public void setMoveValue(int row, int col, int rowD, int colD) {
		this.row = row;
		this.col = col;
		this.rowD = rowD;
		this.colD = colD;
	}
	
	/**
	 * Creates and returns a new move for the game
	 * @param row initial row
	 * @param col initial col
	 * @param rowD destination 
	 * @param colD destination
	 * @param p piece
	 * @return the move
	 */
	protected GameMove createMove(int row, int col, int rowD, int colD, Piece p) {
		return new AtaxxMove(row, col, rowD, colD, p);
	}
}