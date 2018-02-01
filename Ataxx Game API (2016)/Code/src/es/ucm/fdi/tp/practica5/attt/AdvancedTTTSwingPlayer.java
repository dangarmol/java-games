package es.ucm.fdi.tp.practica5.attt;

import java.util.List;

import es.ucm.fdi.tp.basecode.attt.AdvancedTTTMove;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")
public class AdvancedTTTSwingPlayer extends Player {
		private int row;
		private int col;
		private int rowD;
		private int colD;
		
		/**
		 * Returns a move
		 */
		@Override
		public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
			return createMove(row, col, p);			
		}
		
		/**
		 * Creates the move (returns one)
		 * @param row
		 * @param col
		 * @param p
		 * @return
		 */
		private GameMove createMove(int row, int col, Piece p)
		{
			return new AdvancedTTTMove(this.rowD, this.colD, this.row, this.col, p);
		}
		
		/**
		 * Sets the value of the move
		 * @param row
		 * @param col
		 */
		public void setMoveValue(int row, int col) {
			this.row = row;
			this.col = col;
			this.rowD = 0;
			this.colD = 0;
		}
		
		/**
		 * Sets the value of a move when all 6 pieces are placed
		 * @param row
		 * @param col
		 * @param rowD
		 * @param colD
		 */
		public void setAdvancedMoveValue(int row, int col, int rowD, int colD) {
			this.row = row;
			this.col = col;
			this.rowD = rowD;
			this.colD = colD;
		}
}
