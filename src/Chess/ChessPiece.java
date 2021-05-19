//subClass de Piece

package Chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {//somente o get, pois a corda da peça não vai ser modificada, so podá ser acessada.
		return color;
	}
}
