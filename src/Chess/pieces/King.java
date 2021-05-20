/*Peça Rei*/

package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	
	}
	@Override
	public String toString() {
		return "k";
	}
	@Override
	public boolean[][] possibleMoves() {//movimento rei	basico 			
		boolean[][]mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		return mat;
	}
}
